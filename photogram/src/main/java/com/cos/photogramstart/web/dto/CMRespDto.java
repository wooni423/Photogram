package com.cos.photogramstart.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> { // 에러코드 공통 response DTO
	
	private int code; // 1(성공),-1(실패)
	private String message;
	private T data;
}
