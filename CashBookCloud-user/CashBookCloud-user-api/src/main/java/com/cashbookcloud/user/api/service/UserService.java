package com.cashbookcloud.user.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.user.api.dto.ReportDto;
import com.cashbookcloud.user.api.dto.UserDto;

import java.util.List;

public interface UserService {

    Page<UserDto> findAllPage(Integer pagenum,Integer pagesize,String query);

    UserDto add(UserDto userDto);

    void del(Integer id);

    UserDto upd(UserDto userDto);

    UserDto findByUserName(String name);

    UserDto findById(Integer id);

    void delByRid(Integer rid);

    List<String> findPermissionsByUserId(Integer id);

    List<ReportDto> getReport();
}
