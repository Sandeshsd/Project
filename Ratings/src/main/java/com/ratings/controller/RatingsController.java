package com.ratings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratings.document.Ratings;
import com.ratings.service.RatingService;
import com.ratings.util.ResponseStructure;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

	
	@Autowired
	private RatingService service;
	
	//save
	@PostMapping
	public ResponseEntity<ResponseStructure<Ratings>> saveRatings(@RequestBody Ratings ratings){
		return service.saveRatings(ratings);
	}
	//get
	@GetMapping("/{ratingId}")
	public ResponseEntity<ResponseStructure<Ratings>> getRatings(@PathVariable long ratingId ){
		return service.getRatingsbyId(ratingId);
	}
	//getAllRatings
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Ratings>>> getAllRatings(){
		return service.getAllRatings();
	}
    //getRatingByStudentId
	@GetMapping("/students/{studentId}")
	public List<Ratings> getRatingByStudentId(@PathVariable long studentId){
		return service.getRatingByStudentId(studentId);
	}
	//getRatingByCourseId
	@GetMapping("/courses/{courseId}")
		public List<Ratings> getRatingByCourseId(@PathVariable long courseId){
			return service.getRatingByCourseId(courseId);
		}
	//getRatingsOFCourseByStudentId
	@GetMapping("/courses/{courseId}/{studentId}")
		public Ratings getRatingsOFCourseByStudentId( @PathVariable("courseId") long courseId,@PathVariable("studentId") long studentId) {
			return service.getRatingsOFCourseByStudentId(courseId, studentId);
		}
	
}
