package com.kingguanzhang.toptalk.controller.adminController;

import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.service.EssayServiceImpl;
import com.kingguanzhang.toptalk.service.StoryServiceImpl;
import com.kingguanzhang.toptalk.service.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public class AdminContributeController {
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private EssayServiceImpl essayService;

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
                    topicService.delete(id);
                }catch (Exception e){
                    return Msg.fail().setMsg("删除稿件失败!");
                }
                break;
            case "story":
                try{
                    storyService.delete(id);
                }catch (Exception e){
                    return Msg.fail().setMsg("删除稿件失败!");
                }
                break;
            case "essay":
                try{
                    essayService.delete(id);
                }catch (Exception e){
                    return Msg.fail().setMsg("删除稿件失败!");
                }
                break;
        }
        return Msg.success().setMsg("删除稿件成功!");
    }
}
