package com.wizzhrms.service;

import java.util.List;

import com.wizzhrms.dto.EmployeeDto;
import com.wizzhrms.entity.Employee;

public interface EmployeeService {

	Employee addUpdEmployee(EmployeeDto emp);

	List<EmployeeDto> getAllEmployees();
	
	List<EmployeeDto> getEmployeesByOrgId(String empId);
	
	List<EmployeeDto> getEmployeesByShortNames(List<String> shortNames);
}
