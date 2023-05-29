package com.caizhen.config.configs;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * 注意不要加 @Configuration
 * 如果没有加@Configuration 注解那么配了的服务会使用配置的负载均衡策略，没有配的服务会使用默认的策略，可以避免报错
 * @author canonzhen
 * @name project2305
 * @date 2023/5/24  14:50
 */
public class MyLoadBalancerConfig {
    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
                                                            LoadBalancerClientFactory loadBalancerClientFactory){
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        System.out.println(name+"--------------------------->project_config-loadbalancerConfig");
        //随机轮询
        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name,ServiceInstanceListSupplier.class),name);
    }
}
