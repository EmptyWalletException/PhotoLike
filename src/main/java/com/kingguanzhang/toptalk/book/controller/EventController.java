package com.kingguanzhang.toptalk.book.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kingguanzhang.toptalk.base.entity.City;
import com.kingguanzhang.toptalk.base.service.CityServiceImpl;
import com.kingguanzhang.toptalk.book.entity.Event;
import com.kingguanzhang.toptalk.book.service.EventServiceImpl;
import com.kingguanzhang.toptalk.component.VerifyAuthority;

@Controller
public class EventController {

    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private CityServiceImpl cityService;

    /**
     * 查询所有的活动,分页并排序;
     * @param model
     * @param pn
     * @return
     */
    @RequestMapping("/event")
    public String toEventPage(Model model,HttpServletRequest request, @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        /**
         * 获取所有的城市显示在页面上方的分页导航;
         */
        Pageable pageable = new PageRequest(0,100,  new Sort(Sort.Direction.DESC,"id"));
        Page<City> cityPage = cityService.findAll(pageable);
        model.addAttribute("cityPage",cityPage);


        /**
         * 最新活动,按id倒序,取出5个;
         */
        Pageable pageable2 = new PageRequest(0,5,new Sort(Sort.Direction.DESC,"id"));
        Event newestEvent = new Event();
        newestEvent.setStatus(1);//查出通过审核的状态为展示的活动;
        ExampleMatcher exampleMatcher2 = ExampleMatcher.matching().withIgnorePaths("id");//long类型的需要忽略;
        Example<Event> eventExample = Example.of(newestEvent,exampleMatcher2);
        Page<Event> newestEventPage = eventService.findAllByExample(eventExample,pageable2);
        model.addAttribute("newestEventPage",newestEventPage);

        /**
         * 判断,根据前端如果传入分类则根据分类查询,否则查询所有;
         */
        if (null != request.getParameter("city")){

            long cityId = Long.parseLong(request.getParameter("city"));
            /**
             * 将categoryId传回页面方便接下来的分页跳转;
             */
            model.addAttribute("cityId",cityId);

            /**
             * 通过用户点击的分类获取city
             */
            Pageable pageable3= new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"id"));
            
            Page<Event> eventPage = eventService.findAllByCityId( cityId, pageable3);
            model.addAttribute("eventPage",eventPage);
        }else {
            /**
             * 获取所有的city,用于在默认的没有选择分类的情况下;
             */
            Pageable pageable4 = new PageRequest(pn-1,10,  new Sort(Sort.Direction.DESC,"id"));
            Event allEvent = new Event();
            allEvent.setStatus(1);//查出通过审核的状态为展示的活动;
            ExampleMatcher exampleMatcher3 = ExampleMatcher.matching().withIgnorePaths("id");//long类型的需要忽略;
            Example<Event> alleventExample = Example.of(allEvent,exampleMatcher3);
            Page<Event> eventPage = eventService.findAllByExample(alleventExample,pageable4);
            model.addAttribute("eventPage",eventPage);
        }
        return "portal/event";

    }

    /**
     * 获取单个活动详情;
     */
    @RequestMapping("/event/{eventId}")
    public String toEventDetailsPage(HttpServletRequest request,Model model, @PathVariable("eventId")Long eventId){
        /**
         * 根据用户点击的eventId取出event;
         */
        Event event = eventService.findById(eventId);
        /**
         * 限制浏览者只能浏览状态为1的稿件,除非浏览者是管理员
         */
        if(event.getStatus().equals(1) || VerifyAuthority.isAdmin(request)){
            model.addAttribute("event",event);
        }else{
            model.addAttribute("errorMsg","您暂时没有权限访问此稿件!");
            return "error/promptMessage";
        }

        /**
         * 最新活动,按id倒序,取出5个;
         */
        Pageable pageable2 = new PageRequest(0,5,new Sort(Sort.Direction.DESC,"id"));
        Event newestEvent = new Event();
        newestEvent.setStatus(1);//查出通过审核的状态为展示的活动;
        ExampleMatcher exampleMatcher2 = ExampleMatcher.matching().withIgnorePaths("id");//long类型的需要忽略;
        Example<Event> eventExample = Example.of(newestEvent,exampleMatcher2);
        Page<Event> newestEventPage = eventService.findAllByExample(eventExample,pageable2);
        model.addAttribute("newestEventPage",newestEventPage);

        return "portal/eventDetails";
    }




}
