package com.students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.students.dto.StudentDTO;
import com.students.entity.Students;
import com.students.service.StudentService;
import com.students.util.ResponseStructure;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	//save
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<StudentDTO>> saveStudent(@RequestBody Students students){
		return service.saveStudent(students);
	}
	
	//get
	@GetMapping("/{studentId}")
	@CircuitBreaker(name = "addressCourseRatingBreaker", fallbackMethod = "addressCourseRating")
	@Retry(name = "addressCourseRatingRetry", fallbackMethod = "addressCourseRating")
	@RateLimiter(name = "addressCourseRatingRateLimiter", fallbackMethod = "addressCourseRating")
	public ResponseEntity<ResponseStructure<StudentDTO>> getStudent(@PathVariable long studentId){
		return service.getStudentyId(studentId);
	}
	//creating fallback method
	 public ResponseEntity<ResponseStructure<StudentDTO>> addressCourseRating(long studentId, Exception ex) {
	        // Creating a dummy student object
	        StudentDTO student = StudentDTO.builder()
	                                       .studentName("dummy")
	                                       .studentEmail("dummy@email")
	                                       .studentId(1234)
	                                       .studentPhNo(987654321)
	                                       .build();

	        // Wrapping the student object inside a ResponseStructure
	        ResponseStructure<StudentDTO> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Success", student);

	        // Returning the ResponseEntity with the ResponseStructure
	        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	    }

	
	//put
	@PutMapping("/update/{studentId}")
	public ResponseEntity<ResponseStructure<StudentDTO>> updateStudent(@RequestBody Students students,@PathVariable long studentId){
			return service.updateStudent(studentId, students);
		}
	
	//delete
	@DeleteMapping
	public ResponseEntity<ResponseStructure<StudentDTO>> deleteStudent(@RequestParam long studentId){
		return service.deleteStudents(studentId);
	}
	@GetMapping("/getall")
	public ResponseEntity<ResponseStructure<List<StudentDTO>>> getAll(){
		return service.getAllStudents();
	}

}
