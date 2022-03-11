package com.codinglife;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CodingLife
 * @Description 状态码
 * @since 2022/3/4 9:56
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE) // Lombok 所有参数构造器且私有
public enum ResultCode {
    SUCCESS(true,20000, "操作成功"),
    ERROR(false,20001, "操作失败"),
    VALIDATE_FAILED(false,404, "参数检验失败"),
    UNAUTHORIZED(false,401, "暂未登录或token已经过期"),
    FORBIDDEN(false,403, "没有相关权限");

    private boolean success; // 是否成功
    private long code;       // 状态码
    private String message;  // 提示消息


}
