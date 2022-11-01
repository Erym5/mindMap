package com.example.mindmap.core.common.exception;

import com.example.mindmap.core.common.constant.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    private final ErrorCodeEnum errorCodeEnum;

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        // 不调用父类 Throwable的fillInStackTrace() 方法生成栈追踪信息，提高应用性能
        // 构造器之间的调用必须在第一行
        super();
        this.errorCodeEnum = errorCodeEnum;
    }
}
