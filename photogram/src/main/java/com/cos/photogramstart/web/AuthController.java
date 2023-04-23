package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI할 때 사용
@Controller // 1. IOC 등록 2. 파일을 리턴하는 Controller
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	private final AuthService authService;
	
	@GetMapping("auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	// 회원가입  -> /auth/signup -> /auth/signin
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto,BindingResult bindingResult) { // key=value(x-www-form-urlencoded)
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();			
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(),error.getDefaultMessage());		
			}
			throw new CustomValidationException("유효성 검사 실패함",errorMap);
		} else {
			// signUpDTO -> User
			User user=signupDto.toEntity();
			
			log.info(user.toString());
			User userEntity=authService.signup(user);	
			return "auth/signin";
		}
			
	}
}
