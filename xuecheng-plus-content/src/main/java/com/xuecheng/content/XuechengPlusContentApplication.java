package com.xuecheng.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xuecheng.content", "com.xuecheng.base"})
public class XuechengPlusContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(XuechengPlusContentApplication.class, args);
    }

}
