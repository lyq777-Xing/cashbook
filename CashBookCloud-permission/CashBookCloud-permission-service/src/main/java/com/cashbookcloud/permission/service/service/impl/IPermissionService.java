package com.cashbookcloud.permission.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cashbookcloud.permission.api.dto.PermissionDto;
import com.cashbookcloud.permission.api.service.PermissionService;
import com.cashbookcloud.permission.service.covert.PermissionCovert;
import com.cashbookcloud.permission.service.entity.Permission;
import com.cashbookcloud.permission.service.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@org.apache.dubbo.config.annotation.Service
@Transactional
public class IPermissionService implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PermissionDto findById(Integer id) {
        Permission permission = permissionMapper.selectById(id);
        PermissionDto permissionDto = PermissionCovert.INSTANCE.entity2dto(permission);
        return permissionDto;
    }

    @Override
    public List<PermissionDto> findAll() {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_level",0);
        List<Permission> permissions = permissionMapper.selectList(wrapper);
        QueryWrapper<Permission> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("permission_level",1);
        List<Permission> permissions1 = permissionMapper.selectList(wrapper1);
        ArrayList<PermissionDto> permissionDtos = new ArrayList<>();
        ArrayList<PermissionDto> permissionDtos1 = new ArrayList<>();
        for (Permission p:permissions) {
            permissionDtos.add(PermissionCovert.INSTANCE.entity2dto(p));
        }
        for (Permission p:permissions1) {
            permissionDtos1.add(PermissionCovert.INSTANCE.entity2dto(p));
        }

        for (PermissionDto p:permissionDtos) {
            ArrayList<PermissionDto> list = new ArrayList<>();
            for (PermissionDto c:permissionDtos1) {
                if(c.getPermissionPid().intValue() == p.getId().intValue()){
                    list.add(c);
                }
            }
            p.setChildren(list);
        }
        return permissionDtos;
    }

    @Override
    public List<PermissionDto> findAllList() {
        List<Permission> permissions = permissionMapper.selectList(null);
        ArrayList<PermissionDto> permissionDtos = new ArrayList<>();
        for (Permission p:permissions) {
            permissionDtos.add(PermissionCovert.INSTANCE.entity2dto(p));
        }
        return permissionDtos;
    }

    @Override
    public List<PermissionDto> findAllAminList() {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_level",0).eq("permission_path","admin");
        List<Permission> permissions = permissionMapper.selectList(wrapper);
        QueryWrapper<Permission> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("permission_level",1).eq("permission_path","admin");
        List<Permission> permissions1 = permissionMapper.selectList(wrapper1);
        ArrayList<PermissionDto> permissionDtos = new ArrayList<>();
        ArrayList<PermissionDto> permissionDtos1 = new ArrayList<>();
        for (Permission p:permissions) {
            permissionDtos.add(PermissionCovert.INSTANCE.entity2dto(p));
        }
        for (Permission p:permissions1) {
            permissionDtos1.add(PermissionCovert.INSTANCE.entity2dto(p));
        }

        for (PermissionDto p:permissionDtos) {
            ArrayList<PermissionDto> list = new ArrayList<>();
            for (PermissionDto c:permissionDtos1) {
                if(c.getPermissionPid().intValue() == p.getId().intValue()){
                    list.add(c);
                }
            }
            p.setChildren(list);
        }
        return permissionDtos;
    }

    @Override
    public List<PermissionDto> findAllUserList() {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_level",0).eq("permission_path","user");
        List<Permission> permissions = permissionMapper.selectList(wrapper);
        QueryWrapper<Permission> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("permission_level",1).eq("permission_path","user");
        List<Permission> permissions1 = permissionMapper.selectList(wrapper1);
        ArrayList<PermissionDto> permissionDtos = new ArrayList<>();
        ArrayList<PermissionDto> permissionDtos1 = new ArrayList<>();
        for (Permission p:permissions) {
            permissionDtos.add(PermissionCovert.INSTANCE.entity2dto(p));
        }
        for (Permission p:permissions1) {
            permissionDtos1.add(PermissionCovert.INSTANCE.entity2dto(p));
        }

        for (PermissionDto p:permissionDtos) {
            ArrayList<PermissionDto> list = new ArrayList<>();
            for (PermissionDto c:permissionDtos1) {
                if(c.getPermissionPid().intValue() == p.getId().intValue()){
                    list.add(c);
                }
            }
            p.setChildren(list);
        }
        return permissionDtos;
    }
}
