package com.wizzhrms.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrganizationalEventsDto {
	
	private int id;
	private String eventName;
	private String eventDesc;
	private String fromDate;
	private String toDate;
	private String inviteLink;
	private boolean active;
	private String imageName;
	private Date createdOn;
	private Date modifiedDate;
	private String modifiedBy;

}
