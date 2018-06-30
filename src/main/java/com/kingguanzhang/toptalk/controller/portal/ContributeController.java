package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.Category;
import com.kingguanzhang.toptalk.service.CategoryServiceImpl;
import com.kingguanzhang.toptalk.service.StoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

@Controller
public class ContributeController {
    
    @Autowired
    private CategoryServiceImpl categoryService;

    @RequestMapping("/portal/topicContribute")
    public String toTopicContributePage(Model model){
        Pageable pageable = new PageRequest(0,100,new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        model.addAttribute("categoryPage",categoryPage);
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
