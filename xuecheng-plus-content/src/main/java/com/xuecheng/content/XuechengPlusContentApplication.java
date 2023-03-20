package com.xuecheng.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.xuecheng", "com.xuecheng.base"})
@EnableFeignClients(basePackages = {"com.xuecheng.content.feignclient"})
public class XuechengPlusContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(XuechengPlusContentApplication.class, args);
    }

}
