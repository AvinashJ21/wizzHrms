package com.wizzhrms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.TasksDto;
import com.wizzhrms.entity.Tasks;
import com.wizzhrms.mapper.TasksMapper;
import com.wizzhrms.repo.TasksRepo;
import com.wizzhrms.service.TasksService;

@Service
public class TasksServiceImpl implements TasksService {

	@Autowired
	TasksRepo tasksRepo;

	@Override
	public List<TasksDto> getTasks() {

		List<Tasks> allTasks = tasksRepo.findAll(Sort.by(Direction.DESC, "modifiedDate"));
		List<TasksDto> tasksDtoList = new ArrayList<>();
		allTasks.forEach(item -> {

			TasksDto task = TasksMapper.MAPPER.mapEntityToDto(item);
			tasksDtoList.add(task);
		});

		return tasksDtoList;
	}

	@Override
	public Tasks saveUpdTask(TasksDto taskDto) {

		Tasks taskEntity = TasksMapper.MAPPER.mapDtoToEntity(taskDto);
		return tasksRepo.save(taskEntity);

	}
	
	@Override
	public List<TasksDto> getActiveTasks() {
		
		List<Tasks> lstData = tasksRepo.findByActiveTrue();
		List<TasksDto> tasksDtoData = new ArrayList<>();
		lstData.stream().forEach(item -> {
			TasksDto taskstDto = TasksMapper.MAPPER.mapEntityToDto(item);
			tasksDtoData.add(taskstDto);
		});

		return tasksDtoData;
		 
	}

}
