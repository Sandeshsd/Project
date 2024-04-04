package com.mentor.dto;

import java.util.List;

import com.mentor.entity.Address;
import com.mentor.entity.Subjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MentorDTO {

	private  long mentorId;
	private String mentorName;
	private String mentorEmail;
	private long mentorPhNo;
	private Address address;
	private List<Subjects> subjects;
}
