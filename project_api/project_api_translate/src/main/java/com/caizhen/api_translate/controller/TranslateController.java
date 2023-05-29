package com.caizhen.api_translate.controller;



import com.caizhen.config.annotation.MyLog;
import com.caizhen.mvc.service.TransApiService;
import com.caizhen.mvc.service.impl.TransApiServiceImpl;
import com.caizhen.pojo.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author caizhen
 * @since 2023-05-17
 */
@RestController
@RequestMapping("/translate")
public class TranslateController {

    @Autowired
    private TransApiServiceImpl transApiService;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 翻译
     * @param query  要翻译的文本
     * @param from   源语种
     * @param to   目标语种
     * @return 文本
     */
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    @MyLog(title = "api-translate模块", content = "翻译文本")
    public String query(@RequestParam("query") String query,
                        @RequestParam("from") String from, @RequestParam("to") String to) {
        System.out.println("api-translate------->query翻译一次");
        return transApiService.getTransResult(query, from, to);
    }

    /**
     *  测试restTemplate 负载均衡
     * @return result
     */
    @GetMapping("/test")
    public Result consumerTest() {
        String serviceUrl="http://service-api-translate";
        Result result = restTemplate.getForObject(serviceUrl + "/user/list", Result.class);
        System.out.println(result.toString()+"------------------------------------****************");
        return result;
    }
    /**
     * 测试 nacos  提供回显服务
     * @param string 字符串
     * @return 字符串
     */
    @RequestMapping(value = "/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        System.out.println("api-translate-->服务");
        return "echo-api-translate: " + string;
    }

}
