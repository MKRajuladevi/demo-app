package com.rkmktb.demoapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rkmktb.demoapp.domain.security.User;

@Repository(value = "userRepository")
public interface UserRepository extends CrudRepository<User,Integer>{

	User findByUsername(String username);
}
