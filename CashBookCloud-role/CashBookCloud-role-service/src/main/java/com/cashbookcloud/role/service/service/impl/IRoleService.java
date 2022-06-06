package com.cashbookcloud.role.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.role.api.dto.PermissionDto;
import com.cashbookcloud.role.api.dto.RoleDto;
import com.cashbookcloud.role.api.dto.RolePermissionDto;
import com.cashbookcloud.role.api.service.RoleService;
import com.cashbookcloud.role.service.client.PermissionClient;
import com.cashbookcloud.role.service.client.RolePermissionClient;
import com.cashbookcloud.role.service.covert.RoleCovert;
import com.cashbookcloud.role.service.entity.Role;
import com.cashbookcloud.role.service.mapper.RoleMapper;
import net.sf.json.JSONObject;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@org.apache.dubbo.config.annotation.Service
//@Transactional
public class IRoleService implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionClient rolePermissionClient;

    @Autowired
    private PermissionClient permissionClient;

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
            for (RoleDto r:roleDtos) {
                Integer roleId = r.getId();
                List<RolePermissionDto> byRid = rolePermissionClient.findByRid(roleId);
                ArrayList<PermissionDto> first = new ArrayList<>();
                ArrayList<PermissionDto> two = new ArrayList<>();
                for (RolePermissionDto rp:byRid) {
                    Integer permissionId = rp.getPermissionId();
                    ResponseResult result = permissionClient.findById(permissionId);
                    JSONObject jsonObject = JSONObject.fromObject(result.getData());
                    PermissionDto permissionDto = (PermissionDto) JSONObject.toBean(jsonObject, PermissionDto.class);
                    if(Integer.parseInt(permissionDto.getPermissionLevel()) == 0){
                        first.add(permissionDto);
                    }else {
                        two.add(permissionDto);
                    }
                }
                for (PermissionDto p:first) {
                    ArrayList<PermissionDto> list = new ArrayList<>();
                    for (PermissionDto c :two) {
                        if(c.getPermissionPid().intValue() == p.getId().intValue()){
                            list.add(c);
                        }
                    }
                    p.setChildren(list);
                }
                r.setChildren(first);
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

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = roleMapper.selectList(null);
        ArrayList<RoleDto> roleDtos = new ArrayList<>();
        for (Role r:roles) {
            RoleDto roleDto = RoleCovert.INSTANCE.entity2dto(r);
            roleDtos.add(roleDto);
        }
        for (RoleDto r:roleDtos) {
            Integer roleId = r.getId();
            List<RolePermissionDto> byRid = rolePermissionClient.findByRid(roleId);
            ArrayList<PermissionDto> first = new ArrayList<>();
            ArrayList<PermissionDto> two = new ArrayList<>();
            for (RolePermissionDto rp:byRid) {
                Integer permissionId = rp.getPermissionId();
                ResponseResult result = permissionClient.findById(permissionId);
                JSONObject jsonObject = JSONObject.fromObject(result.getData());
                PermissionDto permissionDto = (PermissionDto) JSONObject.toBean(jsonObject, PermissionDto.class);
                if(Integer.parseInt(permissionDto.getPermissionLevel()) == 0){
                    first.add(permissionDto);
                }else {
                    two.add(permissionDto);
                }
            }
            for (PermissionDto p:first) {
                ArrayList<PermissionDto> list = new ArrayList<>();
                for (PermissionDto c :two) {
                    if(c.getPermissionPid().intValue() == p.getId().intValue()){
                        list.add(c);
                    }
                }
                p.setChildren(list);
            }
           r.setChildren(first);
        }
        return roleDtos;
    }

    @Override
    public List<RoleDto> findAllAdmin() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_pojo","admin");
        List<Role> roles = roleMapper.selectList(wrapper);
        ArrayList<RoleDto> roleDtos = new ArrayList<>();
        for (Role r:roles) {
            RoleDto roleDto = RoleCovert.INSTANCE.entity2dto(r);
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }

    @Override
    public List<RoleDto> findAllUser() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_pojo","user");
        List<Role> roles = roleMapper.selectList(wrapper);
        ArrayList<RoleDto> roleDtos = new ArrayList<>();
        for (Role r:roles) {
            RoleDto roleDto = RoleCovert.INSTANCE.entity2dto(r);
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }

    @Override
    public RoleDto findPermissionByRoleId(Integer roleId) {
        List<RolePermissionDto> byRid = rolePermissionClient.findByRid(roleId);
        ArrayList<PermissionDto> first = new ArrayList<>();
        ArrayList<PermissionDto> two = new ArrayList<>();
        for (RolePermissionDto rp:byRid) {
            Integer permissionId = rp.getPermissionId();
            ResponseResult result = permissionClient.findById(permissionId);
            JSONObject jsonObject = JSONObject.fromObject(result.getData());
            PermissionDto permissionDto = (PermissionDto) JSONObject.toBean(jsonObject, PermissionDto.class);
            if(Integer.parseInt(permissionDto.getPermissionLevel()) == 0){
                first.add(permissionDto);
            }else {
                two.add(permissionDto);
            }
        }
        for (PermissionDto p:first) {
            ArrayList<PermissionDto> list = new ArrayList<>();
            for (PermissionDto c :two) {
                if(c.getPermissionPid().intValue() == p.getId().intValue()){
                    list.add(c);
                }
            }
            p.setChildren(list);
        }
        Role role = roleMapper.selectById(roleId);
        RoleDto roleDto = RoleCovert.INSTANCE.entity2dto(role);
        roleDto.setChildren(first);
        return roleDto;
    }

    @Override
    public List<PermissionDto> removeRightByPermissionId(Integer roleId, Integer permissionId) {
//        先判断permissionId的pid是否为0
        ResponseResult result = permissionClient.findById(permissionId);
        JSONObject jsonObject = JSONObject.fromObject(result.getData());
        PermissionDto permissionDto = (PermissionDto)JSONObject.toBean(jsonObject, PermissionDto.class);
        if(permissionDto.getPermissionPid().intValue() == 0){
//            说明是父权限 需要将子权限一起删除
            List<PermissionDto> permissionDtos = permissionClient.findByPid(permissionId);
//            根据roleid判断有没有对应的子权限
            for (PermissionDto pd:permissionDtos) {
                Integer pdId = pd.getId();
                List<RolePermissionDto> list = rolePermissionClient.findByRid(roleId);
                for (RolePermissionDto rp:list) {
                    if(rp.getPermissionId().intValue() == pdId){
//                        说明有该子权限 删除
                        rolePermissionClient.del(roleId,pdId);
                    }
                }
            }
//            最后删除父权限
            rolePermissionClient.del(roleId,permissionId);
        }else {
//            说明是子权限 直接删除即可
            rolePermissionClient.del(roleId,permissionId);
        }
//        根据roleid查询权限
        List<RolePermissionDto> byRid = rolePermissionClient.findByRid(roleId);
        ArrayList<PermissionDto> first = new ArrayList<>();
        ArrayList<PermissionDto> two = new ArrayList<>();
        for (RolePermissionDto rp:byRid) {
            Integer permissionId2 = rp.getPermissionId();
            ResponseResult result2 = permissionClient.findById(permissionId2);
            JSONObject jsonObject2 = JSONObject.fromObject(result2.getData());
            PermissionDto permissionDto2 = (PermissionDto) JSONObject.toBean(jsonObject2, PermissionDto.class);
            if(Integer.parseInt(permissionDto2.getPermissionLevel()) == 0){
                first.add(permissionDto2);
            }else {
                two.add(permissionDto2);
            }
        }
        for (PermissionDto p:first) {
            ArrayList<PermissionDto> list = new ArrayList<>();
            for (PermissionDto c :two) {
                if(c.getPermissionPid().intValue() == p.getId().intValue()){
                    list.add(c);
                }
            }
            p.setChildren(list);
        }
        return first;
    }

    @Override
    public void updRolePermissionByRoleIAndPermissionIds(Integer roleId, Integer[] permissionIds) {
//        先根据roleId删除对应的数据内容
        // TODO: 2022/6/6
        rolePermissionClient.del(roleId);
//        在根据roleId加权限id对表重新添加数据
        // TODO: 2022/6/6
        rolePermissionClient.add(roleId, permissionIds);
    }
}
