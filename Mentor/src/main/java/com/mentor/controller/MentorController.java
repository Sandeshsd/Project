package com.mentor.controller;

import java.util.List;

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

import com.mentor.dto.MentorDTO;
import com.mentor.entity.Mentor;
import com.mentor.service.MentorService;
import com.mentor.util.ResponseStructure;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/mentors")
public class MentorController {

	
	@Autowired
	private MentorService service;
	
	//post
	@PostMapping
	public ResponseEntity<ResponseStructure<MentorDTO>> saveMentor(@RequestBody Mentor mentor){
		return service.saveMentor(mentor);
	}
	//get
	@GetMapping("/{mentorId}")
	@CircuitBreaker(name = "addressSubjectBreaker",fallbackMethod = "addressSubject")
	@Retry(name = "addressSubjectRetry",fallbackMethod = "addressSubject")
	@RateLimiter(name = "addressSubjectRateLimiter",fallbackMethod = "addressSubject")
	public ResponseEntity<ResponseStructure<MentorDTO>> getMentor(@PathVariable long mentorId){
		return service.getMentoryId(mentorId);
	}
	public ResponseEntity<ResponseStructure<MentorDTO>> addressSubject(long mentorId, Exception ex){
		return service.addressSubject(mentorId);
	} 
	//getAll
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<MentorDTO>>> getAllMentors(){
		return service.getAllMentor();
	}
	//update
	@PutMapping("/{mentorId}")
	public ResponseEntity<ResponseStructure<MentorDTO>> updateMentor(@RequestBody Mentor mentor,@PathVariable long mentorId){
		return service.updateMentor(mentorId, mentor);
	}
	//delete
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MentorDTO>> deleteMentor(@RequestParam long mentorId){
		return service.deleteMentor(mentorId);
	}
	//getMentorByCourseId
	@GetMapping("/courses/{courseId}")
	public List<Mentor> getMentorByCourseId(@PathVariable long courseId){
		return service.getMentorByCourseId(courseId);
	}
	
}
