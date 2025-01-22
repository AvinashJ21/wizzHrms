package com.wizzhrms.service;

import java.util.List;

import com.wizzhrms.dto.RolesDto;
import com.wizzhrms.entity.Roles;

public interface RolesService {

	Roles  addUpdRoles(RolesDto roles);

	List<RolesDto> getRoles();

	List<RolesDto> getActiveRoles();
}
