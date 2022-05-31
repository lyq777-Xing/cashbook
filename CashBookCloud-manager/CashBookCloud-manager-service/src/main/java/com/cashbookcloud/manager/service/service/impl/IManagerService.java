package com.cashbookcloud.manager.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.manager.api.dto.ManagerDto;
import com.cashbookcloud.manager.api.service.ManagerService;
import com.cashbookcloud.manager.service.covert.ManagerCovert;
import com.cashbookcloud.manager.service.entity.Manager;
import com.cashbookcloud.manager.service.mapper.ManagerMapper;
import com.cashbookcloud.role.api.dto.RoleDto;
import com.cashbookcloud.role.api.service.RoleService;
import com.cashbookcloud.rolepermission.api.dto.RolePermissionDto;
import com.cashbookcloud.rolepermission.api.service.RolePermissionService;
import com.cashbookcloud.rolepermission.service.entity.RolePermission;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@org.springframework.stereotype.Service
@Transactional
public class IManagerService implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Reference
    private RoleService roleService;

    @Reference
    private RolePermissionService rolePermissionService;

    @Override
    public ManagerDto findByUsername(String username) {
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.eq("mg_name",username);
        Manager manager = managerMapper.selectOne(wrapper);
        ManagerDto managerDto = ManagerCovert.INSTANCE.entity2dto(manager);
        return managerDto;
    }

    @Override
    public String getAuthorityInfo(Integer userId) {
        String authority = "";
//        获取用户的name
        Manager manager = managerMapper.selectById(userId);
        String managerName = manager.getMgName();
//        获取角色
        Integer roleId1 = manager.getRoleId();
        RoleDto role = roleService.findById(roleId1);
        if(role != null) {
            if (role.getRoleKeyword() != null) {
                authority = authority + role.getRoleKeyword();
            }
        }
        List<RolePermissionDto> byRoleId = rolePermissionService.findByRoleId(roleId1);

        for (RolePermissionDto rp:byRoleId) {
            if(rp != null){
//                Integer permissionId = rp.getPermissionId();
//                QueryWrapper<CkPermissionApi> wrapper1 = new QueryWrapper<>();
//                wrapper1.eq("permission_id",permissionId);
//                CkPermissionApi api = permissionApiMapper.selectOne(wrapper1);
//                if(api != null){
//                    if(api.getPermissionKeyword() != null){
//                        authority = authority + "," + api.getPermissionKeyword();
//                    }
//                }
            }
        }
        return authority;

    }

    @Override
    public Page<ManagerDto> findAllManagerPage(Integer pagenum, Integer pagesize, String query) {
        Page<Manager> page = new Page<>(pagenum, pagesize);
        if(query != null && !query.equals("")){
            QueryWrapper<Manager> wrapper = new QueryWrapper<>();
            wrapper.like("mg_name",query).or().like("mg_phone",query).or().like("mg_email",query);
            Page<Manager> page1 = managerMapper.selectPage(page, wrapper);
            Page<ManagerDto> managerDtoPage = new Page<>();
            ArrayList<ManagerDto> managerDtos = new ArrayList<>();
            for (int i = 0; i < page1.getRecords().size(); i++) {
                ManagerDto managerDto = ManagerCovert.INSTANCE.entity2dto(page1.getRecords().get(i));
                RoleDto byId = roleService.findById(managerDto.getRoleId());
                managerDto.setRoleName(byId.getRoleName());
                managerDtos.add(managerDto);
            }
            managerDtoPage.setRecords(managerDtos);
            managerDtoPage.setTotal(page1.getTotal());
            managerDtoPage.setCountId(page1.getCountId());
            managerDtoPage.setCurrent(page1.getCurrent());
            managerDtoPage.setMaxLimit(page1.getMaxLimit());
            managerDtoPage.setSize(page1.getSize());
            managerDtoPage.setPages(page1.getPages());
            return managerDtoPage;
        }else{
            Page<Manager> page1 = managerMapper.selectPage(page, null);
            Page<ManagerDto> managerDtoPage = new Page<>();
            ArrayList<ManagerDto> managerDtos = new ArrayList<>();
            for (int i = 0; i < page1.getRecords().size(); i++) {
                ManagerDto managerDto = ManagerCovert.INSTANCE.entity2dto(page1.getRecords().get(i));
                RoleDto byId = roleService.findById(managerDto.getRoleId());
                managerDto.setRoleName(byId.getRoleName());
                managerDtos.add(managerDto);
            }
            managerDtoPage.setRecords(managerDtos);
            managerDtoPage.setTotal(page1.getTotal());
            managerDtoPage.setCountId(page1.getCountId());
            managerDtoPage.setCurrent(page1.getCurrent());
            managerDtoPage.setMaxLimit(page1.getMaxLimit());
            managerDtoPage.setSize(page1.getSize());
            managerDtoPage.setPages(page1.getPages());
            return managerDtoPage;
        }
    }

    @Override
    public void deleteById(Integer id) {
        managerMapper.deleteById(id);
    }

    @Override
    public ManagerDto updateById(ManagerDto managerDto) {
        Manager manager = ManagerCovert.INSTANCE.dto2entity(managerDto);
        managerMapper.updateById(manager);
        ManagerDto managerDto1 = ManagerCovert.INSTANCE.entity2dto(manager);
        return managerDto1;
    }

    @Override
    public ManagerDto addManager(ManagerDto managerDto) {
        Date date = new Date();
//        String s = String.valueOf(date);
//        managerDto.setMgDate((LocalDate) date);
        LocalDate now = LocalDate.now();
        managerDto.setMgDate(now);
        Manager manager = ManagerCovert.INSTANCE.dto2entity(managerDto);
        managerMapper.insert(manager);
        ManagerDto managerDto1 = ManagerCovert.INSTANCE.entity2dto(manager);
        return managerDto1;
    }

    @Override
    public ManagerDto findById(Integer id) {
        Manager manager = managerMapper.selectById(id);
        ManagerDto managerDto = ManagerCovert.INSTANCE.entity2dto(manager);
        return managerDto;
    }

    @Override
    public void delByRoleId(Integer rid) {
        QueryWrapper<Manager> managerQueryWrapper = new QueryWrapper<>();
        managerQueryWrapper.eq("role_id",rid);
        managerMapper.delete(managerQueryWrapper);
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
//        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
//        QueryWrapper<RolePermission> role_id = wrapper.eq("role_id", roleId1);
//        List<CkRolePermission> ckRolePermissions = rolePermissionMapper.selectList(wrapper);
//        for (CkRolePermission rp:ckRolePermissions) {
//            if(rp != null){
//                Integer permissionId = rp.getPermissionId();
//                QueryWrapper<CkPermissionApi> wrapper1 = new QueryWrapper<>();
//                wrapper1.eq("permission_id",permissionId);
//                CkPermissionApi api = permissionApiMapper.selectOne(wrapper1);
//                if(api != null){
//                    if(api.getPermissionKeyword() != null){
//                        authority = authority + "," + api.getPermissionKeyword();
//                    }
//                }
//            }
//        }
        return authority;
    }
}
