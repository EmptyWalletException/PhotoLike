package com.kingguanzhang.toptalk.favorite.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingguanzhang.toptalk.common.entity.Msg;
import com.kingguanzhang.toptalk.essay.entity.Essay;
import com.kingguanzhang.toptalk.essay.service.EssayServiceImpl;
import com.kingguanzhang.toptalk.favorite.entity.UserFavorite;
import com.kingguanzhang.toptalk.favorite.service.UserFavoriteServiceImpl;
import com.kingguanzhang.toptalk.story.entity.Story;
import com.kingguanzhang.toptalk.story.service.StoryServiceImpl;
import com.kingguanzhang.toptalk.topic.entity.Topic;
import com.kingguanzhang.toptalk.topic.service.TopicServiceImpl;
import com.kingguanzhang.toptalk.user.entity.User;
import com.kingguanzhang.toptalk.user.service.UserServiceImpl;

@Controller
public class UserFavoriteController {

    @Autowired
    private UserFavoriteServiceImpl userFavoriteService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private TopicServiceImpl topicService;


    /**
     * 取消收藏;
     * @param request
     * @return
     */
    @RequestMapping(value = "/json/user/unlike",method = RequestMethod.POST)
    @ResponseBody
    private Msg unlike(HttpServletRequest request, @RequestParam("plate")String plateAndId){
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");
        /**
         * 判断操作的页面板块是专辑还是故事,将其收藏数-1,前端传过来的模板是"板块名.id",例如"topic.1";
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));
        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                long topicCollectNumber = topic.getCollectNumber();
                topic.setCollectNumber(topicCollectNumber-1);
                try {
                    userFavoriteService.deleteFavoriteTopic(user.getId(),id);
                    topicService.saveAndFlush(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("更新收藏总数时出现异常!");
                }

                break;
            case "story":
                Story story = storyService.findById(id);
                long storyCollectNumber = story.getCollectNumber();
                story.setCollectNumber(storyCollectNumber-1);
                try{
                    userFavoriteService.deleteFavoriteStory(user.getId(),id);
                    storyService.saveAndFlush(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("更新收藏总数时出现异常!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                long essayCollectNumber = essay.getCollectNumber();
                essay.setCollectNumber(essayCollectNumber-1);
                try{
                    userFavoriteService.deleteFavoriteEssay(user.getId(),id);
                    essayService.saveAndFlush(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("更新收藏总数时出现异常!");
                }
                break;
        }
        return Msg.success().setMsg("取消收藏成功");
    }

    /**
     * 收藏;
     * @param request
     * @return
     */
    @RequestMapping(value = "/json/user/like",method = RequestMethod.POST)
    @ResponseBody
    private Msg like(HttpServletRequest request,@RequestParam("plate")String plateAndId){
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        /**
         * 初始化信息;
         */
        User user = (User) request.getSession().getAttribute("user");
        UserFavorite userFavorite = new UserFavorite();
        userFavorite.setUserId(user.getId());
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));
        Pageable pageable = new PageRequest(0,10);

        /**
         * 判断操作的页面板块是专辑还是故事,将其收藏数+1,前端传过来的模板是"板块名.id",例如"topic.1";
         */
        switch (plateName){
            case "topic":
                /**
                 * 判断是否已经收藏过
                 */
                userFavorite.setTopicId(id);
                ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("essayId").withIgnorePaths("storyId");//所有Long 类型的属性默认值都是0不是null,所以要忽略;
                Example<UserFavorite> example = Example.of(userFavorite,exampleMatcher);
                if (userFavoriteService.findAllByExample(example, pageable).hasContent()){
                    return Msg.success().setMsg("已经收藏过");
                }
                Topic topic = topicService.findById(id);
                long topicCollectNumber = topic.getCollectNumber();
                topic.setCollectNumber(topicCollectNumber+1);
                userFavorite.setTopicId(id);
                try{
                    topicService.saveAndFlush(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("更新收藏总数时出现异常!");
                }
                break;
            case "story":
                /**
                 * 判断是否已经收藏过
                 */
                userFavorite.setStoryId(id);
                ExampleMatcher exampleMatcher2 = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("essayId").withIgnorePaths("topicId");//所有Long 类型的属性默认值都是0不是null,所以要忽略;
                Example<UserFavorite> example2 = Example.of(userFavorite,exampleMatcher2);
                if (userFavoriteService.findAllByExample(example2, pageable).hasContent()){
                    return Msg.success().setMsg("已经收藏过");
                }
                Story story = storyService.findById(id);
                long storyCollectNumber = story.getCollectNumber();
                story.setCollectNumber(storyCollectNumber+1);
                userFavorite.setStoryId(id);
                try{
                    storyService.saveAndFlush(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("更新收藏总数时出现异常!");
                }
                break;
            case "essay":
                /**
                 * 判断是否已经收藏过
                 */
                userFavorite.setEssayId(id);
                ExampleMatcher exampleMatcher3 = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("topicId").withIgnorePaths("storyId");//所有Long 类型的属性默认值都是0不是null,所以要忽略;
                Example<UserFavorite> example3 = Example.of(userFavorite,exampleMatcher3);
                if (userFavoriteService.findAllByExample(example3, pageable).hasContent()){
                    return Msg.success().setMsg("已经收藏过");
                }
                Essay essay = essayService.findById(id);
                long essayCollectNumber = essay.getCollectNumber();
                essay.setCollectNumber(essayCollectNumber+1);
                userFavorite.setEssayId(id);
                try{
                    essayService.saveAndFlush(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("更新收藏总数时出现异常!");
                }
                break;
        }
        try {
            userFavoriteService.save(userFavorite);
        }catch (Exception e){
            return Msg.fail().setMsg("更新收藏总数时出现异常!");
        }
        return Msg.success().setMsg("收藏成功");
    }



    /**
     * 查看用户收藏的story,分页并排序;
     * @param model
     * @param userId
     * @param pn
     * @return
     */
    @RequestMapping("/user/favoriteStory")
    public String toUserFavoriteStoryPage(HttpServletRequest request,Model model,@RequestParam("userId")Long userId, @RequestParam(value = "pn",defaultValue = "1")Integer pn){

        /**
         * 检查是否是登录用户
         */
        if (null == request.getSession().getAttribute("user")){
            model.addAttribute("errorMsg","请先登录再执行操作!");
            return "error/promptMessage";
        }
        User user1 = (User)request.getSession().getAttribute("user");
        if ( !user1.getId().equals(userId)){
            model.addAttribute("errorMsg","暂时不开放查看其他用户信息!");
            return "error/promptMessage";
        }

        /**
         * 查询用户信息;
         */
        User user = userService.findById(userId);
        model.addAttribute(user);

        /**
         * 查询用户收藏的story
         */
        Pageable pageable = new PageRequest(pn-1,10);
        Page<Story> storyPage = userFavoriteService.findFavoriteStory(userId, pageable);
        model.addAttribute("storyPage",storyPage);

        return "user/favoriteStory";
    }

    /**
     * 查看用户收藏的topic,分页并排序;
     * @param model
     * @param userId
     * @param pn
     * @return
     */
    @RequestMapping("/user/favoriteTopic")
    public String toUserFavoriteTopicPage(Model model,@RequestParam("userId")Long userId, @RequestParam(value = "pn",defaultValue = "1")Integer pn){

        /**
         * 查询用户信息;
         */
        User user = userService.findById(userId);
        model.addAttribute(user);

        /**
         * 查询用户收藏的topic
         */
        Pageable pageable = new PageRequest(pn-1,9);
        Page<Topic> topicPage = userFavoriteService.findFavoriteTopic(userId, pageable);
        model.addAttribute("topicPage",topicPage);

        return "user/favoriteTopic";
    }

    /**
     * 查看用户收藏的essay,分页并排序;
     * @param model
     * @param userId
     * @param pn
     * @return
     */
    @RequestMapping("/user/favoriteEssay")
    public String toUserFavoriteEssayPage(Model model,@RequestParam("userId")Long userId, @RequestParam(value = "pn",defaultValue = "1")Integer pn){

        /**
         * 查询用户信息;
         */
        User user = userService.findById(userId);
        model.addAttribute(user);

        /**
         * 查询用户收藏的topic
         */
        Pageable pageable = new PageRequest(pn-1,9);
        Page<Essay> essayPage = userFavoriteService.findFavoriteEssay(userId, pageable);
        model.addAttribute("essayPage",essayPage);

        return "user/favoriteEssay";
    }
}
