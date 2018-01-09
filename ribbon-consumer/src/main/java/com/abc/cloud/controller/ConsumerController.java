package com.abc.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consume")
    public String consume(){
        return restTemplate
                //调用在服务注册中心注册了的HELLO-SERVICE的hello接口
                .getForEntity("http://HELLO-SERVICE/hello",String.class)
                .getBody();
    }


}
