package com.codinglife.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.codinglife.entity.CourseSubject;
import org.springframework.web.multipart.MultipartFile;

public interface CourseSubjectService extends IService<CourseSubject> {
        void batchImportSubject(MultipartFile file, CourseSubjectService courseSubjectService);
}
