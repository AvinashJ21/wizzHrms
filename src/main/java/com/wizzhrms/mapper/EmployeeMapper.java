package com.wizzhrms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.EmployeeDto;
import com.wizzhrms.entity.Employee;

@Mapper
@Service
public interface EmployeeMapper {

	EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

	Employee mapEmpDtoToEntity(EmployeeDto employee);

	EmployeeDto mapEmpEntityToDto(Employee employee);

}
