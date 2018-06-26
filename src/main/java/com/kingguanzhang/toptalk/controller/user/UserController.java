package com.kingguanzhang.toptalk.controller.user;

import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CityServiceImpl cityService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private TopicServiceImpl topicService;

    /**
     * 查看编辑用户信息的页面;
     * @param model
     * @return
     */
    @RequestMapping("/user/editInfo")
    public String toUserEditPage(Model model){
        /**
         * 取出城市让用户修改居住城市;
         */
        Pageable pageable = new PageRequest(0,100,new Sort(Sort.Direction.ASC,"id"));
        Page<City> cityPage = cityService.findAll(pageable);
        model.addAttribute("cityPage",cityPage);

        /**
         * 取出用户信息;这里先写死user,以后改成用security的session里取用户账号;
         */
        User user = new User();
        user.setAccount("testuser");
        //注意这里,user.id是long类型,默认值是0而不是null,erexample里传过去不是null时就会开启匹配,所以需要设置忽略掉id属性;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("account",ExampleMatcher.GenericPropertyMatchers.caseSensitive()).withIgnorePaths("id").withIgnoreCase(false);
        Example<User> example = Example.of(user,exampleMatcher);
        user = userService.findOne(example);
        model.addAttribute("user",user);

        return "user/editInfo";
    }

    /**
     * 查看用户撰写的story页面;
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/user/story")
    public String toUserStoryPage(Model model, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam("userId")long userId){


        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"id"));
        Story story = new Story();
        User user = new User();
        user.setId(userId);
        story.setAuthor(user);;
        //注意这里,story里面有几个属性是long类型,默认值是0而不是null,erexample里传过去不是null时就会开启匹配,所以需要设置忽略掉id属性;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("collectNumber").withIgnorePaths("commentNumber");
        Example<Story> example = Example.of(story,exampleMatcher);
        Page<Story> storyPage = storyService.findAllByExample(example, pageable);
        model.addAttribute("storyPage",storyPage);

        /**
         * 返回用户信息以方便页面渲染时生成链接;
         */

        User author ;
        if (0 != storyPage.getContent().size()){ //如果topicPage里有返回值就直接从里面取,
            author=storyPage.getContent().get(0).getAuthor();
        }else {                         //否则从数据库中查
            author = userService.findById(userId);
        }
        model.addAttribute("user",author);
        return "user/userStory";
    }

    /**
     * 查看用户撰写的essay页面;
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/user/essay")
    public String toUserEssayPage(Model model, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam("userId")long userId){
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下,还有页面是9宫格板式,只查出9个即可
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"id"));
        Essay essay = new Essay();
        User user = new User();
        user.setId(userId);
        essay.setAuthor(user);;
        //注意这里,story里面有几个属性是long类型,默认值是0而不是null,erexample里传过去不是null时就会开启匹配,所以需要设置忽略掉id属性;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("collectNumber");
        Example<Essay> example = Example.of(essay,exampleMatcher);
        Page<Essay> essayPage = essayService.findAllByExample(example, pageable);
        model.addAttribute("essayPage",essayPage);

        /**
         * 返回用户信息以方便页面渲染时生成链接;
         */

        User author ;
        if (0 != essayPage.getContent().size()){ //如果topicPage里有返回值就直接从里面取,
            author=essayPage.getContent().get(0).getAuthor();
        }else {                         //否则从数据库中查
            author = userService.findById(userId);
        }
        model.addAttribute("user",author);

        return "user/userEssay";
    }

    /**
     * 查看用户撰写的topic页面;原先链接为"/user/topic",现在改为"/user"
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/user")
    public String toUserTopicPage(Model model, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam("userId")long userId){
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"id"));
        Topic topic = new Topic();
        User user = new User();
        user.setId(userId);
        topic.setAuthor(user);;
        //注意这里,story里面有几个属性是long类型,默认值是0而不是null,erexample里传过去不是null时就会开启匹配,所以需要设置忽略掉id属性;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("collectNumber").withIgnorePaths("commentNumber");
        Example<Topic> example = Example.of(topic,exampleMatcher);
        Page<Topic> topicPage = topicService.findAllByExample(example, pageable);
        model.addAttribute("topicPage",topicPage);

        /**
         * 返回用户信息以方便页面渲染时生成链接;
         */

        User author ;
        if (0 != topicPage.getContent().size()){ //如果topicPage里有返回值就直接从里面取,
            author=topicPage.getContent().get(0).getAuthor();
        }else {                         //否则从数据库中查
           author = userService.findById(userId);
        }
        model.addAttribute("user",author);

        return "user/userTopic";
    }



}
