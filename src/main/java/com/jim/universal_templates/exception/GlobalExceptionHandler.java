package com.jim.universal_templates.exception;

import com.jim.universal_templates.common.ResultUtil;
import com.jim.universal_templates.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author Jim_Lam
 * @description GlobalExceptionHander
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public BaseResponse exceptionHandler(BusinessException e) {
        log.error(e.getDescription());
        return ResultUtil.failure(e.getCode(), e.getDescription());
    }

    @ExceptionHandler({RuntimeException.class})
    public BaseResponse exceptionHandler(RuntimeException e) {
        return ResultUtil.failure(ErrorCode.SYSTEM_ERROR);
    }
}