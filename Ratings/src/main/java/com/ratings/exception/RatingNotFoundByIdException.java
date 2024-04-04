package com.ratings.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RatingNotFoundByIdException extends RuntimeException {

	private String message;
}
