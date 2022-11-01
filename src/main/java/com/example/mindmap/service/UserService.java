package com.example.mindmap.service;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.dao.entity.User;
import com.example.mindmap.dto.UserInfoDto;
import com.example.mindmap.dto.req.UserLoginReqDto;
import com.example.mindmap.dto.resp.UserLoginRespDto;

public interface UserService {
    /**
     * 用户登录
     *
     * @return JWT + 昵称
     */
    RestResp<UserLoginRespDto> login(UserLoginReqDto dto);

    /**
     * 用户登出
     *
     * @return JWT + 昵称
     */
    RestResp<Void> logout(UserLoginReqDto dto);
    RestResp<UserInfoDto> getAll(Integer id);

}
