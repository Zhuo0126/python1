package com.springboot.demo.impl;

import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.model.User;
import com.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Service
@Component
public class UserImpl implements UserService{
    @Autowired(required = false)
    UserMapper usermapper;

//    private userMapper usermapper;
    public User addMember(String username, String password){
        return usermapper.findByUserName(username,password);
    }
}
