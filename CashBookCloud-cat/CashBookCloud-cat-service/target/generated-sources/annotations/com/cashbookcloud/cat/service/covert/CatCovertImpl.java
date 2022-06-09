package com.cashbookcloud.cat.service.covert;

import com.cashbookcloud.cat.api.dto.CatDto;
import com.cashbookcloud.cat.service.entity.Cat;
import com.cashbookcloud.cat.service.vo.CatVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-09T13:24:46+0800",
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
        catDto.setCatImg( entity.getCatImg() );

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
        cat.setCatImg( dto.getCatImg() );

        return cat;
    }

    @Override
    public CatVo dto2vo(CatDto dto) {
        if ( dto == null ) {
            return null;
        }

        CatVo catVo = new CatVo();

        catVo.setId( dto.getId() );
        catVo.setCatName( dto.getCatName() );
        catVo.setCatImg( dto.getCatImg() );

        return catVo;
    }
}
