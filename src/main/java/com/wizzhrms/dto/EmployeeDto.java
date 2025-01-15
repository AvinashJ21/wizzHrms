package com.wizzhrms.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDto {
	
	private String employeeId;
	private String employeeFullName;
	private String emailId;
	private String countryCode;
	private String mobNumber;
	private String hireDate;
	private Date createdOn;
	private Date modifiedDate;
	private String modifiedBy;

}
