package com.wizzhrms.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProjectsDto {
	
	private int id;
	private String projectName;
	private String projectCode;
	private int managerId;
	private String projectType;
	private String projectOwner;
	private String startDate;
	private String endDate;
	private Date createdOn;
	private Date modifiedDate;
	private String modifiedBy;
	private boolean active;

}
