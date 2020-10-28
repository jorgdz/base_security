package com.github.com.jorgdz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.com.jorgdz.app.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{
	
	Privilege findByName(String name);
	
}
