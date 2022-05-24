package com.cashbookcloud.user.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.user.api.dto.UserDto;

public interface UserService {

    Page<UserDto> findAllPage(Integer pagenum,Integer pagesize,String query);

    UserDto add(UserDto userDto);

    void del(Integer id);

    UserDto upd(UserDto userDto);

    UserDto findByUserName(String name);

    UserDto findById(Integer id);

}
