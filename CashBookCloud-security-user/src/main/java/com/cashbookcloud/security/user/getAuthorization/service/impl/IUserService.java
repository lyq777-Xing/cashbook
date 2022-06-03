package com.cashbookcloud.security.user.getAuthorization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.security.user.client.PermissionApiClient;
import com.cashbookcloud.security.user.client.RolePermissionClient;
import com.cashbookcloud.security.user.getAuthorization.covert.UserCovert;
import com.cashbookcloud.security.user.getAuthorization.dto.PermissionApiDto;
import com.cashbookcloud.security.user.getAuthorization.dto.RoleDto;
import com.cashbookcloud.security.user.getAuthorization.dto.RolePermissionDto;
import com.cashbookcloud.security.user.getAuthorization.dto.UserDto;
import com.cashbookcloud.security.user.getAuthorization.entity.User;
import com.cashbookcloud.security.user.getAuthorization.mapper.UserMapper;
import com.cashbookcloud.security.user.getAuthorization.service.RoleService;
import com.cashbookcloud.security.user.getAuthorization.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@org.springframework.stereotype.Service
@Transactional
public class IUserService implements UserService {

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private RoleClient roleClient;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionClient rolePermissionClient;

    @Autowired
    private PermissionApiClient permissionApiClient;

//    @Reference
//    private RoleService roleService;

    @Override
    public UserDto findByUserName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",name);
        User user = userMapper.selectOne(wrapper);
        UserDto userDto = UserCovert.INSTANCE.entity2dto(user);
        return userDto;
    }


    @Override
    public List<String> findPermissionsByUserId(Integer id) {

        ArrayList<String> authority = new ArrayList<>();
//        获取用户的name
        User user = userMapper.selectById(id);
        String userName = user.getUserName();
//        获取角色
        Integer roleId1 = user.getRoleId();
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
