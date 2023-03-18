package com.xuecheng.content.service;

import cn.hutool.core.lang.Console;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class CourseCategoryServiceTest {

    @Resource
    private CourseCategoryService categoryService;

    @Test
    void testQueryTreeNodes() {
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = categoryService.queryTreeNodes("1");
        Console.log(courseCategoryTreeDtos);
    }

}