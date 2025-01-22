package com.wizzhrms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.RolesDto;
import com.wizzhrms.entity.Roles;
import com.wizzhrms.mapper.RolesMapper;
import com.wizzhrms.repo.RolesRepo;
import com.wizzhrms.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	RolesRepo rolesRepo;

	@Override
	public Roles addUpdRoles(RolesDto roles) {

		return rolesRepo.save(RolesMapper.MAPPER.mapDtoToEntity(roles));

	}

	@Override
	public List<RolesDto> getRoles() {
		
			List<Roles> rolesAll = rolesRepo.findAll(Sort.by(Direction.DESC, "modifiedDate"));
			List<RolesDto> rolesDtoAll = new ArrayList<>();
			rolesAll.stream().forEach(role->{
				
				RolesDto roleDto = RolesMapper.MAPPER.mapEntityToDto(role);
				rolesDtoAll.add(roleDto);
				
			});
			
			return rolesDtoAll;
	}
	
	@Override
	public List<RolesDto> getActiveRoles() {
		
			List<Roles> rolesAll = rolesRepo.findByActiveTrue();
			List<RolesDto> rolesDtoAll = new ArrayList<>();
			rolesAll.stream().forEach(role->{
				
				RolesDto roleDto = RolesMapper.MAPPER.mapEntityToDto(role);
				rolesDtoAll.add(roleDto);
				
			});
			
			return rolesDtoAll;
	}

}
