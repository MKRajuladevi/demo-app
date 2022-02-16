package com.rkmktb.demoapp.bootstrap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.rkmktb.demoapp.domain.security.Role;
import com.rkmktb.demoapp.domain.security.User;
import com.rkmktb.demoapp.services.RoleService;
import com.rkmktb.demoapp.services.UserService;

import java.util.List;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private UserService userService;
    private RoleService roleService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadRoles();
        assignUsersToDefaultRole();
        assignUsersToAdminRole();

    }

    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role ->{
            if(role.getRole().equalsIgnoreCase("USER")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }

        });
    }

    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("admin")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }


    private void loadRoles() {
        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
    }


    public void loadUsers() {
        User user1 = new User();
        user1.setUsername("mkiran");
        user1.setPassword("password");
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("password");
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("ukiran");
        user3.setPassword("password");
        userService.saveOrUpdate(user3);
    }
}
