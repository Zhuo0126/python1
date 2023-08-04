package com.springboot.demo.service;

import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired(required = false)
    UserMapper usermapper;
    public User addMember(String username, String password){
        return usermapper.findByUserName(username,password);
    }
}
