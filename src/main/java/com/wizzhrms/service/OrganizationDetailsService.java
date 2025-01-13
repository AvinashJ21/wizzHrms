package com.wizzhrms.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wizzhrms.dto.OrganizationalDetailsDto;
import com.wizzhrms.entity.OrganizationDetails;

public interface OrganizationDetailsService {

	OrganizationDetails saveOrganizationDetails(OrganizationalDetailsDto orgDto,MultipartFile file);

	ModelAndView findById(Integer orgDetailsId);
}
