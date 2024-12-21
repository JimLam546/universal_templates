package com.jim.universal_templates.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jim_Lam
 * @description UserAddRequest
 */

@Data
public class UserAddRequest implements Serializable {
    private static final long serialVersionUID = -5441877740951272660L;
    /**
     * 昵称
     */
    private String username;

    /**
     * 登录账号
     */
    private String userAccount;

    /**
     * 登录头像
     */
    private String avatarUrl;

    /**
     * 性别：1男 0女
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色
     */
    private String userRole;

    /**
     * 个人简介
     */
    private String profile;

}