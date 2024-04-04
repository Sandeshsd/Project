package com.mentor.dao;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.mentor.entity.Address;
import com.mentor.entity.Mentor;
import com.mentor.entity.Subjects;
import com.mentor.repo.MentorRepo;

@Repository
public class MentorDAO {

	@Autowired
	private MentorRepo repo;

	// for communicating microservices
	@Autowired
	private RestTemplate restTemplate;

	// save
	public Mentor savementor(Mentor mentor) {
		return repo.save(mentor);
	}

	// get
	public Mentor getMentorById(long mentorId) {
		Mentor mentors = repo.findById(mentorId).orElse(null);

		Address address = restTemplate.getForObject("http://ADRRESS-SERVICES/mentoraddress/mentors/" + mentors.getMentorId(),Address.class);
		List<Subjects> subjects= Arrays.asList( restTemplate.getForObject("http://SUBJECT-SERVICES/subjects/mentors/"+mentors.getMentorId(), Subjects[].class));
	
		 mentors.setSubjects(subjects);
		mentors.setAddress(address);
		return mentors;
	}
	//getMentorByCourseId
	public List<Mentor> getMentorByCourseId(long courseId) {
		return repo.getMentorsByCourseId(courseId);
	}

	// update
	public Mentor updateMentor(Mentor mentors, long mentorId) {
		Optional<Mentor> optional = repo.findById(mentorId);
		if (optional.isPresent()) {
			mentors.setMentorId(mentorId);
			return repo.save(mentors);
		}
		return null;
	}

	// delete
	public Mentor deleteMentor(long mentorId) {
		Mentor mentors = getMentorById(mentorId);
		if (mentors != null) {
			repo.delete(mentors);
		}
		return null;
	}

	// getAll
	public List<Mentor> getAll() {
		List<Mentor> mentor= repo.findAll();
		for(Mentor mentor2:mentor) {
			getMentorById(mentor2.getMentorId());
		}
		return mentor;
	}
}
