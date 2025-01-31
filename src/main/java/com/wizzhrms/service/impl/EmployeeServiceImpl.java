package com.wizzhrms.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.EmployeeDto;
import com.wizzhrms.dto.EmployeePersonalDetailsDto;
import com.wizzhrms.dto.RolesDto;
import com.wizzhrms.entity.Employee;
import com.wizzhrms.entity.EmployeePersonalDetails;
import com.wizzhrms.entity.Roles;
import com.wizzhrms.mapper.EmployeeMapper;
import com.wizzhrms.repo.EmployeeRepo;
import com.wizzhrms.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo empRepo;
	
	

	@Override
	public Employee addUpdEmployee(EmployeeDto emp) {

	
			Employee empEntity = EmployeeMapper.MAPPER.mapEmpDtoToEntity(emp);
			empEntity.getEmpPersonalDetails().setEmployee(empEntity);
			return empRepo.save(empEntity);
	}	
	@Override
	public List<EmployeeDto> getAllEmployees() {

		List<Employee> allEmployees = empRepo.findAll(Sort.by(Direction.DESC, "modifiedDate"));
		List<EmployeeDto> allEmpDto = new ArrayList<>();
		allEmployees.stream().forEach(item -> {

			EmployeeDto empDto = EmployeeMapper.MAPPER.mapEmpEntityToDto(item);
			allEmpDto.add(empDto);
		});

		return allEmpDto;
	}

	@Override
	public List<EmployeeDto> getEmployeesByOrgId(String empId) {
		List<Employee> filteredData = empRepo.findByEmployeeOrgIdContainingIgnoreCase(empId,Sort.by(Direction.DESC, "modifiedDate"));
		List<EmployeeDto> allEmpDto = new ArrayList<>();
		filteredData.stream().forEach(item -> {

			EmployeeDto empDto = EmployeeMapper.MAPPER.mapEmpEntityToDto(item);
			allEmpDto.add(empDto);
		});
		return allEmpDto;

	}
	
	@Override
	public List<EmployeeDto> getEmployeesByShortNames(List<String> shortNames) {
		List<Employee> filteredData = empRepo.findByActiveTrueAndDesignationShortNameIn(shortNames);
		List<EmployeeDto> allEmpDto = new ArrayList<>();
		filteredData.stream().forEach(item -> {

			EmployeeDto empDto = EmployeeMapper.MAPPER.mapEmpEntityToDto(item);
			allEmpDto.add(empDto);
		});
		return allEmpDto;

	}

}
