package com.kingguanzhang.toptalk.controller.portal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingguanzhang.toptalk.dto.Msg;
import com.kingguanzhang.toptalk.entity.*;
import com.kingguanzhang.toptalk.service.*;
import com.kingguanzhang.toptalk.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
import java.util.Date;
import java.util.Map;

/**
 * 用户投稿,设置稿件状态,删除稿件等操作;
 */
@Controller
public class ContributeController {
    
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private CityServiceImpl cityService;
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EventServiceImpl eventService;




    /**
     * 将自己的稿件设置为废弃状态,页面上显示的按钮是" 废弃";
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/user/contribute/deprecated",method = RequestMethod.POST)
    @ResponseBody
    private Msg deprecatedContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        //不能直接按照id操作,避免用户修改前端传过来的id导致用户能随意操作他人的稿件;
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断当前用户想操作的是否是自己的稿件,并且稿件此时的状态是否合理;
                if (user.getId() != topic.getAuthor().getId() || 4 == topic.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try {
                    topic.setStatus(4);
                    topicService.save(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "story":
                Story story = storyService.findById(id);
                //判断当前用户想删除的是否是自己的稿件;
                if (user.getId() != story.getAuthor().getId() || 4 == story.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    story.setStatus(4);
                    storyService.save(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                //判断当前用户想删除的是否是自己的稿件;
                if (user.getId() != essay.getAuthor().getId() || 4 == essay.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    essay.setStatus(4);
                    essayService.save(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
        }
        return Msg.success().setMsg("操作成功!");
    }

    /**
     * 将自己的稿件设置为展示状态;
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/user/contribute/show",method = RequestMethod.POST)
    @ResponseBody
    private Msg showContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        //不能直接按照id操作,避免用户修改前端传过来的id导致用户能随意操作他人的稿件;
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断当前用户想操作的是否是自己的稿件,并且稿件此时的状态是否合理;
                if (user.getId() != topic.getAuthor().getId() || 2 != topic.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try {
                    topic.setStatus(1);
                    topicService.save(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "story":
                Story story = storyService.findById(id);
                if (user.getId() != story.getAuthor().getId() || 2 != story.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    story.setStatus(1);
                    storyService.save(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                if (user.getId() != essay.getAuthor().getId() || 2 != essay.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    essay.setStatus(1);
                    essayService.save(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
        }
        return Msg.success().setMsg("操作成功!");
    }

    /**
     * 将自己的稿件设置为隐藏状态;
     * @param request
     * @param plateAndId
     * @return
     */
    @RequestMapping(value = "/user/contribute/hide",method = RequestMethod.POST)
    @ResponseBody
    private Msg hideContribution(HttpServletRequest request,@RequestParam("plate")String plateAndId) {
        //不能直接按照id操作,避免用户修改前端传过来的id导致用户能随意操作他人的稿件;
        if (null == request.getSession().getAttribute("user")){
            return Msg.fail().setCode(101).setMsg("操作失败,请重新登录后再尝试");
        }
        User user = (User) request.getSession().getAttribute("user");
        /**
         * 判断操作的是专辑还是故事还是随笔;
         */
        String plateName = plateAndId.substring(0, plateAndId.indexOf("."));
        Long id = Long.parseLong(plateAndId.substring(plateAndId.indexOf(".")+1));

        switch (plateName){
            case "topic":
                Topic topic = topicService.findById(id);
                //判断当前用户想操作的是否是自己的稿件,并且稿件此时的状态是否合理;
                if (user.getId() != topic.getAuthor().getId() || 1 != topic.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try {
                    topic.setStatus(2);
                    topicService.save(topic);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "story":
                Story story = storyService.findById(id);
                if (user.getId() != story.getAuthor().getId() || 1 != story.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    story.setStatus(2);
                    storyService.save(story);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
            case "essay":
                Essay essay = essayService.findById(id);
                if (user.getId() != essay.getAuthor().getId() || 1 != essay.getStatus()){
                    return Msg.fail().setMsg("非法的操作!");
                }
                try{
                    essay.setStatus(2);
                    essayService.save(essay);
                }catch (Exception e){
                    return Msg.fail().setMsg("操作失败!");
                }
                break;
        }
        return Msg.success().setMsg("操作成功!");
    }


    /**
     * 查看用户撰写的story页面;
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/user/story")
    public String toUserStoryPage(Model model,@RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam("userId")long userId){

        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,10,new Sort(Sort.Direction.DESC,"creatTime"));
        Story story = new Story();
        User user = new User();
        user.setId(userId);
        story.setAuthor(user);

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
         * 返回用户信息以方便页面渲染时生成链接;
         */

        User author ;
        if (0 != storyPage.getContent().size()){ //如果topicPage里有返回值就直接从里面取,
            author=storyPage.getContent().get(0).getAuthor();
        }else {                         //否则从数据库中查
            author = userService.findById(userId);
        }
        model.addAttribute("user",author);

        return "user/userStory";
    }

    /**
     * 查看用户撰写的essay页面;
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/user/essay")
    public String toUserEssayPage(Model model,@RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam("userId")long userId){
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下,还有页面是9宫格板式,只查出9个即可
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"creatTime"));
        Essay essay = new Essay();
        User user = new User();
        user.setId(userId);
        essay.setAuthor(user);;

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

        User author ;
        if (0 != essayPage.getContent().size()){ //如果topicPage里有返回值就直接从里面取,
            author=essayPage.getContent().get(0).getAuthor();
        }else {                         //否则从数据库中查
            author = userService.findById(userId);
        }
        model.addAttribute("user",author);

        return "user/userEssay";
    }

    /**
     * 查看用户撰写的topic页面;原先链接为"/user",现在改为"/user/topic"
     * @param model
     * @param pn
     * @param userId
     * @return
     */
    @RequestMapping("/user/topic")
    public String toUserTopicPage(Model model,@RequestParam(value = "contributionStatus",defaultValue = "1")Integer contributionStatus, @RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam("userId")long userId){
        /**
         * 取出用户撰写的story,分页并排序;注意pn因为pageRequest默认是从0开始的,所有要处理一下
         */
        Pageable pageable = new PageRequest(pn-1,9,new Sort(Sort.Direction.DESC,"creatTime"));
        Topic topic = new Topic();
        User user = new User();
        user.setId(userId);
        topic.setAuthor(user);

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

        User author ;
        if (0 != topicPage.getContent().size()){ //如果topicPage里有返回值就直接从里面取,
            author=topicPage.getContent().get(0).getAuthor();
        }else {                         //否则从数据库中查
            author = userService.findById(userId);
        }
        model.addAttribute("user",author);

        return "user/userTopic";
    }


    /**
     * 持久化用户tiopic投稿
     * @param request
     * @return
     */
    @RequestMapping(value = "/topic/contribute",method = RequestMethod.POST)
    @ResponseBody
    private Msg topicContribute(HttpServletRequest request) throws IOException {
        //从前端传来的请求中获取键为shopStr的值;
        String topicStr = RequestUtil.parserString(request, "topicStr");
        System.out.print("storyStr的值是:" + topicStr);
        ObjectMapper objectMapper = new ObjectMapper();
        Topic topic = null;
        try {
            //将字符串转成实体类
            topic = objectMapper.readValue(topicStr, Topic.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().setMsg("读取稿件信息失败!");
        }
        //限制稿件标题和正文的字符长度;
        if (30 < topic.getTitle().length() || 2000 < topic.getContent().length()){
            return Msg.fail().setMsg("保存失败,稿件标题或内容超出字数限制!");
        }

        //从request中解析出上传的所有文件图片;
        Map<String, MultipartFile> fileMap = ((MultipartRequest) request).getFileMap();
        //单独拿出封面图片
        MultipartFile coverImg = fileMap.get("img");
        //再移除封面图片,剩下内容图片;//更新需求:将封面图一同打包进内容,页面上移除掉封面图展示;
        /*fileMap.remove("img");*/


        /**
         * 保存topic信息并获取返回的id值;
         */
        User author = (User) request.getSession().getAttribute("user");
        topic.setAuthor(author);
        topic.setCommentNumber(0);
        topic.setCollectNumber(0);
        topic.setStatus(0);
        topic.setCreatTime(new Date(System.currentTimeMillis()));
        String categoryId = request.getParameter("categoryId");//取出前端传入的categoryId,级联保存;
        Category category = new Category();
        category.setId(Long.parseLong(categoryId));
        topic.setCategory(category);
        Long topidId = 0L;
        try{
            topidId = topicService.saveAndFlush(topic);//先保存一次返回id用于文件夹隔离;
        }catch (Exception e){
            return Msg.fail().setMsg("投稿失败,保存稿件信息时出现异常");
        }


        if (null != topic && null != coverImg) {
            //设置中间文件夹,方便整理图片
            String centreAddr = "/topic/"+author.getId()+"/"+topidId+"/";
            //保存封面图片并返回地址;
            String imgAddr = ImgUtil.generateThumbnail(coverImg, centreAddr,1920, 1080);

            // 新增的保存到七牛云的部分;
            String coverImgAddr = QiniuCloudUtil.upload(PathUtil.getImgBasePath()+imgAddr, imgAddr.substring(imgAddr.lastIndexOf("/")+1));//注意+1是为了避开/,否则保存的文件名前面会有一个/,

            //用于接收遍历出的每一个图片文件;
            MultipartFile contentImg;
            //保存内容图片并返回地址,拼接成字符串方便存在数据库表中;;
            String contentImgsAddr = "";
            //接受七牛云的返回的URL,拼接成字符串方便存在数据库表中;;
            String cloudImgsAddr = "";

            //新建一个String数组储存图片实际地址用于下面的zip打包传值;注意length一定要和fileMap实际要保存的图片数量保持一致,否则downloadZip类遍历时会抛空指针异常;
            String[] sourcePathArry = new String[fileMap.size()];
            //新建一个String数组用于储存七牛云的图片地址用于在网页引用;
            String[] cloudPathArry = new String[fileMap.size()];

            for (int i = fileMap.size()-1 ; i>=0 ; i -- ){//这里注意有一张封面图的键是"img",不能用i取;倒序是为了保证封面图在第一;
                if (fileMap.size()-1 == i){
                    contentImg = fileMap.get("img");
                }else {
                    contentImg = fileMap.get(i+"");
                }
                String tempImgAddr = ImgUtil.generateThumbnail(contentImg, centreAddr, 1920, 1080);
                //新增zip压缩打包功能,所以将原本的字符直接 += 废弃掉;
                sourcePathArry[i] = PathUtil.getImgBasePath() + tempImgAddr;
                contentImgsAddr = contentImgsAddr+tempImgAddr+",";

                // 新增的保存到七牛云的云储运部分;
                String cloudImgAddr = QiniuCloudUtil.upload(PathUtil.getImgBasePath()+tempImgAddr, tempImgAddr.substring(imgAddr.lastIndexOf("/")+1));//注意+1是为了避开/,否则保存的文件名前面会有一个/,
                //生成七牛云的url的数组
                cloudPathArry[i] = cloudImgAddr;
                cloudImgsAddr = cloudImgsAddr+cloudImgAddr+",";

            }
            //将最后一个","逗号去掉; 已废弃,改为向数据库保存七牛云的cloudImgsAddr
            //contentImgsAddr = contentImgsAddr.substring(0, contentImgsAddr.length() - 1);
            //将最后一个","逗号去掉;
            cloudImgsAddr = cloudImgsAddr.substring(0, cloudImgsAddr.length() - 1);
            /**
             * 开始储存topic实体的图片信息;
             */
            topic.setCoverImgAddr(coverImgAddr);//coverImgAddr已经在上面改成了七牛云的地址;
            topic.setContentImgsAddr(cloudImgsAddr); //已经将原来的comtentImgsAddr 修改成了七牛云的cloudImgsAddr



            /**
             * 新增zip打包功能,将用户上传的图片打包成zip,在页面提供zip下载地址;
             */
            try {// window下将zip名字设置为"D:projectdev/images/upload/topic/{authouid}/{topicid}.zip"
                // Linux下将zip名字设置为"/usr/projectdev/images/upload/topic/{authouid}/{topicid}.zip"
                DownloadZip.downLoadZIP(PathUtil.getImgBasePath()+"/upload"+centreAddr+".zip",sourcePathArry);
                topic.setZipAddr("/upload"+centreAddr+".zip");
                topicService.saveAndFlush(topic);
            } catch (IOException e) {
                e.printStackTrace();
                return Msg.fail().setMsg("投稿失败,保存稿件信息时出现异常");
            }
            //返回注册店铺的最终结果;
            return Msg.success().setMsg("投稿成功,请等待审核");
        } else {
            return Msg.fail().setMsg("投稿失败,稿件信息读取不完整");
        }
    }

    /**
     * 持久化用户essay投稿
     * @param request
     * @return
     */
    @RequestMapping(value = "/essay/contribute",method = RequestMethod.POST)
    @ResponseBody
    private Msg essayContribute(HttpServletRequest request) throws IOException {


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

        //限制稿件标题和正文的字符长度;
        if (30 < essay.getTitle().length() || 300 < essay.getContent().length()){
            return Msg.fail().setMsg("保存失败,稿件标题或内容超出字数限制!");
        }

        User author = (User) request.getSession().getAttribute("user");
        essay.setAuthor(author);
        essay.setStatus(0);

        //从request中解析出上传的文件图片;
        MultipartFile essayImg = ((MultipartRequest) request).getFile("img");

        //原版直接储存在本地的方法;
       /* if (null != essay && null != essayImg) {
            //设置中间文件夹,方便整理图片
            String centreAddr = "/essay/"+author.getId()+"/";
            String imgAddr = ImgUtil.generateThumbnail(essayImg, centreAddr,1920, 1080);
            essay.setImgAddr(imgAddr);
            System.out.print("essayStr的值是:" + essayStr);
            essayService.save(essay);
            //返回注册店铺的最终结果;
            return Msg.success().setMsg("投稿成功,请等待审核.");
        } else {
            return Msg.fail().setMsg("投稿失败,稿件信息不完整!");
        }*/

       //引用七牛云后同步储存在云端的方法
        if (null != essay && null != essayImg) {
            //设置中间文件夹,方便整理图片
            String centreAddr = "/essay/"+author.getId()+"/";
            String imgAddr = ImgUtil.generateThumbnail(essayImg, centreAddr,1920, 1080);
            String filePath = PathUtil.getImgBasePath() + imgAddr;
            String cloudImgAddr = QiniuCloudUtil.upload(filePath, imgAddr.substring(imgAddr.lastIndexOf("/")+1));//注意+1是为了避开/,否则保存的文件名前面会有一个/,

            essay.setImgAddr(cloudImgAddr);//已经从原来的imgAddr 修改成了七牛云的cloudImgAddr;

            System.out.print("essayStr的值是:" + essayStr);
            essayService.save(essay);
            //返回注册店铺的最终结果;
            return Msg.success().setMsg("投稿成功,请等待审核.");
        } else {
            return Msg.fail().setMsg("投稿失败,稿件信息不完整!");
        }
    }

    /**
     * 持久化用户故事投稿
     * @param request
     * @return
     */
    @RequestMapping(value = "/story/contribute",method = RequestMethod.POST)
    @ResponseBody
    private Msg storyContribute(HttpServletRequest request) throws IOException {
        //从前端传来的请求中获取键为shopStr的值;
        String storyStr = RequestUtil.parserString(request, "storyStr");
        System.out.print("storyStr的值是:" + storyStr);
        ObjectMapper objectMapper = new ObjectMapper();
        Story story = null;
        try {
            //将字符串转成实体类
            story = objectMapper.readValue(storyStr, Story.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().setMsg("读取稿件信息失败!");
        }

        //限制稿件标题和摘要的字符长度;
        if (30 < story.getTitle().length() || 300 < story.getSubscribe().length()){
            return Msg.fail().setMsg("保存失败,稿件标题或摘要超出字数限制!");
        }

        User author = (User) request.getSession().getAttribute("user");
        story.setAuthor(author);
        story.setStatus(0);

        //从request中解析出上传的文件图片;
        MultipartFile coverImg = ((MultipartRequest) request).getFile("img");

        //注册店铺,尽可能的减少从前端获取的值;
        if (null != story && null != coverImg) {
            //设置中间文件夹,方便整理图片
            String centreAddr = "/story/"+author.getId()+"/";
            String imgAddr = ImgUtil.generateThumbnail(coverImg, centreAddr,1920, 1080);

            // 新增的保存到七牛云的云储运部分;
            String cloudImgAddr = QiniuCloudUtil.upload(PathUtil.getImgBasePath()+imgAddr, imgAddr.substring(imgAddr.lastIndexOf("/")+1));//注意+1是为了避开/,否则保存的文件名前面会有一个/,

            story.setCommentNumber(0);
            story.setCollectNumber(0);
            story.setCreatTime(new Date(System.currentTimeMillis()));
            story.setCoverImgAddr(cloudImgAddr); //已经从原来的imgAddr 修改成了七牛云的cloudImgAddr;

            storyService.save(story);
            //返回注册店铺的最终结果;
            return Msg.success().setMsg("投稿成功,请等待审核.");
        } else {
            return Msg.fail().setMsg("投稿失败,稿件信息不完整!");
        }
    }

    /**
     * 持久化用户活动投稿
     * @param request
     * @return
     */
    @RequestMapping(value = "/event/contribute",method = RequestMethod.POST)
    @ResponseBody
    private Msg eventContribute(HttpServletRequest request) throws IOException {
        //从前端传来的请求中获取键为userStr的值;
        String eventStr = RequestUtil.parserString(request, "eventStr");
       // System.out.print("eventStr的值是:" + eventStr);
        ObjectMapper objectMapper = new ObjectMapper();
        Event event = null;
        try {
            //将字符串转成实体类
            event = objectMapper.readValue(eventStr, Event.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().setMsg("读取稿件信息失败!");
        }
        User author = (User) request.getSession().getAttribute("user");
        City city = new City();
        if (null != request.getParameter("cityId")){
            String cityId = request.getParameter("cityId");
            city.setId(Long.parseLong(cityId));
        }else {
            city.setId(1);//如果城市参数传递失败则默认选择一个城市,之后管理员审核时可以修改;
        }
        event.setCity(city);
        event.setStatus(0);
        //从request中解析出上传的文件图片;
        MultipartFile coverImg = ((MultipartRequest) request).getFile("img");

        //注册店铺,尽可能的减少从前端获取的值;
        if (null != event && null != coverImg) {
            //设置中间文件夹,方便整理图片
            String centreAddr = "/event/"+author.getId()+"/";
            String imgAddr = ImgUtil.generateThumbnail(coverImg, centreAddr,180, 255);
            // 新增的保存到七牛云的云储运部分;
            String cloudImgAddr = QiniuCloudUtil.upload(PathUtil.getImgBasePath()+imgAddr, imgAddr.substring(imgAddr.lastIndexOf("/")+1));//注意+1是为了避开/,否则保存的文件名前面会有一个/,

            event.setCoverImgAddr(cloudImgAddr); //已经从原来的imgAddr 修改成了七牛云的cloudImgAddr;

            eventService.save(event);
            //返回注册店铺的最终结果;
            return Msg.success().setMsg("投稿成功,请等待审核.");
        } else {
            return Msg.fail().setMsg("投稿失败,稿件信息不完整!");
        }
    }

    /**
     * 保存ue富文本编辑器上传的图片并回显;
     * @param upfile
     * @return
     */
    @RequestMapping(value = "/storyContribute/imgUpload")
    @ResponseBody
    public String imgUpload3(MultipartFile upfile,HttpServletRequest request) {
        if (upfile.isEmpty()) {
            return "error";
        }
        User author = (User) request.getSession().getAttribute("user");
        //设置中间文件夹,方便整理图片
        String centreAddr = "/story/"+author.getId()+"/";
        try {
            //使用工具类保存图片并返回文件名给网页;
            String imgAddr = ImgUtil.generateThumbnail(upfile,centreAddr,1920,1080);
            // 新增的保存到七牛云的云储运部分;
            String cloudImgAddr = QiniuCloudUtil.upload(PathUtil.getImgBasePath()+imgAddr, imgAddr.substring(imgAddr.lastIndexOf("/")+1));//注意+1是为了避开/,否则保存的文件名前面会有一个/,

            //url为文件访问的完整路径,注意应该配合mvc中配置的虚拟路径"/upload"
            String config = "{\"state\": \"SUCCESS\"," +
                    "\"url\": \"" + cloudImgAddr + "\"," +
                    "\"title\": \"" + cloudImgAddr + "\"," +
                    "\"original\": \"" + cloudImgAddr + "\"}";
            return config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 保存ue富文本编辑器上传的图片并回显;
     * @param upfile
     * @return
     */
    @RequestMapping(value = "/eventContribute/imgUpload")
    @ResponseBody
    public String imgUpload4(MultipartFile upfile,HttpServletRequest request) {
        if (upfile.isEmpty()) {
            return "error";
        }

        User author = (User) request.getSession().getAttribute("user");
        //设置中间文件夹,方便整理图片
        String centreAddr = "/event/"+author.getId()+"/";
        try {
            //使用工具类保存图片并返回文件名给网页;
            String imgAddr = ImgUtil.generateThumbnail(upfile,centreAddr,1920,1080);
            // 新增的保存到七牛云的云储运部分;
            String cloudImgAddr = QiniuCloudUtil.upload(PathUtil.getImgBasePath()+imgAddr, imgAddr.substring(imgAddr.lastIndexOf("/")+1));//注意+1是为了避开/,否则保存的文件名前面会有一个/,

            //url为文件访问的完整路径,注意应该配合mvc中配置的虚拟路径"/upload"
            String config = "{\"state\": \"SUCCESS\"," +
                    "\"url\": \"" + cloudImgAddr + "\"," +
                    "\"title\": \"" + cloudImgAddr + "\"," +
                    "\"original\": \"" + cloudImgAddr + "\"}";
            return config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 跳转到topic投稿界面
     * @param model
     * @return
     */
    @RequestMapping("/contribute/topicContribute")
    public String toTopicContributePage(Model model){
        Pageable pageable = new PageRequest(0,100,new Sort(Sort.Direction.DESC,"rank"));
        Page<Category> categoryPage = categoryService.findAll(pageable);
        model.addAttribute("categoryPage",categoryPage);
        return "contribute/topicContribute";
    }

    @RequestMapping("/contribute/eventContribute")
    public String toEventContributePage(Model model){
        Pageable pageable = new PageRequest(0,200,new Sort(Sort.Direction.ASC,"rank"));
        Page<City> cityPage = cityService.findAll(pageable);
        model.addAttribute("cityPage",cityPage);
        return "contribute/eventContribute";
    }

    @RequestMapping("/contribute/storyContribute")
    public String toStoryContributePage(){
        return "contribute/storyContribute";
    }

    @RequestMapping("/contribute/essayContribute")
    public String toEssayContributePage(){
        return "contribute/essayContribute";
    }

    @RequestMapping("/ue")
    public String toUEPage(){
        return "portal/ue";
    }




}
