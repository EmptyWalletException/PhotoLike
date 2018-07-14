package com.kingguanzhang.toptalk.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import com.kingguanzhang.toptalk.utils.Base64ToMultipartUtil;
import com.kingguanzhang.toptalk.utils.ImgUtil;
import com.kingguanzhang.toptalk.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CityServiceImpl cityService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private UserFavoriteServiceImpl userFavoriteService;



    // TODO 需要完成修改密码的功能:




    /**
     * 修改用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/edit",method = RequestMethod.POST)
    @ResponseBody
    private Msg editInfo(HttpServletRequest request){
        //从session中取出user;
        if (null == request.getSession().getAttribute("user")){
            //判断session失效后返回登录界面
            return Msg.fail().setMsg("操作失败,请重新登录后再试!");
        }
        User user = (User) request.getSession().getAttribute("user");

        /*设置昵称*/
        if (null != request.getParameter("nickname")){
            String nickname = request.getParameter("nickname");
            user.setNickname(nickname);
        }

        /*设置城市*/
        City city = new City();
        if (null != request.getParameter("cityId")){
            String cityId = request.getParameter("cityId");
            city.setId(Long.parseLong(cityId));
            user.setCity(city);
        }

        /*设置性别*/
        if (null != request.getParameter("gender")){
            String gender = request.getParameter("gender");
            user.setGender(Integer.valueOf(gender));
        }

        //修改用户信息,尽可能的减少从前端获取的值;
        if (null != user ) {
              try{
                userService.save(user);
            }catch (Exception e){
                return Msg.fail().setMsg("修改失败,保存用户信息时出现错误!");
            }
            //返回注册店铺的最终结果;
            return Msg.success().setMsg("修改成功!");
        } else {
            return Msg.fail().setMsg("修改失败!");
        }
    }

    /**
     * 修改签名;
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/signature/add",method = RequestMethod.POST)
    @ResponseBody
    private Msg userSignatureAdd(HttpServletRequest request){
        if (null == request.getSession().getAttribute("user")){
            //判断session失效后返回登录界面
            return Msg.fail().setMsg("操作失败,请重新登录后再试!");
        }
        User user = (User) request.getSession().getAttribute("user");
        String signature = request.getParameter("signature");
        user.setSignature(signature);
        try{
            userService.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return Msg.fail().setMsg("修改失败!");
        }
       return Msg.success().setMsg("修改签名成功!");
    }

    /**
     * 修改用户头像,base64格式的字符串转成MultipartFile;
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/headImgUpload",method = RequestMethod.POST)
    @ResponseBody
    private Msg testBase64HeadImgUpload(HttpServletRequest request){
        if (null == request.getSession().getAttribute("user")){
            //设置错误代码为101,前端判断是此代码时跳转到登录界面;
            return Msg.fail().setMsg("登录已超时,请重新登录后再尝试!").setCode(101);
        }
       User user = (User) request.getSession().getAttribute("user");
        String img = request.getParameter("img");
        //调用自定义的工具将base64字符串转成multipartFile,这个multipartFile里的除了响应头和byte数组外的字符(例如文件名,原始文件名)都是随机生成的;
        MultipartFile multipartFile = Base64ToMultipartUtil.base64ToMultipart(img);
        String imgAddr2 = ImgUtil.generateThumbnail(multipartFile, "/user/"+user.getId()+"/",200, 200);
        user.setImgAddr(imgAddr2);
        try{
            userService.save(user);
        }catch (Exception e){
            return Msg.fail().setMsg("更新头像信息失败");
        }
        return Msg.success().setMsg("修改成功");

    }

    /**
     * 跳转到注册用户页面
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    private String toRegisterPage(Model model){
        /**
         * 取出城市让用户修改居住城市;
         */
        Pageable pageable = new PageRequest(0,100,new Sort(Sort.Direction.ASC,"rank"));
        Page<City> cityPage = cityService.findAll(pageable);
        model.addAttribute("cityPage",cityPage);
        return "portal/register";
    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    private Msg saveUser(HttpServletRequest request){
        //从前端传来的请求中获取键为userStr的值;
        String userStr = RequestUtil.parserString(request, "userStr");
        System.out.print("userStr的值:" + userStr);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        try {
            //将字符串转成实体类
            user = objectMapper.readValue(userStr, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().setMsg("读取注册信息失败!");
        }
        City city = new City();
        if (null != request.getParameter("cityId")){
            String cityId = request.getParameter("cityId");
            city.setId(Long.parseLong(cityId));
        }else {
            city.setId(1);//如果城市参数传递失败则默认选择一个城市,之后管理员审核时可以修改;
        }
        //注册店铺,尽可能的减少从前端获取的值;
        if (null != user ) {
           user.setJoinTime(new Date(System.currentTimeMillis()));
           user.setSignature("这个人很懒,没有设置签名...");
           user.setCity(city);
           user.setImgAddr("/img/test/userUserHead.jpg");//先设置用户的默认头像,以后等用户修改;
            try{
                userService.save(user);
            }catch (Exception e){
                return Msg.fail().setMsg("注册失败,保存用户信息时出现错误!");
            }
            //返回注册店铺的最终结果;
            return Msg.success().setMsg("注册成功!");
        } else {
            return Msg.fail().setMsg("注册失败!");
        }
    }

    /**
     * 查看编辑用户信息的页面;
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/editInfo",method = RequestMethod.GET)
    public String toUserEditPage(Model model, HttpServletRequest request){
        /**
         * 取出城市让用户修改居住城市;
         */
        Pageable pageable = new PageRequest(0,100,new Sort(Sort.Direction.ASC,"rank"));
        Page<City> cityPage = cityService.findAll(pageable);
        model.addAttribute("cityPage",cityPage);

        /**
         * 判断session中是否有user,没有则返回到错误页面或重新登录界面;
         */
        if (null != request.getSession().getAttribute("user")){
            User user = (User) request.getSession().getAttribute("user");
            model.addAttribute("user",user);
            return "user/editInfo";
        }
        return "error";

        //已废弃,现在可直接获取user实体类,因为在index.html页面加载时会从后台取用户信息;
        /*// 注意这里,user.id是long类型,默认值是0而不是null,erexample里传过去不是null时就会开启匹配,所以需要设置忽略掉id属性;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("account",ExampleMatcher.GenericPropertyMatchers.caseSensitive()).withIgnorePaths("id").withIgnoreCase(false);
        Example<User> example = Example.of(user,exampleMatcher);
        user = userService.findOne(example);
        model.addAttribute("user",user);
        */

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
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"id"));
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
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"id"));
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
     * 查看用户撰写的topic页面;原先链接为"/user/topic",现在改为"/user"
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
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"id"));
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



}
