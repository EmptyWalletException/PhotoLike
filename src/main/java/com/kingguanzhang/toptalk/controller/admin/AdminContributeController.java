package com.kingguanzhang.toptalk.controller.admin;

import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminContributeController {
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private PraiseServiceImpl praiseService;
    @Autowired
    private CRESTServiceImpl crestService;
    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 编辑专辑所属分类;
     * @param topicCategoryId
     * @param topicId
     * @return
     */
    @RequestMapping("/admin/topic/category/edit")
    @ResponseBody
    private Msg editTopicCategory(@RequestParam("topicCategoryId")String topicCategoryId,@RequestParam("topicId")String topicId){
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
        // TODO 需要修改成从security判断是否有admin角色信息:
       /* if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");*/
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
        // TODO 需要修改成从security判断是否有admin角色信息:
       /* if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");*/
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
        // TODO 需要修改成从security判断是否有admin角色信息:
       /* if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");*/
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
        // TODO 需要修改成从security判断是否有admin角色信息:
       /* if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");*/
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
        // TODO 需要修改成从security判断是否有admin角色信息:
       /* if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");*/
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
        // TODO 需要修改成从security判断是否有admin角色信息:
       /* if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");*/
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
        }
        return Msg.success().setMsg("删除稿件成功!");
    }


    /**
     * 查看用户撰写的story页面;
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/admin/story")
    public String toUserStoryPage(Model model,
                                  @RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus,
                                  @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                                  @RequestParam(value = "userId",defaultValue = "0")long userId){

        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"creatTime"));
        Story story = new Story();
        /**
         * 当没有传用户id时则查询所有用户的故事稿件;
         */
        if (0 != userId){
            User user = new User();
            user.setId(userId);
            story.setAuthor(user);
        }
        model.addAttribute("userId",userId);

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
     * @param userId
     * @return
     */
    @RequestMapping("/admin/essay")
    public String toUserEssayPage(Model model,@RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam(value = "userId",defaultValue = "0")long userId){
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下,还有页面是9宫格板式,只查出9个即可
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"creatTime"));
        Essay essay = new Essay();
        if (0 != userId) {
            User user = new User();
            user.setId(userId);
            essay.setAuthor(user);
        }
        model.addAttribute("userId",userId);
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
     * @param userId
     * @return
     */
    @RequestMapping("/admin/topic")
    public String toUserTopicPage(Model model,
                                  @RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus,
                                  @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                                  @RequestParam(value = "userId",defaultValue = "0")long userId,
                                  @RequestParam(value = "categoryId",defaultValue = "0")int categoryId){
        /**
         * 设置分页和排序条件;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"creatTime"));
        Topic topic = new Topic();


        /**
         * 判断是否需要设置作者id筛选条件,并将作者id返回给前端再次利用;
         */
        if (0 != userId) {
            User user = new User();
            user.setId(userId);
            topic.setAuthor(user);
        }
        model.addAttribute("userId",userId);

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


}
