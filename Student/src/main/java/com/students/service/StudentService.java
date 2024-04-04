package com.students.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.students.dao.StudentDAO;
import com.students.dto.StudentDTO;
import com.students.entity.Students;
import com.students.exception.StudentNotFoundByIdException;
import com.students.util.ResponseStructure;

@Service
public class StudentService {

	@Autowired
	private StudentDAO dao;
	
	@Autowired
	private ModelMapper mapper;
	
	//save
	public ResponseEntity<ResponseStructure<StudentDTO>> saveStudent(Students students){
		ResponseStructure<StudentDTO> responseStructure=new ResponseStructure<StudentDTO>();
		 students=dao.saveStudents(students);
		StudentDTO dto=this.mapper.map(students, StudentDTO.class);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Created");
		responseStructure.setData(dto);
		return new ResponseEntity<ResponseStructure<StudentDTO>>(responseStructure,HttpStatus.CREATED);
	}
	
	//find
	public ResponseEntity<ResponseStructure<StudentDTO>> getStudentyId(long studentId){
		Students students=dao.getStudentById(studentId);
		if(students!=null) {
			StudentDTO dto=this.mapper.map(students, StudentDTO.class);
			ResponseStructure<StudentDTO> responseStructure=new ResponseStructure<StudentDTO>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("student data found successfull");
			responseStructure.setData(dto);
			return new ResponseEntity<ResponseStructure<StudentDTO>>(responseStructure,HttpStatus.FOUND);
		}throw new StudentNotFoundByIdException("Enter correct StudentID");
	}
	
	//update
	
	public  ResponseEntity<ResponseStructure<StudentDTO>> updateStudent(long studentId,Students students){
	 students=dao.getStudentById(studentId);
		if(students!=null) {
			Students student=dao.updateStudent(students, studentId);
			StudentDTO dto=this.mapper.map(student, StudentDTO.class);
			ResponseStructure<StudentDTO> responseStructure=new ResponseStructure<StudentDTO>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("updated successfully");
			responseStructure.setData(dto);
			return new ResponseEntity<ResponseStructure<StudentDTO>>(responseStructure,HttpStatus.CREATED);
		}
		throw new StudentNotFoundByIdException("Enter correct StudentID");
	}
	//delete
	public ResponseEntity<ResponseStructure<StudentDTO>> deleteStudents(long studentId){
		Students students=dao.getStudentById(studentId);
		if(students!=null) {
			students=dao.deleteStudent(studentId);
			ResponseStructure<StudentDTO> responseStructure=new ResponseStructure<StudentDTO>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("deleted successfully");
			responseStructure.setData(students);
			return new ResponseEntity<ResponseStructure<StudentDTO>>(responseStructure,HttpStatus.OK);
		}throw new StudentNotFoundByIdException("Enter correct StudentID");
	}
	
	//getAll
	public ResponseEntity<ResponseStructure<List<StudentDTO>>> getAllStudents() {
	    List<Students> students = dao.getAll();
	    List<StudentDTO> dtos = students.stream()
	                                    .map(student -> mapper.map(student, StudentDTO.class))
	                                    .collect(Collectors.toList());

	    ResponseStructure<List<StudentDTO>> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatus(HttpStatus.OK.value());
	    responseStructure.setMessage("Student data found successfully");
	    responseStructure.setData(dtos);

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	
}
