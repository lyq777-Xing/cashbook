package com.cashbookcloud.permissionapi.service.covert;

import com.cashbookcloud.permissionapi.api.dto.PermissionApiDto;
import com.cashbookcloud.permissionapi.service.entity.PermissionApi;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionApiCovert {
    PermissionApiCovert INSTANCE = Mappers.getMapper(PermissionApiCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    PermissionApiDto entity2dto(PermissionApi entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    PermissionApi dto2entity(PermissionApiDto dto);

    /**
     * 将dto 转化为vo
     */
//    BillVo dto2vo(BillDto dto);

    /**
     * 将vo 转化为dto
     */
//    BillDto vo2dto(BillVo vo);
}
