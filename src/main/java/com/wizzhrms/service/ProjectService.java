package com.wizzhrms.service;

import java.util.List;

import com.wizzhrms.dto.ProjectsDto;
import com.wizzhrms.entity.Projects;

public interface ProjectService {

	Projects addUpdProject(ProjectsDto projectsDto);

	List<ProjectsDto> getProjects();
	
}
