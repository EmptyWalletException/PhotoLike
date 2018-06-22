TopTalk网站,照片 壁纸 绘画和涂鸦爱好者书写情感的地方.

使用SpringBoot搭建的项目,使用Mysql数据库,Spring Data JPA,Security,Redis,Thymeleaf等流行的框架和组件;

最近喜欢在luoo网上听歌,感觉luoo网的前端页面设计得很简洁漂亮,板块全面而且功能不杂乱,虽然此网站是一个在线听歌的网站,但是板块却很适合用于论坛或博客等项目,于是我将页面全部另存为本地文件后准备改造成自己的论坛;

2018/6/21 : 创建项目,导入前端页面;

2018/6/22 : 

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