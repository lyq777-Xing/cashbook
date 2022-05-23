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
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@org.springframework.stereotype.Service
@Transactional
public class IManagerService implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Reference
    private RoleService roleService;

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
        return null;
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
        Manager manager = ManagerCovert.INSTANCE.dto2entity(managerDto);
        managerMapper.insert(manager);
        ManagerDto managerDto1 = ManagerCovert.INSTANCE.entity2dto(manager);
        return managerDto1;
    }
}
