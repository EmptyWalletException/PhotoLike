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

    @Autowired
    private CRESTServiceImpl crestService;


    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private UserFavoriteServiceImpl userFavoriteService;





    @Test
    public void save(){
        for (int i =30 ; i<60; i++){

           Date date= new Date(System.currentTimeMillis());

            City city = new City();
            city.setId(i);

            User user = new User();
            user.setId(i);

            Event event = new Event();
            event.setId(i);

            Story story = new Story();
            story.setId(i);

            Topic topic = new Topic();
            topic.setId(i);

            Category category = new Category();
            category.setId(i);

            Comment  comment= new Comment();
            comment.setId(i);

            Essay essay = new Essay();
            essay.setId(i);

            Message message = new Message();
            message.setId(i);


            CommentRelateEST commentRelateEST= new CommentRelateEST();
            commentRelateEST.setId(i);

            UserFavorite userFavorite = new UserFavorite();
            userFavorite.setId(i);

            user.setAccount("testuser"+i);
            user.setGender(1);
            user.setImgAddr("upload/user.jpg");
            user.setJoinTime(date);
            user.setLocation("艾泽拉斯");
            user.setNickname("测试用户"+i);
            user.setPassword("password"+i);
            user.setSignature("测试签名"+i);
//            userService.save(user);

            city.setName("城市"+i);
            city.setRank(i);
//            cityService.save(city);

            category.setName("分类"+i);
            category.setRank(i+10);
//            categoryService.save(category);

            message.setContent("测试消息正文"+i);
            message.setCreatTime(date);
//            messageService.save(message);

            essay.setAuthor(user);
            essay.setCollectNumber(i*100);
            essay.setContent("测试随笔内容"+i);
            essay.setTitle("测试随笔标题"+i);
            essay.setCreatTime(date);
            essay.setImgAddr("upload/1.img");
//            essayService.save(essay);

            event.setContent("测试活动正文"+i);
            event.setLocation("测试活动位置"+i);
            event.setMoney("$5");
            event.setName("线下聚会");
            event.setTime(date);
            event.setTheme("聚会");
            event.setCity(city);
//            eventService.save(event);


            story.setCreatTime(date);
            story.setTitle("测试标题"+i);
            story.setAuthor(user);
            story.setCollectNumber(i*100);
            story.setCommentNumber(i*20);
//            storyService.save(story);


            topic.setTitle("测试标题"+i);
            topic.setCreatTime(date);
            user.setId(1);
            topic.setAuthor(user);
            topic.setContent("测试正文"+i);
            topic.setCollectNumber(i*100);
            topic.setCommentNumber(i*20);
            topicService.save(topic);

            commentRelateEST.setCommentId(i);
            commentRelateEST.setTopicId(i);
//            crestService.save(commentRelateEST);


            comment.setContent("测试评论正文");
            comment.setCreatTime(date);
            comment.setSupcommentId(1);
            comment.setAuthor(user);
//            commentService.save(comment);

            userFavorite.setUserId(i);
            userFavorite.setTopicId(i);
//            userFavoriteService.save(userFavorite);
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
