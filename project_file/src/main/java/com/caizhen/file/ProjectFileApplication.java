package com.caizhen.file;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author caizhen
 */
@EnableDiscoveryClient
@Slf4j
@ComponentScan(basePackages = {"com.caizhen.file","com.caizhen.mvc","com.caizhen.pojo",
        "com.caizhen.config","com.caizhen.commonutils",})
@SpringBootApplication
@MapperScan(basePackages = "com.caizhen.mvc.mapper")
public class ProjectFileApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext =   SpringApplication.run(ProjectFileApplication.class, args);

        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        String info = env.getProperty("config.info");
        String contextpath = env.getProperty("server.servlet.context-path");
        System.out.println(port+"------------"+info);
        System.out.println("- ->"+contextpath+"<- -");



    }

}
