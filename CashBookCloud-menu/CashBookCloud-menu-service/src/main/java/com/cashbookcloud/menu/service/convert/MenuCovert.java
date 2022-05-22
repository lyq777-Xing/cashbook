package com.cashbookcloud.menu.service.convert;

import com.cashbookcloud.menu.api.dto.MenuDto;
import com.cashbookcloud.menu.service.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuCovert {
    MenuCovert INSTANCE = Mappers.getMapper(MenuCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    MenuDto entity2dto(Menu entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    Menu dto2entity(MenuDto dto);

    /**
     * 将dto 转化为vo
     */
//    BillVo dto2vo(BillDto dto);

    /**
     * 将vo 转化为dto
     */
//    BillDto vo2dto(BillVo vo);
}
