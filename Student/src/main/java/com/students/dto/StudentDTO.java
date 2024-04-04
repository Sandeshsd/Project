package com.students.dto;


import java.util.List;

import com.students.entity.Address;
import com.students.entity.Courses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentDTO {

	
	private long studentId;
	private String studentName;
	private String studentEmail;
	private long studentPhNo;
	private Address address;
	private List<Courses>  courses;	
}
