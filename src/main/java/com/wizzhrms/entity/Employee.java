package com.wizzhrms.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Employee")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Employee {

	@Id
	private String employeeId;
	private String employeeFullName;
	private String emailId;
	private String countryCode;
	private String mobNumber;
	private String hireDate;
	@CreatedDate
	@Column(updatable = false)
	private Date createdOn;
	@LastModifiedDate
	@Column(insertable = true, updatable = true)
	private Date modifiedDate;
	private String modifiedBy;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "employee")
	private EmpPersonalDetails empPersonalDetails;
	
}
