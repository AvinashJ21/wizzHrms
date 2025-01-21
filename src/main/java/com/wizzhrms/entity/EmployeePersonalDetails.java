package com.wizzhrms.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EmpPersonalDetails")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class EmployeePersonalDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4139866371511780291L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empPersonalId;
	private String personalEmailId;
	private String birthDate;
	private String adharNo;
	private String panNo;
	private String permanentAddress;
	private String altMobNumber;
	@CreatedDate
	@Column(updatable = false)
	private Date createdOn;
	@LastModifiedDate
	@Column(insertable = true, updatable = true)
	private Date createdDate;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn( name="employeeIdFk", referencedColumnName  = "employeeId")
	@JsonIgnore
	private Employee employee;


}
