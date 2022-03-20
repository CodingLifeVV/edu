package com.codinglife.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/18 9:59
 */
@Data
@Api("课程类别Excel数据")
public class CourseSubjectExcelData {

    @ApiModelProperty("一级课程类别")
    @ExcelProperty(index = 0)
    private String oneLevelSubjectName;

    @ApiModelProperty("二级课程类别")
    @ExcelProperty(index = 1)
    private String twoLevelSubjectName;
}
