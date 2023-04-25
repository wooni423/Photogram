package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.imageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder; // .yml에서 파일 저장 경로 가져옴
	
	public void imageUpload(imageUploadDto imageUploadDto,PrincipalDetails principalDetails) {
		UUID uuid=UUID.randomUUID();
		String imageFileName=uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); // 1.jpg
		System.out.println("이미지 파일 이름:"+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		// 통신,I/O -> 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블에 저장
		Image image=imageUploadDto.toEntity(imageFileName,principalDetails.getUser()); // DTO->Entity 변환		
		Image imageEntity=imageRepository.save(image);
		System.out.println(imageEntity);
	} 
}
