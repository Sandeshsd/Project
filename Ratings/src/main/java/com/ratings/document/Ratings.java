package com.ratings.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Ratings {

	
	@Id
	@Indexed(unique = true)
	private long ratingId;
	@Indexed(unique = true)
	private long studentId;
	@Indexed(unique = true)
	private long courseId;
	private int rating;
	private String feedback;
}
