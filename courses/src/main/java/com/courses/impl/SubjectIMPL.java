package com.courses.impl;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.courses.document.Subjects;



@FeignClient(name = "SUBJECT-SERVICES")
public interface SubjectIMPL {

	@GetMapping("subjects/courses/{courseId}")
	List<Subjects> getSubjects(@PathVariable("courseId") long courseId);	
	
}
