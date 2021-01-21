package com.pighome.eat.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionStatus {
    BUSINESS_ERR("250", "业务异常"),
    PARAM_NOT_NULL("250", "参数不能为空"),
    REQUEST_PROCESSING("250", "请求处理中，请稍后再试！"),
    NO_LOGIN("401", "未登录"),
    METHOD_NOT_ALLOWED("403", "请求方法未允许"),
    SYSTEM_ERROR("500", "系统异常");
    private String status;
    private String message;

}
