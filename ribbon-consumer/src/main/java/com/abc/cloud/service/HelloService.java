package com.abc.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello(){

        long start = System.currentTimeMillis();

        String response = restTemplate
                //调用在服务注册中心注册了的HELLO-SERVICE的hello接口
                .getForEntity("http://HELLO-SERVICE/hello",String.class)
                .getBody();

        long end = System.currentTimeMillis();

        logger.info("RIBBON-CONSUMER invoke HELLO-SERVICE.hello(), Spent time: {} ms",(end-start));

        return response;
    }

    public String helloFallback(){
        return "Invoke HELLO-SERVICE fail";
    }



}
