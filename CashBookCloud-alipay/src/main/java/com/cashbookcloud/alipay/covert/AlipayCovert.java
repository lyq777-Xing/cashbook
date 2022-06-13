package com.cashbookcloud.alipay.covert;

import com.cashbookcloud.alipay.dto.AliPayCheckResponseBeanDto;
import com.cashbookcloud.alipay.entity.AliPayCheckResponseBean;
import com.cashbookcloud.billlist.api.dto.BilllistDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlipayCovert {

    AlipayCovert INSTANCE = Mappers.getMapper(AlipayCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    AliPayCheckResponseBeanDto entity2dto(AliPayCheckResponseBean entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    AliPayCheckResponseBean dto2entity(AliPayCheckResponseBeanDto dto);

    /**
     * 将dto 转化为vo
     */
//    BilllistVo dto2vo(BilllistDto dto);

    /**
     * 将vo 转化为dto
     */
//    BilllistDto vo2dto(BilllistVo billlistVo);
}
