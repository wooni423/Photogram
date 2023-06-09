package com.cos.photogramstart.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class imageUploadDto { // 이미지 업로드를 위한 DTO
	private MultipartFile file;
	private String caption;
	
	public Image toEntity(String postImageUrl,User user) {
		return Image.builder()
					.caption(caption)
					.postImageUrl(postImageUrl) // 이미지 실제 저장 경로
					.user(user)
					.build();
	}
}
