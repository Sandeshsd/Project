package com.address.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;



@Document
@Getter
@Setter
public class MentorAddress {

	
	@Id
	@Indexed(unique = true)
	private long addressId;
	@Indexed(unique = true)
	private long mentorId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private long pincode;
	
}
