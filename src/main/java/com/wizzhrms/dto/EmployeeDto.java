package com.wizzhrms.dto;

import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class EmployeeDto {
	
	private int employeeId;
	private String employeeOrgId;
	private String employeeFullName;
	private String designation;
	private int designationId;
	private String emailId;
	private String countryCode;
	private String mobNumber;
	private String hireDate;
	private Date createdOn;
	private Date modifiedDate;
	private String modifiedBy;
    private EmployeePersonalDetailsDto empPersonalDetails;
    private Set<RolesDto> roles;
    private boolean active;
}
