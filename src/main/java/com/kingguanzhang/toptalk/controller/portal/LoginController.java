package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    private String toLoginPage(){
        return "portal/login";
    }



}
