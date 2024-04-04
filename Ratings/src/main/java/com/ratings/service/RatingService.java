package com.ratings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ratings.dao.RatingDAO;
import com.ratings.document.Ratings;
import com.ratings.exception.RatingNotFoundByIdException;
import com.ratings.util.ResponseStructure;

@Service
public class RatingService {

	@Autowired
	private RatingDAO dao;
	
	//save
	public ResponseEntity<ResponseStructure<Ratings>> saveRatings(Ratings ratings ){
		ratings=dao.saveRating(ratings);
		ResponseStructure<Ratings> responseStructure=new ResponseStructure<Ratings>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Ratings saved");
		responseStructure.setData(ratings);
		return new ResponseEntity<ResponseStructure<Ratings>>(responseStructure,HttpStatus.CREATED);
	}
	//get
	public ResponseEntity<ResponseStructure<Ratings>> getRatingsbyId(long ratingId ){
		Ratings ratings=dao.getRatingById(ratingId);
		if(ratings!=null) {
		ResponseStructure<Ratings> responseStructure=new ResponseStructure<Ratings>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("found successful !!");
		responseStructure.setData(ratings);
		return new ResponseEntity<ResponseStructure<Ratings>>(responseStructure,HttpStatus.FOUND);
		}throw new RatingNotFoundByIdException("provide correct RatingId");
	}
	//getAll
	public ResponseEntity<ResponseStructure<List<Ratings>>> getAllRatings(){
		List<Ratings> list=dao.getAllRatings();
		ResponseStructure<List<Ratings>> responseStructure=new ResponseStructure<List<Ratings>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("fetch successful !!");
		responseStructure.setData(list);
		return new ResponseEntity<ResponseStructure<List<Ratings>>>(responseStructure,HttpStatus.OK);
	}
	//getRatingsByStudentId
	public List<Ratings> getRatingByCourseId(long courseId){
		return dao.getRatingByCourseId(courseId);
	}
	//getRatingsByCourseId
	public List<Ratings> getRatingByStudentId(long studentId){
		return dao.getRatingByStudentId(studentId);
	}
	//getRatingsOFCourseByStudentId
		public Ratings getRatingsOFCourseByStudentId(long courseId,long studentId) {
			return dao.getRatingsOFCourseByStudentId(courseId, studentId);
		}
	
}
