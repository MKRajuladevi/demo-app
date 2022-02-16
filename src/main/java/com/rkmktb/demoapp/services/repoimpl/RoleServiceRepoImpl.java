package com.rkmktb.demoapp.services.repoimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rkmktb.demoapp.domain.security.Role;
import com.rkmktb.demoapp.repositories.RoleRepository;
import com.rkmktb.demoapp.services.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceRepoImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepsoitory(RoleRepository roleRepsoitory) {
        this.roleRepository = roleRepsoitory;
    }

    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add); //fun with Java 8
        return roles;
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return roleRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
