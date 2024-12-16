package com.jim.universal_templates.exception;

import lombok.Getter;

/**
 * 异常枚举类
 *
 * @author Jim_Lam
 * @description ErrorCode
 */
@Getter
public enum ErrorCode {


    SUCCESS(0, "请求成功", "请求成功"),
    PARAMS_ERROR(40000, "请求参数错误", "请求参数不符合要求"),
    NOT_LOGIN_ERROR(40100, "未登录", "未登录"),
    NO_AUTH_ERROR(40101, "无权限", "您没有操作权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在", "请求数据不存在"),
    FORBIDDEN_ERROR(40300, "禁止访问", "禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常", "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败", "操作失败");
    ;

    /**
     * 状态码
     */
    private final int code;

    /**
     * 错误信息（后端提示）
     */
    private final String message;

    /**
     * 错误描述（前端提示）
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
}
