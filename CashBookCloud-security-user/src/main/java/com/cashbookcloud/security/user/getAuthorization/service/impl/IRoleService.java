package com.cashbookcloud.security.user.getAuthorization.service.impl;

import com.cashbookcloud.security.user.getAuthorization.covert.RoleCovert;
import com.cashbookcloud.security.user.getAuthorization.dto.RoleDto;
import com.cashbookcloud.security.user.getAuthorization.entity.Role;
import com.cashbookcloud.security.user.getAuthorization.mapper.RoleMapper;
import com.cashbookcloud.security.user.getAuthorization.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
