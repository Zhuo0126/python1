package com.springboot.demo.controller;

import com.springboot.demo.model.ApiResponse;
import com.springboot.demo.model.User;
import com.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired(required = false)
    private UserService userService;

    private static ApplicationContext applicationContext;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {
        String username = loginData.getUserName();
        String password = loginData.getPassword();

        User user =userService.addMember(username,password);

        if(user != null){
            ApiResponse response= new ApiResponse(true,"登錄成功");
            return ResponseEntity.ok().body(response);
        }else{
             return  null;
        }
//        if ("admin".equals(username) && "password".equals(password)) {
//            ApiResponse response= new ApiResponse(true,"登錄成功");
//            return ResponseEntity.ok().body(response);
//        } else {
//            return null; // 登錄失敗返回null
//        }
    }

}
