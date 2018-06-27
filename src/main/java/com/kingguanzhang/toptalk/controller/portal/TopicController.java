package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.Category;
import com.kingguanzhang.toptalk.entity.Comment;
import com.kingguanzhang.toptalk.entity.Topic;
import com.kingguanzhang.toptalk.service.CategoryServiceImpl;
import com.kingguanzhang.toptalk.service.CommentServiceImpl;
import com.kingguanzhang.toptalk.service.TopicServiceImpl;
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

import javax.servlet.http.HttpServletRequest;

@Controller
public class TopicController {

    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private CommentServiceImpl commentService;


    /**
     * topic详情
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/topic/{topicId}")
    public String toTopicPage(Model model, @PathVariable("topicId")String id,@RequestParam(value = "pn",defaultValue = "1")Integer pn){

        /**
         * 获取指定id的topic;
         */
        Topic topic = topicService.findById(Long.parseLong(id));
        model.addAttribute("topic",topic);

        /**
         * 获取热专栏,只显示5个;
         */
        Pageable pageable3 = new PageRequest(0,5,  new Sort(Sort.Direction.DESC,"collectNumber"));
        Page<Topic> hotTopicPage = topicService.findAll(pageable3);
        model.addAttribute("hotTopicPage",hotTopicPage);

        /**
         * 获取topic关联的category,只显示4个即可
         */
        Pageable pageable4 = new PageRequest(0,4,  new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findByTopicId(Long.parseLong(id), pageable4);
        model.addAttribute("categoryPage",categoryPage);

        /**
         * 获取topic关联的父Comment,sql语句中已经排除了评论表中supcomment_id 不等于0的情况(即排除掉此评论为子评论时的情况);
         */
        Pageable pageable5 = new PageRequest(pn-1,10,  new Sort(Sort.Direction.DESC,"id"));
        Page<Comment> commentPage = commentService.findByTopicId(Long.parseLong(id), pageable5);
        model.addAttribute("commentPage",commentPage);

        return "portal/topicDetails";
    }




    /**
     * 点击分类后获取分类下的topic并分页排序;
     * @param model
     * @param request
     * @param pn
     * @return
     */
    @RequestMapping("/topic")
    public String toTopicPageByCategory(Model model, HttpServletRequest request, @RequestParam(name = "pn",defaultValue = "1") Integer pn){

        /**
         * 获取所有的分类显示在页面上方的分页导航;
         */
        Pageable pageable = new PageRequest(0,100,  new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        model.addAttribute("categoryPage",categoryPage);


        /**
         * 获取热门专栏
         */
        Pageable pageable3 = new PageRequest(0,5,  new Sort(Sort.Direction.DESC,"collectNumber"));
        Page<Topic> hotTopicPage = topicService.findAll(pageable3);
        model.addAttribute("hotTopicPage",hotTopicPage);

        /**
         * 判断,根据前端如果传入分类则根据分类查询,否则查询所有;
         */
        if (null != request.getParameter("category")){

            long categoryId = Long.parseLong(request.getParameter("category"));
            /**
             * 将categoryId传回页面方便接下来的分页跳转;
             */
            model.addAttribute("categoryId",categoryId);

            /**
             * 通过用户点击的分类获取topic
             */
            Pageable pageable1 = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"id"));
            Page<Topic> topicPage = topicService.findAllByCategoryId( categoryId, pageable1);
            model.addAttribute("topicPage",topicPage);
        }else {
            /**
             * 获取所有的topic,用于在默认的没有选择分类的情况下;
             */
            Pageable pageable2 = new PageRequest(pn-1,10,  new Sort(Sort.Direction.DESC,"id"));
            Page<Topic> topicPage = topicService.findAll(pageable2);
            model.addAttribute("topicPage",topicPage);
        }

        return "portal/topic";
    }

}
