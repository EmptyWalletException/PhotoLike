TopTalk网站,照片 壁纸 绘画和涂鸦爱好者书写情感的地方.

使用SpringBoot搭建的项目,使用Mysql数据库,Spring Data JPA,Security,Redis,Thymeleaf等流行的框架和组件;

项目预览效果在根目录screenshot文件中;

最近喜欢在luoo网上听歌,感觉luoo网的前端页面设计得很简洁漂亮,板块全面而且功能不杂乱,虽然此网站是一个在线听歌的网站,但是板块却很适合用于论坛或博客等项目,于是我将页面全部另存为本地文件后准备改造成自己的论坛;

网站分为四个大板块:
     topic 专题壁纸分享板块(例如巴黎专辑,樱花专辑,建筑专辑) ; 
     essay 单张壁纸分享板块 (例如微软bing的每日一张壁纸推荐) , 
     story 有故事的系列壁纸分享板块(例如游记,电影截图影评) , 
     event 组织网友活动的板块(例如旅游,聚会,照相等);
     
目前用户可以在四个板块投稿,story板块和event板块的投稿引入了百度富文本编辑器,topic板块的投稿可以一次上传多张图片;

注意事项,项目中url地址中带/upload的是映射本地文件夹D:\projectdev\images\upload,所有图片会存在此文件夹内子文件夹中,可以先测试投稿页面再查看项目运行效果,否则部分页面板式会混乱或报错;

2018/7/05:

1:整合了security,新增角色实体类Role,角色与用户关联的实体类RoleRelateUser,数据库新增role表和role_user表;
同时提交了测试用sql数据库数据文件;

2:完成了用户登录和用户注册功能;当用户登录时security会从数据库中找到用户账号对应的角色信息判断是否具有对应网页的权限;

3:移除了弹出式登录框和注册框,改为登录页面和注册页面,这是为了更好的配合security权限组件;

2018/7/04:

1:修改了一下用户收藏页面和用户投稿查看页面;

2018/7/03:

1:完成了活动投稿功能;

2:完善了活动城市分类筛选功能,完善了活动详情页及列表页页面显示效果;

2018/7/02:

1:新增在页面导航条上点击登录链接时弹出登录框,点击关闭则关闭登录框;

2:新增注册框,登录和注册功能暂时放到后面与security一起完成;

2018/7/01:

1:新增了文件上传时自动打包成zip文件的功能,同时设置所有上传图片以1080p分辨率保存;专辑页面新增下载zip文件的功能,点击后可以下载专辑页面所有图片的zip压缩包;随笔页面可以点击图片查看原图,故事页面可以右击图片保存原图;

2:完善了各板块的页面显示效果;将上次强制缩放为1.42比例的图片更改为1080P的16:9的图片,这样用户就可以下载高清的图片作为壁纸,同时也可以上传自己的壁纸,板块开发需求从侧重文字抒情改为分享精美壁纸;

2018/6/30:

1:完成了随笔投稿功能,用户可以投稿随笔,撰写随笔标题,上传随笔封面图片,撰写随笔正文,目前会强制缩放用户上传的图片,将其缩放成长宽比为1.42的矩形,在网上找了一下页面裁剪图片的插件,发现都是js记录并发送裁剪的数据和原图,还需要后台去处理裁剪数据,由于时间不够充裕,所以留着以后完善此功能;

2:完成了故事和专辑的投稿功能;

3:新增了专辑页面的轮播图功能;

4:废弃掉了专辑与分类的多对多的关系,修改为专辑与分类的多对一关系,因此修改了相关实体类与数据库表结构;

2018/6/29:

1:发现Umeditor 存在段落样式无效的问题,已经换成了完整版的Ueditor,测试段落样式可以有效使用;

2:完成了Ueditory富文本编辑器的图片上传保存和回显;

2018/6/28:

1:完成了专辑投稿页面的前端;使用js完成了封面单图选择和回显,以及多图片上传的选择和回显,

2:完成了故事投稿页面的百度富文本编辑器Umeditor 的前端页面整合;

2018/6/27:

1:完成了topic和story板块的评论加载和子评论加载;父评论翻页以完成,子评论还没有完成翻页功能;

2:修复了当查询的记录为0时topic和story总页面尾页参数为0的错误;

2018/6/26:

1:在开发查询用户信息时发现jpa的example查询会查出不区分大小写的记录;
        
        //创建的example,withMatcher()已经设置了属性account区分大小写,然后又将整个example的withIgnoreCase()忽略大小写设置成了false,
            ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("account",ExampleMatcher.GenericPropertyMatchers.caseSensitive()).withIgnorePaths("id").withIgnoreCase(false);
        //然而还是查询多条不区分大小写的记录,原因在于mysql数据库查询时不区分大小写;
            报错信息:query did not return a unique result: 3; nested exception is javax.persistence.NonUniqueResultException: query did not return a unique result: 3
        //解决方法,以下方法任选一个即可:
        //1:在查询语句中加上binary;  
            select * from user where account = binary 'testuser'
        //2:在mysql设置文件里开启区别大小写;可以修改my.ini或者my.cnf 
                [mysqld] 
                lower_case_table_names=1 
                （0：区分；1：不区分）
        //3:修改表中的字段使其区别大小写;
            alter table user change account account varchar(225) binary;
  
2:完成了浏览用户发布的专辑,随笔,故事三个页面,并完善了一下用户收藏页面的逻辑,当查询不到记录时显示提示;     

2018/6/25:

1:完成了用户信息里的随笔收藏,专辑收藏,故事收藏这三个板块的浏览页面;

2018/6/24:

1:完成了各个板块除评论功能外基本的页面渲染及链接的跳转;

2018/6/23:

1:完成了通过分类id查询topic并且返回page<Topic>的功能,解决的问题:开发时遇到自定义查询能完成查询但是却不能分页,总是查询出所有的记录,在网上搜索了很多遇到相同问题的记录,后来有网友解释早先版本是因为bug问题不能按照官方文档上的说明来实现分页,解决方案是在语句后加order by ?#{#pageable}来实现分页,但是从spring 2.0.4版本之后修复了这个bug,分页排序可以直接传pageable,加了这段语句反而不能分页,
        
        spring2.0.4之前需要在value语句后加ORDER BY ?#{#pageable}并且在接口方法里传pageable来实现分页和排序 ;
        spring2.0.4之后直接传pageable即可,再加ORDER BY ?#{#pageable}反而不能分页排序;       
         @Query(nativeQuery = true, value = "select * from topic  where id in (select topic_id from category_topic where category_id= :categoryId)",//ORDER BY ?#{#pageable}
                    countQuery = "select count(*) from topic  where id in (select topic_id from category_topic where category_id= :categoryId)")
            Page<Topic> findByCategoryId(@Param("categoryId")Long categoryId,Pageable pageable);

2018/6/22:

1:经过跟网友的讨论,重新整理了实体类,废弃了多对多的级联映射,以提高性能及数据操作的灵活性;

2:在数据库中重新注入了测试数据;

3:实现了首页及专辑页的不带条件的查询的页面显示,接下来需要实现点击分类后进行分页查询并显示;

2018/6/21 : 

1:根据前端页面及功能设计了一些实体类,通过hibernate注解配置好了实体类及实体类之间的级联关系,生成了数据库表;

2:增删改查已经通过测试,目前发现的问题:在Java9中已经移除了部分jarEE包导致hibernate会报错java.lang.ClassNotFoundException: javax.xml.bind.JAXBException
       
                
                <!--下面四个jar包是由于java9移除了这些jarEE包导致hibernate运行时抛出java.lang.ClassNotFoundException: javax.xml.bind.JAXBException-->
                <dependency>
                    <groupId>javax.xml.bind</groupId>
                    <artifactId>jaxb-api</artifactId>
                    <version>2.3.0</version>
                </dependency>
                <dependency>
                    <groupId>com.sun.xml.bind</groupId>
                    <artifactId>jaxb-impl</artifactId>
                    <version>2.3.0</version>
                </dependency>
                <dependency>
                    <groupId>com.sun.xml.bind</groupId>
                    <artifactId>jaxb-core</artifactId>
                    <version>2.3.0</version>
                </dependency>
                <dependency>
                    <groupId>javax.activation</groupId>
                    <artifactId>activation</artifactId>
                    <version>1.2.0</version>
                </dependency>
另外在执行批量save时,注意saveAndFlush会返回id导致批量持久化会报错说重复的id字段;批量save时注意要在for循环里new对象才能保证id为空,否则只会不停的update数据导致只保存了一条记录;

2018/6/20 : 创建项目,导入前端页面;
