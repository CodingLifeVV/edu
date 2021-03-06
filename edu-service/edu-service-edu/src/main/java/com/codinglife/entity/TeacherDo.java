package com.codinglife.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-03
 */
@Getter
@Setter
@TableName("edu_teacher")
@ApiModel(value = "Teacher对象", description = "教师")
public class TeacherDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("教师ID")
    @TableId("id")
    private String id;

    @ApiModelProperty("教师姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty("教师简介")
    @TableField("intro")
    private String intro;

    @NotEmpty(message = "教师资历不能为空")
    @ApiModelProperty("教师资历,一句话说明讲师")
    @TableField("career")
    private String career;

    @ApiModelProperty("教师头衔 1教授 2副教授 3讲师 4助教")
    @TableField("level")
    private Integer level;

    @ApiModelProperty("教师头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT) // value 数据库字段值
    private LocalDateTime gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
