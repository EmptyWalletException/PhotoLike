package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.Essay;
import com.kingguanzhang.toptalk.service.EssayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
}
