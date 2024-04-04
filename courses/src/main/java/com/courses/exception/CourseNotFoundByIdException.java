package com.courses.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
public class CourseNotFoundByIdException extends RuntimeException {

	
	private String message;
}
