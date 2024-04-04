package com.students.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class StudentNotFoundByIdException extends RuntimeException {
	
   private String message;
}
