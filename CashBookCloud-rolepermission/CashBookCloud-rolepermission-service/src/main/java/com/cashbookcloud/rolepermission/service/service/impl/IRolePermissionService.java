package com.cashbookcloud.rolepermission.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cashbookcloud.rolepermission.api.dto.RolePermissionDto;
import com.cashbookcloud.rolepermission.api.service.RolePermissionService;
import com.cashbookcloud.rolepermission.service.covert.RolePermissionCovert;
import com.cashbookcloud.rolepermission.service.entity.RolePermission;
import com.cashbookcloud.rolepermission.service.mapper.RolePermissionMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@Service
@Transactional
public class IRolePermissionService implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermissionDto> findByRoleId(Integer rid) {
        QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
        rolePermissionQueryWrapper.eq("role_id",rid);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(rolePermissionQueryWrapper);
        ArrayList<RolePermissionDto> rolePermissionDtos = new ArrayList<>();
        for (int i = 0; i < rolePermissions.size(); i++) {
            rolePermissionDtos.add(RolePermissionCovert.INSTANCE.entity2dto(rolePermissions.get(i)));
        }
        return rolePermissionDtos;
    }

    @Override
    public void delByRoleIdAndPermissionId(Integer roleId, Integer PermissionId) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId).eq("permission_id",PermissionId);
        rolePermissionMapper.delete(wrapper);
    }

    @Override
    public void delByRoleId(Integer rid) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",rid);
        rolePermissionMapper.delete(wrapper);
    }

    @Override
    public void add(Integer roleId, Integer[] permissionIds) {
        for (int i = 0; i < permissionIds.length; i++) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionIds[i]);
            rolePermission.setRoleId(roleId);
            rolePermissionMapper.insert(rolePermission);
        }
    }
}
