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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tasks")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Tasks implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5954167247609508516L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String taskName;
	private String taskDesc;
	@CreatedDate
	@Column(updatable = false)
	private Date createdOn;
	private String modifiedBy;
	@LastModifiedDate
	@Column(insertable = true, updatable = true)
	private Date modifiedDate;
	private boolean active;
	@ManyToMany(mappedBy = "tasks", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JsonIgnore
    private Set<Projects> projects = new HashSet<>();
	

}
