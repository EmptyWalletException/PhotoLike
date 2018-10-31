package com.kingguanzhang.toptalk.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kingguanzhang.toptalk.common.utils.PathUtil;

/**
 * 扩展springMvc
 */
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
         * 说明：增加虚拟路径(经过测试：在此处配置的虚拟路径，用springboot内置的tomcat时有效，
         * 用外部的tomcat也有效;所以用到外部的tomcat时不需在tomcat/config下的相应文件配置虚拟路径了,阿里云linux也没问题)
         */
        //此处会根据操作系统不同而设置不同的图片资源路径；
        registry.addResourceHandler("/upload/**").addResourceLocations(PathUtil.getResourceLocations());

        super.addResourceHandlers(registry);
    }

    /**
     * 设置文件上传的临时目录
     * @return
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //此处会根据操作系统不同而设置不同的文件上传路径；
        factory.setLocation(PathUtil.getUploadTempLocation());
        return factory.createMultipartConfig();
    }




}
