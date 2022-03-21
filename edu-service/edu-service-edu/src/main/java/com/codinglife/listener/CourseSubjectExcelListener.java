package com.codinglife.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codinglife.entity.CourseSubject;
import com.codinglife.entity.excel.CourseSubjectExcelData;
import com.codinglife.exception.CustomizeApiException;
import com.codinglife.service.CourseSubjectService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/18 14:53
 */
@Slf4j
public class CourseSubjectExcelListener extends AnalysisEventListener<CourseSubjectExcelData> {

    public CourseSubjectService courseSubjectService;

    public CourseSubjectExcelListener(CourseSubjectService courseSubjectService) {
        this.courseSubjectService = courseSubjectService;
    }

    public CourseSubjectExcelListener() {
    }


    @Override
    public void invoke(CourseSubjectExcelData courseSubjectExcelData, AnalysisContext analysisContext) {
        if (courseSubjectExcelData == null) {
            throw new CustomizeApiException(20001, "文件数据为空");
        }
        // 添加一级分类
        CourseSubject oneLevelSubject =
                this.existOneLevelSubject(courseSubjectService, courseSubjectExcelData.getOneLevelSubjectName());

        // 不存在该数据, 添加
        if (oneLevelSubject == null) {
            oneLevelSubject = new CourseSubject();
            oneLevelSubject.setTitle(courseSubjectExcelData.getOneLevelSubjectName());
            oneLevelSubject.setParentId("0");
            courseSubjectService.save(oneLevelSubject);
        }

        //获取一级分类id值
        String oneLevelSubjectId = oneLevelSubject.getId();
        // 添加二级分类
        CourseSubject twoLevelSubject =
                this.existTwoLevelSubject(
                        courseSubjectService,
                        courseSubjectExcelData.getTwoLevelSubjectName(),
                        oneLevelSubjectId);
        if(twoLevelSubject == null) {
            twoLevelSubject = new CourseSubject();
            twoLevelSubject.setTitle(courseSubjectExcelData.getTwoLevelSubjectName());
            twoLevelSubject.setParentId(oneLevelSubjectId);
            courseSubjectService.save(twoLevelSubject);
        }
    }
    /**
     * 判断课程一级分类是否重复, 名称存在且父ID=0则重复
     * @param courseSubjectService
     * @param courseSubjectName 课程类别名称
     * @return
     */
    private CourseSubject existOneLevelSubject(CourseSubjectService courseSubjectService, String courseSubjectName) {
        QueryWrapper<CourseSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", courseSubjectName);
        wrapper.eq("parent_id", "0");
        return courseSubjectService.getOne(wrapper)  ;
    }

    /**
     * 判断课程二级分类是否重复, 名称存在且父ID=一级课程类别ID, 则重复
     * @param courseSubjectService
     * @param courseSubjectName 课程类别名称
     * @return
     */
    private CourseSubject existTwoLevelSubject(CourseSubjectService courseSubjectService, String courseSubjectName, String oneLevelSubjectId) {
        QueryWrapper<CourseSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", courseSubjectName);
        wrapper.eq("parent_id", oneLevelSubjectId);
        return courseSubjectService.getOne(wrapper);
    }


    /**
     * 读取Excel表头信息
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息: " + headMap);
        // LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据读取完成！");
    }
}
