package com.wizzhrms.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Projects")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Projects {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String projectName;
	private String projectCode;
	private int managerId;
	private String projectType;
	private String productOwner;
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
	
	
}
