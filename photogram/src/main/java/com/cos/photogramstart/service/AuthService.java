package com.cos.photogramstart.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service  // 1.IoC  2.트랜젝션 관리
public class AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder brBCryptPasswordEncoder;
	
	@Transactional // Write(Insert,Update,Delete)
	public User signup(User user) {
		// 회원가입 진행
		String rawPassword = user.getPassword();
		String encPassword = brBCryptPasswordEncoder.encode(rawPassword); // 비밀번호 해시 암호화
		user.setPassword(encPassword);
		user.setRole("ROLE_USER"); // 관리자 :ROLE_ADMIN
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
