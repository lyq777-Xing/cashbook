package com.cashbookcloud.manager.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.manager.api.dto.ManagerDto;

public interface ManagerService {
    ManagerDto findByUsername(String username);

    String getAuthorityInfo(Integer userId);

    Page<ManagerDto> findAllManagerPage(Integer pagenum, Integer pagesize, String query);

    void deleteById(Integer id);

    ManagerDto updateById(ManagerDto managerDto);

    ManagerDto addManager(ManagerDto managerDto);

}
