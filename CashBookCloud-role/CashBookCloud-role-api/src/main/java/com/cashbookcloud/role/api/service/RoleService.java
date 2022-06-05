package com.cashbookcloud.role.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.role.api.dto.RoleDto;
import io.swagger.models.auth.In;

import java.util.List;

public interface RoleService {
    RoleDto findById(Integer id);

    RoleDto add(RoleDto roleDto);

    void del(Integer id);

    RoleDto upd(RoleDto roleDto);

    RoleDto findByName(String roleName);

    Page<RoleDto> findAllPage(Integer pagenum,Integer pagesize,String query);

    List<RoleDto> findAll();

    List<RoleDto> findAllAdmin();

    List<RoleDto> findAllUser();

    RoleDto findPermissionByRoleId(Integer roleId);

}
