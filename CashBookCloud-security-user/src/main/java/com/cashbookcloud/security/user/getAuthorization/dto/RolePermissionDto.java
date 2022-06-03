package com.cashbookcloud.security.user.getAuthorization.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePermissionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer permissionId;


}
