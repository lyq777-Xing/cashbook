package com.cashbookcloud.rolepermission.api.service;

import com.cashbookcloud.rolepermission.api.dto.RolePermissionDto;

import java.util.List;

public interface RolePermissionService {
    List<RolePermissionDto> findByRoleId(Integer rid);

    void delByRoleIdAndPermissionId(Integer roleId,Integer PermissionId);

    void delByRoleId(Integer rid);

    void add(Integer roleId,Integer[] permissionIds);

}
