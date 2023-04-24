package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

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
@Table(
		uniqueConstraints= {
				@UniqueConstraint(
				name="subscribe_uk",
				columnNames ={"fromUserId","toUserId"}
				)}
		)
public class Subscribe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 데이터베이스에 위임
	private int id;
	
	@JoinColumn(name ="fromUserId")
	@ManyToOne
	private User fromUser; // 구독하는 사용자
	
	@JoinColumn(name ="toUserId")
	@ManyToOne
	private User toUser;  // 구독받는 사용자

	private LocalDateTime createDate;
		
	@PrePersist // DB에 insert 되기 직전에 실행됨
	public void createDate() {
		this.createDate=LocalDateTime.now();
	}
}
