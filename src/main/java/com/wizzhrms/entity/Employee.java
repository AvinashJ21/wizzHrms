package com.wizzhrms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Employee")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4530328868737216930L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	private String employeeOrgId;
	private String employeeFullName;
	private String designation;
	private String designationShortName;
	private int designationId;
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
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	@JsonIgnore
	private EmployeePersonalDetails empPersonalDetails;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "employee_roles", joinColumns = {
			@JoinColumn(name = "employeeId", referencedColumnName = "employeeId") }, inverseJoinColumns = {
					@JoinColumn(name = "rolesId", referencedColumnName = "id") })
	@JsonIgnore
	private Set<Roles> roles = new HashSet<>();
	private boolean active;

}
