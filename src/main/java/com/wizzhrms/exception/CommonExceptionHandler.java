package com.wizzhrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerErrorException;

import com.wizzhrms.dto.CommonResponseDto;

//@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<CommonResponseDto> commonExceptionHandler(Throwable th) {
		 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponseDto(th.getMessage()));

	}

	@ExceptionHandler(value = ServerErrorException.class)
	public ResponseEntity<CommonResponseDto> commonExceptionHandler(ServerErrorException ex) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponseDto(ex.getMessage()));

	}

	@ExceptionHandler(value = HttpClientErrorException.class)
	public ResponseEntity<CommonResponseDto> commonExceptionHandler(HttpClientErrorException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponseDto(ex.getMessage()));

	}

}
