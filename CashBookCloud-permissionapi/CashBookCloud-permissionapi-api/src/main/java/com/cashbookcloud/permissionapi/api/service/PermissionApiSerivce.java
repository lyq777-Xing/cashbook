package com.cashbookcloud.permissionapi.api.service;

import com.cashbookcloud.permissionapi.api.dto.PermissionApiDto;

public interface PermissionApiSerivce {
    PermissionApiDto findById(Integer id);

    PermissionApiDto findByPermissionId(Integer PermissionId);
}
