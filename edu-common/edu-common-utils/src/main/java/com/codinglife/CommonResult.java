package com.codinglife;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CodingLife
 * @Description 统一返回结果 封装类
 * @since 2022/3/4 10:03
 */
@Data
@AllArgsConstructor
public class CommonResult {

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功")
    private boolean success;
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private long code;
    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    private String message;
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    private  CommonResult() {}

    /**
     * 成功返回结果
     * @return
     */
    public static CommonResult success() {
        return CommonResult.setCommonResult(ResultCode.SUCCESS);
    }

    /**
     * 失败返回结果
     * @return
     */
    public static CommonResult error() {
        return CommonResult.setCommonResult(ResultCode.ERROR);
    }

    /**
     * 参数验证失败返回结果
     * @return
     */
    public static CommonResult validateFailed() {
        return CommonResult.setCommonResult(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 统一设置返回结果
     * @param resultCode
     * @return
     */
    public static CommonResult setCommonResult(ResultCode resultCode) {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(resultCode.isSuccess());
        commonResult.setCode(resultCode.getCode());
        commonResult.setMessage(resultCode.getMessage());
        return commonResult;
    }

    /**
     * 统一设置返回结果, 修改提示信息 message
     * @param resultCode
     * @param message
     * @return
     */
    public static CommonResult setCommonResult(ResultCode resultCode, String message) {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(resultCode.isSuccess());
        commonResult.setCode(resultCode.getCode());
        commonResult.setMessage(message);
        return commonResult;
    }



    /**
     * 返回数据处理
     * @param key 返回数据 key 值
     * @param value 返回数据 value 值
     * @return
     */
    public  CommonResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    /**
     * 返回数据处理
     * @param map 返回数据
     * @return
     */
    public CommonResult data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
