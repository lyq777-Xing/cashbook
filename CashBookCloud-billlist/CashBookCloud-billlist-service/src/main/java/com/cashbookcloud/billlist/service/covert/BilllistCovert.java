package com.cashbookcloud.billlist.service.covert;


import com.cashbookcloud.billlist.api.dto.BilllistDto;
import com.cashbookcloud.billlist.service.entity.Billlist;
import com.cashbookcloud.billlist.service.vo.BilllistVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BilllistCovert {
    BilllistCovert INSTANCE = Mappers.getMapper(BilllistCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    BilllistDto entity2dto(Billlist entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    Billlist dto2entity(BilllistDto dto);

    /**
     * 将dto 转化为vo
     */
    BilllistVo dto2vo(BilllistDto dto);

    /**
     * 将vo 转化为dto
     */
    BilllistDto vo2dto(BilllistVo billlistVo);
}
