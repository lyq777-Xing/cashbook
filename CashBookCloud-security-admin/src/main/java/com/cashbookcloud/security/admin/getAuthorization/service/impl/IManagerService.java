package com.cashbookcloud.security.admin.getAuthorization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cashbookcloud.security.admin.client.PermissionApiClient;
import com.cashbookcloud.security.admin.client.RolePermissionClient;
import com.cashbookcloud.security.admin.getAuthorization.covert.ManagerCovert;
import com.cashbookcloud.security.admin.getAuthorization.dto.ManagerDto;
import com.cashbookcloud.security.admin.getAuthorization.dto.RoleDto;
import com.cashbookcloud.security.admin.getAuthorization.entity.Manager;
import com.cashbookcloud.security.admin.getAuthorization.dto.PermissionApiDto;
import com.cashbookcloud.security.admin.getAuthorization.dto.RolePermissionDto;
import com.cashbookcloud.security.admin.getAuthorization.mapper.ManagerMapper;
import com.cashbookcloud.security.admin.getAuthorization.service.ManagerService;
import com.cashbookcloud.security.admin.getAuthorization.service.RoleService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
@org.springframework.stereotype.Service
//@Transactional
public class IManagerService implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionClient rolePermissionClient;

    @Autowired
    private PermissionApiClient permissionApiClient;

    @Override
    public ManagerDto findByUsername(String username) {
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.eq("mg_name",username);
        Manager manager = managerMapper.selectOne(wrapper);
        ManagerDto managerDto = ManagerCovert.INSTANCE.entity2dto(manager);
        return managerDto;
    }


    @Override
    public List<String> findPermissionsByUserId(Integer id) {
        ArrayList<String> authority = new ArrayList<>();
//        获取用户的name
        Manager manager = managerMapper.selectById(id);
        String managerName = manager.getMgName();
//        获取角色
        Integer roleId1 = manager.getRoleId();
        RoleDto role = roleService.findById(roleId1);
        if(role != null){
            if(role.getRoleKeyword() != null){
                authority.add(role.getRoleKeyword());
            }
        }
        List<RolePermissionDto> byRoleId = rolePermissionClient.findByRid(roleId1);

        for (RolePermissionDto rp:byRoleId) {
            if(rp != null){
                Integer permissionId = rp.getPermissionId();
                PermissionApiDto api = permissionApiClient.findByPermissionId(permissionId);
                if(api != null){
                    if(api.getPermissionKeyword() != null){
                        authority.add(api.getPermissionKeyword());
//                        authority = authority + "," + api.getPermissionKeyword();
                    }
                }
            }
        }
        return authority;
    }
}
