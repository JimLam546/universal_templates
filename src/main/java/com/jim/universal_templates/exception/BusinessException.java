package com.jim.universal_templates.exception;

import lombok.Getter;

/**
 * 自定义业务异常类
 *
 * @author Jim_Lam
 * @description BusinessException
 */

@Getter
public class BusinessException extends RuntimeException{

    private final int code;

    private final String description;

    public BusinessException(int code, String message, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }
}