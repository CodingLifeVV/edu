package com.codinglife.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 注册数据封装实体类
 * @author: CodingLifeVV
 * @date: 2022.04.25
 */
@Data
public class RegisterVo implements Serializable {

        @ApiModelProperty(value = "昵称")
        private String nickname;

        @ApiModelProperty(value = "手机号")
        private String phone;

        @ApiModelProperty(value = "密码")
        private String password;

        @ApiModelProperty(value = "验证码")
        private String code;

}
