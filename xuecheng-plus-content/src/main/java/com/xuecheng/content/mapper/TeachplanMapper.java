package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 28961
 * @description 针对表【teachplan(课程计划)】的数据库操作Mapper
 * @createDate 2023-03-15 23:42:25
 * @Entity com.xuecheng.content.model.po.Teachplan
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {
    /**
     * @param courseId
     * @return com.xuecheng.content.model.dto.TeachplanDto
     * @description 查询某课程的课程计划，组成树型结构
     * @author Mr.M
     * @date 2022/9/9 11:10
     */
    List<TeachplanDto> selectTreeNodes(@Param("value") long courseId);

}




