package com.jim.universal_templates.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jim_Lam
 * @description UserRegisterRequest
 */

@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 3924563501188339885L;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;
}