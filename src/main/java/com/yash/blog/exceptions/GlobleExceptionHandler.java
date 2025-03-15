package com.yash.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.yash.blog.exceptions.*;

import com.yash.blog.payloads.ApiResponce;

@RestControllerAdvice
public class GlobleExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponce>(new ApiResponce(message,false),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> resp = new HashMap<String, String>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldError = ((FieldError)error).getField();
			String defaultError = error.getDefaultMessage();
			resp.put(fieldError, defaultError);
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
}
