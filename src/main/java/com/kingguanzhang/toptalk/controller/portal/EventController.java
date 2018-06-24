package com.kingguanzhang.toptalk.controller.portal;

import com.kingguanzhang.toptalk.entity.Event;
import com.kingguanzhang.toptalk.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    /**
     * 查询所有的活动,分页并排序;
     * @param model
     * @param pn
     * @return
     */
    @RequestMapping("/event")
    public String toEventPage(Model model, @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        /**
         * 所有活动,分页并排序;
         */
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"id"));
        Page<Event> eventPage = eventService.findAll(pageable);
        model.addAttribute("eventPage",eventPage);

        /**
         * 最新活动,按id倒序,取出5个;
         */
        Pageable pageable2 = new PageRequest(1,10,new Sort(Sort.Direction.DESC,"id"));
        Page<Event> newestEventPage = eventService.findAll(pageable2);
        model.addAttribute("newestEventPage",newestEventPage);
        return "/portal/event";
    }

    /**
     * 获取单个活动详情;
     */
    @RequestMapping("/event/{eventId}")
    public String toEventDetailsPage(Model model, @PathVariable("eventId")Long eventId){
        /**
         * 根据用户点击的eventId取出event;
         */
        Event event = eventService.findById(eventId);
        model.addAttribute("event",event);

        /**
         * 最新活动,按id倒序,取出5个;
         */
        Pageable pageable2 = new PageRequest(1,10,new Sort(Sort.Direction.DESC,"id"));
        Page<Event> newestEventPage = eventService.findAll(pageable2);
        model.addAttribute("newestEventPage",newestEventPage);

        return "/portal/eventDetails";
    }
}
