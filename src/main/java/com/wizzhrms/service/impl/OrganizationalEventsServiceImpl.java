package com.wizzhrms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wizzhrms.dto.OrganizationalEventsDto;
import com.wizzhrms.entity.OrganizationalEvents;
import com.wizzhrms.mapper.OrganizationEventsMapper;
import com.wizzhrms.repo.OrganizationalEventsRepo;
import com.wizzhrms.service.FileUploadService;
import com.wizzhrms.service.OrganizationEventService;
import com.wizzhrms.service.constants.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrganizationalEventsServiceImpl implements OrganizationEventService {

	@Autowired
	FileUploadService fileuploadService;

	@Autowired
	OrganizationalEventsRepo orgEventsRepo;


	@Override
	public OrganizationalEvents saveEvent(OrganizationalEventsDto orgDto, MultipartFile file) {

		OrganizationalEvents orgEvents = OrganizationEventsMapper.MAPPER.mapEventDtoToEntity(orgDto);
		String saveFile = "";
		if (!file.getOriginalFilename().isEmpty()) {
			saveFile = fileuploadService.saveFile(file,
					getClass().getClassLoader().getResource(Constants.EVENT_IMG_PATH).getPath());
		}

		if (saveFile.equalsIgnoreCase(Constants.SUCCESS)) {
			orgEvents.setImageName("../" + Constants.EVENT_IMG_PATH + "/" + file.getOriginalFilename());
		}
		//orgEvents.setActive(true);
		orgEvents = orgEventsRepo.save(orgEvents);
		log.info("Details Saved");
		return orgEvents;
	}

	@Override
	public List<OrganizationalEventsDto> getOrganizationalEventsData() {

		List<OrganizationalEvents> eventsData = orgEventsRepo.findAll(Sort.by(Direction.DESC, "modifiedDate"));
		List<OrganizationalEventsDto> lstData = new ArrayList<>();
		eventsData.forEach(item -> {
			OrganizationalEventsDto eventDto = OrganizationEventsMapper.MAPPER.mapEventEntityToDto(item);
			lstData.add(eventDto);
		});
		return lstData;

	}

}
