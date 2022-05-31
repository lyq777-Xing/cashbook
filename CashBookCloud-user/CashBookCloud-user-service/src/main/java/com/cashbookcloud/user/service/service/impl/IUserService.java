package com.cashbookcloud.user.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.role.api.dto.RoleDto;
import com.cashbookcloud.role.api.service.RoleService;
import com.cashbookcloud.user.api.dto.UserDto;
import com.cashbookcloud.user.api.service.UserService;
import com.cashbookcloud.user.service.covert.UserCovert;
import com.cashbookcloud.user.service.entity.User;
import com.cashbookcloud.user.service.mapper.UserMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
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

    @Reference
    private RoleService roleService;

    @Override
    public Page<UserDto> findAllPage(Integer pagenum, Integer pagesize, String query) {
        Page<User> userPage = new Page<>(pagenum,pagesize);
        if(!query.equals("")||query!=null){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.like("id",query).or().like("user_name",query).or().like("user_phone",query);
            Page<User> userPage1 = userMapper.selectPage(userPage, wrapper);
            ArrayList<UserDto> userDtos = new ArrayList<>();
            for (int i = 0; i < userPage1.getRecords().size(); i++) {
                User user = userPage1.getRecords().get(i);
                Integer roleId = user.getRoleId();
                RoleDto byId = roleService.findById(roleId);
                UserDto userDto = UserCovert.INSTANCE.entity2dto(user);
                userDto.setRoleName(byId.getRoleName());
                userDtos.add(userDto);
            }
            Page<UserDto> userDtoPage = new Page<>();
            userDtoPage.setRecords(userDtos);
            userDtoPage.setSize(userPage1.getSize());
            userDtoPage.setCurrent(userPage1.getCurrent());
            userDtoPage.setTotal(userPage1.getTotal());
            userDtoPage.setOrders(userPage1.getOrders());
            userDtoPage.setMaxLimit(userPage1.getMaxLimit());
            return userDtoPage;
        }else {

            Page<User> userPage1 = userMapper.selectPage(userPage, null);
            ArrayList<UserDto> userDtos = new ArrayList<>();
            for (int i = 0; i < userPage1.getRecords().size(); i++) {
                User user = userPage1.getRecords().get(i);
                Integer roleId = user.getRoleId();
                RoleDto byId = roleService.findById(roleId);
                UserDto userDto = UserCovert.INSTANCE.entity2dto(user);
                userDto.setRoleName(byId.getRoleName());
                userDtos.add(userDto);
            }
            Page<UserDto> userDtoPage = new Page<>();
            userDtoPage.setRecords(userDtos);
            userDtoPage.setSize(userPage1.getSize());
            userDtoPage.setCurrent(userPage1.getCurrent());
            userDtoPage.setTotal(userPage1.getTotal());
            userDtoPage.setOrders(userPage1.getOrders());
            userDtoPage.setMaxLimit(userPage1.getMaxLimit());
            return userDtoPage;
        }
    }

    @Override
    public UserDto add(UserDto userDto) {
        User user = UserCovert.INSTANCE.dto2entity(userDto);
        userMapper.insert(user);
        UserDto userDto1 = UserCovert.INSTANCE.entity2dto(user);
        return userDto1;
    }

    @Override
    public void del(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public UserDto upd(UserDto userDto) {
        User user = UserCovert.INSTANCE.dto2entity(userDto);
        userMapper.updateById(user);
        UserDto userDto1 = UserCovert.INSTANCE.entity2dto(user);
        return userDto1;
    }

    @Override
    public UserDto findByUserName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",name);
        User user = userMapper.selectOne(wrapper);
        UserDto userDto = UserCovert.INSTANCE.entity2dto(user);
        return userDto;
    }

    @Override
    public UserDto findById(Integer id) {
        User user = userMapper.selectById(id);
        UserDto userDto = UserCovert.INSTANCE.entity2dto(user);
        return userDto;
    }

    @Override
    public void delByRid(Integer rid) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("role_id",rid);
        userMapper.delete(userQueryWrapper);
    }

    @Override
    public List<String> findPermissionsByUserId(Integer id) {
        User user = userMapper.selectById(id);
        Integer roleId = user.getRoleId();
        RoleDto roleDto = roleService.findById(roleId);
        ArrayList<String> strings = new ArrayList<>();
        strings.add(roleDto.getRoleKeyword());
        return strings;
    }
}
