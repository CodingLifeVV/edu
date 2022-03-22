package com.codinglife.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-22
 */

@Data
@TableName("edu_course")
@ApiModel(value = "Course对象", description = "课程")
public class CourseDo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 课程状态 COURSE_DRAFT未发布  Normal已发布
     */
    public static final String COURSE_DRAFT = "Draft";
    /**
     * 课程状态 COURSE_NORMAL已发布
     */
    public static final String COURSE_NORMAL = "Normal";

    @ApiModelProperty("课程ID")
    @TableId(value = "id") // 主键生成策略修改为插入对象ID为空时,自动填充UUID
    private String id;

    @ApiModelProperty("课程授课教师ID")
    @TableField("teacher_id")
    private String teacherId;

    @ApiModelProperty("课程专业ID")
    @TableField("subject_id")
    private String subjectId;

    @TableField("subject_parent_id")
    private String subjectParentId;

    @ApiModelProperty("课程标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("课程销售价格,设置为0则可免费观看")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("总课时")
    @TableField("lesson_num")
    private Integer lessonNum;

    @ApiModelProperty("课程封面图片路径")
    @TableField("cover")
    private String cover;

    @ApiModelProperty("销售数量")
    @TableField("buy_count")
    private Long buyCount;

    @ApiModelProperty("浏览数量")
    @TableField("view_count")
    private Long viewCount;

    @ApiModelProperty("乐观锁")
    @TableField("version")
    @Version
    private Long version;

    @ApiModelProperty("课程状态 Draft未发布  Normal已发布")
    @TableField("status")
    private String status;

    @ApiModelProperty("逻辑删除 1(true)已删除， 0(false)未删除")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
