package com.mentor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mentor.entity.Mentor;

public interface MentorRepo extends JpaRepository<Mentor, Long> {

	List<Mentor> getMentorsByCourseId(long courseId);
}
