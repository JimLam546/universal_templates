package com.jim.universal_templates.common;

import com.jim.universal_templates.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jim_Lam
 * @description BaseResponse
 */


@Data
public class BaseResponse<T> implements Serializable {


    private static final long serialVersionUID = 3851724796509027343L;

    private int code;

    /**
     * 数据
     */
    private T data;

    /**
     * 描述
     */
    private String description;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.description = message;
    }

    public BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public BaseResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BaseResponse(ErrorCode errorCode, String description) {
        this.code = errorCode.getCode();
        this.description = description;
    }

    public BaseResponse(int code, String description) {
        this.code = code;
        this.description = description;
    }

}