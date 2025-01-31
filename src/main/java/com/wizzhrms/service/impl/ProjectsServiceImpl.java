package com.wizzhrms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.ProjectsDto;
import com.wizzhrms.entity.Projects;
import com.wizzhrms.mapper.ProjectsMapper;
import com.wizzhrms.repo.ProjectsRepo;
import com.wizzhrms.service.ProjectService;

@Service
public class ProjectsServiceImpl implements ProjectService {

	@Autowired
	ProjectsRepo projectRepo;

	@Override
	public Projects addUpdProject(ProjectsDto projectsDto) {

		return projectRepo.save(ProjectsMapper.MAPPER.mapDtoToEntity(projectsDto));

	}

	@Override
	public List<ProjectsDto> getProjects() {

		List<Projects> lstData = projectRepo.findAll(Sort.by(Direction.DESC, "modifiedDate"));
		List<ProjectsDto> projectsData = new ArrayList<>();
		lstData.stream().forEach(item -> {
			ProjectsDto projectDto = ProjectsMapper.MAPPER.mapEntityToDto(item);
			projectsData.add(projectDto);
		});

		return projectsData;
	}
	
	
	@Override
	public List<ProjectsDto> getActiveProjects() {

		List<Projects> lstData = projectRepo.findByActiveTrue();
		List<ProjectsDto> projectsData = new ArrayList<>();
		lstData.stream().forEach(item -> {
			ProjectsDto projectDto = ProjectsMapper.MAPPER.mapEntityToDto(item);
			projectsData.add(projectDto);
		});

		return projectsData;
	}


	
	

}
