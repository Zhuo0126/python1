package com.springboot.demo.impl;

import com.springboot.demo.mapper.LoginMapper;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.model.Login;
import com.springboot.demo.model.User;
import com.springboot.demo.service.LoginService;
import com.springboot.demo.service.UserService;
import com.springboot.demo.util.SpringBeanFactoryUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Service
@Component
public class LoginImpl implements LoginService {
    private LoginMapper loginmapper = SpringBeanFactoryUtil.getBean(LoginMapper.class);

    public ArrayList<Login> select(String UserID){
        return loginmapper.select(UserID);
    }
}
