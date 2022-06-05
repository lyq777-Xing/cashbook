package com.cashbookcloud.role.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PermissionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限等级
     */
    private String permissionLevel;

    /**
     * 权限地址
     */
    private String permissionPath;

    /**
     * 权限pid
     */
    private Integer permissionPid;

    /**
     * 子权限
     */
    private List<PermissionDto> children;


}
