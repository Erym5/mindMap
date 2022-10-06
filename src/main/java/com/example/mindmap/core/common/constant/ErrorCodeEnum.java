package com.example.mindmap.core.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    OK("00000", "一切 ok"),
    ERROR("11111", "失败");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 中文描述
     */
    private final String message;
}
