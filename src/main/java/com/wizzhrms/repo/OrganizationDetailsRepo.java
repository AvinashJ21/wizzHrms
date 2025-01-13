package com.wizzhrms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wizzhrms.entity.OrganizationDetails;

public interface OrganizationDetailsRepo extends JpaRepository<OrganizationDetails, Integer> {

}
