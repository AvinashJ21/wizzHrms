package com.wizzhrms.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DesignationDto {

	private int id;
	private String designationName;
	private String designationDesc;
	private Date createdOn;
	private String modifiedBy;
	private Date modifiedDate;
	private boolean active;
	
}
