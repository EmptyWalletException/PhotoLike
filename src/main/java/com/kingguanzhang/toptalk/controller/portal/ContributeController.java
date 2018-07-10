package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.Category;
import com.kingguanzhang.toptalk.entity.City;
import com.kingguanzhang.toptalk.service.CategoryServiceImpl;
import com.kingguanzhang.toptalk.service.CityServiceImpl;
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
    @Autowired
    private CityServiceImpl cityService;

    @RequestMapping("/contribute/topicContribute")
    public String toTopicContributePage(Model model){
        Pageable pageable = new PageRequest(0,100,new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        model.addAttribute("categoryPage",categoryPage);
        return "/contribute/topicContribute";
    }

    @RequestMapping("/contribute/eventContribute")
    public String toEventContributePage(Model model){
        Pageable pageable = new PageRequest(0,200,new Sort(Sort.Direction.ASC,"rank"));
        Page<City> cityPage = cityService.findAll(pageable);
        model.addAttribute("cityPage",cityPage);
        return "/contribute/eventContribute";
    }

    @RequestMapping("/contribute/storyContribute")
    public String toStoryContributePage(){
        return "/contribute/storyContribute";
    }

    @RequestMapping("/contribute/essayContribute")
    public String toEssayContributePage(){
        return "/contribute/essayContribute";
    }

    @RequestMapping("/ue")
    public String toUEPage(){
        return "/portal/ue";
    }




}
