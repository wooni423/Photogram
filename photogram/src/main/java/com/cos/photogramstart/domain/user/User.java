package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

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
	
	private LocalDateTime createDateTime;
	
	@PrePersist // DB에 insert 되기 직전에 실행됨
	public void createDate() {
		this.createDateTime=LocalDateTime.now();
	}
}
