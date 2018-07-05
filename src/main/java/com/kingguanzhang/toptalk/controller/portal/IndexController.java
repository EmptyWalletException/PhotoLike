package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
    public String index(Model model, HttpServletRequest request) {

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
        
        // TODO 配合security从session中获取用户名再从数据库中获取用户信息返回给页面;并且当用户没有登录时不返回信息:
        //使用security在session中取出用户信息;
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if (null != securityContextImpl) {
            String username = securityContextImpl.getAuthentication().getName();
            if (null != username) {
                User user = new User();
                user.setAccount(username);
                ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");//因为id是Long类型,默认为0,需要忽略掉;
                Example<User> example = Example.of(user,exampleMatcher);
                User user1 = userService.findOne(example);
                //将用户信息写入session
                request.getSession().setAttribute("user", user1);
            }
        }
        return "/portal/index";
    }


}
