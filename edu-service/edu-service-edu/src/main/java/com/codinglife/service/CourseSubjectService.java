package com.codinglife.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.codinglife.entity.CourseSubjectDo;
import com.codinglife.entity.subject.OneLevelCourseSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseSubjectService extends IService<CourseSubjectDo> {
        void batchImportSubject(MultipartFile file, CourseSubjectService courseSubjectService);
        List<OneLevelCourseSubject> getAlltNestedCourseSubject();
}
