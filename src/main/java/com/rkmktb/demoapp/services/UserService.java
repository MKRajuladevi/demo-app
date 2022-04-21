package com.rkmktb.demoapp.services;

import com.rkmktb.demoapp.domain.security.User;

public interface UserService extends CRUDService<User> {

    User findByUserName(String userName);

}
