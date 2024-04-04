package com.courses.exception;

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

import com.courses.util.ResponseStructure;

@RestControllerAdvice
public class CourseExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpStatus httpStatus,HttpHeaders headers,WebRequest request){
		List<ObjectError> list=ex.getAllErrors();
		Map<String, String> map=new HashMap<String, String>();
		for(ObjectError error:list) {
			String messege=error.getDefaultMessage();
			String fieldName=((FieldError) error).getField();
			map.put(messege, fieldName);
		}
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CourseNotFoundByIdException.class)
	public ResponseEntity<ResponseStructure<String>> courseNotFoundByIdExceptionHandler(CourseNotFoundByIdException ex){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Invalid CourseId");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CoursesNotFoundByStudentIdException.class)
	public ResponseEntity<ResponseStructure<String>> courseNotFoundByStudentIdExceptionHandler(CoursesNotFoundByStudentIdException ex){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Invalid CourseId");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
}
