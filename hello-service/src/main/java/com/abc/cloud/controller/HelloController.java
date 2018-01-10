package com.abc.cloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();

        //模拟处理超时
        int sleepTime = new Random().nextInt(3000);
        logger.info("HELLO-SERVICE mock timeout, sleep: {} ms",sleepTime);
        Thread.sleep(sleepTime);

        logger.info("/hello, host:{}, serviceId:{}",instance.getHost(),instance.getServiceId());
        return "Hello World, this message is provided by Hello-Service";
    }

}
