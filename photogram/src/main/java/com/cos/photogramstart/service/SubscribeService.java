package com.cos.photogramstart.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
	
	private final SubscribeRepository subscribeRepository;
	
	@Transactional
	public void subscribe(int fromUserId,int toUserId) {
		try {
			subscribeRepository.msubscribe(fromUserId, toUserId);		
		} catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다.");
		}
		
	}
	
	@Transactional
	public void unsubscirbe(int fromUserId,int toUserId) {
		subscribeRepository.mUnsubscribe(fromUserId, toUserId);
	}
}
