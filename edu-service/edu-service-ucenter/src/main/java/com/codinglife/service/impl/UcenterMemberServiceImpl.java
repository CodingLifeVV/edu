package com.codinglife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codinglife.MD5;
import com.codinglife.entity.UcenterMember;
import com.codinglife.entity.vo.LoginVo;
import com.codinglife.entity.vo.RegisterVo;
import com.codinglife.exception.CustomizeApiException;
import com.codinglife.jwt.JwtUtils;
import com.codinglife.mapper.UcenterMemberMapper;
import com.codinglife.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author CodingLife
 * @since 2022-04-25
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();

        if (ObjectUtils.isEmpty(phone)||ObjectUtils.isEmpty(password)){
            throw new CustomizeApiException(20001,"手机号和密码为空");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",phone);
        UcenterMember mobileMember = baseMapper.selectOne(queryWrapper);
        if (mobileMember == null){
            throw new CustomizeApiException(20001,"手机号不存在");
        }

        //判断密码是否相等
        //数据库密码进行加密，不能直接对比
        //MD5对密码进行加密，再与数据库进行比较
        String password1 = mobileMember.getPassword();
        if (!MD5.encrypt(password).equals(password1)){
            throw new CustomizeApiException(20001,"密码错误");
        }

        //判断用户是否被禁用
        if(mobileMember.getDisabled()){
            throw new CustomizeApiException(20001,"用户被禁用登录失败");
        }

        //登录成功
        //按照jwt生产token返回
        String token = JwtUtils.createToken(mobileMember.getId(), mobileMember.getNickname());
        return token;
    }

    /**
     * 注册会员
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册基本信息
        String code = registerVo.getCode();
        String mobile = registerVo.getPhone();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        //判断是否为空
        if (ObjectUtils.isEmpty(code) || ObjectUtils.isEmpty(mobile) || ObjectUtils.isEmpty(nickname) || ObjectUtils.isEmpty(password)){
            throw new CustomizeApiException(20001,"注册失败");
        }

        //判断验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        System.out.println("redisCode:"+redisCode);
        System.out.println("code:"+code);
        if (!code.equals(redisCode)){
            throw new CustomizeApiException(20001,"注册失败");
        }

        //判断手机号在数据库中是否存在
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Long count = baseMapper.selectCount(wrapper);
        if (count > 0){
            throw new CustomizeApiException(20001,"注册失败");
        }

        //将数据添加到数据库
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setNickname(nickname);
        ucenterMember.setDisabled(false); //用户不禁用
        ucenterMember.setAvatar("https://cdn.jsdelivr.net/gh/CodingLifeV/images//docs/image-20220425145347516.png");
        baseMapper.insert(ucenterMember);

    }
}
