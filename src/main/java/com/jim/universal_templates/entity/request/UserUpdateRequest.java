package com.jim.universal_templates.entity.request;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jim_Lam
 * @description UserUpdateRequest
 */

@Data
public class UserUpdateRequest implements Serializable {
    private static final long serialVersionUID = 2436613381105114380L;

    private Long id;

    /**
     * 昵称
     */
    private String username;

    /**
     * 登录头像
     */
    private String avatarUrl;

    /**
     * 是否有效(是否被封号)
     */
    private Integer isValid;

    /**
     * 角色
     */
    private String userRole;

    /**
     * 个人简介
     */
    private String profile;

}