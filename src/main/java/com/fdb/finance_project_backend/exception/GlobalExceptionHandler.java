package com.fdb.finance_project_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
		return ResponseEntity.badRequest()
				.body(Map.of("error",ex.getBindingResult().getFieldError().getDefaultMessage()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleGeneral(RuntimeException ex){
		return ResponseEntity.badRequest()
				.body(Map.of("error",ex.getMessage()));
	}
}
