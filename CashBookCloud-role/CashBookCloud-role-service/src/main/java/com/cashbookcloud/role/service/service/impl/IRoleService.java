package com.cashbookcloud.role.service.service.impl;

import com.cashbookcloud.role.api.dto.RoleDto;
import com.cashbookcloud.role.api.service.RoleService;
import com.cashbookcloud.role.service.covert.RoleCovert;
import com.cashbookcloud.role.service.entity.Role;
import com.cashbookcloud.role.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@org.apache.dubbo.config.annotation.Service
@Transactional
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
