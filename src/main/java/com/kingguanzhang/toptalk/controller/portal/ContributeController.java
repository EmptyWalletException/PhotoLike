package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.service.StoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContributeController {
    
    @Autowired
    private StoryServiceImpl storyService;

    @RequestMapping("/portal/topicContribute")
    public String toTopicContributePage(){
        return "/portal/topicContribute";
    }

    @RequestMapping("/portal/storyContribute")
    public String toStoryContributePage(){
        return "/portal/storyContribute";
    }

    @RequestMapping("/portal/essayContribute")
    public String toEssayContributePage(){
        return "/portal/essayContribute";
    }

    @RequestMapping("/ue")
    public String toUEPage(){
        return "/portal/ue";
    }




}
