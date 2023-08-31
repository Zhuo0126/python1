package com.springboot.demo.controller;

import com.springboot.demo.base.LogData;
import com.springboot.demo.base.base;
import com.springboot.demo.model.ApiResponse;
import com.springboot.demo.model.Login;
import com.springboot.demo.model.User;
import com.springboot.demo.service.LoginService;
import com.springboot.demo.service.RSA;
import com.springboot.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class UserController extends base {
    @Autowired(required = false)
    private UserService userService;


    @Autowired(required = false)
    private LoginService loginService;

    @Autowired(required = false)
    private RSA rsa;

    private static ApplicationContext applicationContext;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {
        String username = loginData.getUserName();
        String password = loginData.getPassword();

        getLogData().setProgramName(this.getClass().getSimpleName());
        getLogData().setMessage(StringUtils.join("UserName is:",username));
        logMessage(getLogData());

        String realPassword = rsa.decrypt(password);
        User user = null;
        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            user = userService.addMember(username, realPassword);
        }
        if(user != null){
//            ApiResponse response= new ApiResponse(true,"登錄成功");
            System.out.println("成功");
            getLogData().setProgramName(this.getClass().getSimpleName());
            getLogData().setMessage("驗證成功");
            logMessage(Level.DEBUG,getLogData());

            //檢核成功後，取得menu資料
            String UserID ="";
            switch(user.getUserName()){
                case "admin":
                    UserID ="1";
                    break;
            }
            ArrayList<Login> login = loginService.select(UserID);

            if(login != null){
                return ResponseEntity.ok().body(login);
            }
        }else{
            System.out.println("失敗");
            getLogData().setProgramName(this.getClass().getSimpleName());
            getLogData().setMessage("驗證失敗");
            logMessage(Level.DEBUG,getLogData());
            return  null;
        }
//        if ("admin".equals(username) && "password".equals(password)) {
//            ApiResponse response= new ApiResponse(true,"登錄成功");
//            return ResponseEntity.ok().body(response);
//        } else {
//            return null; // 登錄失敗返回null
//        }
        return null;
    }

    @GetMapping("/pubkey")
    public String getPublicKey(){
        return rsa.getPublicKey();
    }

}
