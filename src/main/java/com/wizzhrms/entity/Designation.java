package com.wizzhrms.entity;

import java.io.Serializable;
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
@Table(name = "Designation")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Designation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1022208869860555675L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String designationName;
	private String designationDesc;
	@CreatedDate
	@Column(updatable = false)
	private Date createdOn;
	private String modifiedBy;
	@LastModifiedDate
	@Column(insertable = true, updatable = true)
	private Date modifiedDate;
	private boolean active;

}
