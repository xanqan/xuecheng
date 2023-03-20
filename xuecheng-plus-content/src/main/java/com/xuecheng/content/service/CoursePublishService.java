package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.po.CoursePublish;

/**
 * @author 28961
 * @description 针对表【course_publish(课程发布)】的数据库操作Service
 * @createDate 2023-03-15 23:42:24
 */
public interface CoursePublishService extends IService<CoursePublish> {

    /**
     * @param courseId 课程id
     * @return com.xuecheng.content.model.dto.CoursePreviewDto
     * @description 获取课程预览信息
     * @author Mr.M
     * @date 2022/9/16 15:36
     */
    CoursePreviewDto getCoursePreviewInfo(Long courseId);

    /**
     * @param courseId 课程id
     * @return void
     * @description 提交审核
     * @author Mr.M
     * @date 2022/9/18 10:31
     */
    void commitAudit(Long companyId, Long courseId);

    /**
     * @param companyId 机构id
     * @param courseId  课程id
     * @return void
     * @description 课程发布接口
     * @author Mr.M
     * @date 2022/9/20 16:23
     */
    void publish(Long companyId, Long courseId);

}
