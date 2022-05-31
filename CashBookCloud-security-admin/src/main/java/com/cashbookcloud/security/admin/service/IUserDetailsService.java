package com.cashbookcloud.security.admin.service;

import com.alibaba.fastjson.JSON;
import com.cashbookcloud.manager.api.dto.ManagerDto;
import com.cashbookcloud.manager.api.service.ManagerService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@org.apache.dubbo.config.annotation.Service
@Service
public class IUserDetailsService implements UserDetailsService {

    @Reference
    private ManagerService managerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //将来连接数据库根据账号查询用户信息
        ManagerDto managerDto = managerService.findByUsername(username);
        if(managerDto == null){
            //如果用户查不到，返回null，由provider来抛出异常
            return null;
        }
        //根据用户的id查询用户的权限
        List<String> permissions = managerService.findPermissionsByUserId(managerDto.getId());
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        //将userDto转成json
        String principal = JSON.toJSONString(managerDto);
        UserDetails userDetails = User.withUsername(principal).password(managerDto.getMgPassword()).authorities(permissionArray).build();
        return userDetails;
    }
}
