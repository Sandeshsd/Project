package com.courses.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.courses.document.Courses;

public interface CourcesRepo extends MongoRepository<Courses, Long> {

	//getCoursesByStudentsId
	public List<Courses> getCoursesByStudentId(long StudentId);
}
