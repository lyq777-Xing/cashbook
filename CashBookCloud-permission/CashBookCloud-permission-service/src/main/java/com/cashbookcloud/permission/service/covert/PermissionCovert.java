package com.cashbookcloud.permission.service.covert;

import com.cashbookcloud.permission.api.dto.PermissionDto;
import com.cashbookcloud.permission.service.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionCovert {
    PermissionCovert INSTANCE = Mappers.getMapper(PermissionCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    PermissionDto entity2dto(Permission entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    Permission dto2entity(PermissionDto dto);

    /**
     * 将dto 转化为vo
     */
//    BillVo dto2vo(BillDto dto);

    /**
     * 将vo 转化为dto
     */
//    BillDto vo2dto(BillVo vo);
}
