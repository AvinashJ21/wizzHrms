package com.wizzhrms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wizzhrms.entity.Tasks;

@Repository
public interface TasksRepo extends JpaRepository<Tasks, Integer> {

	List<Tasks> findByActiveTrue();
}
