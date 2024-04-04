package com.ratings.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ratings.document.Ratings;

public interface RatingRepo extends MongoRepository<Ratings, Long>{

	
	public List<Ratings> getRatingsByStudentId(long studentId);
	public List<Ratings> getRatingsByCourseId(long courseId);
	public Ratings getRatingsOFCourseByStudentId(long courseId,long studentId);
}
