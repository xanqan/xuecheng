package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseBaseServiceTest {

    @Autowired
    private CourseBaseService courseBaseService;

    @Test
    void testCourseBaseService() {
        CourseBase courseBase = courseBaseService.getById(74L);
        Assertions.assertNotNull(courseBase);
    }

}