package com.xuecheng.content.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.execption.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.service.CourseBaseService;
import com.xuecheng.content.service.CourseMarketService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 28961
 * @description 针对表【course_base(课程基本信息)】的数据库操作Service实现
 * @createDate 2023-03-15 23:42:24
 */
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase>
        implements CourseBaseService {

    @Resource
    private CourseMarketMapper courseMarketMapper;

    @Resource
    private CourseMarketService courseMarketService;

    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        // 拼装查询条件
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        // 根据名称模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(queryCourseParamsDto.getCourseName()), CourseBase::getName, queryCourseParamsDto.getCourseName());
        // 根据课程审核状态查询
        queryWrapper.eq(StrUtil.isNotEmpty(queryCourseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus());

        // 分页对象
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<CourseBase> pageResult = this.page(page, queryWrapper);
        // 获取数据列表
        List<CourseBase> items = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();

        return new PageResult<>(items, total, pageParams.getPageNo(), pageParams.getPageSize());
    }

    @Transactional
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto dto) {
        //合法性校验
        if (StrUtil.isBlank(dto.getName())) {
            throw new XueChengPlusException("课程名称为空");
        }

        if (StrUtil.isBlank(dto.getMt())) {
            throw new XueChengPlusException("课程分类为空");
        }

        if (StrUtil.isBlank(dto.getSt())) {
            throw new XueChengPlusException("课程分类为空");
        }

        if (StrUtil.isBlank(dto.getGrade())) {
            throw new XueChengPlusException("课程等级为空");
        }

        if (StrUtil.isBlank(dto.getTeachmode())) {
            throw new XueChengPlusException("教育模式为空");
        }

        if (StrUtil.isBlank(dto.getUsers())) {
            throw new XueChengPlusException("适应人群为空");
        }

        if (StrUtil.isBlank(dto.getCharge())) {
            throw new XueChengPlusException("收费规则为空");
        }
        //新增对象
        CourseBase courseBaseNew = new CourseBase();
        //将填写的课程信息赋值给新增对象
        BeanUtils.copyProperties(dto, courseBaseNew);
        //设置审核状态
        courseBaseNew.setAuditStatus("202002");
        //设置发布状态
        courseBaseNew.setStatus("203001");
        //机构id
        courseBaseNew.setCompanyId(companyId);
        //添加时间
        courseBaseNew.setCreateDate(new Date());
        //插入课程基本信息表
        boolean insert = this.save(courseBaseNew);
        Long courseId = courseBaseNew.getId();
        //课程营销信息
        CourseMarket courseMarketNew = new CourseMarket();
        BeanUtils.copyProperties(dto, courseMarketNew);
        courseMarketNew.setId(courseId);
        //收费规则
        String charge = dto.getCharge();

        //收费课程必须写价格且价格大于0
        if ("201001".equals(charge)) {
            BigDecimal price = dto.getPrice();
            if (price == null || price.floatValue() <= 0) {
                throw new XueChengPlusException("课程设置了收费价格不能为空且必须大于0");
            }
        }

        //插入课程营销信息
        int insert1 = courseMarketMapper.insert(courseMarketNew);

        if (!insert || insert1 <= 0) {
            throw new XueChengPlusException("新增课程基本信息失败");
        }
        //添加成功
        //返回添加的课程信息
        return getCourseBaseInfo(courseId);
    }

    @Override
    public CourseBaseInfoDto getCourseBaseInfo(long courseId) {

        CourseBase courseBase = this.getById(courseId);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

        if (courseBase == null) {
            return null;
        }
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        if (courseMarket != null) {
            BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);
        }

        //查询分类名称
        CourseCategory courseCategoryBySt = courseCategoryMapper.selectById(courseBase.getSt());
        courseBaseInfoDto.setStName(courseCategoryBySt.getName());
        CourseCategory courseCategoryByMt = courseCategoryMapper.selectById(courseBase.getMt());
        courseBaseInfoDto.setMtName(courseCategoryByMt.getName());

        return courseBaseInfoDto;
    }

    @Transactional
    @Override
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto) {
        Long couresId = dto.getId();
        CourseBase courseBaseUpdate = this.getById(couresId);
        if (courseBaseUpdate == null) {
            XueChengPlusException.cast("课程信息不存在");
        }
        if (!companyId.equals(courseBaseUpdate.getCompanyId())) {
            XueChengPlusException.cast("只允许修改本机构的课程");
        }

        // 更新
        BeanUtils.copyProperties(dto, courseBaseUpdate);
        courseBaseUpdate.setChangeDate(new Date());
        boolean i = this.updateById(courseBaseUpdate);

        // 查询营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(couresId);
        if (courseMarket == null) {
            courseMarket = new CourseMarket();
        }

        // 判断是否收费
        String charge = dto.getCharge();
        if ("201001".equals(charge)) {
            BigDecimal price = dto.getPrice();
            if (price == null || price.floatValue() <= 0) {
                XueChengPlusException.cast("课程设置了收费价格不能为空且必须大于0");
            }
        }

        // 将dto中的课程营销信息拷贝至courseMarket对象中
        BeanUtils.copyProperties(dto, courseMarket);
        boolean seva = courseMarketService.saveOrUpdate(courseMarket);

        return getCourseBaseInfo(couresId);
    }
}




