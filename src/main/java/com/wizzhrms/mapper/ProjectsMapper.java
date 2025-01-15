package com.wizzhrms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.ProjectsDto;
import com.wizzhrms.entity.Projects;

@Mapper
@Service
public interface ProjectsMapper {

	ProjectsMapper MAPPER = Mappers.getMapper(ProjectsMapper.class);
			Projects mapDtoToEntity(ProjectsDto projects);
			ProjectsDto mapEntityToDto(Projects project);
	
}
