package com.cashbookcloud.user.service.covert;

import com.cashbookcloud.user.api.dto.UserDto;
import com.cashbookcloud.user.api.service.UserService;
import com.cashbookcloud.user.service.entity.User;
import com.cashbookcloud.user.service.vo.UserVo;
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
    UserVo dto2vo(UserDto dto);

    /**
     * 将vo 转化为dto
     */
    UserDto vo2dto(UserVo vo);
}
