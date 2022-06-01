package com.cashbookcloud.permission.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cashbookcloud.permission.service.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
