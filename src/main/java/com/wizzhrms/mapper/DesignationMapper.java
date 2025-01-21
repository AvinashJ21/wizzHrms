package com.wizzhrms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.DesignationDto;
import com.wizzhrms.entity.Designation;

@Mapper
@Service
public interface DesignationMapper {

	DesignationMapper MAPPER = Mappers.getMapper(DesignationMapper.class);
	
			 Designation mapDtoToEntity(DesignationDto designation);
			 DesignationDto mapEntityToDto(Designation designation);
							
}
