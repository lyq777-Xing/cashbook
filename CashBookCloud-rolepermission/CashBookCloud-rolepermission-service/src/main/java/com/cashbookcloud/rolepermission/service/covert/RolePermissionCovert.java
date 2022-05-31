package com.cashbookcloud.rolepermission.service.covert;

import com.cashbookcloud.rolepermission.api.dto.RolePermissionDto;
import com.cashbookcloud.rolepermission.service.entity.RolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolePermissionCovert {
    RolePermissionCovert INSTANCE = Mappers.getMapper(RolePermissionCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    RolePermissionDto entity2dto(RolePermission entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    RolePermission dto2entity(RolePermissionDto dto);

    /**
     * 将dto 转化为vo
     */
//    BillVo dto2vo(BillDto dto);

    /**
     * 将vo 转化为dto
     */
//    BillDto vo2dto(BillVo vo);
}
