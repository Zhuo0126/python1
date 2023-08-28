package com.springboot.demo.service;

import com.springboot.demo.model.Login;
import com.springboot.demo.model.User;

import java.util.ArrayList;


public interface LoginService {

    public ArrayList<Login> select(String USERID);

}
