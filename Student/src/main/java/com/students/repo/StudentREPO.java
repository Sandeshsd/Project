package com.students.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.students.entity.Students;



public interface StudentREPO  extends JpaRepository<Students, Long>{
	    
}
