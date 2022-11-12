package com.hlw.config;

import com.hlw.handler.MyPathInterceptors;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author zzy
 * @desc
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ApplicationHome home = new ApplicationHome();
        File dir = home.getDir();
        String resources = dir.getAbsolutePath()+"\\target\\static\\";
        System.out.println("图片存放地址"+resources);
        registry.addResourceHandler("/assets/**").addResourceLocations("file:"+resources);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyPathInterceptors())
                .addPathPatterns("/**")
                .excludePathPatterns("/admin","/adminLogin","/","/login","/register","/user/addUser");
    }
}
