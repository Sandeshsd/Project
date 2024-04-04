package com.address.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressNotFoundByStudentIdException extends RuntimeException {
   
	
	private String message;
}
