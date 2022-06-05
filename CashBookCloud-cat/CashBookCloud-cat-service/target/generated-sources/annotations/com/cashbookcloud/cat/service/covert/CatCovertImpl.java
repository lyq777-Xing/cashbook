package com.cashbookcloud.cat.service.covert;

import com.cashbookcloud.cat.api.dto.CatDto;
import com.cashbookcloud.cat.service.entity.Cat;
import com.cashbookcloud.cat.service.vo.CatVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-05T19:24:14+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
public class CatCovertImpl implements CatCovert {

    @Override
    public CatDto entity2dto(Cat entity) {
        if ( entity == null ) {
            return null;
        }

        CatDto catDto = new CatDto();

        catDto.setId( entity.getId() );
        catDto.setCatName( entity.getCatName() );

        return catDto;
    }

    @Override
    public Cat dto2entity(CatDto dto) {
        if ( dto == null ) {
            return null;
        }

        Cat cat = new Cat();

        cat.setId( dto.getId() );
        cat.setCatName( dto.getCatName() );

        return cat;
    }

    @Override
    public CatVo dto2vo(CatDto dto) {
        if ( dto == null ) {
            return null;
        }

        CatVo catVo = new CatVo();

        return catVo;
    }
}
