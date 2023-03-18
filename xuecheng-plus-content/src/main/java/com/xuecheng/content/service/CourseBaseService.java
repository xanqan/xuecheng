package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author 28961
 * @description 针对表【course_base(课程基本信息)】的数据库操作Service
 * @createDate 2023-03-15 23:42:24
 */
public interface CourseBaseService extends IService<CourseBase> {

    /**
     * @param pageParams           分页参数
     * @param queryCourseParamsDto 条件条件
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
     * @description 课程查询接口
     * @author Mr.M
     * @date 2022/9/6 21:44
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * @param companyId    教学机构id
     * @param addCourseDto 课程基本信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 添加课程基本信息
     * @author Mr.M
     * @date 2022/9/7 17:51
     */
    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

    /**
     * @param courseId 课程id
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 根据id查询课程基本信息
     * @author Mr.M
     * @date 2022/10/9 8:13
     */
    CourseBaseInfoDto getCourseBaseInfo(long courseId);

    /**
     * @param companyId 机构id
     * @param dto       课程信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 修改课程信息
     * @author Mr.M
     * @date 2022/9/8 21:04
     */
    CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto);
}
