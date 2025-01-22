package com.wizzhrms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wizzhrms.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
		
			List<Employee> findByEmployeeOrgIdContainingIgnoreCase(String empOrgId);

}
