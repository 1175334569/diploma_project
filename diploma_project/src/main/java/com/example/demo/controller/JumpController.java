package com.example.demo.controller;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class JumpController {
    @RequestMapping("/")
    public String findAll(){
        return "index";

    }
    @RequestMapping("/activation")
    public String activation(){
        return "activation";
    }
    @RequestMapping("/forget_email")
    public String forget_email(){
        return "forget_email";
    }
    @RequestMapping("/resetPassword")
    public String reset_password(){
        return "reset_password";
    }
    @RequestMapping("/recruit_detail")
    public String recruit_detail(){
        return "User/recruit_detail";
    }
    @RequestMapping("/Admin")
    public String Admin(){
        return "Admin/index";
    }
}
