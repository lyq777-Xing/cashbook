package com.cashbookcloud.role.api.service;

import com.cashbookcloud.role.api.dto.RoleDto;
import io.swagger.models.auth.In;

public interface RoleService {
    RoleDto findById(Integer id);
}
