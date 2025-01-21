package com.wizzhrms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wizzhrms.entity.Designation;

@Repository
public interface DesignationRepo extends JpaRepository<Designation, Integer>{
	
		
	
}
