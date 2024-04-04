package com.students.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.students.util.ResponseStructure;

@RestControllerAdvice
public class StudentsExceptionHandler{

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatus httpStatus,WebRequest request){
		List<ObjectError> errors=ex.getAllErrors();
		Map<String, String> map=new HashMap<String, String>();
		for(ObjectError e: errors) {
			String message=e.getDefaultMessage();
			String feildName=((FieldError) e).getField();
			map.put(message, feildName);
		}
	return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StudentNotFoundByIdException.class)
	public ResponseEntity<ResponseStructure<String>> studentNotFoundById(StudentNotFoundByIdException ex){
		ResponseStructure<String> responseStructure=new  ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Invalid studentId");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
}
