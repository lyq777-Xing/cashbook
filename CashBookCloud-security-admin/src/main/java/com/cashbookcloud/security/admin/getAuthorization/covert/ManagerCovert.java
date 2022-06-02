package com.cashbookcloud.security.admin.getAuthorization.covert;

import com.cashbookcloud.security.admin.getAuthorization.dto.ManagerDto;
import com.cashbookcloud.security.admin.getAuthorization.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManagerCovert {
    ManagerCovert INSTANCE = Mappers.getMapper(ManagerCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    ManagerDto entity2dto(Manager entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    Manager dto2entity(ManagerDto dto);

    /**
     * 将dto 转化为vo
     */
//    ManagerVo dto2vo(ManagerDto dto);

    /**
     * 将vo 转化为dto
     */
//    ManagerDto vo2dto(ManagerVo vo);
}
