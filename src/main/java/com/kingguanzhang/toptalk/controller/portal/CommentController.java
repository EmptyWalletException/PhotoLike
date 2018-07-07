package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.Comment;
import com.kingguanzhang.toptalk.entity.CommentRelateEST;
import com.kingguanzhang.toptalk.entity.Topic;
import com.kingguanzhang.toptalk.entity.User;
import com.kingguanzhang.toptalk.service.CRESTServiceImpl;
import com.kingguanzhang.toptalk.service.CommentServiceImpl;
import com.kingguanzhang.toptalk.service.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private CRESTServiceImpl crestService;

    @Autowired
    private TopicServiceImpl topicService;

    /**
     * 评论专辑;
     * @param request
     * @param commentStr
     * @return
     */
    @RequestMapping(value = "/topic/comment/add",method = RequestMethod.POST)
    @ResponseBody
    private Msg topicCommentAdd(HttpServletRequest request, @RequestParam("comment")String commentStr,@RequestParam("topicId")String topicId){
        /**
         * 判断session中是否有user,没有则返回到错误页面或重新登录界面;
         */
        if (null == request.getSession().getAttribute("user")){
             return Msg.fail().setMsg("登录已超时,请重新登录后再评论!");
        }
        /**
         * 初始化Comment信息;
         */
        User user = (User) request.getSession().getAttribute("user");
        Comment comment1 = new Comment();
        comment1.setAuthor(user);
        comment1.setContent(commentStr);
        comment1.setCreatTime(new Date(System.currentTimeMillis()));
        //保存Comment并返回id;
        long commentId;
        try{
            commentId = commentService.saveAndFlush(comment1);
        }catch (Exception e){
            return Msg.fail().setMsg("保存评论失败");
        }

        /**
         * 设置专辑与评论关联;
         */
        CommentRelateEST commentRelateEST = new CommentRelateEST();
        commentRelateEST.setTopicId(Long.parseLong(topicId));
        commentRelateEST.setCommentId(commentId);
        //保存关联信息;
        try{
            crestService.save(commentRelateEST);
        }catch (Exception e){
            return Msg.fail().setMsg("评论关联文章失败");
        }

        /**
         * 将专辑里的评论数+1;
         */
        Topic topic = topicService.findById(Long.parseLong(topicId));
        long commentNumber = topic.getCommentNumber();
        topic.setCommentNumber(commentNumber+1);
        topicService.save(topic);

        return Msg.success().setMsg("评论成功!");

    }


    /**
     * ajax获取父评论下的子评论
     * @param supcommentIds
     * @param pn
     * @return
     */
    @RequestMapping(value = "/comment/json/subcomments",method = RequestMethod.POST)
    @ResponseBody
    public Msg getSubComment(@RequestParam("supcommentIds")String supcommentIds, @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        /**
         * 处理字符串参数
         */
        if (null == supcommentIds || "" == supcommentIds.trim()){
            return Msg.fail().setMsg("没有收到任何父评论id参数");
        }
        String[] split = supcommentIds.split(",");
        //定义一个map用于存放对应的subcommentPage, 键为父评论id,值为子评论的page;
        Map<Long,Page<Comment>> subcommentPageMap = new HashMap<>();

        Comment comment =new Comment();
        ExampleMatcher exampleMatcher;
        Example<Comment> example;
        Pageable pageable;
        Page<Comment> subCommentPage;
        //开始遍历,每遍历出一个id就查一次数据库
        for (String supcommentId:split){
            long id = Long.parseLong(supcommentId);
            /**
             * 创建查询对象并设置父类id
             */
            comment.setSupcommentId(id);
            //创建匹配器,设置匹配规则
            exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");//注意需要忽略id,因为id是long类型,默认值不是null而是0;
            //创建example对象,传入查询对象和匹配器
            example= Example.of(comment,exampleMatcher);

            pageable= new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"id"));
            subCommentPage = commentService.findAllByExample(example, pageable);

            /**
             * 如果没有查出数据则不往map里存,这样前台直接遍历map即可;
             */
            if (null != subCommentPage && subCommentPage.hasContent()){
                subcommentPageMap.put(id,subCommentPage);
            }
        }
        if (null == subcommentPageMap){
            return Msg.fail().setMsg("没有查询到任何子评论");
        }
        return Msg.success().add("subcommentPageMap",subcommentPageMap);

    }
}
