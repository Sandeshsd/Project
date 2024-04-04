package com.address.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.address.document.StudentAddress;
import com.address.repo.StudentAddressRepo;

@Repository
public class StudentAddressDAO {
	
	@Autowired
	private StudentAddressRepo repo;

	// save
	public StudentAddress saveAddress(StudentAddress address) {
		return repo.save(address);
	}

	// get
	public StudentAddress getAddressById(long addressId) {
		Optional<StudentAddress> optional = repo.findById(addressId);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	// update
	public StudentAddress updatAddress(StudentAddress address, long addressId) {
		Optional<StudentAddress> optional = repo.findById(addressId);
		if (optional.isPresent()) {
			address.setAddressId(addressId);
			return repo.save(address);
		}
		return null;
	}

	// delete
	public StudentAddress deleteAddress(long addressId) {
		StudentAddress address = getAddressById(addressId);
		if (address != null) {
			repo.delete(address);
		}
		return null;
	}

	// byStudentId
	public StudentAddress getAddressByStudentId(long studentId) {
		StudentAddress address = repo.getAddressByStudentId(studentId);
		if (address != null) {
			return address;
		}
		return null;
	}

}


