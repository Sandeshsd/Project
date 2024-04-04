package com.subjects.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.subjects.dao.SubjectDAO;
import com.subjects.entity.Subject;
import com.subjects.exception.SubjectNotFoundBySubjectIdException;
import com.subjects.util.ResponseStructure;

@Service
public class SubjectService {

	@Autowired
	private SubjectDAO dao;
	
	//save
	public ResponseEntity<ResponseStructure<Subject>> saveSubject(Subject subject){
		ResponseStructure<Subject> responseStructure=new ResponseStructure<Subject>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Subject saved");
		responseStructure.setData(dao.saveSubject(subject));
		return new ResponseEntity<ResponseStructure<Subject>>(responseStructure,HttpStatus.CREATED);
	}
	//get
	public ResponseEntity<ResponseStructure<Subject>> getSubjectById(long subjectId){
		Subject subject=dao.getSubjectById(subjectId);
		if(subject!=null) {
		ResponseStructure<Subject> responseStructure=new ResponseStructure<Subject>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("fetch success");
		responseStructure.setData(subject);
		return new ResponseEntity<ResponseStructure<Subject>>(responseStructure,HttpStatus.FOUND);
		}throw new SubjectNotFoundBySubjectIdException("wrong subjectId");
	}
	//getSubjectsByMentorId
	public List<Subject> getSubjectByMentorId(long mentorId) {
		return dao.getSubjectByMentorId(mentorId);
	}
	//getSubjectsByCourseId
	public List<Subject> getSubjectByCourseId(long mentorId) {
		return dao.getSubjectByCourseId(mentorId);
	}

	//update
	public ResponseEntity<ResponseStructure<Subject>> updateSubjectById(Subject subject, long subjectId){
		 subject=dao.getSubjectById(subjectId);
		if(subject!=null) {
			subject=dao.updateSubjectById(subject,subjectId);
		ResponseStructure<Subject> responseStructure=new ResponseStructure<Subject>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("update success");
		responseStructure.setData(subject);
		return new ResponseEntity<ResponseStructure<Subject>>(responseStructure,HttpStatus.OK);
		}throw new SubjectNotFoundBySubjectIdException("wrong subjectId");
	}
	//delete
	public ResponseEntity<ResponseStructure<Subject>> deleteSubjectById(long subjectId){
		Subject subject=dao.getSubjectById(subjectId);
		if(subject!=null) {
			dao.deleteSubjectById(subjectId);
		ResponseStructure<Subject> responseStructure=new ResponseStructure<Subject>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("update success");
		responseStructure.setData(subject);
		return new ResponseEntity<ResponseStructure<Subject>>(responseStructure,HttpStatus.OK);
		}throw new SubjectNotFoundBySubjectIdException("wrong subjectId");
	}
}
