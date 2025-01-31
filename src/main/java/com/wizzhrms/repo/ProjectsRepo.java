package com.wizzhrms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wizzhrms.entity.Projects;

public interface ProjectsRepo extends JpaRepository<Projects, Integer> {
	
	List<Projects> findByActiveTrue();

}
