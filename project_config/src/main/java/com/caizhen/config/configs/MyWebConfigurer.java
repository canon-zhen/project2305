package com.caizhen.config.configs;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author canonzhen
 * @date 2023/5/17
 */
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {

    /**
     * 资源访问映射 拦截器
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/userPhoto/**").addResourceLocations("file:" + "D:/softwareData/ideaProjects/white_jotter/img/");
    }
    //http://localhost:8975/file/userPhoto/wj9wp7.jpg




}
