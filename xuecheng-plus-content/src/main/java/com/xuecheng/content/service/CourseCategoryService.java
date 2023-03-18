package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;

import java.util.List;

/**
 * @author 28961
 * @description 针对表【course_category(课程分类)】的数据库操作Service
 * @createDate 2023-03-15 23:42:24
 */
public interface CourseCategoryService extends IService<CourseCategory> {

    /**
     * 课程分类树形结构查询
     *
     * @return
     */
    List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
