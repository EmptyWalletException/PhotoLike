TopTalk网站,照片 壁纸 绘画和涂鸦爱好者书写情感的地方.

使用SpringBoot搭建的项目,使用Mysql数据库,Spring Data JPA,Security,Redis,Thymeleaf等流行的框架和组件;

最近喜欢在luoo网上听歌,感觉luoo网的前端页面设计得很简洁漂亮,板块全面而且功能不杂乱,虽然此网站是一个在线听歌的网站,但是板块却很适合用于论坛或博客等项目,于是我将页面全部另存为本地文件后准备改造成自己的论坛;

2018/6/25:

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

2018/6/21 : 创建项目,导入前端页面;

2018/6/20 : 

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