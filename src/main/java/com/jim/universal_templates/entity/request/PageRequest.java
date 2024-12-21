package com.jim.universal_templates.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jim_Lam
 * @description PageRequest
 */

@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 7632360047937344414L;

    /**
     * 当前页
     */
    private int current = 1;

    /**
     * 页大小
     */
    private int pageSize = 10;
}