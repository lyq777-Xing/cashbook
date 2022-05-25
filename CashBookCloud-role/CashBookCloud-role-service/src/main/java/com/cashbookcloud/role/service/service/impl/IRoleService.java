package com.cashbookcloud.role.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.role.api.dto.RoleDto;
import com.cashbookcloud.role.api.service.RoleService;
import com.cashbookcloud.role.service.covert.RoleCovert;
import com.cashbookcloud.role.service.entity.Role;
import com.cashbookcloud.role.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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

    @Override
    public RoleDto add(RoleDto roleDto) {
        Role role = RoleCovert.INSTANCE.dto2entity(roleDto);
        roleMapper.insert(role);
        RoleDto roleDto1 = RoleCovert.INSTANCE.entity2dto(role);
        return roleDto1;
    }

    @Override
    public void del(Integer id) {
//        Role role = roleMapper.selectById(id);
//        String rolePojo = role.getRolePojo();
        roleMapper.deleteById(id);
    }

    @Override
    public RoleDto upd(RoleDto roleDto) {
        Role role = RoleCovert.INSTANCE.dto2entity(roleDto);
        roleMapper.updateById(role);
        RoleDto roleDto1 = RoleCovert.INSTANCE.entity2dto(role);
        return roleDto1;
    }

    @Override
    public RoleDto findByName(String roleName) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name",roleName);
        Role role = roleMapper.selectOne(wrapper);
        RoleDto roleDto = RoleCovert.INSTANCE.entity2dto(role);
        return roleDto;
    }

    @Override
    public Page<RoleDto> findAllPage(Integer pagenum, Integer pagesize, String query) {
        Page<Role> page = new Page<>(pagenum, pagesize);
        if(query != null || !query.equals("")){
            QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.like("role_name",query).or().like("role_keyword",query).or().like("role_pojo",query);
            Page<Role> rolePage = roleMapper.selectPage(page, roleQueryWrapper);
            ArrayList<RoleDto> roleDtos = new ArrayList<>();
            for (int i = 0; i < rolePage.getRecords().size(); i++) {
                RoleDto roleDto = RoleCovert.INSTANCE.entity2dto(rolePage.getRecords().get(i));
                roleDtos.add(roleDto);
            }
            Page<RoleDto> roleDtoPage = new Page<>();
            roleDtoPage.setRecords(roleDtos);
            roleDtoPage.setPages(rolePage.getPages());
            roleDtoPage.setOrders(rolePage.getOrders());
            roleDtoPage.setMaxLimit(rolePage.maxLimit());
            roleDtoPage.setCurrent(rolePage.getCurrent());
            roleDtoPage.setTotal(rolePage.getTotal());
            roleDtoPage.setSize(roleDtoPage.getSize());
            return roleDtoPage;
        }else {
            Page<Role> rolePage = roleMapper.selectPage(page, null);
            ArrayList<RoleDto> roleDtos = new ArrayList<>();
            for (int i = 0; i < rolePage.getRecords().size(); i++) {
                RoleDto roleDto = RoleCovert.INSTANCE.entity2dto(rolePage.getRecords().get(i));
                roleDtos.add(roleDto);
            }
            Page<RoleDto> roleDtoPage = new Page<>();
            roleDtoPage.setRecords(roleDtos);
            roleDtoPage.setPages(rolePage.getPages());
            roleDtoPage.setOrders(rolePage.getOrders());
            roleDtoPage.setMaxLimit(rolePage.maxLimit());
            roleDtoPage.setCurrent(rolePage.getCurrent());
            roleDtoPage.setTotal(rolePage.getTotal());
            roleDtoPage.setSize(roleDtoPage.getSize());
            return roleDtoPage;
        }
    }
}
