package com.courses.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Courses {

	@Id
	private long courseId;
	private String coursename;
	private String courseFee;
	private String aboutCourse;
	@Indexed(unique = true)
	private long studentId;
	@Indexed(unique = true)
	private long ratingId;

}
