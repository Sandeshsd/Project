package com.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.address.dao.StudentAddressDAO;
import com.address.document.StudentAddress;
import com.address.exception.AddressNotFoundByIdException;
import com.address.util.Response;

@Service
public class StudentAddressService {
	@Autowired
	private StudentAddressDAO dao;
	
	//save
	public ResponseEntity<Response<StudentAddress>> saveAddress(StudentAddress address){
		Response<StudentAddress> response=new Response<>();
		address=dao.saveAddress(address);
		response.setStatus(HttpStatus.CREATED.value());
		response.setMessage("Address saved");
		response.setData(address);
		return new ResponseEntity<Response<StudentAddress>>(response,HttpStatus.CREATED);
	}
	//get
		public ResponseEntity<Response<StudentAddress>> getAddressById(long addressId){
		StudentAddress	address=dao.getAddressById(addressId);
		if(address!=null) {
		Response<StudentAddress> response=new Response<>();
		response.setStatus(HttpStatus.CREATED.value());
		response.setMessage("Address found");
		response.setData(address);
		return new ResponseEntity<Response<StudentAddress>>(response,HttpStatus.FOUND);
		}throw new AddressNotFoundByIdException("please enter correct AddressID");
		}
		//delete
		public ResponseEntity<Response<StudentAddress>> deleteAddress(long addressId){
			StudentAddress	address=dao.getAddressById(addressId);
			if(address!=null) {
			Response<StudentAddress> response=new Response<>();
			address=dao.deleteAddress(addressId);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Address deleted");
			response.setData(address);
			return new ResponseEntity<Response<StudentAddress>>(response,HttpStatus.OK);
			}throw new AddressNotFoundByIdException("please enter correct AddressID");
		}
		//update
		public ResponseEntity<Response<StudentAddress>> updateAddress(StudentAddress address,long addressId){
			StudentAddress address2=dao.getAddressById(addressId);
			if(address2!=null) {
				Response<StudentAddress> response=new Response<>();
				address=dao.updatAddress(address,addressId);
				response.setStatus(HttpStatus.OK.value());
				response.setMessage("Address updated");
				response.setData(address);
				return new ResponseEntity<Response<StudentAddress>>(response,HttpStatus.OK);
			}throw new AddressNotFoundByIdException("please enter correct AddressID");
	   }
	  //getAddressBystudentId
		public StudentAddress getAddressByStudentId(long studentId) {
			StudentAddress address=dao.getAddressByStudentId(studentId);
			if(address!=null) {
				return address;
			}return null;
		}
}