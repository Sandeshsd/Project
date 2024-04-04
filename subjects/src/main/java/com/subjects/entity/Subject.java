package com.subjects.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Subject {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long subjectId;
	private String subjectName;
	private String subjectCode;
	public String subjectDetails;
	private long courseId;
	private long mentorId;
}
