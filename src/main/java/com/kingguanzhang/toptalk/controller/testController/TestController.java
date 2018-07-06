package com.kingguanzhang.toptalk.controller.testController;

import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.utils.Base64ToMultipartUtil;
import com.kingguanzhang.toptalk.utils.ImgUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    /**
     * 跳转到测试弹出式模态框的页面;
     * @return
     */
    @RequestMapping("/test/testModal")
    private String testModal(){
        return "test/testModal";
    }

    /**
     * 跳转到测试用户头像上传的页面;
     * @return
     */
    @RequestMapping("/test/testEditImg")
    private String testEditImg(){
        return "user/testEditImg";
    }

    /**
     * 用于测试base64格式的头像图片上传;
     * @param request
     * @return
     */
    @RequestMapping(value = "/test/headImgUpload",method = RequestMethod.POST)
    private Msg testBase64HeadImgUpload(HttpServletRequest request){
        String img = request.getParameter("img");
        //调用自定义的工具将base64字符串转成multipartFile,这个multipartFile里的除了响应头和byte数组外的字符(例如文件名,原始文件名)都是随机生成的;
        MultipartFile multipartFile = Base64ToMultipartUtil.base64ToMultipart(img);
        String imgAddr2 = ImgUtil.generateThumbnail(multipartFile, "/user/test/",1920, 1080);
        return Msg.success().setMsg("成功");

    }
}
