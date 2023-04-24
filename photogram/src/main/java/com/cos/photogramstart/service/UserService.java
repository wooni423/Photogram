package com.cos.photogramstart.service;

import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User userUpdate(int id,User user) {
		
		// 1. 영속화
		// 2. 무조건 찾았다.걱정마 get() 2. 못찾았어 exception 발동 -> orElseThrow()
		User userEntity=userRepository.findById(id).orElseThrow(() -> { return new CustomValidationApiException("찾을 수 없는 ID입니다.");}); 
		
			
		// 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 수정 비밀번호 해시 암호화
		userEntity.setName(user.getName());
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());		
		
		return userEntity;
		//더티체킹이 일어나서 업데이트가 완료됨.
	}
}
