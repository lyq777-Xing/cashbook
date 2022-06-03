package com.cashbookcloud.security.user.getAuthorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cashbookcloud.security.user.getAuthorization.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
