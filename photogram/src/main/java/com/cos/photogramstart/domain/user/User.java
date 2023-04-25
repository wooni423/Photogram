package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA -> Java Persistence API(자바로 데이터를 영구적으로 저장(DB) 할 수 있는 API 제공)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // 디비에 테이블을 생성
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 데이터베이스에 위임
	private int id;
	
	@Column(length = 20,unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String website; // 웹 사이트
	private String bio;  // 자기소개
	
	@Column(nullable = false)
	private String email;
	
	private String phone;
	private String gender;
	
	private String profileImageUrl; // 사진
	private String role; // 권한
	
	// 나는 연관관계가 주인이 아니다.테이블에 컬럼생성 x
	// User를 select 할 때 해당 user id로 등록된 image들을 다 가져올것.
	// Lazy = User를 select 할 때 해당 User id 이미지들을 가져오지 않는다.-대신 getimages()함수의 이미지들이 호출될때 가져올것
	// Eager = User를 select 할 떄 해당 User id 이미지들을 join해서 다 가져온다.
	@OneToMany(mappedBy ="user",fetch = FetchType.LAZY) 
	private List<Image> images; // 양방향 매핑
	
	private LocalDateTime createDateTime;
	
	@PrePersist // DB에 insert 되기 직전에 실행됨
	public void createDate() {
		this.createDateTime=LocalDateTime.now();
	}
}
