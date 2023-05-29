package com.caizhen.gateway.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author caizhen
 * @since 2023-05-17
 */
@RestController
@RequestMapping("/gateway")
public class TestController {


    /**
     * 测试 nacos  提供回显服务
     *
     * @param string 字符串
     * @return 字符串
     */
    @RequestMapping(value = "/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        System.out.println("gateway服务");
        return "echo-gateway-: " + string;
    }
}
