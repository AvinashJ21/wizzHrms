package com.wizzhrms.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RolesDto {
	
	private int id;
	private String roleName;
	private String roleDesc;
	private Date createdOn;
	private String modifiedBy;
	private Date modifiedDate;
	private boolean active;

}
