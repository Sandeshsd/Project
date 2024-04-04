package com.ratings.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ratings.document.Ratings;
import com.ratings.repo.RatingRepo;

@Repository
public class RatingDAO {
	
	@Autowired
	private RatingRepo repo;
	
	//save
	public Ratings saveRating(Ratings ratings) {
	 return	repo.save(ratings);
	}
    //get
	public Ratings getRatingById(long ratingId) {
		Optional<Ratings> optional=repo.findById(ratingId);
		if(optional.isEmpty()) {
			return null;
		}return optional.get();
	}
	//getAll
	public List<Ratings> getAllRatings(){
		return repo.findAll();
		}
	
	//getRatingByStudentId
	public List<Ratings> getRatingByStudentId(long studentId){
		return repo.getRatingsByStudentId(studentId);
	}
	
	//getRatingByCourseId
	public List<Ratings> getRatingByCourseId(long courseId){
		return repo.getRatingsByCourseId(courseId);
	}
	//getRatingsOFCourseByStudentId
	public Ratings getRatingsOFCourseByStudentId(long courseId,long studentId) {
		return repo.getRatingsOFCourseByStudentId(courseId, studentId);
	}
}
