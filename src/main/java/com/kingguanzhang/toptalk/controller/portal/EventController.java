package com.kingguanzhang.toptalk.controller.portal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.City;
import com.kingguanzhang.toptalk.entity.Event;
import com.kingguanzhang.toptalk.entity.User;
import com.kingguanzhang.toptalk.service.CityServiceImpl;
import com.kingguanzhang.toptalk.service.EventServiceImpl;
import com.kingguanzhang.toptalk.utils.ImgUtil;
import com.kingguanzhang.toptalk.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;

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
        return "/portal/event";

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
         * 限制浏览者只能浏览状态为1的稿件,除非浏览者是作者或管理员
         */
        if(1 == event.getStatus() || null != request.getSession().getAttribute(("admin"))){
            model.addAttribute("event",event);
        }else{
            return "error"; // TODO 需要完成错误页面 提示没有权限访问此稿件:
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

        return "/portal/eventDetails";
    }

    /**
     * 保存ue富文本编辑器上传的图片并回显;
     * @param upfile
     * @return
     */
    @RequestMapping(value = "/eventContribute/imgUpload")
    @ResponseBody
    public String imgUpload3(MultipartFile upfile) {
        if (upfile.isEmpty()) {
            return "error";
        }

        // TODO 此处user id 需要改成从session中获取security 保存的用户信息来从数据库中查出id:
        User author = new User();
        author.setId(1);
        //设置中间文件夹,方便整理图片
        String centreAddr = "/event/"+author.getId()+"/";
        try {
            //使用工具类保存图片并返回文件名给网页;
            String fileName = ImgUtil.generateThumbnail(upfile,centreAddr,1920,1080);
            //url为文件访问的完整路径,注意应该配合mvc中配置的虚拟路径"/upload"
            String config = "{\"state\": \"SUCCESS\"," +
                    "\"url\": \"" + fileName + "\"," +
                    "\"title\": \"" + fileName + "\"," +
                    "\"original\": \"" + fileName + "\"}";
            return config;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 持久化用户投稿
     * @param request
     * @return
     */
    @RequestMapping(value = "/event/contribute",method = RequestMethod.POST)
    @ResponseBody
    private Msg eventContribute(HttpServletRequest request){
        //从前端传来的请求中获取键为userStr的值;
        String eventStr = RequestUtil.parserString(request, "eventStr");
        System.out.print("eventStr的值是:" + eventStr);
        ObjectMapper objectMapper = new ObjectMapper();
        Event event = null;
        try {
            //将字符串转成实体类
            event = objectMapper.readValue(eventStr, Event.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().setMsg("读取稿件信息失败!");
        }
        User author = new User();
        author.setId(1);
        City city = new City();
        if (null != request.getParameter("cityId")){
            String cityId = request.getParameter("cityId");
            city.setId(Long.parseLong(cityId));
        }else {
            city.setId(1);//如果城市参数传递失败则默认选择一个城市,之后管理员审核时可以修改;
        }
        //从request中解析出上传的文件图片;
        MultipartFile coverImg = ((MultipartRequest) request).getFile("img");

        //注册店铺,尽可能的减少从前端获取的值;
        if (null != event && null != coverImg) {
            //设置中间文件夹,方便整理图片
            String centreAddr = "/event/"+author.getId()+"/";
            String imgAddr = ImgUtil.generateThumbnail(coverImg, centreAddr,180, 255);
            event.setCoverImgAddr(imgAddr);
            event.setCity(city);
            eventService.save(event);
            //返回注册店铺的最终结果;
            return Msg.success().setMsg("投稿成功,请等待审核.");
        } else {
            return Msg.fail().setMsg("投稿失败,稿件信息不完整!");
        }
    }
}
