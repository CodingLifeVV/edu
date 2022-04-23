package com.codinglife.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 首页banner表
 * </p>
 *
 * @author CodingLife
 * @since 2022-04-21
 */
@Getter
@Setter
@TableName("crm_banner")
@ApiModel(value = "CrmBanner对象", description = "首页banner表")
public class CrmBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId("id")
    private String id;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("图片地址")
    @TableField("image_url")
    private String imageUrl;

    @ApiModelProperty("链接地址")
    @TableField("link_url")
    private String linkUrl;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
