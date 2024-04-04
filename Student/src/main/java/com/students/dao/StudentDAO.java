package com.students.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.students.entity.Address;
import com.students.entity.Courses;
import com.students.entity.Ratings;
import com.students.entity.Students;
import com.students.repo.StudentREPO;



@Repository
public class StudentDAO {

	@Autowired
	private StudentREPO repo;
	
	//for communicating microservices
	@Autowired
	private RestTemplate restTemplate;
	
	//save
	public Students saveStudents(Students student) {
		return repo.save(student);
	}
	
	//get
	public Students getStudentById(long studentId) {
		Students students= repo.findById(studentId).orElse(null);
		
		//getAddressByStudentId
		Address address =restTemplate.getForObject("http://ADRRESS-SERVICES/studentaddress/students/"+students.getStudentId(), Address.class);
		students.setAddress(address);
		Courses[] coursesArray = restTemplate.getForObject("http://COURSE-SERVICES/courses/students/" + students.getStudentId(), Courses[].class);
        if (coursesArray != null && coursesArray.length > 0) {
            List<Courses> courses = new ArrayList<>();
            for (Courses course : coursesArray) {
                // Retrieve ratings for each course
                Ratings ratings = restTemplate.getForObject("http://RATING-SERVICES/ratings/courses/" + course.getCourseId() + "/" + studentId, Ratings.class);
                course.setRatings(ratings);
                courses.add(course);
            }
            students.setCourses(courses);
        }
		return students;
	}
	//update
	public Students updateStudent(Students students,long studentId) {
		Optional<Students> optional=repo.findById(studentId);
		if(optional.isPresent()) {
			students.setStudentId(studentId);
			return repo.save(students);
		}return null;
	}
	
	
	//delete
	public Students deleteStudent(long studentId) {
		Students students=getStudentById(studentId);
		if(students!=null) {
			repo.delete(students);
		}return null;
	}
	
   //getAll
	public List<Students> getAll() {
		List<Students> list= repo.findAll();
		for(Students students:list) {
			getStudentById(students.getStudentId());
		}
		return list;
	}
	
}
