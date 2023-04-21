package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // IOC  
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
        http.authorizeRequests(requests -> requests
                .antMatchers("/", "/user/**", "/image/**", "subscribe/**", "/comment/**").authenticated() // 해당 주소는 인증이 필요함
                .anyRequest().permitAll())
                .formLogin(login -> login
                .loginPage("/auth/signin") // Get
                .defaultSuccessUrl("/")); 
		return http.build(); 
	}
		
}
