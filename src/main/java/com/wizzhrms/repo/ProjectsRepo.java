package com.wizzhrms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wizzhrms.entity.Projects;

public interface ProjectsRepo extends JpaRepository<Projects, Integer> {

}
