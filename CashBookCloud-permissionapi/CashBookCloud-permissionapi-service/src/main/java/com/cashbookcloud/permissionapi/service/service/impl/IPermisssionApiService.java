package com.cashbookcloud.permissionapi.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cashbookcloud.permissionapi.api.dto.PermissionApiDto;
import com.cashbookcloud.permissionapi.api.service.PermissionApiSerivce;
import com.cashbookcloud.permissionapi.service.covert.PermissionApiCovert;
import com.cashbookcloud.permissionapi.service.entity.PermissionApi;
import com.cashbookcloud.permissionapi.service.mapper.PermissionApiMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@org.springframework.stereotype.Service
public class IPermisssionApiService implements PermissionApiSerivce {

    @Autowired
    private PermissionApiMapper permissionApiMapper;

    @Override
    public PermissionApiDto findById(Integer id) {
        PermissionApi permissionApi = permissionApiMapper.selectById(id);
        PermissionApiDto permissionApiDto = PermissionApiCovert.INSTANCE.entity2dto(permissionApi);
        return permissionApiDto;
    }

    @Override
    public PermissionApiDto findByPermissionId(Integer PermissionId) {
        QueryWrapper<PermissionApi> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_id",PermissionId);
        PermissionApi permissionApi = permissionApiMapper.selectOne(wrapper);
        PermissionApiDto permissionApiDto = PermissionApiCovert.INSTANCE.entity2dto(permissionApi);
        return permissionApiDto;
    }
}
