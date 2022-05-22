package com.cashbookcloud.manager.service.covert;

import com.cashbookcloud.manager.api.dto.ManagerDto;
import com.cashbookcloud.manager.service.entity.Manager;
import com.cashbookcloud.manager.service.vo.ManagerVo;
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
    ManagerVo dto2vo(ManagerDto dto);

    /**
     * 将vo 转化为dto
     */
    ManagerDto vo2dto(ManagerVo vo);
}
