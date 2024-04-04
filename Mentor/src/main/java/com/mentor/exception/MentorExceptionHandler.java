package com.mentor.exception;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mentor.util.ResponseStructure;

@RestControllerAdvice
public class MentorExceptionHandler  {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> mentorExceptionHandler(MethodArgumentNotValidException ex,HttpStatus httpStatus,HttpHeaders headers,WebRequest request){
		List<ObjectError> list=ex.getAllErrors();
		Map<String, String> map=new HashMap<String, String>();
		for(ObjectError error: list) {
			String message=error.getDefaultMessage();
			String fieldName=((FieldError) error).getField();
			map.put(message, fieldName);
		}return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	public  ResponseEntity<ResponseStructure<String>> mentorNotFoundByIdExceptionHandler(MentorNotFoundByIdException ex){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("invalid mentorId");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
}
