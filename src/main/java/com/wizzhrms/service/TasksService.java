package com.wizzhrms.service;

import java.util.List;

import com.wizzhrms.dto.TasksDto;
import com.wizzhrms.entity.Tasks;

public interface TasksService {

	List<TasksDto> getTasks();

	Tasks saveUpdTask(TasksDto taskDto);
	
	List<TasksDto> getActiveTasks();

}
