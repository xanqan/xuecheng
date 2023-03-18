package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 28961
 * @description 针对表【course_category(课程分类)】的数据库操作Mapper
 * @createDate 2023-03-15 23:42:24
 * @Entity com.xuecheng.content.model.po.CourseCategory
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {

    @Select("with recursive t1 as (\n" +
            "        select * from  course_category p where  id= #{id}\n" +
            "        union all\n" +
            "        select t.* from course_category t inner join t1 on t1.id = t.parentid\n" +
            "    )\n" +
            "    select *  from t1 order by t1.id, t1.orderby")
    List<CourseCategoryTreeDto> selectTreeNodes(@Param("id") String id);

}




