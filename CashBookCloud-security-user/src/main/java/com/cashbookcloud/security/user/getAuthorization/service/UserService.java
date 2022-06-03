package com.cashbookcloud.security.user.getAuthorization.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.security.user.getAuthorization.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findByUserName(String name);


    List<String> findPermissionsByUserId(Integer id);
}
