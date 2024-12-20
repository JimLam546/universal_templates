package com.jim.universal_templates.controller;

import com.jim.universal_templates.annotation.AuthCheck;
import com.jim.universal_templates.common.BaseResponse;
import com.jim.universal_templates.common.ResultUtil;
import com.jim.universal_templates.entity.User;
import com.jim.universal_templates.entity.request.UserLoginRequest;
import com.jim.universal_templates.entity.request.UserRegisterRequest;
import com.jim.universal_templates.entity.vo.UserVO;
import com.jim.universal_templates.exception.BusinessException;
import com.jim.universal_templates.exception.ErrorCode;
import com.jim.universal_templates.exception.ThrowUtils;
import com.jim.universal_templates.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.jim.universal_templates.constant.UserConstant.USER_LOGIN_STATE;


/**
 * @author Jim_Lam
 * @description UserController
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterRequest 用户注册请求
     * @return 用户id
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        Long userId = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtil.success(userId);
    }

    /**
     * 用户登录
     * @param userLoginRequest 用户登录请求参数
     * @param request 会话请求
     * @return 脱敏后的用户数据
     */
    @PostMapping("/login")
    public BaseResponse<UserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        UserVO userVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtil.success(userVO);
    }

    /**
     * 获取当前登录用户信息
     * @param request 会话请求
     * @return 脱敏后的用户信息
     */
    @GetMapping("/get/current")
    public BaseResponse<UserVO> getLoginUser(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        ThrowUtils.throwIf(attribute == null, ErrorCode.NOT_LOGIN_ERROR);
        UserVO userVO = userService.getLoginUser(request);
        return ResultUtil.success(userVO);
    }

    /**
     * 用户注销
     * @param request 会话亲够
     * @return 注销操作结果
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        userService.userLogout(request);
        return ResultUtil.success(true);
    }
}