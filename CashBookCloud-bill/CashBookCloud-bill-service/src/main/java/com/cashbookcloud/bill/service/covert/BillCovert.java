package com.cashbookcloud.bill.service.covert;

import com.cashbookcloud.bill.api.dto.BillDto;
import com.cashbookcloud.bill.service.entity.Bill;
import com.cashbookcloud.bill.service.vo.BillVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillCovert {
    BillCovert INSTANCE = Mappers.getMapper(BillCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    BillDto entity2dto(Bill entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    Bill dto2entity(BillDto dto);

    /**
     * 将dto 转化为vo
     */
    BillVo dto2vo(BillDto dto);

    /**
     * 将vo 转化为dto
     */
    BillDto vo2dto(BillVo vo);

}
