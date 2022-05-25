package com.cashbookcloud.role.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.role.api.dto.RoleDto;
import io.swagger.models.auth.In;

public interface RoleService {
    RoleDto findById(Integer id);

    RoleDto add(RoleDto roleDto);

    void del(Integer id);

    RoleDto upd(RoleDto roleDto);

    RoleDto findByName(String roleName);

    Page<RoleDto> findAllPage(Integer pagenum,Integer pagesize,String query);

}
