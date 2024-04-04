package com.subjects.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subjects.entity.Subject;
import com.subjects.repo.SubjectRepo;
import com.subjects.service.SubjectService;
import com.subjects.util.ResponseStructure;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

	@Autowired
	private SubjectRepo repo;
	
	@Autowired
	private SubjectService service;
	
	//save
	@PostMapping
	public ResponseEntity<ResponseStructure<Subject>> saveSubject(@RequestBody Subject subject){
		return service.saveSubject(subject);
	}
	
	//getsubjectByID
	@GetMapping("/{subjectId}")
	public ResponseEntity<ResponseStructure<Subject>> getSubject(@PathVariable long subjectId){
		return service.getSubjectById(subjectId);
	}
	//getAll
	@GetMapping
	public List<Subject> getSubjects(){
		return repo.findAll();
	}
	
	//getSubjectByMentorId
	@GetMapping("/mentors/{mentorId}")
	public List<Subject> getSubjectByMentorId(@PathVariable long mentorId ){
		return repo.getSubjectByMentorId(mentorId);
	}
	
	//getSubjectByCourseId
	@GetMapping("/courses/{courseId}")
	public List<Subject> getSubjectByCourseId(@PathVariable long courseId){
		return repo.getSubjectByCourseId(courseId);
	}
	
}
