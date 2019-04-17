package com.reptile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    /**
     * addResourceHandler() 添加请求路径方法
     * 注意:/video/** 改为 /**会覆盖默认的映射(classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/)
     *      这种情况下项目路径下的resources目录中的所有文件都不能访问,
     *      解决办法:addResourceLocations(String... resourceLocations) 依次把映射路径加入进去
     *      registry.addResourceHandler("/**").addResourceLocations("classpath:/resources/","classpath:/resources/","file:F:/迅雷下载/").;
     * addResourceLocations() 添加映射路径方法
     * 注意:必须要加 file:或classpath: 否则无法访问
     *      file:绝对路径下的映射
     *      classpath:相对路径下的映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //迅雷下载文件夹下有a.mp4,访问路径:http:xxxx/video/a.mp4
        registry.addResourceHandler("/images/**").addResourceLocations("file:D:/File/");
        super.addResourceHandlers(registry);
    }
}
