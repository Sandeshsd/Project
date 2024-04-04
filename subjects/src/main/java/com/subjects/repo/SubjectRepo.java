package com.subjects.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subjects.entity.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long>{

	//getSubjectByCourseId
	public  List<Subject> getSubjectByCourseId(long courseId);
	//getSubjectByMentorId
	public  List<Subject> getSubjectByMentorId(long mentorId);
}
