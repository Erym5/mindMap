package com.example.mindmap.controller;

import com.example.mindmap.core.common.RestResp.RestResp;
import com.example.mindmap.core.common.constant.ApiRouterConsts;
import com.example.mindmap.dao.entity.User;
import com.example.mindmap.dto.UserInfoDto;
import com.example.mindmap.dto.req.UserLoginReqDto;
import com.example.mindmap.dto.resp.UserLoginRespDto;
import com.example.mindmap.service.UserService;
import com.example.mindmap.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRouterConsts.USER_URL_PREFIX)
@Api(tags = "用户管理相关接口")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户登录接口
     */

    @Operation(summary = "用户登录接口")
    @PostMapping("login")
    public RestResp<UserLoginRespDto> login(@Valid @RequestBody UserLoginReqDto dto) {
        System.out.println(dto);
        return userService.login(dto);
    }

    @Operation(summary = "测试")
    @GetMapping("test")
    public RestResp<UserInfoDto> getAll() {
        return userService.getAll(1);
    }

    /**
     * 用户登出接口
     */

    @Operation(summary = "登出功能")
    @PostMapping("/logout")
    public RestResp<Void> logout() {
        return RestResp.ok(null);
    }
}