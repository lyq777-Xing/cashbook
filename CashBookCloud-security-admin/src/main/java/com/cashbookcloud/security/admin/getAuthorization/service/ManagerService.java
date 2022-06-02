package com.cashbookcloud.security.admin.getAuthorization.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.security.admin.getAuthorization.dto.ManagerDto;

import java.util.List;

public interface ManagerService {
    ManagerDto findByUsername(String username);

    List<String> findPermissionsByUserId(Integer id);

}
