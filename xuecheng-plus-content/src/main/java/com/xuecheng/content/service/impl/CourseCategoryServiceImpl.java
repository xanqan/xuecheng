package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.service.CourseCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 28961
 * @description 针对表【course_category(课程分类)】的数据库操作Service实现
 * @createDate 2023-03-15 23:42:24
 */
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory>
        implements CourseCategoryService {

    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        // 查询数据库得到的课程分类
        List<CourseCategoryTreeDto> courseCategoryTreeDtos =
                courseCategoryMapper.selectTreeNodes(id);
        //最终返回的列表
        List<CourseCategoryTreeDto> categoryTreeDtos = new ArrayList<>();
        HashMap<String, CourseCategoryTreeDto> map = new HashMap<>();

        courseCategoryTreeDtos.forEach(item -> {
            map.put(item.getId(), item);
            // 只将根节点的下级节点放入list
            if (item.getParentid().equals(id)) {
                categoryTreeDtos.add(item);
            }

            // 向节点的下级节点list加入节点
            CourseCategoryTreeDto courseCategoryTreeDto = map.get(item.getParentid());
            if (courseCategoryTreeDto != null) {
                if (courseCategoryTreeDto.getChildrenTreeNodes() == null) {
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<>());
                }
                courseCategoryTreeDto.getChildrenTreeNodes().add(item);
            }
        });
        return categoryTreeDtos;
    }
}




