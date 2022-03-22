package com.codinglife.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CodingLife
 * @Description 课程科目
 * @since 2022/3/18 10:21
 */
@Data
@Api("课程类别")
@TableName("edu_subject")
public class CourseSubjectDo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程类别ID")
    private String id;

    @ApiModelProperty(value = "课程类别名称")
    private String title;

    @ApiModelProperty(value = "课程类别父ID")
    private String parentId;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;
}
