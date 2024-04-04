package com.courses.document;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CourseDTO {

	
	private long courseId;
	private String coursename;
	private String courseFee;
	private String aboutCourse;
	private List<Subjects> subjects;
	private List<Mentor> mentors;
	private List<Ratings> ratings;

}
