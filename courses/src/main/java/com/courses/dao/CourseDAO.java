package com.courses.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.document.Courses;
import com.courses.repo.CourcesRepo;

@Repository
public class CourseDAO {

	@Autowired
	private CourcesRepo repo;
	
	//save
	public Courses saveCourses(Courses courses) {
		return repo.save(courses);
	}
	//get
	public Courses getCourseById(long courseId) {
		Optional<Courses>  optional=repo.findById(courseId);
		if(optional.isEmpty()) {
			return null;
		}return optional.get();
	}
	//getAll
	public List<Courses> getAllCourses() {
		return repo.findAll();
	}
	//getCoursesByStudentId
	public List<Courses> getCoursesByStudentId(long studentId) {
		return repo.getCoursesByStudentId(studentId);
	}
	//update
	public Courses updateCourses(Courses courses,long courseId) {
		Optional<Courses> optional=repo.findById(courseId);
		if(optional.isPresent()) {
			courses.setCourseId(courseId);
			return repo.save(courses);
		}return null;
	}
	//delete
	public Courses deleteCourse(long courseId) {
		Courses courses=getCourseById(courseId);
		if(courses!=null) {
			 repo.delete(courses);
		}return null;
	}
}
