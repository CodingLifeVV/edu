package com.codinglife.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 课程发布Vo
 * @author: CodingLifeVV
 * @date: 2022.04.07
 */
@Data
public class CoursePublishVo implements Serializable {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
