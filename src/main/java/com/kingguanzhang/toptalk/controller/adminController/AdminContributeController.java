package com.kingguanzhang.toptalk.controller.adminController;

import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.Essay;
import com.kingguanzhang.toptalk.entity.Story;
import com.kingguanzhang.toptalk.entity.Topic;
import com.kingguanzhang.toptalk.entity.User;
import com.kingguanzhang.toptalk.service.EssayServiceImpl;
import com.kingguanzhang.toptalk.service.StoryServiceImpl;
import com.kingguanzhang.toptalk.service.TopicServiceImpl;
import com.kingguanzhang.toptalk.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminContributeController {
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private UserServiceImpl userService;


    /**
     * 查看用户撰写的story页面;
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/admin/story")
    public String toUserStoryPage(Model model, @RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn, @RequestParam(value = "userId",defaultValue = "0")long userId){

        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"creatTime"));
        Story story = new Story();
        /**
         * 当没有传用户id时则查询所有用户的故事稿件;
         */
        if (0 != userId){
            User user = new User();
            user.setId(userId);
            story.setAuthor(user);
        }

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
         * 返回作者信息以方便页面渲染时生成链接;
         */

        User author = new User() ;
        if (0 != userId) {
            if (0 != storyPage.getContent().size()) { //如果topicPage里有返回值就直接从里面取,
                author = storyPage.getContent().get(0).getAuthor();
            } else {                         //否则从数据库中查
                author = userService.findById(userId);
            }
        }else {
            author.setId(0);
        }
        model.addAttribute("author",author);

        return "admin/adminStory";
    }

    /**
     * 查看用户撰写的essay页面;
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/admin/essay")
    public String toUserEssayPage(Model model,@RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam(value = "userId",defaultValue = "0")long userId){
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下,还有页面是9宫格板式,只查出9个即可
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"creatTime"));
        Essay essay = new Essay();
        if (0 != userId) {
            User user = new User();
            user.setId(userId);
            essay.setAuthor(user);
        }
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

        User author = new User() ;
        if (0 != userId) {
            if (0 != essayPage.getContent().size()) { //如果topicPage里有返回值就直接从里面取,
                author = essayPage.getContent().get(0).getAuthor();
            } else {                         //否则从数据库中查
                author = userService.findById(userId);
            }
        }else {
            author.setId(0);
        }
        model.addAttribute("author",author);

        return "admin/adminEssay";
    }

    /**
     * 查看用户撰写的topic页面
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/admin/topic")
    public String toUserTopicPage(Model model,@RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam(value = "userId",defaultValue = "0")long userId){
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"creatTime"));
        Topic topic = new Topic();
        if (0 != userId) {
            User user = new User();
            user.setId(userId);
            topic.setAuthor(user);
        }

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

        User author = new User() ;
        if (0 != userId) {
            if (0 != topicPage.getContent().size()) { //如果topicPage里有返回值就直接从里面取,
                author = topicPage.getContent().get(0).getAuthor();
            } else {                         //否则从数据库中查
                author = userService.findById(userId);
            }
        }else {
            author.setId(0);
        }
        model.addAttribute("author",author);

        return "admin/adminTopic";
    }

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
