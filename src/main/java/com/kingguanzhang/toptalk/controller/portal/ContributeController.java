package com.kingguanzhang.toptalk.controller.portal;

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

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户投稿,设置稿件状态,删除稿件等操作;
 */
@Controller
public class ContributeController {
    
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private CityServiceImpl cityService;
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private UserServiceImpl userService;




    /**
     * 将自己的稿件设置为废弃状态,页面上显示的按钮是" 废弃";
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/user/contribute/deprecated",method = RequestMethod.POST)
    @ResponseBody
    private Msg deprecatedContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        //不能直接按照id操作,避免用户修改前端传过来的id导致用户能随意操作他人的稿件;
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断当前用户想操作的是否是自己的稿件,并且稿件此时的状态是否合理;
                if (user.getId() != topic.getAuthor().getId() || 4 == topic.getStatus()){
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
                //判断当前用户想删除的是否是自己的稿件;
                if (user.getId() != story.getAuthor().getId() || 4 == story.getStatus()){
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
                //判断当前用户想删除的是否是自己的稿件;
                if (user.getId() != essay.getAuthor().getId() || 4 == essay.getStatus()){
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
     * 将自己的稿件设置为展示状态;
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/user/contribute/show",method = RequestMethod.POST)
    @ResponseBody
    private Msg showContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        //不能直接按照id操作,避免用户修改前端传过来的id导致用户能随意操作他人的稿件;
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断当前用户想操作的是否是自己的稿件,并且稿件此时的状态是否合理;
                if (user.getId() != topic.getAuthor().getId() || 2 != topic.getStatus()){
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
                if (user.getId() != story.getAuthor().getId() || 2 != story.getStatus()){
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
                if (user.getId() != essay.getAuthor().getId() || 2 != essay.getStatus()){
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
     * 将自己的稿件设置为隐藏状态;
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/user/contribute/hide",method = RequestMethod.POST)
    @ResponseBody
    private Msg hideContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        //不能直接按照id操作,避免用户修改前端传过来的id导致用户能随意操作他人的稿件;
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断当前用户想操作的是否是自己的稿件,并且稿件此时的状态是否合理;
                if (user.getId() != topic.getAuthor().getId() || 1 != topic.getStatus()){
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
                if (user.getId() != story.getAuthor().getId() || 1 != story.getStatus()){
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
                if (user.getId() != essay.getAuthor().getId() || 1 != essay.getStatus()){
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
     * 查看用户撰写的story页面;
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/user/story")
    public String toUserStoryPage(Model model,@RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam("userId")long userId){

        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"creatTime"));
        Story story = new Story();
        User user = new User();
        user.setId(userId);
        story.setAuthor(user);

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

        /**
         * 返回用户信息以方便页面渲染时生成链接;
         */

        User author ;
        if (0 != storyPage.getContent().size()){ //如果topicPage里有返回值就直接从里面取,
            author=storyPage.getContent().get(0).getAuthor();
        }else {                         //否则从数据库中查
            author = userService.findById(userId);
        }
        model.addAttribute("user",author);

        return "user/userStory";
    }

    /**
     * 查看用户撰写的essay页面;
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/user/essay")
    public String toUserEssayPage(Model model,@RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam("userId")long userId){
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下,还有页面是9宫格板式,只查出9个即可
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"creatTime"));
        Essay essay = new Essay();
        User user = new User();
        user.setId(userId);
        essay.setAuthor(user);;

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

        /**
         * 返回用户信息以方便页面渲染时生成链接;
         */

        User author ;
        if (0 != essayPage.getContent().size()){ //如果topicPage里有返回值就直接从里面取,
            author=essayPage.getContent().get(0).getAuthor();
        }else {                         //否则从数据库中查
            author = userService.findById(userId);
        }
        model.addAttribute("user",author);

        return "user/userEssay";
    }

    /**
     * 查看用户撰写的topic页面;原先链接为"/user",现在改为"/user/topic"
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/user/topic")
    public String toUserTopicPage(Model model,@RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam("userId")long userId){
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"creatTime"));
        Topic topic = new Topic();
        User user = new User();
        user.setId(userId);
        topic.setAuthor(user);

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

        /**
         * 返回用户信息以方便页面渲染时生成链接;
         */

        User author ;
        if (0 != topicPage.getContent().size()){ //如果topicPage里有返回值就直接从里面取,
            author=topicPage.getContent().get(0).getAuthor();
        }else {                         //否则从数据库中查
            author = userService.findById(userId);
        }
        model.addAttribute("user",author);

        return "user/userTopic";
    }



    /**
     * 跳转到topic投稿界面
     * @param model
     * @return
     */
    @RequestMapping("/contribute/topicContribute")
    public String toTopicContributePage(Model model){
        Pageable pageable = new PageRequest(0,100,new Sort(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        model.addAttribute("categoryPage",categoryPage);
        return "/contribute/topicContribute";
    }

    @RequestMapping("/contribute/eventContribute")
    public String toEventContributePage(Model model){
        Pageable pageable = new PageRequest(0,200,new Sort(Sort.Direction.ASC,"rank"));
        Page<City> cityPage = cityService.findAll(pageable);
        model.addAttribute("cityPage",cityPage);
        return "/contribute/eventContribute";
    }

    @RequestMapping("/contribute/storyContribute")
    public String toStoryContributePage(){
        return "/contribute/storyContribute";
    }

    @RequestMapping("/contribute/essayContribute")
    public String toEssayContributePage(){
        return "/contribute/essayContribute";
    }

    @RequestMapping("/ue")
    public String toUEPage(){
        return "/portal/ue";
    }




}
