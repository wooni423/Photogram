package com.cos.photogramstart.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class imageUploadDto { // 이미지 업로드를 위한 DTO
	private MultipartFile file;
	private String caption;
}
