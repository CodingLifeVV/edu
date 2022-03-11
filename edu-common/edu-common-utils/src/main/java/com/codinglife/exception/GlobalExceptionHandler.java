package com.codinglife.exception;

import com.codinglife.CommonResult;
import com.codinglife.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CodingLife
 * @Description 全局异常处理
 * @since 2022/3/5 17:11
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     *
     * @param e BindException 表单绑定到 JavaBean 出错时,抛出 BindException 异常
     * @return
     * @ExceptionHandler 统一处理 BindException 异常
     * 参考 : https://juejin.cn/post/6844904003684302861
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult handleValidException(BindException e) {
        List<Map<String, String>> list = new ArrayList<>();
        for (ObjectError objectError : e.getAllErrors()) {
            Map<String, String> map = new HashMap<>();
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                map.put("field", fieldError.getField());
                map.put("message", fieldError.getDefaultMessage());
            } else {
                map.put("field", objectError.getObjectName());
                map.put("message", objectError.getDefaultMessage());
            }
            list.add(map);
        }
        return CommonResult.validateFailed().data("items", list);
    }

    /**
     *
     * @param e 将请求体解析并绑定到 JavaBean 出错时,抛出 MethodArgumentNotValidException 异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleValidException(MethodArgumentNotValidException e) {
        List<Map<String, String>> list = new ArrayList<>();
        for (ObjectError objectError : e.getAllErrors()) { // 遍历所有错误
            Map<String, String> map = new HashMap<>();
            if (objectError instanceof FieldError) {       // 是否为参数错误
                FieldError fieldError = (FieldError) objectError;
                map.put("field", fieldError.getField());
                map.put("message", fieldError.getDefaultMessage());
            } else {
                map.put("field", objectError.getObjectName());
                map.put("message", objectError.getDefaultMessage());
            }
            list.add(map);
        }
        return CommonResult.validateFailed().data("items", list);
    }

    /**
     * 自定义异常处理
     * @return
     */
    @ExceptionHandler(CustomizeApiException.class)
    @ResponseBody
    public CommonResult handleCustomizeApiException(CustomizeApiException e) {
        log.error(e.getMessage());

        e.printStackTrace();
        return CommonResult.setCommonResult(ResultCode.ERROR, e.getMsg());
    }



}
