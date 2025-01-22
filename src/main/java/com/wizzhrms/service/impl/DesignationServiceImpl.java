package com.wizzhrms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.DesignationDto;
import com.wizzhrms.entity.Designation;
import com.wizzhrms.mapper.DesignationMapper;
import com.wizzhrms.repo.DesignationRepo;
import com.wizzhrms.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	DesignationRepo designationRepo;

	@Override
	public Designation addUpdDesignation(DesignationDto designationDto) {

		Designation designationEntity = DesignationMapper.MAPPER.mapDtoToEntity(designationDto);
		return designationRepo.save(designationEntity);

	}

	@Override
	public List<DesignationDto> getDesignations() {

		List<Designation> allDesignations = designationRepo.findAll(Sort.by(Direction.DESC, "modifiedDate"));
		List<DesignationDto> allDesignationsDto = new ArrayList<>();
		allDesignations.stream().forEach(item -> {
			DesignationDto designDto = DesignationMapper.MAPPER.mapEntityToDto(item);
			allDesignationsDto.add(designDto);
		});
		return allDesignationsDto;
	}

	@Override
	public List<DesignationDto> getActiveDesignations() {
		List<Designation> allDesignations = designationRepo.findByActiveTrue();
		List<DesignationDto> allDesignationsDto = new ArrayList<>();
		allDesignations.stream().forEach(item -> {
			DesignationDto designDto = DesignationMapper.MAPPER.mapEntityToDto(item);
			allDesignationsDto.add(designDto);
		});
		return allDesignationsDto;
	}
	
	

}
