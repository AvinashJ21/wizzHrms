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

		Employee employeeEntity = getEmployeeEntity(emp);
		EmployeePersonalDetails empPersonalDetails = getEmpPersonalDetail(emp.getEmpPersonalDetails());
		empPersonalDetails.setEmployee(employeeEntity);
		employeeEntity.setEmpPersonalDetails(empPersonalDetails);
		Set<Roles> rolesEntity = getRolesEntity(emp.getRoles(), employeeEntity);
		employeeEntity.setRoles(rolesEntity);
		return empRepo.save(employeeEntity);
	}

	private Employee getEmployeeEntity(EmployeeDto empDto) {

		Employee emp = new Employee();
		emp.setEmployeeId(empDto.getEmployeeId());
		emp.setEmployeeFullName(empDto.getEmployeeFullName());
		emp.setDesignation(empDto.getDesignation());
		emp.setDesignationId(empDto.getDesignationId());
		emp.setEmployeeOrgId(empDto.getEmployeeOrgId().toUpperCase());
		emp.setEmailId(empDto.getEmailId());
		emp.setCountryCode(empDto.getCountryCode());
		emp.setMobNumber(empDto.getMobNumber());
		emp.setHireDate(empDto.getHireDate());
		emp.setActive(empDto.isActive());
		return emp;

	}

	private EmployeePersonalDetails getEmpPersonalDetail(EmployeePersonalDetailsDto empPersonalDto) {

		EmployeePersonalDetails empDtls = new EmployeePersonalDetails();
		empDtls.setEmpPersonalId(empPersonalDto.getEmpPersonalId());
		empDtls.setPersonalEmailId(empPersonalDto.getPersonalEmailId());
		empDtls.setBirthDate(empPersonalDto.getBirthDate());
		empDtls.setAdharNo(empPersonalDto.getAdharNo());
		empDtls.setPanNo(empPersonalDto.getPanNo());
		empDtls.setPermanentAddress(empPersonalDto.getPermanentAddress());
		empDtls.setAltMobNumber(empPersonalDto.getAltMobNumber());
		return empDtls;
	}

	public Set<Roles> getRolesEntity(Set<RolesDto> set, Employee employee) {

		Set<Roles> lstRoleEntity = new HashSet<>();
		set.stream().forEach(item -> {
			Roles r = new Roles();
			r.setId(item.getId());
			r.setRoleName(item.getRoleName());
			r.setRoleDesc(item.getRoleDesc());
			r.setActive(item.isActive());
			Set<Employee> empSet = new HashSet<>();
			empSet.add(employee);
			r.setEmployee(empSet);
			lstRoleEntity.add(r);
		});
		return lstRoleEntity;
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

		List<Employee> filteredData = empRepo.findByEmployeeOrgIdContainingIgnoreCase(empId);
		List<EmployeeDto> allEmpDto = new ArrayList<>();
		filteredData.stream().forEach(item -> {

			EmployeeDto empDto = EmployeeMapper.MAPPER.mapEmpEntityToDto(item);
			allEmpDto.add(empDto);
		});
		return allEmpDto;

	}

}
