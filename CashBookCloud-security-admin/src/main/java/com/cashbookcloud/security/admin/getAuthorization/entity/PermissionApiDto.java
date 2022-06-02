package com.cashbookcloud.security.admin.getAuthorization.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionApiDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * api的id
     */
    private Integer id;

    /**
     * 权限的id
     */
    private Integer permissionId;

    /**
     * 权限的keyword
     */
    private String permissionKeyword;


}
