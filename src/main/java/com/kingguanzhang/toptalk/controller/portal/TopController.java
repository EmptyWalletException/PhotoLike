package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.Category;
import com.kingguanzhang.toptalk.entity.Topic;
import com.kingguanzhang.toptalk.service.CategoryServiceImpl;
import com.kingguanzhang.toptalk.service.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopController {

    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private TopicServiceImpl topicService;

    @RequestMapping("/topic")
    public String toTopicPage(Model model, @RequestParam(name = "pn",defaultValue = "1") Integer pn){
        /**
         * 获取所有的分类显示在页面上方的分页导航;
         */
        Pageable pageable = new PageRequest(0,100,  new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        model.addAttribute("categoryPage",categoryPage);

        /**
         * 获取所有的topic,用于在默认的没有选择分类的情况下;
         */
        Pageable pageable2 = new PageRequest(pn-1,10,  new Sort(Sort.Direction.DESC,"id"));
        Page<Topic> topicPage = topicService.findAll(pageable2);
        model.addAttribute("topicPage",topicPage);

        return "portal/topic";
    }

    @RequestMapping("/topic/filtrate")
    public String toTopicPageByCategory(Model model,@RequestParam(value = "category",defaultValue = "1")Integer categoryId, @RequestParam(name = "pn",defaultValue = "1") Integer pn){
        /**
         * 获取所有的分类显示在页面上方的分页导航;
         */
        Pageable pageable = new PageRequest(pn-1,100,  new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        model.addAttribute("categoryPage",categoryPage);

        /**
         * 通过用户点击的分类获取topic
         */
        Pageable pageable1 = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"id"));
        Page<Topic> topicPage = topicService.findAllByCategoryId((long) categoryId, pageable1);
        model.addAttribute("topicPage",topicPage);
        return "portal/topic";
    }
}
