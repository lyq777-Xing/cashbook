package com.cashbookcloud.security.admin.getAuthorization.covert;

import com.cashbookcloud.security.admin.getAuthorization.dto.RoleDto;
import com.cashbookcloud.security.admin.getAuthorization.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleCovert {
    RoleCovert INSTANCE = Mappers.getMapper(RoleCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    RoleDto entity2dto(Role entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    Role dto2entity(RoleDto dto);

    /**
     * 将dto 转化为vo
     */
//    RoleVo dto2vo(RoleDto dto);

    /**
     * 将vo 转化为dto
     */
//    RoleDto vo2dto(RoleVo vo);
}
