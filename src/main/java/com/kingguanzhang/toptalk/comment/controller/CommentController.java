package com.kingguanzhang.toptalk.comment.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingguanzhang.toptalk.comment.entity.Comment;
import com.kingguanzhang.toptalk.comment.entity.CommentRelateEST;
import com.kingguanzhang.toptalk.comment.service.CRESTServiceImpl;
import com.kingguanzhang.toptalk.comment.service.CommentServiceImpl;
import com.kingguanzhang.toptalk.common.entity.Msg;
import com.kingguanzhang.toptalk.praiser.entity.Praise;
import com.kingguanzhang.toptalk.praiser.service.PraiseServiceImpl;
import com.kingguanzhang.toptalk.story.entity.Story;
import com.kingguanzhang.toptalk.story.service.StoryServiceImpl;
import com.kingguanzhang.toptalk.topic.entity.Topic;
import com.kingguanzhang.toptalk.topic.service.TopicServiceImpl;
import com.kingguanzhang.toptalk.user.entity.User;

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private CRESTServiceImpl crestService;

    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private PraiseServiceImpl praiseService;
    private Logger logger = LoggerFactory.getLogger(CommentController.class);


    /**
     * 管理员删除评论;
     * @param request
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/admin/comment/delete",method = RequestMethod.POST)
    @ResponseBody
    private Msg deleteComment(HttpServletRequest request,@RequestParam("commentId")String commentId,@RequestParam("plate")String plateAndId){
        /**
         * 判断securite中是否有admin角色,没有则返回到错误页面或重新登录界面;
         */
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("管理员未登录或登录已超时,请重新登录后再操作!");
        }


        /**
         * 删除评论,同时记住要更新稿件总评论数;
         */
        Long supcommentId = Long.parseLong(commentId);
        int rowsNumber = 0;//记录总共删除了多少子评论
        try{
            commentService.delete(supcommentId);//删除当前评论
            rowsNumber = commentService.deleteSubcomment(supcommentId);//删除它的子评论,如果有的话;
        }catch (Exception e){
        	if (logger.isErrorEnabled()) {
        		logger.error("删除当前评论和其子评论时出现异常,当前评论id:" + supcommentId+ " 异常信息:" + e.getMessage());
			}
            return Msg.fail().setMsg("操作失败,请尝试刷新后重试");
        }

        /**
         * 判断操作的页面板块是专辑还是故事,将其评论数减少相应数量,前端传过来的模板是"板块名.id",例如"topic.1";
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));
        switch (plateName){
            case "topic":
                crestService.deleteCommentRelateTopic(supcommentId,id);//这里子评论其实没有删除,但是不影响关联查询,关联查询只会查出父评论,删除父评论的关联关系即可;
                Topic topic = topicService.findById(id);
                long topicCommentNumber = topic.getCommentNumber();
                topic.setCommentNumber(topicCommentNumber-rowsNumber-1);//rowsNumber是子评论数量,再减去删除的父评论数量1;
                try {
                    topicService.save(topic);
                }catch (Exception e){
                	if (logger.isErrorEnabled()) {
                		logger.error("更新当前topic稿件信息时出现异常,当前稿件id: " + id+ " 异常信息:" + e.getMessage());
        			}
                    return Msg.fail().setMsg("更新评论总数时出现异常!");
                }
                break;
            case "story":
                crestService.deleteCommentRelateStory(supcommentId,id);
                Story story = storyService.findById(id);
                long storyCommentNumber = story.getCommentNumber();
                story.setCommentNumber(storyCommentNumber-rowsNumber-1);
                try{
                    storyService.save(story);
                }catch (Exception e){
                	if (logger.isErrorEnabled()) {
                		logger.error("更新当前topic稿件评论总数时出现异常,当前稿件id: " + id+ " 异常信息:" + e.getMessage());
        			}
                    return Msg.fail().setMsg("更新评论总数时出现异常!");
                }
                break;
        }
        return Msg.success().setMsg("删除评论成功!");
    }


    /**
     * ajax给评论点赞操作;
     * @param request
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/comment/json/praise",method = RequestMethod.POST)
    @ResponseBody
    private Msg praiseComment(HttpServletRequest request,@RequestParam("commentId")String commentId){
        /**
         * 判断session中是否有user,没有则返回到错误页面或重新登录界面;
         */
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("用户未登录或登录已超时,请重新登录后再评论!");
        }

        /**
         * 判断是否是重复点赞;
         */
        User user = (User) request.getSession().getAttribute("user");
        Praise praise = new Praise();
        praise.setCommentId(Long.parseLong(commentId));
        praise.setUserId(user.getId());
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("topicId").withIgnorePaths("id").withIgnorePaths("essayId").withIgnorePaths("storyId").withIgnorePaths("eventId");
        Example<Praise> example = Example.of(praise,exampleMatcher);
        Pageable pageable = new PageRequest(0,2);//只需要查出一个就能判断是重复的;
        Page<Praise> allByExample = praiseService.findAllByExample(example, pageable);
        if (allByExample.hasContent()){
            return Msg.success().setMsg("您已经赞过了");
        }
        /**
         * 如果没有重复点赞,则保存此次操作;
         */
        try{
            praiseService.save(praise);
        }catch (Exception e){
        	if (logger.isErrorEnabled()) {
        		logger.error("保存点赞信息时出现异常,异常信息:" + e.getMessage());
			}
            return Msg.fail().setMsg("操作失败,请尝试刷新后重试");
        }

        /**
         * 查询Comment信息并将点赞数+1;
         */
        try {
            Comment comment = commentService.findById(Long.parseLong(commentId));
            long praiseNumber = comment.getPraiseNumber();
            comment.setPraiseNumber(praiseNumber + 1);
            commentService.save(comment);
        }catch (Exception e){
        	if (logger.isErrorEnabled()) {
        		logger.error("更新当前评论点赞总数时发生异常,当前评论id: " + commentId+ "异常信息:" + e.getMessage());
			}
            return Msg.fail().setMsg("更新点赞总数时发生异常");
        }
        return Msg.success().setMsg("点赞成功");
    }

    /**
     * ajax取消点赞操作;
     * @param request
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/comment/json/cancelPraise",method = RequestMethod.POST)
    @ResponseBody
    private Msg cancelPraiseComment(HttpServletRequest request,@RequestParam("commentId")String commentId){
        /**
         * 判断session中是否有user,没有则返回到错误页面或重新登录界面;
         */
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("用户未登录或登录已超时,请重新登录后再评论!");
        }

        /**
         * 删除数据库中的点赞记录;
         */
        User user = (User) request.getSession().getAttribute("user");
        try {
            praiseService.deletePraiseComment(user.getId(),Long.parseLong(commentId));
        }catch (Exception e){
        	if (logger.isErrorEnabled()) {
        		logger.error("删除当前评论的点赞信息时发生异常,当前评论id: " + commentId+ "异常信息:" + e.getMessage());
			}
            return Msg.fail().setMsg("操作失败,刷新后再重试");
        }

        /**
         * 查询Comment信息并将点赞数-1;
         */
        try {
            Comment comment = commentService.findById(Long.parseLong(commentId));
            long praiseNumber = comment.getPraiseNumber();
            comment.setPraiseNumber(praiseNumber -1);
            commentService.save(comment);
        }catch (Exception e){
        	if (logger.isErrorEnabled()) {
        		logger.error("更新当前评论的点赞总数时发生异常,当前评论id: " + commentId+ "异常信息:" + e.getMessage());
			}
            return Msg.fail().setMsg("更新点赞总数时发生异常");
        }
        return Msg.success().setMsg("取消点赞成功");
    }


    /**
     * ajax回复父评论;
     * @param request
     * @return
     */
    @RequestMapping(value = "/comment/json/subcomment/add",method = RequestMethod.POST)
    @ResponseBody
    private Msg subcommentAdd(HttpServletRequest request, @RequestParam("subcomment")String subcomment,@RequestParam("supcommentId")String supcommentId,@RequestParam("plateAndId")String plateAndId){
        /**
         * 判断session中是否有user,没有则返回到错误页面或重新登录界面;
         */
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("用户未登录或登录已超时,请重新登录后再评论!");
        }
        /**
         * 初始化Comment信息;
         */
        User user = (User) request.getSession().getAttribute("user");
        Comment comment1 = new Comment();
        comment1.setAuthor(user);
        comment1.setContent(subcomment);
        comment1.setCreatTime(new Date(System.currentTimeMillis()));
        comment1.setSupcommentId(Long.parseLong(supcommentId));
        //保存Comment
        try{
           commentService.save(comment1);
        }catch (Exception e){
        	if (logger.isErrorEnabled()) {
        		logger.error("保存子评论时发生异常,当前父评论id: " + supcommentId+ "异常信息:" + e.getMessage());
			}
            return Msg.fail().setMsg("保存评论失败");
        }

        /**
         * 判断操作的页面板块是专辑还是故事,将其评论数+1,前端传过来的模板是"板块名.id",例如"topic.1";
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        String id = plateAndId.substring(plateAndId.indexOf(".")+1);
        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(Long.parseLong(id));
                long topicCommentNumber = topic.getCommentNumber();
                topic.setCommentNumber(topicCommentNumber+1);
                try {
                    topicService.save(topic);
                }catch (Exception e){
                	if (logger.isErrorEnabled()) {
                		logger.error("更新评论总数时发生异常,当前稿件板块: "+plateName+" 当前稿件id: " + supcommentId+ "异常信息:" + e.getMessage());
        			}
                    return Msg.fail().setMsg("更新评论总数时出现异常!");
                }
                break;
            case "story":
                Story story = storyService.findById(Long.parseLong(id));
                long storyCommentNumber = story.getCommentNumber();
                story.setCommentNumber(storyCommentNumber+1);
                try{
                    storyService.save(story);
                }catch (Exception e){
                	if (logger.isErrorEnabled()) {
                		logger.error("更新评论总数时发生异常,当前稿件板块: "+plateName+" 当前稿件id: " + supcommentId+ "异常信息:" + e.getMessage());
        			}
                    return Msg.fail().setMsg("更新评论总数时出现异常!");
                }
                break;
        }
        return Msg.success().setMsg("评论成功!");

    }

    /**
     * ajax发布评论;
     * @param request
     * @param commentStr
     * @return
     */
    @RequestMapping(value = "/comment/json/add",method = RequestMethod.POST)
    @ResponseBody
    private Msg commentAdd(HttpServletRequest request, @RequestParam("comment")String commentStr,@RequestParam("plateAndId")String plateAndId){
        /**
         * 判断session中是否有user,没有则返回到错误页面或重新登录界面;
         */
        if (null == request.getSession().getAttribute("user")){
             return Msg.fail().setCode(101).setMsg("用户未登录或登录已超时,请重新登录后再评论!");
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
        	if (logger.isErrorEnabled()) {
        		logger.error("保存评论时发生异常,异常信息:" + e.getMessage());
			}
            return Msg.fail().setMsg("保存评论失败");
        }

        /**
         * 判断操作的页面板块是专辑还是故事,设置关联并将其评论数+1,前端传过来的模板是"板块名.id",例如"topic.1";
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        String id = plateAndId.substring(plateAndId.indexOf(".")+1);
        /**
         * 初始化保存关联信息的实体类;
         */
        CommentRelateEST commentRelateEST = new CommentRelateEST();
        commentRelateEST.setCommentId(commentId);
        switch (plateName){
            case "topic":
                commentRelateEST.setTopicId(Long.parseLong(id));//设置关联
                Topic topic = topicService.findById(Long.parseLong(id));
                long topicCommentNumber = topic.getCommentNumber();
                topic.setCommentNumber(topicCommentNumber+1);//评论总数+1
                try {
                    topicService.save(topic);
                }catch (Exception e){
                	if (logger.isErrorEnabled()) {
                		logger.error("更新评论总数时发生异常,当前稿件板块: "+plateName+" 当前稿件id: " + id+ "异常信息:" + e.getMessage());
        			}
                    return Msg.fail().setMsg("更新评论总数时出现异常!");
                }
                break;
            case "story":
                commentRelateEST.setStoryId(Long.parseLong(id));//设置关联
                Story story = storyService.findById(Long.parseLong(id));
                long storyCommentNumber = story.getCommentNumber();
                story.setCommentNumber(storyCommentNumber+1);//评论总数+1
                try{
                    storyService.save(story);
                }catch (Exception e){
                	if (logger.isErrorEnabled()) {
                		logger.error("更新评论总数时发生异常,当前稿件板块: "+plateName+" 当前稿件id: " + id+ "异常信息:" + e.getMessage());
        			}
                    return Msg.fail().setMsg("更新评论总数时出现异常!");
                }
                break;
        }
        crestService.save(commentRelateEST);//保存关联关系实体类;

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
    public Msg getSubComment(HttpServletRequest request,@RequestParam("supcommentIds")String supcommentIds, @RequestParam(value = "pn",defaultValue = "1")Integer pn){

        /**
         * 处理字符串参数
         */
        if (null == supcommentIds || "".equals(supcommentIds.trim())){
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

        /**
         * 初始化查询点赞的模板,返回一个记录当前页被收藏的评论Id的拼接字符串
         */
        String praiseCommentIds = "";
        User user ;
        Praise praise = new Praise();
        if (null != request.getSession().getAttribute("user")){
            user = (User) request.getSession().getAttribute("user");
            praise.setUserId(user.getId());
        }


        //开始遍历,每遍历出一个id就查一次数据库
        for (String supcommentId:split){
            long id = Long.parseLong(supcommentId);
            /**
             * 创建查询对象并设置父类id
             */
            comment.setSupcommentId(id);
            //创建匹配器,设置匹配规则
            exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("praiseNumber");//注意需要忽略id,因为id是long类型,默认值不是null而是0;忽略掉点赞状态;
            //创建example对象,传入查询对象和匹配器
            example= Example.of(comment,exampleMatcher);

            pageable= new PageRequest(0,999999,new Sort(Sort.Direction.DESC,"creatTime"));
            subCommentPage = commentService.findAllByExample(example, pageable);

            /**
             * 如果没有查出数据则不往map里存,这样前台直接遍历map即可;
             */
            if (null != subCommentPage && subCommentPage.hasContent()){
                subcommentPageMap.put(id,subCommentPage);
                /**
                 * 如果查出,则继续查子评论是否被当前用户点赞;
                 */
                for (Comment subcomment:subCommentPage){
                    praise.setCommentId(subcomment.getId());
                    ExampleMatcher exampleMatcher2 = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("topicId").withIgnorePaths("storyId").withIgnorePaths("essayId").withIgnorePaths("eventId");
                    Example<Praise> example2 = Example.of(praise,exampleMatcher2);
                    Pageable pageable1 = new PageRequest(0,2);
                    if (praiseService.findAllByExample(example2,pageable1).hasContent()){
                        praiseCommentIds = praiseCommentIds+subcomment.getId()+ ",";
                    }
                }
            }
        }
       
        if ( !"".equals(praiseCommentIds) ){
            praiseCommentIds = praiseCommentIds.substring(0,praiseCommentIds.lastIndexOf(","));
        }

        return Msg.success().add("subcommentPageMap",subcommentPageMap).add("praiseCommentIds",praiseCommentIds);

    }
}
