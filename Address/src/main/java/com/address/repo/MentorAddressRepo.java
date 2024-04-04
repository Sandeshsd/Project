package com.address.repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.address.document.MentorAddress;

public interface MentorAddressRepo extends MongoRepository<MentorAddress, Long> {

	//getAddressByMentorId
	public MentorAddress getAddressByMentorId(long mentorId);
	
}
