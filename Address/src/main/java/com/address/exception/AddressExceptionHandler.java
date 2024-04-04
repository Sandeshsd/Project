package com.address.exception;

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

import com.address.util.Response;



@RestControllerAdvice
public class AddressExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex,HttpHeaders headers,WebRequest request,HttpStatus httpStatus){
		List<ObjectError> list=ex.getAllErrors();
		Map<String, String> map=new HashMap<String, String>();
		for(ObjectError error:list) {
			String message=error.getDefaultMessage();
			String fieldName=((FieldError) error).getField();
			map.put(message, fieldName);
		}
	return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AddressNotFoundByIdException.class)
	public ResponseEntity<Response<String>> addressNotFoundById(AddressNotFoundByIdException ex){
		Response<String> responseStructure=new  Response<String>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Invalid addressID");
		return new ResponseEntity<Response<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AddressNotFoundByStudentIdException.class)
	public ResponseEntity<Response<String>> studentNotFoundById(AddressNotFoundByStudentIdException ex){
		Response<String> responseStructure=new  Response<String>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Invalid studentID");
		return new ResponseEntity<Response<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AddressNotFoundByMentorIdException.class)
	public ResponseEntity<Response<String>> mentorNotFoundById(AddressNotFoundByMentorIdException ex){
		Response<String> responseStructure=new  Response<String>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Invalid mentorID");
		return new ResponseEntity<Response<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
}

