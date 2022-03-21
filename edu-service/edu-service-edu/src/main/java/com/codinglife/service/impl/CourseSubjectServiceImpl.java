package com.codinglife.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codinglife.entity.CourseSubject;
import com.codinglife.entity.excel.CourseSubjectExcelData;
import com.codinglife.entity.subject.OneLevelCourseSubject;
import com.codinglife.entity.subject.TwoLevelCourseSubject;
import com.codinglife.listener.CourseSubjectExcelListener;
import com.codinglife.mapper.CourseSubjectMapper;
import com.codinglife.service.CourseSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/18 11:18
 */
@Service
public class CourseSubjectServiceImpl extends ServiceImpl<CourseSubjectMapper, CourseSubject> implements CourseSubjectService {
    /**
     * 批量导入课程分类文件
     * @param file 课程分类excel文件
     * @param courseSubjectService
     */
    public void batchImportSubject(MultipartFile file, CourseSubjectService courseSubjectService) {
        try {
            InputStream is = file.getInputStream();
            // read(InputStream inputStream, Class head, ReadListener readListener)
            // inputStream 输入流; head 需要导入excel表对应的实体类; readListener 事件监听器,用来监听处理读取到的每一条数据,读取excel表第一个sheet
            EasyExcel.read(is, CourseSubjectExcelData.class, new CourseSubjectExcelListener(courseSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有嵌套课程分类
     * @return
     */
    @Override
    public List<OneLevelCourseSubject> getAlltNestedCourseSubject() {
        // 1.查询出所有一级分类 parent_id=0
        QueryWrapper<CourseSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        List<CourseSubject> oneLevelCourseSubjectList = baseMapper.selectList(wrapper);



        // 2.查询出所有二级分类 parent_id!=0
        QueryWrapper<CourseSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id", "0");
        List<CourseSubject> twoLevelCourseSubjectList = baseMapper.selectList(wrapper2);

        // 3.封装一级分类
        List<OneLevelCourseSubject> finnalList = new ArrayList<>();
        for (int i = 0; i < oneLevelCourseSubjectList.size(); i++) {
            CourseSubject courseSubject = oneLevelCourseSubjectList.get(i);

            // 一级课程分类
            OneLevelCourseSubject oneLevelCourseSubject = new OneLevelCourseSubject();
            // courseSubject值赋给oneLevelCourseSubject
            BeanUtils.copyProperties(courseSubject, oneLevelCourseSubject);
            finnalList.add(oneLevelCourseSubject);

            // 4.封装二级课程分类,创建list集合封装每一个一级课程分类的二级课程分类
            List<TwoLevelCourseSubject> twoFinnalList = new ArrayList<>();
            for (int j = 0; j < twoLevelCourseSubjectList.size(); j++) {
                CourseSubject courseSubject2 = twoLevelCourseSubjectList.get(j);
                // 如过一级课程分类的 id 等于二级课程分类的 parent_id,进行封装
                if (courseSubject.getId().equals(courseSubject2.getParentId())) {
                    TwoLevelCourseSubject twoLevelCourseSubject = new TwoLevelCourseSubject();
                    BeanUtils.copyProperties(courseSubject2, twoLevelCourseSubject);
                    twoFinnalList.add(twoLevelCourseSubject);
                }
            }
            oneLevelCourseSubject.setChildren(twoFinnalList);
        }

        return finnalList;
    }
}
