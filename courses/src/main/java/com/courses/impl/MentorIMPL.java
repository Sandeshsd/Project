package com.courses.impl;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.courses.document.Mentor;

@FeignClient(name ="MENTOR-SERVICES")
public interface MentorIMPL {
	
	@GetMapping("mentors/courses/{courseId}")
	List<Mentor> getmentors(@PathVariable long courseId);

}
