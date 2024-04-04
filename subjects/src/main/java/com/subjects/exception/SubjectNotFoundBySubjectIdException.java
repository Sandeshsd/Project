package com.subjects.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectNotFoundBySubjectIdException extends RuntimeException {
	
	private String message;

}
