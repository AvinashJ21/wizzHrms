package com.wizzhrms.service;

import java.util.List;

import com.wizzhrms.dto.DesignationDto;
import com.wizzhrms.entity.Designation;

public interface DesignationService {
	
	 Designation addUpdDesignation(DesignationDto designationDto);
	 List<DesignationDto> getDesignations();
	 List<DesignationDto> getActiveDesignations();	

}
