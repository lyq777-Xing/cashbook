package com.cashbookcloud.security.admin.getAuthorization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.security.admin.getAuthorization.covert.RoleCovert;
import com.cashbookcloud.security.admin.getAuthorization.dto.RoleDto;
import com.cashbookcloud.security.admin.getAuthorization.entity.Role;
import com.cashbookcloud.security.admin.getAuthorization.mapper.RoleMapper;
import com.cashbookcloud.security.admin.getAuthorization.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@org.apache.dubbo.config.annotation.Service
//@Transactional
public class IRoleService implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleDto findById(Integer id) {
        Role role = roleMapper.selectById(id);
        RoleDto roleDto = RoleCovert.INSTANCE.entity2dto(role);
        return roleDto;
    }

}
