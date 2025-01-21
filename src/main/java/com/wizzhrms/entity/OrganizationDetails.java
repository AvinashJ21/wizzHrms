package com.wizzhrms.entity;

import java.io.Serializable;
import java.util.Date;

import com.wizzhrms.service.constants.Constants;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "OrganizationDetails")
@Data
public class OrganizationDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8354370750050464541L;
	@Id
	private int id = Constants.ORG_DETAILS_ID;
	private String name;
	private String orgDesc;
	private String websiteURL;
	private String contactEmail;
	private String copyRights;
	private String imgFileName;
	private Date createdOn;
}
