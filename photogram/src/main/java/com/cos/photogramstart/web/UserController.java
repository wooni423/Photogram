package com.cos.photogramstart.web;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id,Model model) {
		User userEntity= userService.userProfile(id);
		model.addAttribute("images",userEntity);
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		//  1. 추천
		System.out.println("세션 정보: "+principalDetails.getUser());
		
		// 2. 극혐 코드
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//PrincipalDetails mpPrincipalDetails =(PrincipalDetails)auth.getPrincipal();
		//System.out.println("직접찾은 세션 정보:" +auth.getPrincipal());
		
		return "user/update";
	}
}
