package com.ratings.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseStructure<T> {

	
	private int status;
	private String message;
	private Object data;
}
