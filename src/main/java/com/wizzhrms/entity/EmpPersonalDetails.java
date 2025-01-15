package com.wizzhrms.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "EmpPersonalDetails")
@Data
@EntityListeners(AuditingEntityListener.class)
public class EmpPersonalDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int empPersonalId;
	private String personalEmailId;
	private String birthDate;
	private String adharNo;
	private String panNo;
	private String permanentAddress;
	private String altMobNumber;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	@CreatedDate
	@Column(updatable = false)
	private Date createdOn;
	@LastModifiedDate
	@Column(insertable = true, updatable = true)
	private Date createdDate;

}
