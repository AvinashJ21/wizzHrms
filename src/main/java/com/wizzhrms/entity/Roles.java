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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Roles")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4522382624557637046L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String roleName;
	private String roleDesc;
	@CreatedDate
	@Column(updatable = false)
	private Date createdOn;
	private String modifiedBy;
	@LastModifiedDate
	@Column(insertable = true, updatable = true)
	private Date modifiedDate;
	private boolean active;
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JsonIgnore
    private Set<Employee> employee = new HashSet<>();
	
	
}
