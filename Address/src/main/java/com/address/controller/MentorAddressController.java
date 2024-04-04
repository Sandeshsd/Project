package com.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.address.document.MentorAddress;
import com.address.service.MentorAddressService;
import com.address.util.Response;

@RestController
@RequestMapping("/mentoraddress")
public class MentorAddressController {

	@Autowired
	private MentorAddressService service;

	// save
	@PostMapping
	public ResponseEntity<Response<MentorAddress>> saveAddress(@RequestBody MentorAddress address) {
		return service.saveAddress(address);
	}

	// get
	@GetMapping
	public ResponseEntity<Response<MentorAddress>> getAddress(@RequestParam long addressId) {
		return service.getAddressById(addressId);
	}

	// delete
	@DeleteMapping
	public ResponseEntity<Response<MentorAddress>> deleteAddress(@RequestParam long addressId) {
		return service.deleteAddress(addressId);
	}

	// update
	@PutMapping
	public ResponseEntity<Response<MentorAddress>> updateAddress(@RequestBody MentorAddress address, @RequestParam long addressId) {
		return service.updateAddress(address, addressId);
	}
	
	@GetMapping("/mentors/{mentorId}")
	public MentorAddress getAddressByMentorId(@PathVariable long mentorId) {
		return service.getAddressByMentorId(mentorId);
	}
}



//@GetMapping("/students/{studentId}")
//public ResponseEntity<Response<Address>> getAddressByStudentId(@PathVariable long studentId) {
//	return service.getAddressByStudentId(studentId);
//}
//@GetMapping("/mentors/{mentorId}")
//public ResponseEntity<Response<Address>> getAddressByMentorId(@PathVariable long mentorId) {
//	return service.getAddressByMentorId(mentorId);
//}
