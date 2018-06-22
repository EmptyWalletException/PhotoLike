package com.kingguanzhang.toptalk.crudTest;

import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudTest {
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
    private CommentServiceImpl commentService;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void save(){

        for (int i =0 ; i<15; i++){
            Topic topic = new Topic();
            topic.setTitle("测试标题"+i);
            topic.setCreatTime(new Date(System.currentTimeMillis()));
            User user = new User();
            user.setId((long) 1);
            topic.setAuthor(user);
            topic.setContent("测试正文"+i);
            topicService.save(topic);
        }

        for (int i =0 ; i<15; i++){
            Category category = new Category();
            category.setName("测试分类"+i);
            category.setRank(i+10);
            categoryService.save(category);
        }

    }

    @Test
    public void find(){
        org.springframework.data.domain.Pageable pageable = new PageRequest(0,10,  new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        Page<City> cityPage = cityService.findAll(pageable);
        Page<Essay> essayPage = essayService.findAll(pageable);
        Page<Event> eventPage = eventService.findAll(pageable);
        Page<Photo> photoPage = photoService.findAll(pageable);
        Page<Story> storyPage = storyService.findAll(pageable);
        Page<Topic> topicPage = topicService.findAll(pageable);
        Page<User> userPage = userService.findAll(pageable);
    }

}
