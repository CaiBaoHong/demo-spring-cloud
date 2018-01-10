package com.abc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 这里也可以使用@SpringCloudApplication来代替@EnableCircuitBreaker、@EnableDiscoveryClient、@SpringBootApplication
 * 三个注解。意味着一个SpringCloud标准应用应该包含服务发现和断路器
 */
@EnableCircuitBreaker//断路器
@EnableDiscoveryClient//添加服务发现能力
@SpringBootApplication
public class RibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class,args);
    }

    //===================================

    @LoadBalanced//开启客户端负载均衡
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
