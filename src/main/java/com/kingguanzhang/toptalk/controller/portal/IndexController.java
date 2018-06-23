package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private EssayServiceImpl essayService;

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private PhotoServiceImpl photoService;

    @Autowired
    private StoryServiceImpl storyService;

    @Autowired
    private TopicServiceImpl topicService;

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/index")
    public String index(Model model) {

        org.springframework.data.domain.Pageable pageable = new PageRequest(0,10,  new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        Page<City> cityPage = cityService.findAll(pageable);
        Page<Essay> essayPage = essayService.findAll(pageable);
        Page<Event> eventPage = eventService.findAll(pageable);
        Page<Story> storyPage = storyService.findAll(pageable);
        Page<Topic> topicPage = topicService.findAll(pageable);
        model.addAttribute("categoryList",categoryPage.getContent());
        model.addAttribute("cityList",cityPage.getContent());
        model.addAttribute("essayList",essayPage.getContent());
        model.addAttribute("eventList",eventPage.getContent());
        model.addAttribute("storyList",storyPage.getContent());
        model.addAttribute("topicList",topicPage.getContent());
        return "/portal/index";
    }


}
