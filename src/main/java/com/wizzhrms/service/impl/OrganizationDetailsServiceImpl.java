package com.wizzhrms.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wizzhrms.dto.OrganizationalDetailsDto;
import com.wizzhrms.entity.OrganizationDetails;
import com.wizzhrms.mapper.OrganizationDetailsMapper;
import com.wizzhrms.repo.OrganizationDetailsRepo;
import com.wizzhrms.service.FileUploadService;
import com.wizzhrms.service.OrganizationDetailsService;
import com.wizzhrms.service.constants.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrganizationDetailsServiceImpl implements OrganizationDetailsService {
	
	
	@Autowired
	OrganizationDetailsRepo orgDetailsRepo;
	
	@Autowired
	FileUploadService fileuploadService;
	
	@Override
	public OrganizationDetails saveOrganizationDetails(OrganizationalDetailsDto orgDto,MultipartFile file) {
	
		 OrganizationDetails orgDetails = OrganizationDetailsMapper.MAPPER.mapDtoToEntity(orgDto);
		 String saveFile ="";
		 if(!file.getOriginalFilename().isEmpty()) {
			 saveFile = fileuploadService.saveFile(file, getClass().getClassLoader().getResource(Constants.IMG_PATH).getPath()); 
		 }
		  
		 if(saveFile.equalsIgnoreCase(Constants.SUCCESS)) {
			 orgDetails.setImgFileName("../"+Constants.IMG_PATH+"/"+file.getOriginalFilename());
		 }
		 orgDetails = orgDetailsRepo.save(orgDetails);
		 log.info("Details Saved");
		 return orgDetails;
	}

	@Override
	public ModelAndView findById(Integer orgDetailsId) {
		 
		Optional<OrganizationDetails> orgDetails =  orgDetailsRepo.findById(orgDetailsId);
		 
		 ModelAndView mv = new ModelAndView("organization_details.html");
			mv.addObject("admin_settings_menu_div_active", true);
			mv.addObject("admin_settings_ul_active", true);
			mv.addObject("admin_settings_li_active", true);
			mv.addObject("admin_settings_orgDetails", true);
			if (orgDetails.isPresent()) {
				mv.addObject("orgDetails", orgDetails.get());
				mv.addObject("orgDtlsAvailable", true);
			} else {
				mv.addObject("orgDtlsAvailable", false);
				mv.addObject("orgDetails", null);
			}
	
			return mv;
	}
	
	

	
}
