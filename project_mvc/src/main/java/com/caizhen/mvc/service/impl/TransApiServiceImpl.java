package com.caizhen.mvc.service.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.caizhen.mvc.service.TransApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.caizhen.commonutils.utils.Md5Utils;

/**
 * 调用百度翻译API
 * @author caizhen
 * @since 2023-05-17
 */
@Service
public class TransApiServiceImpl implements TransApiService {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    @Value("${baidu_translate.appid}")
    private String appid;
    @Value("${baidu_translate.securityKey}")
    private String securityKey;

    // 添加了一个无参构造器，不然无法注入这个bean，否则需要额外的配置。
    public TransApiServiceImpl() {}

    @Autowired
    private RestTemplate restTemplate;

    public TransApiServiceImpl(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

	public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.setAll(params);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TRANS_API_HOST);
		URI uri = builder.queryParams(requestParams).build().encode().toUri();  //  这里不能进行 encode 了，编码就错误了
		System.out.println("uri: " + uri);
        return restTemplate.getForObject(uri, String.class);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", Md5Utils.md5(src));

        return params;
    }

}

