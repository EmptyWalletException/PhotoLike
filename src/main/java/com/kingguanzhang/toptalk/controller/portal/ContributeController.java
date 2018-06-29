package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.Story;
import com.kingguanzhang.toptalk.service.StoryServiceImpl;
import com.kingguanzhang.toptalk.utils.ImgUtil;
import com.kingguanzhang.toptalk.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ContributeController {
    
    @Autowired
    private StoryServiceImpl storyService;

    @RequestMapping("/portal/topicContribute")
    public String toTopicContributePage(){
        return "/portal/topicContribute";
    }

    @RequestMapping("/portal/storyContribute")
    public String toStoryContributePage(){
        return "/portal/storyContribute";
    }

    @RequestMapping("/portal/essayContribute")
    public String toEssayContributePage(){
        return "/portal/essayContribute";
    }

    @RequestMapping("/ue")
    public String toUEPage(){
        return "/portal/ue";
    }

    /**
     * 持久化用户上传的故事稿件
     * @param request
     * @return
     */
    @RequestMapping("/story/save")
    @ResponseBody
    public Msg storySave(HttpServletRequest request){
        //从前端传来的请求中获取键为图片之外的内容;
        Story story = new Story();

        /**
         *  处理图片
         */
        //从request中解析出上传的文件图片;
        MultipartFile coverImg = ((MultipartRequest) request).getFile("coverImg");

        /*//注册店铺,尽可能的减少从前端获取的值;
        if ( null != coverImg) {
            try {
                *//**
                 * 保存图片到本地,返回图片本地地址并保存;
                 *//*
                 // TODO 将图片地址保存至Photo实体类中:
                //使用文件.getOriginalFilename可以获取带后缀.jpg的全名;或者文件.getItem.getName也可以获取带后缀的文件名;否则只能取到不带后缀的文件名;
                // TODO 调用service的save方法持久化稿件;:
                //storyService.save(story);
            } catch (IOException e) {
                System.out.print("异常信息"+e.getMessage());
                return Msg.fail().setMsg("保存稿件出错了");
            }
            //返回注册店铺的最终结果;
            return Msg.success().setMsg("投稿成功,请等待审核");
        } else {

        }*/
        return Msg.fail().setMsg("投稿失败,稿件内容不完整!");
    }


}
