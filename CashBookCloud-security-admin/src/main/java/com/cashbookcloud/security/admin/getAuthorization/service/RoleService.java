package com.cashbookcloud.security.admin.getAuthorization.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.security.admin.getAuthorization.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto findById(Integer id);

}
