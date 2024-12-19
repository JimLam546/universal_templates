package com.jim.universal_templates.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jim_Lam
 * @description UserLoginRequest
 */

@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = -6532306348003709717L;

    private String userAccount;

    private String userPassword;
}