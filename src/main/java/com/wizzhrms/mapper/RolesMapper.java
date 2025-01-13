package com.wizzhrms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.wizzhrms.dto.RolesDto;
import com.wizzhrms.entity.Roles;

@Mapper
@Service
public interface RolesMapper {

	RolesMapper MAPPER = Mappers.getMapper(RolesMapper.class);
		Roles mapDtoToEntity(RolesDto rolesDto);
		RolesDto mapEntityToDto(Roles roles);

}
