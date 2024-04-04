package com.courses.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.courses.dao.CourseDAO;
import com.courses.document.CourseDTO;
import com.courses.document.Courses;
import com.courses.document.Mentor;
import com.courses.document.Ratings;
import com.courses.document.Subjects;
import com.courses.exception.CourseNotFoundByIdException;
import com.courses.impl.MentorIMPL;
import com.courses.impl.RatingIMPL;
import com.courses.impl.SubjectIMPL;
import com.courses.util.ResponseStructure;

@Service
public class CourseService {

	@Autowired
	private CourseDAO dao;
	@Autowired
	private SubjectIMPL subjectIMPL;
	@Autowired
	private MentorIMPL mentorIMPL;
	@Autowired
	private RatingIMPL  ratingIMPL;
	@Autowired
	private ModelMapper mapper;
	
	
	//save
	public ResponseEntity<ResponseStructure<Courses>> saveCourse(Courses courses){
		courses=dao.saveCourses(courses);
		ResponseStructure<Courses> responseStructure=new ResponseStructure<Courses>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("course created");
		responseStructure.setData(courses);
		return new ResponseEntity<ResponseStructure<Courses>>(responseStructure,HttpStatus.CREATED);
	}
	//get
	public ResponseEntity<ResponseStructure<CourseDTO>> getCourseById(long courseId){
		Courses courses=dao.getCourseById(courseId);
		if(courses!=null) {
			CourseDTO dto=this.mapper.map(courses, CourseDTO.class);
			List<Subjects> subjects=  subjectIMPL.getSubjects(courseId);
			List<Mentor> mentors=mentorIMPL.getmentors(courseId);
			List<Ratings> ratings=ratingIMPL.getratings(courseId);
			dto.setRatings(ratings);
			dto.setMentors(mentors);
			dto.setSubjects(subjects);
		ResponseStructure<CourseDTO> responseStructure=new ResponseStructure<CourseDTO>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("course found");
		responseStructure.setData(dto);
		return new ResponseEntity<ResponseStructure<CourseDTO>>(responseStructure,HttpStatus.FOUND);
		}throw new CourseNotFoundByIdException("provide correct courseId");
	}
	//getAll
	public ResponseEntity<ResponseStructure<List<Courses>>> getCourses(){
		List<Courses> list=dao.getAllCourses();
		ResponseStructure<List<Courses>> responseStructure=new ResponseStructure<List<Courses>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("fetch success !!");
		responseStructure.setData(list);
		return new ResponseEntity<ResponseStructure<List<Courses>>>(responseStructure,HttpStatus.OK);
	}
	//getCourseByStudentId
	public List<Courses> getCourseByStudentId(long studentId){
		return dao.getCoursesByStudentId(studentId);
	}
	//update
	public ResponseEntity<ResponseStructure<Courses>> updateCourse(Courses courses,long courseId){
		Courses courses2=dao.getCourseById(courseId);
		if(courses2!=null) {	
		ResponseStructure<Courses> responseStructure=new ResponseStructure<Courses>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("course updated");
		responseStructure.setData(dao.updateCourses(courses, courseId));
		return new ResponseEntity<ResponseStructure<Courses>>(responseStructure,HttpStatus.CREATED);
		}throw new CourseNotFoundByIdException("provide correct courseId");
	}
	//delete
	public ResponseEntity<ResponseStructure<Courses>> deleteCourse(long courseId){
		Courses courses=dao.getCourseById(courseId);
		if(courses!=null) {
		ResponseStructure<Courses> responseStructure=new ResponseStructure<Courses>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("course deleted");
		responseStructure.setData(dao.deleteCourse(courseId));
		return new ResponseEntity<ResponseStructure<Courses>>(responseStructure,HttpStatus.CREATED);
		}throw new CourseNotFoundByIdException("provide correct courseId");
	}
}
