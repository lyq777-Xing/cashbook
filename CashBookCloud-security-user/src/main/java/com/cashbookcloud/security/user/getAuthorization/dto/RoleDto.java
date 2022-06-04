package com.cashbookcloud.security.user.getAuthorization.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色keywrod
     */
    private String roleKeyword;

    /**
     * 角色描述
     */
    private String roleDescribe;

    /**
     * 判断角色是admin的还是user的
     */
    private String rolePojo;


}