package com.kingguanzhang.toptalk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kingguanzhang.toptalk.entity.Essay;
import com.kingguanzhang.toptalk.entity.User;
import com.kingguanzhang.toptalk.entity.UserFavorite;
import com.kingguanzhang.toptalk.service.EssayServiceImpl;
import com.kingguanzhang.toptalk.service.UserFavoriteServiceImpl;
import com.kingguanzhang.toptalk.utils.VerifyAuthorityUtil;

@Controller
public class EssayController {

    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private UserFavoriteServiceImpl userFavoriteService;

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
        Pageable pageable = new PageRequest(0,5,new Sort(Sort.Direction.DESC,"collectNumber"));
        Essay hotEssay = new Essay();
        hotEssay.setStatus(1);//查出通过审核的状态为展示的随笔;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id","collectNumber");//long类型的需要忽略;
        Example<Essay> example = Example.of(hotEssay,exampleMatcher);
        Page<Essay> hotEssayPage =essayService.findAllByExample(example,pageable);
        model.addAttribute("hotEssayPage",hotEssayPage);

        /**
         * 获取所有的随笔,分页并排序;
         */
        Pageable pageable2 = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"creatTime"));
        Essay allEssay = new Essay();
        allEssay.setStatus(1);//查出通过审核的状态为展示的随笔;
        ExampleMatcher exampleMatcher2 = ExampleMatcher.matching().withIgnorePaths("id","collectNumber");//long类型的需要忽略;
        Example<Essay> example2 = Example.of(allEssay,exampleMatcher2);
        Page<Essay> allEssayPage =essayService.findAllByExample(example2,pageable2);
        model.addAttribute("essayPage",allEssayPage);

        /**
         * 判断当前取出的随笔是否被用户收藏,返回一个记录当前页被收藏的随笔Id的拼接字符串
         */
        String favEssayIds = "";
        if (null != request.getSession().getAttribute("user")){
            User user = (User) request.getSession().getAttribute("user");
            UserFavorite userFavorite = new UserFavorite();
            userFavorite.setUserId(user.getId());
            for(Essay temp:allEssayPage.getContent()){
                userFavorite.setEssayId(temp.getId());
                ExampleMatcher exampleMatcher3 = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("topicId").withIgnorePaths("storyId");
                Example<UserFavorite> example3 = Example.of(userFavorite,exampleMatcher3);
                Pageable pageable1 = new PageRequest(0,2);
                if (userFavoriteService.findAllByExample(example3,pageable1).hasContent()){
                    favEssayIds = favEssayIds+temp.getId() + ",";
                }
            }
        }
        if ( !"".equals(favEssayIds)){
            favEssayIds = favEssayIds.substring(0,favEssayIds.lastIndexOf(","));
        }
        model.addAttribute("favEssayIds",favEssayIds);

        /**
         * 判断如果用户点击了某个随笔,将其置顶;否则默认将收藏数最高的随笔置顶
         */
        if (null != request.getParameter("essayId")) {
            long essayId = Long.parseLong(request.getParameter("essayId"));
            Essay upEssay = essayService.findById(essayId);
            //判断当此id取不出记录时返回默认的置顶的随笔;
            if (null == upEssay){
                model.addAttribute("upEssay",hotEssayPage.getContent().get(0));
            }else {
                /**
                 * 限制浏览者只能浏览状态为1的稿件,除非浏览者是作者或管理员
                 */
                if (upEssay.getStatus().equals(1) || VerifyAuthorityUtil.isAdmin(request) || VerifyAuthorityUtil.isAuthorForThisEssay(request,upEssay)) {
                    model.addAttribute("upEssay", upEssay);
                } else {
                    //如果用户恶意传入的稿件id不符合浏览权限则从最热随笔中取出第一个置顶;
                    model.addAttribute("upEssay", hotEssayPage.getContent().get(0));

                }
            }
        }else {
            //默认从最热随笔中取出第一个置顶;
            model.addAttribute("upEssay",hotEssayPage.getContent().get(0));
        }

        return "portal/essay";
    }


}
