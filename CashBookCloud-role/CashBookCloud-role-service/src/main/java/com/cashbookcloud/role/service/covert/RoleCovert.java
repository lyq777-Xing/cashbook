package com.cashbookcloud.role.service.covert;

import com.cashbookcloud.role.api.dto.RoleDto;
import com.cashbookcloud.role.service.entity.Role;
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
