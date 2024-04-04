package com.students.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Students{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long studentId;
	private String studentName;
	private String studentEmail;
	private String studentPassword;
	private long studentPhNo;
	
	@Transient
	private Address address;
	
//	@Transient
//	private Ratings ratings;
	
	@Transient
	private List<Courses>  courses;
	
}
