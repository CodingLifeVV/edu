package com.codinglife;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CodingLife
 * @Description TODD
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
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * data 对象使用 put(K key, V val) 方法的 key
     */
    private static final String KEY = "items";

    private  CommonResult() {}

    public static CommonResult success() {
        CommonResult cr = new CommonResult();
        cr.setSuccess(ResultCode.SUCCESS.isSuccess());
        cr.setCode(ResultCode.SUCCESS.getCode());
        cr.setMessage(ResultCode.SUCCESS.getMessage());
        return cr;
    }

    public static CommonResult error() {
        CommonResult cr = new CommonResult();
        cr.setSuccess(ResultCode.ERROR.isSuccess());
        cr.setCode(ResultCode.ERROR.getCode());
        cr.setMessage(ResultCode.ERROR.getMessage());
        return cr;
    }


    /**
     *
     * @param data 返回数据
     * @return
     */
    public  CommonResult data(Object data) {
        // 如果返回数据是 Map 类型
        if (data instanceof Map<?,?>) {
            this.setData((Map<String, Object>) data);
        } else { // 否则将数据存储在当前 data 对象
            this.data.put(this.KEY, data);
        }
        return this;
    }

}
