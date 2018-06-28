package com.kingguanzhang.toptalk.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContributeController {


    @RequestMapping("/portal/contribute")
    public String toContributePage(){
        return "/portal/contribute";
    }
}
