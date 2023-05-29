package com.caizhen.apiuser.feign;

import com.caizhen.pojo.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author canonzhen
 * @name project2305
 * @date 2023/5/26  15:27
 */
@Component
@FeignClient(name = "service-api-translate", path = "/translate")
public interface TranslateApiFeignService {

    /**
     * 翻译
     * @param query  要翻译的文本
     * @param from   源语种
     * @param to   目标语种
     * @return 文本
     */
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public String query(@RequestParam("query") String query,
                        @RequestParam("from") String from, @RequestParam("to") String to);


    /**
     *  测试restTemplate 负载均衡
     * @return result
     */
    @GetMapping("/test")
    public Result consumerTest();

    /**
     * 测试 nacos  提供回显服务
     * @param string 字符串
     * @return 字符串
     */
    @RequestMapping(value = "/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string);
}
