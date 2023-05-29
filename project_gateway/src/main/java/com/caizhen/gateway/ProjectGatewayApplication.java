package com.caizhen.gateway;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author 86184
 */
@EnableDiscoveryClient
@Slf4j
@SpringBootApplication
@MapperScan(basePackages = "com.caizhen.mvc.mapper")
public class ProjectGatewayApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =   SpringApplication.run(ProjectGatewayApplication.class, args);

        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        String info = env.getProperty("config.info");
        String contextpath = env.getProperty("server.servlet.context-path");
        System.out.println(port+"------------"+info);
        System.out.println("- ->"+contextpath+"<- -");
    }


}
