package com.students.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Courses {

	private String courseId;
	private String coursename;
	private String courseFee;
	private String aboutCourse;
	private Ratings ratings;
	
}
