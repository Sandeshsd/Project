package com.mentor.entity;

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
public class Mentor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  long mentorId;
	private String mentorName;
	private String mentorEmail;
	private String mentorPassword;
	private long mentorPhNo;
	private  long courseId;
	
	@Transient
	private Address address;
	
	@Transient
	private List<Subjects> subjects;
}
