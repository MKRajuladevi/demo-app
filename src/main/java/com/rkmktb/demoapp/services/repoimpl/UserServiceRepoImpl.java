package com.rkmktb.demoapp.services.repoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rkmktb.demoapp.domain.security.User;
import com.rkmktb.demoapp.repositories.UserRepository;
import com.rkmktb.demoapp.services.UserService;
import com.rkmktb.demoapp.services.security.EncryptionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceRepoImpl implements UserService {

    private UserRepository userRepsoitory;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepsoitory = userRepository;
    }

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }


    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepsoitory.findAll().forEach(users::add); //fun with Java 8
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepsoitory.findById(id).get();
    }

    @Override
    public User saveOrUpdate(User domainObject) {

        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }
        return userRepsoitory.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        User user = userRepsoitory.findById(id).get();
        userRepsoitory.delete(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepsoitory.findByUsername(userName);
    }
}
