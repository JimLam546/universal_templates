package com.jim.universal_templates.exception;

import com.jim.universal_templates.common.ResultUtil;
import com.jim.universal_templates.common.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理类
 *
 * @author Jim_Lam
 * @description GlobalExceptionHander
 */

@ControllerAdvice
public class GlobalExceptionHander {

    @ExceptionHandler({BusinessException.class})
    public BaseResponse exceptionHandler(BusinessException e) {
        return ResultUtil.failure(e.getCode(), e.getDescription());
    }

    @ExceptionHandler({RuntimeException.class})
    public BaseResponse exceptionHandler(RuntimeException e) {
        return ResultUtil.failure(ErrorCode.SYSTEM_ERROR);
    }
}