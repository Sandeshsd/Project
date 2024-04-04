package com.mentor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mentor.dao.MentorDAO;
import com.mentor.dto.MentorDTO;
import com.mentor.entity.Mentor;
import com.mentor.exception.MentorNotFoundByIdException;
import com.mentor.util.ResponseStructure;

@Service
public class MentorService {

	@Autowired
	private MentorDAO dao;

	@Autowired
	private ModelMapper mapper;

	// save
	public ResponseEntity<ResponseStructure<MentorDTO>> saveMentor(Mentor mentor) {
		ResponseStructure<MentorDTO> responseStructure = new ResponseStructure<MentorDTO>();
		mentor = dao.savementor(mentor);
		MentorDTO dto = this.mapper.map(mentor, MentorDTO.class);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Created");
		responseStructure.setData(dto);
		return new ResponseEntity<ResponseStructure<MentorDTO>>(responseStructure, HttpStatus.CREATED);
	}

	// find
	public ResponseEntity<ResponseStructure<MentorDTO>> getMentoryId(long mentorId) {
		Mentor Mentor = dao.getMentorById(mentorId);
		if (Mentor != null) {
			MentorDTO dto = this.mapper.map(Mentor, MentorDTO.class);
			ResponseStructure<MentorDTO> responseStructure = new ResponseStructure<MentorDTO>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Mentor data found successfull");
			responseStructure.setData(dto);
			return new ResponseEntity<ResponseStructure<MentorDTO>>(responseStructure, HttpStatus.FOUND);
		}
		throw new MentorNotFoundByIdException("Enter correct MentorID");
	}

	// update
	public ResponseEntity<ResponseStructure<MentorDTO>> updateMentor(long mentorId, Mentor mentor) {
		Mentor mentor2 = dao.getMentorById(mentorId);
		if (mentor2 != null) {
			mentor = dao.updateMentor(mentor, mentorId);
			MentorDTO dto = this.mapper.map(mentor, MentorDTO.class);
			ResponseStructure<MentorDTO> responseStructure = new ResponseStructure<MentorDTO>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("updated successfully");
			responseStructure.setData(dto);
			return new ResponseEntity<ResponseStructure<MentorDTO>>(responseStructure, HttpStatus.CREATED);
		}
		throw new MentorNotFoundByIdException("Enter correct MentorID");
	}

	// delete
	public ResponseEntity<ResponseStructure<MentorDTO>> deleteMentor(long mentorId) {
		Mentor mentor = dao.getMentorById(mentorId);
		if (mentor != null) {
			mentor = dao.deleteMentor(mentorId);
			ResponseStructure<MentorDTO> responseStructure = new ResponseStructure<MentorDTO>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("deleted successfully");
			responseStructure.setData(mentor);
			return new ResponseEntity<ResponseStructure<MentorDTO>>(responseStructure, HttpStatus.OK);
		}
		throw new MentorNotFoundByIdException("Enter correct MentorID");
	}

	// getAll
	public  ResponseEntity<ResponseStructure<List<MentorDTO>>> getAllMentor(){
		List<Mentor> mentors=dao.getAll();
		List<MentorDTO> dto=mentors.stream().map(mentor->mapper.map(mentor, MentorDTO.class))
				                                                          .collect(Collectors.toList());
		ResponseStructure<List<MentorDTO>> responseStructure=new ResponseStructure<List<MentorDTO>>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("Mentor data found successfull");
		responseStructure.setData(dto);
		return new ResponseEntity<ResponseStructure<List<MentorDTO>>>(responseStructure,HttpStatus.FOUND);
	}
	//getMentorByCourseId
	public List<Mentor> getMentorByCourseId(long courseId){
		return dao.getMentorByCourseId(courseId);
	}

	//addressSubject Fallbackmethod
	public ResponseEntity<ResponseStructure<MentorDTO>> addressSubject(long mentorId) {
		MentorDTO mentorDTO= MentorDTO.builder().mentorEmail("dummymentor@gmail.com").mentorName("dummyMentor").mentorPhNo(0000000000).build();
		ResponseStructure<MentorDTO> responseStructure=new ResponseStructure<MentorDTO>(HttpStatus.OK.value(), "success", mentorDTO);
		return new ResponseEntity<ResponseStructure<MentorDTO>>(responseStructure,HttpStatus.OK);
	}

}
