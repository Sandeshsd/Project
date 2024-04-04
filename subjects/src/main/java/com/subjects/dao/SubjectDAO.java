package com.subjects.dao;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.subjects.entity.Subject;
import com.subjects.repo.SubjectRepo;

@Repository
public class SubjectDAO {

	
	@Autowired
	private SubjectRepo repo;
	
	//save
	public Subject saveSubject(Subject subject) {
		return repo.save(subject);
	}
	//get
	public Subject getSubjectById(long subjectId) {
		Subject subject=repo.findById(subjectId).orElse(null);
		return subject;
	}
	//getSubjectByMentorId
	public List<Subject> getSubjectByMentorId(long mentorId) {
		return repo.getSubjectByMentorId(mentorId);
	}
	//getSubjectByCourseId
		public List<Subject> getSubjectByCourseId(long mentorId) {
			return repo.getSubjectByCourseId(mentorId);
		}
	
	//update
	public Subject updateSubjectById(Subject subject, long subjectId) {
		 subject=repo.findById(subjectId).orElse(null);
		return repo.save(subject);
	}
	//delete
	public Subject deleteSubjectById( long subjectId) {
		Subject subject=getSubjectById(subjectId);
				if(subject!=null) {
		 repo.delete(subject);
				}return null;
	}
}
