package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.dto.BindTeachplanMediaDto;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;

import java.util.List;

/**
 * @author 28961
 * @description 针对表【teachplan(课程计划)】的数据库操作Service
 * @createDate 2023-03-15 23:42:25
 */
public interface TeachplanService extends IService<Teachplan> {

    /**
     * @param courseId 课程id
     * @return List<TeachplanDto>
     * @description 查询课程计划树型结构
     * @author Mr.M
     * @date 2022/9/9 11:13
     */
    List<TeachplanDto> findTeachplayTree(long courseId);

    /**
     * @param teachplanDto 课程计划信息
     * @return void
     * @description 只在课程计划
     * @author Mr.M
     * @date 2022/9/9 13:39
     */
    void saveTeachplan(SaveTeachplanDto teachplanDto);

    /**
     * @param bindTeachplanMediaDto
     * @return com.xuecheng.content.model.po.TeachplanMedia
     * @description 教学计划绑定媒资
     * @author Mr.M
     * @date 2022/9/14 22:20
     */
    TeachplanMedia associationMedia(BindTeachplanMediaDto bindTeachplanMediaDto);


}
