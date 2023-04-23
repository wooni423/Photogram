package com.cos.photogramstart.handler;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;


@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationException.class) // javascript 응답
	public String validationException(CustomValidationException e) {
		// CMRespDto,Script 비교
		// 1. 클라이언트에게 응답할때는 Script가 좋음
		// 2. Ajax 통신 -> CMRespDto
		// 3. Android -> CMRespDto
		return Script.back(e.getErrorMap().toString());
	}
	
	@ExceptionHandler(CustomValidationApiException.class) // API Data 응답
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
	}
}
 