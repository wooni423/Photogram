package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.imageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	public void imageUpload(imageUploadDto imageUploadDto,PrincipalDetails principalDetails) {
		String imageFileName=imageUploadDto.getFile().getOriginalFilename(); // 1.jpg
		
	} 
}
