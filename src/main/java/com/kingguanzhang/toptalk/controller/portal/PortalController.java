package com.kingguanzhang.toptalk.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PortalController {

    @RequestMapping("/index")
    public String index(){
        return "/portal/index";
    }

    @RequestMapping("/column")
    public String column(){
        return "/portal/column";
    }

    @RequestMapping("/albumDetails")
    public String albumDetails(){
        return "/portal/albumDetails";
    }

    @RequestMapping("/essay")
    public String essay(){
        return "/portal/essay";
    }

    @RequestMapping("/event")
    public String event(){
        return "/portal/event";
    }

    @RequestMapping("/eventDetails")
    public String eventDetails(){
        return "/portal/eventDetails";
    }
    @RequestMapping("/story")
    public String story(){
        return "/portal/story";
    }

    @RequestMapping("/storyDetails")
    public String storyDetails(){
        return "/portal/storyDetails";
    }
}
