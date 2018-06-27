package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.Comment;
import com.kingguanzhang.toptalk.entity.Story;
import com.kingguanzhang.toptalk.repositories.CategoryRepository;
import com.kingguanzhang.toptalk.service.CommentServiceImpl;
import com.kingguanzhang.toptalk.service.StoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoryController {

    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private CommentServiceImpl commentService;

    /**
     * 获取所有故事,分页并排序;
     * @param model
     * @param pn
     * @return
     */
    @RequestMapping("/story")
    public String toStoryPage(Model model, @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        /**
         * 获取所有故事,分页并排序;
         */
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"id"));
        Page<Story> storyPage = storyService.findAll(pageable);
        model.addAttribute("storyPage",storyPage);

        /**
         * 获取5个最热故事,即收藏数最多的故事;
         */
        Pageable pageable2 = new PageRequest(0,5,new Sort(Sort.Direction.DESC,"collectNumber"));
        Page<Story> hotStoryPage = storyService.findAll(pageable2);
        model.addAttribute("hotStoryPage",hotStoryPage);

        return "/portal/story";
    }


    /**
     * 获取故事详情;
     * @param model
     * @param storyId
     * @return
     */
    @RequestMapping("/story/{storyId}")
    public String toStoryDetailsPage(Model model, @PathVariable("storyId")String storyId,@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        Story story = storyService.findById(Long.parseLong(storyId));
        model.addAttribute("story",story);


        /**
         * 获取topic关联的父Comment,sql语句中已经排除了评论表中supcomment_id 不等于0的情况(即排除掉此评论为子评论时的情况);
         */
        Pageable pageable5 = new PageRequest(pn-1,10,  new Sort(Sort.Direction.DESC,"id"));
        Page<Comment> commentPage = commentService.findByStoryId(Long.parseLong(storyId), pageable5);
        model.addAttribute("commentPage",commentPage);

        return "/portal/storyDetails";

    }
}
