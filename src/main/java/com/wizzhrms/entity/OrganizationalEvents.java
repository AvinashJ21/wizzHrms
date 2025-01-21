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
@Table(name = "OrganizationalEvents")
@EntityListeners(AuditingEntityListener.class)
@Data
public class OrganizationalEvents implements Serializable {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 9059096602271816066L;
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private int id;
		private String eventName;
		private String eventDesc;
		private String fromDate;
		private String toDate;
		private String inviteLink;
		private boolean active;
		private String imageName;
		@CreatedDate
		@Column(updatable = false)
		private Date createdOn;
		@LastModifiedDate
		@Column(insertable = true, updatable = true)
		private Date modifiedDate;
		private String modifiedBy;
}
