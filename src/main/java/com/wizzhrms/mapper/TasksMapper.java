package com.wizzhrms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.TasksDto;
import com.wizzhrms.entity.Tasks;

@Mapper
@Service
public interface TasksMapper {

	TasksMapper MAPPER = Mappers.getMapper(TasksMapper.class);
	TasksDto mapEntityToDto(Tasks tasks);
	Tasks  mapDtoToEntity(TasksDto tasksDto);
}
