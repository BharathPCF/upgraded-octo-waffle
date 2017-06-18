package com.bharath.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bharath.model.security.Authority;
import com.bharath.model.security.AuthorityName;



@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Authority, Integer>{
	Authority findByName(AuthorityName name);

	
	
}
