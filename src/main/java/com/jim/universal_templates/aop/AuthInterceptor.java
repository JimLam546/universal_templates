package com.jim.universal_templates.aop;

import com.jim.universal_templates.annotation.AuthCheck;
import com.jim.universal_templates.entity.enums.UserRoleEnum;
import com.jim.universal_templates.entity.vo.UserVO;
import com.jim.universal_templates.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Jim_Lam
 * @description AuthInterceptor
 */

@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        UserVO loginUser = userService.getLoginUser(request);
        UserRoleEnum enumByValue = UserRoleEnum.getEnumByValue(mustRole);
        if (enumByValue == null) {
            return joinPoint.proceed();
        }
        return joinPoint.proceed();
    }
}