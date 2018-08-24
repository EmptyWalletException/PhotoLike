package com.kingguanzhang.toptalk.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * 扩展springMvc
 */
//@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    /*注册视图解析器,所有在localhost:8080后面加上的路径会被springMvc以返回的字符串解析
    并在templates路径下寻找对应的html*/
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("portal/index");
        registry.addViewController("/index").setViewName("portal/index");
        registry.addViewController("/index.html").setViewName("portal/index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
    }


    /*配置了security权限组件后就不需要此处的配置*/
   // @Override
 //   public void addInterceptors(InterceptorRegistry registry) {
 //       registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
 //               .excludePathPatterns("/","/index","/login","/index.html","/login.html","/user/login","**/**/*.css","**/**/*.js","**/**/*.jpg");

 //   }

    //配置uri资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /*
         * 说明：增加虚拟路径(经过本人测试：在此处配置的虚拟路径，用springboot内置的tomcat时有效，
         * 用外部的tomcat也有效;所以用到外部的tomcat时不需在tomcat/config下的相应文件配置虚拟路径了,阿里云linux也没问题)
         */
        //registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/projectdev/images/upload/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/home/projectdev/images/upload/");

        super.addResourceHandlers(registry);
    }

    /**
     * 设置文件上传的临时目录
     * @return
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //factory.setLocation("D:/temp");
        factory.setLocation("/usr/temp");
        return factory.createMultipartConfig();
    }



/*    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setMaxInMemorySize(409600);
        resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
        return resolver;
    }*/

}
