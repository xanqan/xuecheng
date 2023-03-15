package com.xuecheng.content;

import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XuechengPlusContentApplicationTests {

    @Resource
    private CourseBaseService courseBaseService;

    @Test
    void contextLoads() {
        CourseBase courseBase = courseBaseService.getById(74L);
        System.out.println(courseBase);
    }

}
