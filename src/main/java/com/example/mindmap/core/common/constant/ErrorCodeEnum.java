package com.example.mindmap.core.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    OK("00000", "一切 ok"),
    ERROR("11111", "失败"),
    USER_ACCOUNT_NOT_EXIST("A0201", "用户账号不存在"),
    USER_LOGIN_EXPIRED("A0230", "用户登录已过期"),
    USER_PASSWORD_ERROR("A0210", "用户密码错误"),
    USER_REQ_MANY("A0501", "请求超出限制");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 中文描述
     */
    private final String message;
}
