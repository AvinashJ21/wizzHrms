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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Projects")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Projects implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7089673675007842818L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String projectName;
	private String projectCode;
//	private int managerId;
//	private String managerOrgId;
//	private String managerName;
	private String projectType;
	private String projectOwner;
	private String startDate;
	private String endDate;
	@CreatedDate
	@Column(updatable = false)
	private Date createdOn;
	@LastModifiedDate
	@Column(insertable = true, updatable = true)
	private Date modifiedDate;
	private String modifiedBy;
	private boolean active;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "project_tasks", joinColumns = {
			@JoinColumn(name = "projectId", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "taskId", referencedColumnName = "id") })
	@JsonIgnore
	private Set<Tasks> tasks = new HashSet<>();
	
	@ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JsonIgnore
    private Set<Employee> employees = new HashSet<>();
	
	
}
