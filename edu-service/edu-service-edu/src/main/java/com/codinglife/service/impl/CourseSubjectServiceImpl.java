package com.codinglife.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codinglife.entity.CourseSubject;
import com.codinglife.entity.excel.CourseSubjectExcelData;
import com.codinglife.listener.CourseSubjectExcelListener;
import com.codinglife.mapper.CourseSubjectMapper;
import com.codinglife.service.CourseSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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
}
