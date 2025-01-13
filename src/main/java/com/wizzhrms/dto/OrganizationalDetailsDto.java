package com.wizzhrms.dto;

import com.wizzhrms.service.constants.Constants;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrganizationalDetailsDto {
	
	private int id = Constants.ORG_DETAILS_ID;
	private String name;
	private String orgDesc;
	private String websiteURL;
	private String contactEmail;
	private String copyRights;
	private String imgFileName;

}
