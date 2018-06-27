package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.Comment;
import com.kingguanzhang.toptalk.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

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
