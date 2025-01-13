package com.wizzhrms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.OrganizationalDetailsDto;
import com.wizzhrms.entity.OrganizationDetails;


@Mapper
@Service
public interface OrganizationDetailsMapper {

	OrganizationDetailsMapper MAPPER = Mappers.getMapper(OrganizationDetailsMapper.class);
	OrganizationDetails mapDtoToEntity(OrganizationalDetailsDto orgDto);
	
}
