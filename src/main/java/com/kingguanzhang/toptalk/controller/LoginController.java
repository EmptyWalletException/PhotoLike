package com.kingguanzhang.toptalk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    private String toLoginPage(){
        return "portal/login";
    }



}
