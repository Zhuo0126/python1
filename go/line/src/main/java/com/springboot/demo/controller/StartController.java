package com.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class StartController  {
    @GetMapping("/websocket")
    public String first() throws Exception {
        return "redirect:/websocket.html";
    }
}
