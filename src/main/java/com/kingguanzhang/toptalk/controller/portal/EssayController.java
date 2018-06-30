package com.kingguanzhang.toptalk.controller.portal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.Essay;
import com.kingguanzhang.toptalk.entity.User;
import com.kingguanzhang.toptalk.service.EssayServiceImpl;
import com.kingguanzhang.toptalk.utils.ImgUtil;
import com.kingguanzhang.toptalk.utils.RequestUtil;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class EssayController {

    @Autowired
    private EssayServiceImpl essayService;

    /**
     * 获取所有的随笔,分页并排序,判断如果用户点击了某个随笔,将其置顶;
     * @param model
     * @param pn
     * @return
     */
    @RequestMapping("/essay")
    public String toEssayPage(Model model, HttpServletRequest request, @RequestParam(name="pn",defaultValue = "1")Integer pn){

        /**
         * 根据收藏数倒序获取最热的随笔,分页并排序;
         */
        Pageable pageable2 = new PageRequest(0,5,new Sort(Sort.Direction.DESC,"collectNumber"));
        Page<Essay> hotEssayPage = essayService.findAll(pageable2);
        model.addAttribute("hotEssayPage",hotEssayPage);

        /**
         * 获取所有的随笔,分页并排序;
         */
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"id"));
        Page<Essay> essayPage = essayService.findAll(pageable);
        model.addAttribute("essayPage",essayPage);

        /**
         * 判断如果用户点击了某个随笔,将其置顶;否则默认将收藏数最高的随笔置顶
         */
        if (null != request.getParameter("essayId")) {
            long essayId = Long.parseLong(request.getParameter("essayId"));
            Essay upEssay = essayService.findById(essayId);
            model.addAttribute("upEssay",upEssay);
        }else {
            //默认从最热随笔中取出第一个置顶;
            model.addAttribute("upEssay",hotEssayPage.getContent().get(0));
        }

        return "/portal/essay";
    }

    /**
     * 持久化用户投稿
     * @param request
     * @return
     */
    @RequestMapping(value = "/essay/contribute",method = RequestMethod.POST)
    @ResponseBody
    private Msg essayContribute(HttpServletRequest request){
        //从前端传来的请求中获取键为shopStr的值;
        String essayStr = RequestUtil.parserString(request, "essayStr");
        ObjectMapper objectMapper = new ObjectMapper();
        Essay essay = null;
        try {
            //将字符串转成实体类
            essay = objectMapper.readValue(essayStr, Essay.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().setMsg("读取稿件信息失败!");
        }

        // TODO 这里先默认author id为1,后面改成从session中根据登录名获取user id;:
        User author = new User();
        author.setId(1);
        essay.setAuthor(author);

        //从request中解析出上传的文件图片;
        MultipartFile essayImg = ((MultipartRequest) request).getFile("img");

        //注册店铺,尽可能的减少从前端获取的值;
        if (null != essay && null != essayImg) {
            //设置中间文件夹,方便整理图片
            String centreAddr = "/essay/"+author.getId()+"/";
            String imgAddr = ImgUtil.generateThumbnail(essayImg, centreAddr,750, 530);
            essay.setImgAddr(imgAddr);
            System.out.print("essayStr的值是:" + essayStr);
            essayService.save(essay);
            //返回注册店铺的最终结果;
            return Msg.success().setMsg("投稿成功,请等待审核.");
        } else {
            return Msg.fail().setMsg("投稿失败,稿件信息不完整!");
        }
    }
}
