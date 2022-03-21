package com.codinglife.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodingLife
 * @Description 一级课程分类
 * @since 2022/3/21 18:17
 */
@Data
public class OneLevelCourseSubject {
    private String id;
    private String title;
    private List<TwoLevelCourseSubject> children = new ArrayList<>();
}
