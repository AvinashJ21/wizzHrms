package com.wizzhrms.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TasksDto {

	private int id;
	private String taskName;
	private String taskDesc;
	private Date createdOn;
	private String modifiedBy;
	private Date modifiedDate;
	private boolean active;

}
