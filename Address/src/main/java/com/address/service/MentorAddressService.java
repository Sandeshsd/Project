package com.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.address.dao.MentorAddressDAO;
import com.address.document.MentorAddress;
import com.address.exception.AddressNotFoundByIdException;
import com.address.util.Response;


@Service
public class MentorAddressService {

	@Autowired
	private MentorAddressDAO dao;
	
	//save
	public ResponseEntity<Response<MentorAddress>> saveAddress(MentorAddress address){
		Response<MentorAddress> response=new Response<>();
		address=dao.saveAddress(address);
		response.setStatus(HttpStatus.CREATED.value());
		response.setMessage("Address saved");
		response.setData(address);
		return new ResponseEntity<Response<MentorAddress>>(response,HttpStatus.CREATED);
	}
	//get
		public ResponseEntity<Response<MentorAddress>> getAddressById(long addressId){
		MentorAddress	address=dao.getAddressById(addressId);
		if(address!=null) {
		Response<MentorAddress> response=new Response<>();
		response.setStatus(HttpStatus.CREATED.value());
		response.setMessage("Address found");
		response.setData(address);
		return new ResponseEntity<Response<MentorAddress>>(response,HttpStatus.FOUND);
		}throw new AddressNotFoundByIdException("please enter correct AddressID");
		}
		//delete
		public ResponseEntity<Response<MentorAddress>> deleteAddress(long addressId){
			MentorAddress	address=dao.getAddressById(addressId);
			if(address!=null) {
			Response<MentorAddress> response=new Response<>();
			address=dao.deleteAddress(addressId);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Address deleted");
			response.setData(address);
			return new ResponseEntity<Response<MentorAddress>>(response,HttpStatus.OK);
			}throw new AddressNotFoundByIdException("please enter correct AddressID");
		}
		//update
		public ResponseEntity<Response<MentorAddress>> updateAddress(MentorAddress address,long addressId){
			MentorAddress address2=dao.getAddressById(addressId);
			if(address2!=null) {
				Response<MentorAddress> response=new Response<>();
				address=dao.updatAddress(address,addressId);
				response.setStatus(HttpStatus.OK.value());
				response.setMessage("Address updated");
				response.setData(address);
				return new ResponseEntity<Response<MentorAddress>>(response,HttpStatus.OK);
			}throw new AddressNotFoundByIdException("please enter correct AddressID");
	   }
		 //getAddressByMentorId
				public MentorAddress  getAddressByMentorId(long mentorId) {
					MentorAddress address=dao.getAddressByMentorId(mentorId);
					if(address!=null) {
						return address;
					}return null;
				}
		
}







//public ResponseEntity<Response<Address>> getAddressByStudentId(long studentId){
//Address address2=dao.getAddressByStudentId(studentId);
//if(address2!=null) {
//	Response<Address> response=new Response<>();
//	response.setStatus(HttpStatus.OK.value());
//	response.setMessage("Address found");
//	response.setData(address2);
//	return new ResponseEntity<Response<Address>>(response,HttpStatus.OK);
//}throw new AddressNotFoundByStudentIdException("please enter correct studentID");
//}

//getAddressByMentorId
//	public ResponseEntity<Response<Address>> getAddressByMentorId(long mentorId){
//		Address address2=dao.getAddressByMentorId(mentorId);
//		if(address2!=null) {
//			Response<Address> response=new Response<>();
//			response.setStatus(HttpStatus.OK.value());
//			response.setMessage("Address found");
//			response.setData(address2);
//			return new ResponseEntity<Response<Address>>(response,HttpStatus.OK);
//		}throw new AddressNotFoundByMentorIdException("please enter correct mentorID");
//   }
