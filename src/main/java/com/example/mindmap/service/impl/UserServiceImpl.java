package com.example.mindmap.service.impl;

//import com.example.mindmap.core.util.JwtUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.core.common.constant.ErrorCodeEnum;
import com.example.mindmap.core.common.exception.BusinessException;
import com.example.mindmap.core.util.JwtUtils;
import com.example.mindmap.dao.entity.User;
import com.example.mindmap.dao.mapper.UserInfoMapper;
import com.example.mindmap.dto.UserInfoDto;
import com.example.mindmap.dto.req.UserLoginReqDto;
import com.example.mindmap.dto.resp.UserLoginRespDto;
import com.example.mindmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public RestResp<UserLoginRespDto> login(UserLoginReqDto dto) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", dto.getName())
                .last("limit 1");
        User userInfo = (User) userInfoMapper.selectOne(queryWrapper);
//        System.out.println( userInfo.getPwd()+ "::pwd::" + DigestUtils.md5DigestAsHex(dto.getPwd().getBytes(StandardCharsets.UTF_8)));

        if (Objects.isNull(userInfo)) {
            // 用户不存在
            throw new BusinessException(ErrorCodeEnum.USER_ACCOUNT_NOT_EXIST);
        }
        if (!Objects.equals(userInfo.getPwd()
                , DigestUtils.md5DigestAsHex(dto.getPwd().getBytes(StandardCharsets.UTF_8)))) {
            // 密码错误
            throw new BusinessException(ErrorCodeEnum.USER_PASSWORD_ERROR);
        }
//        System.out.println("12");
//
        return RestResp.ok(UserLoginRespDto.builder()
                .token(jwtUtils.generateToken(userInfo.getId()))
                .uid(userInfo.getId()).build());
    }

    @Override
    public RestResp<Void> logout(UserLoginReqDto user) {
        return RestResp.ok();
    }

    @Override
    public RestResp<UserInfoDto> getAll(Integer id) {
        System.out.println("ok");
        User user = (User) userInfoMapper.selectById(id);
        System.out.println(user);
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(user.getId())
                .build();
        System.out.println(userInfoDto);
        return RestResp.ok(userInfoDto);
    }

}
