package com.wizzhrms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wizzhrms.entity.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {

		List<Roles> findByActiveTrue();
}
