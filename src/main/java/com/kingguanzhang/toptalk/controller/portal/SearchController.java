package com.kingguanzhang.toptalk.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kingguanzhang.toptalk.entity.Essay;
import com.kingguanzhang.toptalk.entity.Story;
import com.kingguanzhang.toptalk.entity.Topic;
import com.kingguanzhang.toptalk.service.EssayServiceImpl;
import com.kingguanzhang.toptalk.service.StoryServiceImpl;
import com.kingguanzhang.toptalk.service.TopicServiceImpl;

@Controller
public class SearchController {

    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private StoryServiceImpl storyService;

    @RequestMapping("/search")
    public String toSearchTopicPage(Model model,@RequestParam(value = "filtrate",defaultValue = "topic")String filtrate, @RequestParam(value = "keyword",defaultValue = "请输入关键字")String keyword,@RequestParam(value = "pn",defaultValue = "1")Integer pn){

        model.addAttribute("keyword",keyword);
        keyword = "%"+keyword+"%";
        Pageable pageable2 = new PageRequest(pn-1,10,  new Sort(Sort.Direction.DESC,"creat_time"));
        if ("topic".equals(filtrate)){
            Page<Topic> topicPage = topicService.findByKeywordAndStatus(keyword,1,pageable2);
            model.addAttribute("topicPage",topicPage);
            return "portal/searchTopic";
        }else if ("essay".equals(filtrate)){
            Page<Essay> essayPage = essayService.findByKeywordAndStatus(keyword,1,pageable2);
            model.addAttribute("essayPage",essayPage);
            return "portal/searchEssay";
        }else if ("story".equals(filtrate)){
            Page<Story> storyPage = storyService.findByKeywordAndStatus(keyword,1,pageable2);
            model.addAttribute("storyPage",storyPage);
            return "portal/searchStory";
        }else {
            model.addAttribute("errorMsg","参数错误!");
            return "error";
        }

    }

}
