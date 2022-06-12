package com.cashbookcloud.cat.service.covert;


import com.cashbookcloud.cat.api.dto.CatDto;
import com.cashbookcloud.cat.service.entity.Cat;
import com.cashbookcloud.cat.service.vo.CatVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatCovert {
    CatCovert INSTANCE = Mappers.getMapper(CatCovert.class);

    /**
     * 将entity转化为DTO
     * @param entity
     * @return
     */
    CatDto entity2dto(Cat entity);

    /**
     * 把DTO转化为entity
     * @param dto
     * @return
     */
    Cat dto2entity(CatDto dto);

    /**
     * 将dto 转化为vo
     */
    CatVo dto2vo(CatDto dto);
}
