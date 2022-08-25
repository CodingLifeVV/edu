package com.codinglife.service;

import com.codinglife.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codinglife.entity.vo.LoginVo;
import com.codinglife.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author CodingLife
 * @since 2022-04-25
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

}
