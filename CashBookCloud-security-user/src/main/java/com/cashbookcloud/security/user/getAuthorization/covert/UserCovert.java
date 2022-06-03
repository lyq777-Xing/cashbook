package com.cashbookcloud.security.user.getAuthorization.covert;

import com.cashbookcloud.security.user.getAuthorization.dto.UserDto;
import com.cashbookcloud.security.user.getAuthorization.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCovert {
    UserCovert INSTANCE = Mappers.getMapper(UserCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    UserDto entity2dto(User entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    User dto2entity(UserDto dto);

    /**
     * 将dto 转化为vo
     */
//    UserVo dto2vo(UserDto dto);

    /**
     * 将vo 转化为dto
     */
//    UserDto vo2dto(UserVo vo);
}
