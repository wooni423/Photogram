package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

	private final UserRepository UserRepository;

	// 1. 패스워드는 알아서 체킹하니깐 신경 쓸 필요가 없다.
	// 2. 리턴이 잘 된다면 자동으로 세션을 만든다.

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User userEntity = UserRepository.findByUsername(username);

		if (userEntity == null) {
			return null;
		} else {
			return new PrincipalDetails(userEntity);

		}

	}
}
