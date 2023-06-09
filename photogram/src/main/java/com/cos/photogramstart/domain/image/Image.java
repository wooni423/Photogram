package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // 디비에 테이블을 생성
public class Image { // N,1

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 데이터베이스에 위임
	private int id;
	private String caption;
	private String postImageUrl; // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 -DB에는 저장된 경로를 insert
	
	@JoinColumn(name ="userId")
	@ManyToOne
	private User user; // 1,1
	
	// 이미지 좋아요
	
	// 이미지 댓글
	
	private LocalDateTime createDate;
	
	@PrePersist // DB에 insert 되기 직전에 실행됨
	public void createDate() {
		this.createDate=LocalDateTime.now();
	}
}
