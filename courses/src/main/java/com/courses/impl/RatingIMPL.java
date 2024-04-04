package com.courses.impl;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.courses.document.Ratings;

@FeignClient(name = "RATING-SERVICES")
public interface RatingIMPL {
	
	@GetMapping("ratings/courses/{courseId}")
	List<Ratings> getratings(@PathVariable long courseId);

}
