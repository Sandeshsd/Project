package com.address.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.address.document.MentorAddress;
import com.address.repo.MentorAddressRepo;

@Repository
public class MentorAddressDAO {

	@Autowired
	private MentorAddressRepo repo;

	// save
	public MentorAddress saveAddress(MentorAddress address) {
		return repo.save(address);
	}

	// get
	public MentorAddress getAddressById(long addressId) {
		Optional<MentorAddress> optional = repo.findById(addressId);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	// update
	public MentorAddress updatAddress(MentorAddress address, long addressId) {
		Optional<MentorAddress> optional = repo.findById(addressId);
		if (optional.isPresent()) {
			address.setAddressId(addressId);
			return repo.save(address);
		}
		return null;
	}

	// delete
	public MentorAddress deleteAddress(long addressId) {
		MentorAddress address = getAddressById(addressId);
		if (address != null) {
			repo.delete(address);
		}
		return null;
	}

	// byMentorId
	public MentorAddress getAddressByMentorId(long mentorId) {
		MentorAddress address = repo.getAddressByMentorId(mentorId);
		if (address != null) {
			return address;
		}
		return null;
	}
}
