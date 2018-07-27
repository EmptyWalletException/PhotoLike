package com.kingguanzhang.toptalk.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import com.kingguanzhang.toptalk.utils.Base64ToMultipartUtil;
import com.kingguanzhang.toptalk.utils.ImgUtil;
import com.kingguanzhang.toptalk.utils.RequestUtil;
import org.hibernate.criterion.Order;
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
            if(checkNickname(nickname)){
                return Msg.fail().setMsg("昵称已经被占用");
            }
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
     * ajax检查用户账号是否被占用;
     * @param account
     * @return
     */
    @RequestMapping(value = "/user/ajax/checkAccount",method = RequestMethod.POST)
    @ResponseBody
    private Msg ajaxCheckAccount(@RequestParam("inputValue")String account){
        if (checkAccount(account)){
            return Msg.fail().setMsg("账号已被其他人注册!");
        }else {
            return Msg.success().setMsg("账号可以使用!");
        }
    }

    /**
     * ajax检查用户昵称是否被占用
     * @param nickname
     * @return
     */
    @RequestMapping(value = "/user/ajax/checkNickname",method = RequestMethod.POST)
    @ResponseBody
    private Msg ajaxCheckNickname(@RequestParam("inputValue")String nickname){
        if (!checkNickname(nickname)){
            return Msg.fail().setMsg("昵称已被他人占用!");
        }else {
            return Msg.success().setMsg("昵称可以使用!");
        }
    }

    /**
     * 抽取出的检查用户账号是否被占用的方法,在前端输入框变更时调用一次,提交注册后保存到数据表之前再调用一次;
     * @param account
     * @return
     */
    private boolean checkAccount(String account){
        if (null == account || account.trim() == ""){
            return false;
        }
        User user = new User();
        user.setAccount(account);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<User> userExample = Example.of(user,exampleMatcher);
        Pageable pageable = new PageRequest(0,2,new Sort(Sort.Direction.DESC,"id"));
        Page<User> allByExample = userService.findAllByExample(userExample, pageable);
        if (allByExample.hasContent()){//如果数据库查出的有值,则说明被占用;
            return false;
        }else {
            return true;
        }
    }

    /**
     * 抽取出的检查用户账号是否被占用的方法,在前端输入框变更时调用一次,提交注册后保存到数据表之前再调用一次,修改用户信息时也会调用一次;
     * @param nickname
     * @return
     */
    private boolean checkNickname(String nickname){
        if (null == nickname || nickname.trim() == ""){
            return false;
        }
        User user = new User();
        user.setNickname(nickname);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<User> userExample = Example.of(user,exampleMatcher);
        Pageable pageable = new PageRequest(0,2,new Sort(Sort.Direction.DESC,"id"));
        Page<User> allByExample = userService.findAllByExample(userExample, pageable);
        if (allByExample.hasContent()){//如果数据库查出的有值,则说明被占用;
            return false;
        }else {
            return true;
        }
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
            if (!checkAccount(user.getAccount())){
                return Msg.fail().setMsg("账号已经被占用!");
            }
            if(!checkNickname(user.getNickname())){
                return Msg.fail().setMsg("昵称已经被占用!");
            }
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




}
