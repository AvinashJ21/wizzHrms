package com.wizzhrms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wizzhrms.dto.OrganizationalEventsDto;
import com.wizzhrms.entity.OrganizationalEvents;

public interface OrganizationEventService {
	
	 OrganizationalEvents saveEvent(OrganizationalEventsDto orgEvents,MultipartFile file);
	 List<OrganizationalEventsDto> getOrganizationalEventsData();

}
