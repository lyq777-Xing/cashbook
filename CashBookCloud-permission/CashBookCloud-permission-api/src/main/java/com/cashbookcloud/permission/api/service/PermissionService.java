package com.cashbookcloud.permission.api.service;

import com.cashbookcloud.permission.api.dto.PermissionDto;

import java.util.List;

public interface PermissionService {
    PermissionDto findById(Integer id);

    List<PermissionDto> findAll();

    List<PermissionDto> findAllList();

    List<PermissionDto> findAllAminList();

    List<PermissionDto> findAllUserList();

    List<PermissionDto> findByPid(Integer pid);
}
