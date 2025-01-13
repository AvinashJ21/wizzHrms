package com.wizzhrms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Employee")
@Data
public class Employee {

	@Id
	private String employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String personalEmail;
	private String countryCode;
	private String phoneNumber;
	
}
