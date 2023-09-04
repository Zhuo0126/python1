package com.springboot.demo.controller;

import com.springboot.demo.base.base;
import com.springboot.demo.model.Login;
import com.springboot.demo.model.User;
import com.springboot.demo.service.LoginService;
import com.springboot.demo.service.QuartzService;
import com.springboot.demo.service.RSA;
import com.springboot.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/quartz")
public class QuartzController extends base {
    private final QuartzService quartzService;

    @Autowired
    public QuartzController(QuartzService quartzService) {
        this.quartzService = quartzService;
    }

    @GetMapping("/schedule")
    public String scheduleJob() {
        try {
            quartzService.scheduleJob();
            return "Job scheduled successfully!";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "Failed to schedule job: " + e.getMessage();
        }
    }
}




