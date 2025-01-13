package com.wizzhrms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wizzhrms.entity.OrganizationalEvents;

@Repository
public interface OrganizationalEventsRepo extends JpaRepository<OrganizationalEvents, Integer>{

}
