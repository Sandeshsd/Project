package com.ratings.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RatingsNotFoundByCourseIdException extends RuntimeException {

	private String message;
}