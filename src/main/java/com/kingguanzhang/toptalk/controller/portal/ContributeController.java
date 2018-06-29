package com.kingguanzhang.toptalk.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContributeController {


    @RequestMapping("/portal/topicContribute")
    public String toTopicContributePage(){
        return "/portal/topicContribute";
    }

    @RequestMapping("/portal/storyContribute")
    public String toStoryContributePage(){
        return "/portal/storyContribute";
    }
    @RequestMapping("/ue")
    public String toUEPage(){
        return "/portal/ue";
    }
}
