package com.wizzhrms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.OrganizationalEventsDto;
import com.wizzhrms.entity.OrganizationalEvents;

@Mapper
@Service
public interface OrganizationEventsMapper {

	OrganizationEventsMapper MAPPER = Mappers.getMapper(OrganizationEventsMapper.class);
	 OrganizationalEvents  mapEventDtoToEntity(OrganizationalEventsDto orgEvent); 
	 OrganizationalEventsDto  mapEventEntityToDto(OrganizationalEvents orgEvent); 
}
