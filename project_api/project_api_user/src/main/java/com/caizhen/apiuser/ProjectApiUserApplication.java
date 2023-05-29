package com.caizhen.apiuser;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author caizhen
 */
@EnableFeignClients(basePackages = {"com.caizhen.feign"})
@EnableDiscoveryClient
@Slf4j
@ComponentScan(basePackages = {"com.caizhen.apiuser","com.caizhen.mvc","com.caizhen.pojo",
        "com.caizhen.api_translate", "com.caizhen.config",
        "com.caizhen.feign","com.caizhen.commonutils",})
@SpringBootApplication
@MapperScan(basePackages = "com.caizhen.mvc.mapper")
public class ProjectApiUserApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext =   SpringApplication.run(ProjectApiUserApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        String info = env.getProperty("config.info");
        String contextpath = env.getProperty("server.servlet.context-path");
        System.out.println(port+"------------"+info);
        System.out.println("- ->"+contextpath+"<- -");
    }

}
