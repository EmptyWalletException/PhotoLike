package com.kingguanzhang.toptalk.controller.portal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import com.kingguanzhang.toptalk.utils.DownloadZip;
import com.kingguanzhang.toptalk.utils.ImgUtil;
import com.kingguanzhang.toptalk.utils.PathUtil;
import com.kingguanzhang.toptalk.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class TopicController {


    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private UserFavoriteServiceImpl userFavoriteService;
    @Autowired
    private PraiseServiceImpl praiseService;



    /**
     * topic详情
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/topic/{topicId}")
    public String toTopicPage(HttpServletRequest request,Model model,  @RequestParam(value = "commentSort",defaultValue = "new")String commentSort,@PathVariable("topicId")String id,@RequestParam(value = "pn",defaultValue = "1")Integer pn){

        /**
         * 判断收藏状态,返回一个名为favStatus 的布尔值给页面;
         
         */
        Boolean favStrtus = false;
        if (null != request.getSession().getAttribute("user")){
            User user = (User) request.getSession().getAttribute("user");
            UserFavorite userFavorite = new UserFavorite();
            userFavorite.setUserId(user.getId());
            userFavorite.setTopicId(Long.parseLong(id));
            ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("essayId").withIgnorePaths("storyId");//所有Long 类型的属性默认值都是0不是null,所以要忽略;
            Example<UserFavorite> example = Example.of(userFavorite,exampleMatcher);
            Pageable pageable = new PageRequest(0,2);
            //查询是否收藏了;
            if (userFavoriteService.findAllByExample(example, pageable).hasContent()){//不使用findOne,因为当数据库有重复时findOne会抛异常;
                favStrtus = true;
            }
        }
        model.addAttribute("favStatus",favStrtus);



        /**
         * 获取指定id的topic;这里需要处理一下内容图片的地址字符串;
         */
        Topic topic = topicService.findById(Long.parseLong(id));
        if (null == topic){
            return "error";// TODO 需要完成错误页面 提示此稿件未找到:
        }

        if (null != topic.getContentImgsAddr()){
            String contentImgsAddr = topic.getContentImgsAddr();
            String[] imgAddrArry = contentImgsAddr.split(",");
            List<String> imgAddrList = new ArrayList<>();
            for (String imgAddr:imgAddrArry){
                imgAddrList.add(imgAddr);
            }
            topic.setImgAddrList(imgAddrList);
        }
        /**
         * 限制浏览者只能浏览状态为1的topic,除非浏览者是作者或管理员
         */
        if(1 == topic.getStatus() || null != request.getSession().getAttribute(("admin"))){
            model.addAttribute("topic",topic);
        }else if (null != request.getSession().getAttribute("user")){
            User user = (User) request.getSession().getAttribute("user");
            if (user.getId() == topic.getAuthor().getId()){
                model.addAttribute("topic",topic);
            }
        }else {
            return "error"; // TODO 需要完成错误页面 提示没有权限访问此稿件:
        }


        /**
         * 获取热专栏,只显示5个;
         */
        Pageable pageable3 = new PageRequest(0,5,  new Sort(Sort.Direction.DESC,"collectNumber"));
        Topic hotTopic = new Topic();
        hotTopic.setStatus(1);//查出通过审核的状态为展示的专辑;
        ExampleMatcher exampleMatcher3 = ExampleMatcher.matching().withIgnorePaths("id","collectNumber","commentNumber");//long类型的需要忽略;
        Example<Topic> example3 = Example.of(hotTopic,exampleMatcher3);
        Page<Topic> hotTopicPage = topicService.findAllByExample(example3,pageable3);
        model.addAttribute("hotTopicPage",hotTopicPage);

        /** 新方案中规范每个topic只对应一个分类,所以可以通过级联查询出分类;
         * 获取topic关联的category,只显示4个即可
        Pageable pageable4 = new PageRequest(0,4,  new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findByTopicId(Long.parseLong(id), pageable4);
        model.addAttribute("categoryPage",categoryPage);*/

        /**
         * 获取topic关联的父Comment,sql语句中已经排除了评论表中supcomment_id 不等于0的情况(即排除掉此评论为子评论时的情况);
         * 请求参数中的commentSort对应的值代表评论排序规则,new代表按最新排序,hot代表按最热排序(点赞数);
         */
        Pageable pageable5 ;
        if ("new" == commentSort) {
             pageable5 = new PageRequest(pn - 1, 10, new Sort(Sort.Direction.DESC, "creat_time"));
        }else {
             pageable5 = new PageRequest(pn - 1, 10, new Sort(Sort.Direction.DESC, "praise_number"));
        }
        Page<Comment> commentPage = commentService.findByTopicId(Long.parseLong(id), pageable5);
        //同时将排序状态返回,方便页面渲染翻页链接:
        model.addAttribute("commentSort",commentSort);
        model.addAttribute("commentPage",commentPage);

        /**
         * 判断当前取出的评论是否被用户点赞,返回一个记录当前页被收藏的评论Id的拼接字符串
         */
        String praiseCommentIds = "";
        if (null != request.getSession().getAttribute("user")){
            User user = (User) request.getSession().getAttribute("user");
            Praise praise = new Praise();
            praise.setUserId(user.getId());
            for(Comment temp:commentPage.getContent()){
                praise.setCommentId(temp.getId());
                ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("topicId").withIgnorePaths("storyId").withIgnorePaths("essayId").withIgnorePaths("eventId");
                Example<Praise> example = Example.of(praise,exampleMatcher);
                Pageable pageable1 = new PageRequest(0,2);
                if (praiseService.findAllByExample(example,pageable1).hasContent()){
                    praiseCommentIds = praiseCommentIds+temp.getId() + ",";
                }
            }
        }
        if ("" != praiseCommentIds){
            praiseCommentIds = praiseCommentIds.substring(0,praiseCommentIds.lastIndexOf(","));
        }
        model.addAttribute("praiseCommentIds",praiseCommentIds);

        /**
         * 获取所有的分类用于在页面生成修改分类的下拉选择框;
         */
        Pageable categoryPageable = new PageRequest(0,100,  new Sort(Sort.Direction.DESC,"rank"));
        Page<Category> categoryPage = categoryService.findAll(categoryPageable);
        model.addAttribute("categoryPage",categoryPage);

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
        Pageable pageable = new PageRequest(0,100,  new Sort(Sort.Direction.DESC,"rank"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        model.addAttribute("categoryPage",categoryPage);


        /**
         * 获取热门专栏
         */
        Pageable pageable3 = new PageRequest(0,5,  new Sort(Sort.Direction.DESC,"collectNumber"));
        Topic hotTopic = new Topic();
        hotTopic.setStatus(1);//查出通过审核的状态为展示的专辑;
        ExampleMatcher exampleMatcher3 = ExampleMatcher.matching().withIgnorePaths("id","collectNumber","commentNumber");//long类型的需要忽略;
        Example<Topic> example3 = Example.of(hotTopic,exampleMatcher3);
        Page<Topic> hotTopicPage = topicService.findAllByExample(example3,pageable3);
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
            Pageable pageable1 = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"creat_time"));
            Page<Topic> topicPage = topicService.findAllByCategoryIdAndStatus( categoryId,1, pageable1);//1 代表展示中的专辑;
            model.addAttribute("topicPage",topicPage);
        }else {
            /**
             * 获取所有的topic,用于在默认的没有选择分类的情况下;
             */
            Pageable pageable2 = new PageRequest(pn-1,10,  new Sort(Sort.Direction.DESC,"creatTime"));
            Topic allTopic = new Topic();
            allTopic.setStatus(1);//查出通过审核的状态为展示的专辑;
            ExampleMatcher exampleMatcher2 = ExampleMatcher.matching().withIgnorePaths("id","collectNumber","commentNumber");//long类型的需要忽略;
            Example<Topic> example2 = Example.of(allTopic,exampleMatcher2);
            Page<Topic> topicPage = topicService.findAllByExample(example2,pageable2);
            model.addAttribute("topicPage",topicPage);
        }

        return "portal/topic";
    }



}
