package com.address.repo;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.address.document.StudentAddress;

public interface StudentAddressRepo extends MongoRepository<StudentAddress, Long>{

	//getAddressByStudentId
	public StudentAddress getAddressByStudentId(long studentId);
}
