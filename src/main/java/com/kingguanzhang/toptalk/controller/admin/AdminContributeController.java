package com.kingguanzhang.toptalk.controller.admin;

import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import com.kingguanzhang.toptalk.utils.VerifyAuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdminContributeController {
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private PraiseServiceImpl praiseService;

    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private CityServiceImpl cityService;


    /**
     * 编辑专辑所属分类;
     * @param topicCategoryId
     * @param topicId
     * @return
     */
    @RequestMapping("/admin/topic/category/edit")
    @ResponseBody
    private Msg editTopicCategory(HttpServletRequest request,@RequestParam("topicCategoryId")String topicCategoryId,@RequestParam("topicId")String topicId){
        if (!VerifyAuthorityUtil.isAdmin(request)){
            return Msg.fail().setMsg("您没有权限执行此操作");
        }
        Topic topic = topicService.findById(Long.parseLong(topicId));
        Category category = categoryService.findById(Long.parseLong(topicCategoryId));
        topic.setCategory(category);
        try {
           topicService.save(topic);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().setMsg("更新信息失败");
        }
        return Msg.success().setMsg("更新信息成功");
    }

    /**
     * 将稿件从回收站恢复为待审核状态;
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/admin/contribute/recover",method = RequestMethod.POST)
    @ResponseBody
    private Msg recoverContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        if (!VerifyAuthorityUtil.isAdmin(request)){
            return Msg.fail().setMsg("您没有权限执行此操作");
        }
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断当前用户想操作的是否是自己的稿件,并且稿件此时的状态是否合理;
                if ( 4 != topic.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try {
                    topic.setStatus(0);
                    topicService.save(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "story":
                Story story = storyService.findById(id);
                if ( 4 != story.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    story.setStatus(0);
                    storyService.save(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                if ( 4 != essay.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    essay.setStatus(0);
                    essayService.save(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "event":
                Event event = eventService.findById(id);
                if ( 4 != event.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    event.setStatus(0);
                    eventService.save(event);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
        }
        return Msg.success().setMsg("操作成功!");
    }

    /**
     * 将待审核的稿件设置为审核通过状态(展示此稿件);
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/admin/contribute/pass",method = RequestMethod.POST)
    @ResponseBody
    private Msg passContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        if (!VerifyAuthorityUtil.isAdmin(request)){
            return Msg.fail().setMsg("您没有权限执行此操作");
        }
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断当前用户想操作的是否是自己的稿件,并且稿件此时的状态是否合理;
                if ( 0 != topic.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try {
                    topic.setStatus(1);
                    topicService.save(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "story":
                Story story = storyService.findById(id);
                if ( 0 != story.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    story.setStatus(1);
                    storyService.save(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                if ( 0 != essay.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    essay.setStatus(1);
                    essayService.save(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "event":
                Event event = eventService.findById(id);
                if ( 0 != event.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    event.setStatus(1);
                    eventService.save(event);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
        }
        return Msg.success().setMsg("操作成功!");
    }

    /**
     * 退稿,需要记录退稿理由,;
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/admin/contribute/sendBack",method = RequestMethod.POST)
    @ResponseBody
    private Msg sendBackContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId,@RequestParam("sendBackInfo")String sendBackInfo) {
        if (!VerifyAuthorityUtil.isAdmin(request)){
            return Msg.fail().setMsg("您没有权限执行此操作");
        }
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断稿件此时的状态是否合理;
                if ( 0 != topic.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try {
                    topic.setStatus(3);
                    topic.setInfo(sendBackInfo);
                    topicService.save(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "story":
                Story story = storyService.findById(id);
                //判断当前用户想删除的是否是自己的稿件;
                if ( 0 != story.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    story.setStatus(3);
                    story.setInfo(sendBackInfo);
                    storyService.save(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                if ( 0 != essay.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    essay.setStatus(3);
                    essay.setInfo(sendBackInfo);
                    essayService.save(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "event":
                Event event = eventService.findById(id);
                if ( 0 != event.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    event.setStatus(3);
                    event.setInfo(sendBackInfo);
                    eventService.save(event);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
        }
        return Msg.success().setMsg("操作成功!");
    }


    /**
     * 将稿件设置为废弃状态,页面上显示的按钮是" 废弃";
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/admin/contribute/deprecated",method = RequestMethod.POST)
    @ResponseBody
    private Msg deprecatedContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        if (!VerifyAuthorityUtil.isAdmin(request)){
            return Msg.fail().setMsg("您没有权限执行此操作");
        }
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断稿件此时的状态是否合理;
                if ( 4 == topic.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try {
                    topic.setStatus(4);
                    topicService.save(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "story":
                Story story = storyService.findById(id);
                if ( 4 == story.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    story.setStatus(4);
                    storyService.save(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                if ( 4 == essay.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    essay.setStatus(4);
                    essayService.save(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "event":
                Event event = eventService.findById(id);
                if ( 4 == event.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    event.setStatus(4);
                    eventService.save(event);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
        }
        return Msg.success().setMsg("操作成功!");
    }

    /**
     * 将稿件设置为展示状态;
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/admin/contribute/show",method = RequestMethod.POST)
    @ResponseBody
    private Msg showContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        if (!VerifyAuthorityUtil.isAdmin(request)){
            return Msg.fail().setMsg("您没有权限执行此操作");
        }
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断当前用户想操作的是否是自己的稿件,并且稿件此时的状态是否合理;
                if ( 2 != topic.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try {
                    topic.setStatus(1);
                    topicService.save(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "story":
                Story story = storyService.findById(id);
                if ( 2 != story.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    story.setStatus(1);
                    storyService.save(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                if ( 2 != essay.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    essay.setStatus(1);
                    essayService.save(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "event":
                Event event = eventService.findById(id);
                if ( 2 != event.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    event.setStatus(1);
                    eventService.save(event);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
        }
        return Msg.success().setMsg("操作成功!");
    }

    /**
     * 将设置为隐藏状态;
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/admin/contribute/hide",method = RequestMethod.POST)
    @ResponseBody
    private Msg hideContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        if (!VerifyAuthorityUtil.isAdmin(request)){
            return Msg.fail().setMsg("您没有权限执行此操作");
        }
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断当前用户想操作的是否是自己的稿件,并且稿件此时的状态是否合理;
                if ( 1 != topic.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try {
                    topic.setStatus(2);
                    topicService.save(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "story":
                Story story = storyService.findById(id);
                if ( 1 != story.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    story.setStatus(2);
                    storyService.save(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                if ( 1 != essay.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    essay.setStatus(2);
                    essayService.save(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "event":
                Event event = eventService.findById(id);
                if ( 1 != event.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    event.setStatus(2);
                    eventService.save(event);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
        }
        return Msg.success().setMsg("操作成功!");
    }

    /**
     * 管理员删除任意用户的投稿;url不带/json防止跳过权限验证
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/contribute/delete",method = RequestMethod.POST)
    @ResponseBody
    private Msg deleteContribution(HttpServletRequest request, @RequestParam("plate")String plateAndId){
        if (!VerifyAuthorityUtil.isAdmin(request)){
            return Msg.fail().setMsg("您没有权限执行此操作");
        }

        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));
        switch (plateName){
            case "topic":
                try {
                    /**
                     * 删除稿件,同时删除稿件下所有父评论及点赞记录,子评论暂时没有好的删除逻辑;图片暂时不实现删除方法,因为以后可能要改成将图片引用第三方云平台保存;
                     */
                    topicService.delete(id);
                    commentService.deleteByTopicId(id);
                    praiseService.deletePraiseTopicByTopicId(id);
                }catch (Exception e){
                    return Msg.fail().setMsg("删除稿件失败!");
                }
                break;
            case "story":
                try{
                    storyService.delete(id);
                    commentService.deleteByStoryId(id);
                    praiseService.deletePraiseStoryByStoryId(id);
                }catch (Exception e){
                    return Msg.fail().setMsg("删除稿件失败!");
                }
                break;
            case "essay":
                try{
                    essayService.delete(id);
                    praiseService.deletePraiseEssayByEssayId(id);
                }catch (Exception e){
                    return Msg.fail().setMsg("删除稿件失败!");
                }
            break;
            case "event":
            try{
                eventService.delete(id);
            }catch (Exception e){
                return Msg.fail().setMsg("删除稿件失败!");
            }
            break;
        }
        return Msg.success().setMsg("删除稿件成功!");
    }


    /**
     * 查看用户撰写的story页面;
     * @param model
     * @param pn
     * @param authorId
     * @return
     */
    @RequestMapping("/admin/story")
    public String toUserStoryPage(Model model,HttpServletRequest request,
                                  @RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus,
                                  @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                                  @RequestParam(value = "authorId",defaultValue = "0")long authorId){
        if (!VerifyAuthorityUtil.isAdmin(request)){
            model.addAttribute("errorMsg","很抱歉,您没有权限执行此操作");
            return "error/promptMessage";
        }
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"creatTime"));
        Story story = new Story();
        /**
         * 当没有传用户id时则查询所有用户的故事稿件;
         */
        if (0 != authorId){
            User user = new User();
            user.setId(authorId);
            story.setAuthor(user);
        }
        model.addAttribute("authorId",authorId);

        /**
         * 新增筛选功能,根据选择的contributionStatus值状态查看对应的投稿:为0时代表稿件正在审核,当为1时则稿件正常展示,为2时表示稿件被隐藏,3代表稿件被管理员退回,4代表稿件被放置于回收站;
         * 同时将用户点击状态返回给页面,方便页面高亮对应的筛选链接和生成对应的筛选链接;
         */
        model.addAttribute("contributionStatus",contributionStatus);
        story.setStatus(contributionStatus);

        //注意这里,story里面有几个属性是long类型,默认值是0而不是null,erexample里传过去不是null时就会开启匹配,所以需要设置忽略掉id属性;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("collectNumber").withIgnorePaths("commentNumber");
        Example<Story> example = Example.of(story,exampleMatcher);
        Page<Story> storyPage = storyService.findAllByExample(example, pageable);
        model.addAttribute("storyPage",storyPage);



        return "admin/adminStory";
    }

    /**
     * 查看用户撰写的essay页面;
     * @param model
     * @param pn
     * @param authorId
     * @return
     */
    @RequestMapping("/admin/essay")
    public String toUserEssayPage(Model model,HttpServletRequest request,@RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam(value = "authorId",defaultValue = "0")long authorId){
        if (!VerifyAuthorityUtil.isAdmin(request)){
            model.addAttribute("errorMsg","很抱歉,您没有权限执行此操作");
            return "error/promptMessage";
        }
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下,还有页面是9宫格板式,只查出9个即可
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"creatTime"));
        Essay essay = new Essay();
        if (0 != authorId) {
            User user = new User();
            user.setId(authorId);
            essay.setAuthor(user);
        }
        model.addAttribute("authorId",authorId);
        /**
         * 新增筛选功能,根据选择的contributionStatus值状态查看对应的投稿:为0时代表稿件正在审核,当为1时则稿件正常展示,为2时表示稿件被隐藏,3代表稿件被管理员退回,4代表稿件被放置于回收站;
         * 同时将用户点击状态返回给页面,方便页面高亮对应的筛选链接和生成对应的筛选链接;
         */
        model.addAttribute("contributionStatus",contributionStatus);
        essay.setStatus(contributionStatus);

        //注意这里,story里面有几个属性是long类型,默认值是0而不是null,erexample里传过去不是null时就会开启匹配,所以需要设置忽略掉id属性;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("collectNumber");
        Example<Essay> example = Example.of(essay,exampleMatcher);
        Page<Essay> essayPage = essayService.findAllByExample(example, pageable);
        model.addAttribute("essayPage",essayPage);



        return "admin/adminEssay";
    }

    /**
     * 查看用户撰写的topic页面
     * @param model
     * @param pn
     * @param authorId
     * @return
     */
    @RequestMapping("/admin/topic")
    public String toUserTopicPage(Model model,HttpServletRequest request,
                                  @RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus,
                                  @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                                  @RequestParam(value = "authorId",defaultValue = "0")long authorId,
                                  @RequestParam(value = "categoryId",defaultValue = "0")int categoryId){
        if (!VerifyAuthorityUtil.isAdmin(request)){
            model.addAttribute("errorMsg","很抱歉,您没有权限执行此操作");
            return "error/promptMessage";
        }
        /**
         * 设置分页和排序条件;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"creatTime"));
        Topic topic = new Topic();


        /**
         * 判断是否需要设置作者id筛选条件,并将作者id返回给前端再次利用;
         */
        if (0 != authorId) {
            User user = new User();
            user.setId(authorId);
            topic.setAuthor(user);
        }
        model.addAttribute("authorId",authorId);

        /**
         * 判断是否需要设置分类id筛选条件,并将分类id返回给前端再次利用;
         */
        if(0 != categoryId){
            Category category = new Category();
            category.setId(categoryId);
            topic.setCategory(category);
        }
        model.addAttribute("categoryId",categoryId);

        /**
         * 同时将所有分类返回前端供选择分类筛选条件
         */
        Pageable categoryPageable = new PageRequest(0,999,new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findAll(categoryPageable);
        model.addAttribute("categoryPage",categoryPage);

        /**
         * 新增筛选功能,根据选择的contributionStatus值状态查看对应的投稿:为0时代表稿件正在审核,当为1时则稿件正常展示,为2时表示稿件被隐藏,3代表稿件被管理员退回,4代表稿件被放置于回收站;
         * 同时将用户点击状态返回给页面,方便页面高亮对应的筛选链接和生成对应的筛选链接;
         */
        model.addAttribute("contributionStatus",contributionStatus);
        topic.setStatus(contributionStatus);

        //注意这里,story里面有几个属性是long类型,默认值是0而不是null,erexample里传过去不是null时就会开启匹配,所以需要设置忽略掉id属性;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("collectNumber").withIgnorePaths("commentNumber");
        Example<Topic> example = Example.of(topic,exampleMatcher);
        Page<Topic> topicPage = topicService.findAllByExample(example, pageable);
        model.addAttribute("topicPage",topicPage);

        return "admin/adminTopic";
    }

    /**
     * 查看用户撰写的event页面
     * @param model
     * @param pn
     * @param authorId
     * @return
     */
    @RequestMapping("/admin/event")
    public String toUserEventPage(Model model,HttpServletRequest request,
                                  @RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus,
                                  @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                                  @RequestParam(value = "authorId",defaultValue = "0")long authorId,
                                  @RequestParam(value = "cityId",defaultValue = "0")int cityId){
        if (!VerifyAuthorityUtil.isAdmin(request)){
            model.addAttribute("errorMsg","很抱歉,您没有权限执行此操作");
            return "error/promptMessage";
        }
        /**
         * 设置分页和排序条件;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"time"));
        Event event = new Event();

        model.addAttribute("authorId",0);

        /**
         * 判断是否需要设置分类id筛选条件,并将分类id返回给前端再次利用;
         */
        if(0 != cityId){
            City city = new City();
            city.setId(cityId);
            event.setCity(city);
        }
        model.addAttribute("cityId",cityId);

        /**
         * 同时将所有城市返回前端供选择城市筛选条件
         */
        Pageable cityPageable = new PageRequest(0,999,new Sort(Sort.Direction.DESC,"id"));
        Page<City> cityPage = cityService.findAll(cityPageable);
        model.addAttribute("cityPage",cityPage);

        /**
         * 新增筛选功能,根据选择的contributionStatus值状态查看对应的投稿:为0时代表稿件正在审核,当为1时则稿件正常展示,为2时表示稿件被隐藏,3代表稿件被管理员退回,4代表稿件被放置于回收站;
         * 同时将用户点击状态返回给页面,方便页面高亮对应的筛选链接和生成对应的筛选链接;
         */
        model.addAttribute("contributionStatus",contributionStatus);
        event.setStatus(contributionStatus);

        //注意这里,story里面有几个属性是long类型,默认值是0而不是null,erexample里传过去不是null时就会开启匹配,所以需要设置忽略掉id属性;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<Event> example = Example.of(event,exampleMatcher);
        Page<Event> eventPage = eventService.findAllByExample(example, pageable);
        model.addAttribute("eventPage",eventPage);

        return "admin/adminEvent";
    }

    /**
     * 通过前端传的板块名称和稿件id跳转到指定的稿件详情页
     * @param request
     * @param response
     * @param plate 板块名称
     * @param contributeId 稿件的id
     */
    @RequestMapping(value = "/admin/contribute/findOneByIdAndPlate" ,method = RequestMethod.POST)
    private void findOneByIdAndPlate(HttpServletRequest request, HttpServletResponse response, @RequestParam("plate")String plate, @RequestParam("contributeId")String contributeId){
        String href ="/admin/topic";//给一个初始值,减少报错几率;
        if (null != contributeId && "" != contributeId.trim()){
            if ("topic".equals(plate)){
                href = "/topic/"+contributeId;
            }else if ("essay".equals(plate)){
                href = "/essay?essayId=" + contributeId;
            }else if ("story".equals(plate)){
                href = "/story/" + contributeId;
            }else if ("event".equals(plate)){
                href = "/event/" + contributeId;
            }
            try {
                request.getRequestDispatcher(href).forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    






}
