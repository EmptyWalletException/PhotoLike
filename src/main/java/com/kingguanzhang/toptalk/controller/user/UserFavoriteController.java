package com.kingguanzhang.toptalk.controller.user;

import com.kingguanzhang.toptalk.entity.Essay;
import com.kingguanzhang.toptalk.entity.Story;
import com.kingguanzhang.toptalk.entity.Topic;
import com.kingguanzhang.toptalk.entity.User;
import com.kingguanzhang.toptalk.service.UserFavoriteServiceImpl;
import com.kingguanzhang.toptalk.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserFavoriteController {

    @Autowired
    private UserFavoriteServiceImpl userFavoriteService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * 查看用户收藏的story,分页并排序;
     * @param model
     * @param userId
     * @param pn
     * @return
     */
    @RequestMapping("/user/favoriteStory")
    public String toUserFavoriteStoryPage(Model model,@RequestParam("userId")Long userId, @RequestParam(value = "pn",defaultValue = "1")Integer pn){

        /**
         * 查询用户信息;
         */
        User user = userService.findById(userId);
        model.addAttribute(user);

        /**
         * 查询用户收藏的story
         */
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"id"));
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
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"id"));
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
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"id"));
        Page<Essay> essayPage = userFavoriteService.findFavoriteEssay(userId, pageable);
        model.addAttribute("essayPage",essayPage);

        return "user/favoriteEssay";
    }
}
