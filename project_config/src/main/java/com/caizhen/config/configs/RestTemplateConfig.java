package com.caizhen.config.configs;

import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 在这里配置我们自定义的LoadBalancer策略 如果想自己扩展算法 需要实现ReactorServiceInstanceLoadBalancer接口
 * /@LoadBalancerClients(defaultConfiguration = {name = "CLOUD-PAYMENT-SERVICE", configuration = MyLoadBalancerConfig.class})
 * 注意这里的name属性 需要和Nacos页面中的服务提供者名字一致
 * @author canonzhen
 * @name project2305
 * @date 2023/5/24  15:31
 */
@Configuration
@LoadBalancerClient(name = "service-api-user", configuration = MyLoadBalancerConfig.class)
public class RestTemplateConfig {
    @Bean
//    @LoadBalanced
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate() {
//        return new RestTemplate(getClientHttpRequestFactory());
        return new RestTemplate();
    }

    /**
     * 使用OkHttpClient作为底层客户端
     * @return 底层客户端
     */
    private ClientHttpRequestFactory getClientHttpRequestFactory(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }
}
