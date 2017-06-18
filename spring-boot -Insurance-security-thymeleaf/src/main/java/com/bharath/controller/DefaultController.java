package com.bharath.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String home1() {
        return "/index";
    }
    
       @RequestMapping("/auth")
        public String index() {
            return "/login";
        }

    @RequestMapping("/registration")
    public String user() {
        return "/registration";
    }

  

    @RequestMapping("/home")
    public String registration() {
        return "/home";
    }
    
    
    @RequestMapping("/login")
    public String login() {
        return "/login";
    }
       

    @RequestMapping("/admin")
    public String admin() {
        return "/admin";
    }

}
