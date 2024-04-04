package com.courses.controller;



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

import com.courses.document.CourseDTO;
import com.courses.document.Courses;
import com.courses.service.CourseService;
import com.courses.util.ResponseStructure;

@RestController
@RequestMapping("/courses")
public class CourseController {
   
	@Autowired
	private CourseService service;
	
	//save
	@PostMapping
	public ResponseEntity<ResponseStructure<Courses>> saveCourse(@RequestBody Courses courses) {
		return service.saveCourse(courses);
	}
	
	//get
	@GetMapping("/{courseId}")
	public ResponseEntity<ResponseStructure<CourseDTO>> getCourseById(@PathVariable long courseId) {
		return service.getCourseById(courseId);
	}
	//getAll
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Courses>>> getCourseById() {
		return service.getCourses();
	}
	
	//getCourseByStudentId
	@GetMapping("/students/{studentId}")
	public List<Courses> getCourseBystudentId(@PathVariable long studentId) {
		return service.getCourseByStudentId(studentId);
	}
	//update
	@PutMapping
	public ResponseEntity<ResponseStructure<Courses>> updateCourse(@RequestBody Courses courses,@RequestParam long courseId) {
		return service.updateCourse(courses, courseId);
	}
	//delete
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Courses>> deleteCourseById(@RequestParam long courseId) {
		return service.deleteCourse(courseId);
	}
	
	
}
