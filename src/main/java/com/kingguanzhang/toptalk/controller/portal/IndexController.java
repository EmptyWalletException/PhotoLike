package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private StoryServiceImpl storyService;

    @Autowired
    private TopicServiceImpl topicService;


    /**
     * 此映射不可缺少;
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/")
    private void toindex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/index");
    }

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request) {

        org.springframework.data.domain.Pageable pageable = new PageRequest(0,10,  new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        Page<City> cityPage = cityService.findAll(pageable);

        Story allStory = new Story();
        allStory.setStatus(1);//查出通过审核的状态为展示的故事;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id","collectNumber","commentNumber");//long类型的需要忽略;
        Example<Story> storyExample = Example.of(allStory,exampleMatcher);
        Page<Story> storyPage = storyService.findAllByExample(storyExample,pageable);

        Essay allEssay = new Essay();
        allEssay.setStatus(1);//查出通过审核的状态为展示的随笔;
        ExampleMatcher exampleMatcher2 = ExampleMatcher.matching().withIgnorePaths("id","collectNumber");//long类型的需要忽略;
        Example<Essay> essayExample = Example.of(allEssay,exampleMatcher2);
        Page<Essay> essayPage =essayService.findAllByExample(essayExample,pageable);

        Topic hotTopic = new Topic();
        hotTopic.setStatus(1);//查出通过审核的状态为展示的专辑;
        ExampleMatcher exampleMatcher3 = ExampleMatcher.matching().withIgnorePaths("id","collectNumber","commentNumber");//long类型的需要忽略;
        Example<Topic> topicExample = Example.of(hotTopic,exampleMatcher3);
        Page<Topic> topicPage = topicService.findAllByExample(topicExample,pageable);

        Event newestEvent = new Event();
        newestEvent.setStatus(1);//查出通过审核的状态为展示的活动;
        ExampleMatcher exampleMatcher4 = ExampleMatcher.matching().withIgnorePaths("id");//long类型的需要忽略;
        Example<Event> eventExample = Example.of(newestEvent,exampleMatcher4);
        Page<Event> eventPage = eventService.findAllByExample(eventExample,pageable);

        model.addAttribute("categoryList",categoryPage.getContent());
        model.addAttribute("cityList",cityPage.getContent());
        model.addAttribute("essayList",essayPage.getContent());
        model.addAttribute("eventList",eventPage.getContent());
        model.addAttribute("storyList",storyPage.getContent());
        model.addAttribute("topicList",topicPage.getContent());

        //下面别注释掉的是原来的当security登录成功后强制跳转到此controller往session写用户信息的方法,现在已经被自定义的LoginSuccessHandle代替;
        /*//使用security在session中取出用户信息;
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if (null != securityContextImpl) {
            String username = securityContextImpl.getAuthentication().getName();
            if (null != username) {
                User user = new User();
                user.setAccount(username);
                ExampleMatcher exampleMatcher5 = ExampleMatcher.matching().withIgnorePaths("id");//因为id是Long类型,默认为0,需要忽略掉;
                Example<User> example = Example.of(user,exampleMatcher5);
                User user1 = userService.findOne(example);
                //将用户信息写入session
                request.getSession().setAttribute("user", user1);
            }
        }*/
        return "portal/index";
    }


}
