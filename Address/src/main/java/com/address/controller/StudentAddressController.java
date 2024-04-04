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

import com.address.document.StudentAddress;
import com.address.service.StudentAddressService;
import com.address.util.Response;

@RestController
@RequestMapping("/studentaddress")
public class StudentAddressController {
	@Autowired
	private StudentAddressService service;

	// save
	@PostMapping
	public ResponseEntity<Response<StudentAddress>> saveAddress(@RequestBody StudentAddress address) {
		return service.saveAddress(address);
	}

	// get
	@GetMapping
	public ResponseEntity<Response<StudentAddress>> getAddress(@RequestParam long addressId) {
		return service.getAddressById(addressId);
	}

	// delete
	@DeleteMapping
	public ResponseEntity<Response<StudentAddress>> deleteAddress(@RequestParam long addressId) {
		return service.deleteAddress(addressId);
	}

	// update
	@PutMapping
	public ResponseEntity<Response<StudentAddress>> updateAddress(@RequestBody StudentAddress address, @RequestParam long addressId) {
		return service.updateAddress(address, addressId);
	}
	
	//getAdressByStudentId
	@GetMapping("/students/{studentId}")
	public StudentAddress getAddressByStudentId(@PathVariable long studentId) {
		return service.getAddressByStudentId(studentId);
	}
}
