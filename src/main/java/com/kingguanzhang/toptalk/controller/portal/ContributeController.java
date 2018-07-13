package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

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

    /**
     * 用户删除自己的投稿;
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/contribute/delete",method = RequestMethod.POST)
    @ResponseBody
    private Msg deleteContribution(HttpServletRequest request, @RequestParam("plate")String plateAndId){
        //不能直接按照id删除,避免用户修改前端传过来的id导致用户能随意删除他人的稿件;
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
                //判断当前用户想删除的是否是自己的稿件;
                if (user.getId() != topic.getAuthor().getId()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try {
                    topicService.delete(id);
                }catch (Exception e){
                    return Msg.fail().setMsg("删除稿件失败!");
                }
                break;
            case "story":
                Story story = storyService.findById(id);
                //判断当前用户想删除的是否是自己的稿件;
                if (user.getId() != story.getAuthor().getId()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                   storyService.delete(id);
                }catch (Exception e){
                    return Msg.fail().setMsg("删除稿件失败!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                //判断当前用户想删除的是否是自己的稿件;
                if (user.getId() != essay.getAuthor().getId()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    essayService.delete(id);
                }catch (Exception e){
                    return Msg.fail().setMsg("删除稿件失败!");
                }
                break;
        }
        return Msg.success().setMsg("删除稿件成功!");
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
