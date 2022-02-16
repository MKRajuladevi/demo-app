package com.rkmktb.demoapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rkmktb.demoapp.domain.security.Role;

@Repository(value = "roleRepository")
public interface RoleRepository extends CrudRepository<Role,Integer>{

}
