package com.wizzhrms.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.wizzhrms.entity.Tasks;

import lombok.Data;

@Data
public class ProjectsDto {
	
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
	private Date createdOn;
	private Date modifiedDate;
	private String modifiedBy;
	private boolean active;
	private Set<Tasks> tasks = new HashSet<>();

}
