package com.cashbookcloud.billlist.service.covert;

import com.cashbookcloud.billlist.api.dto.BilllistDto;
import com.cashbookcloud.billlist.service.entity.Billlist;
import com.cashbookcloud.billlist.service.vo.BilllistVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-30T09:16:59+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
public class BilllistCovertImpl implements BilllistCovert {

    @Override
    public BilllistDto entity2dto(Billlist entity) {
        if ( entity == null ) {
            return null;
        }

        BilllistDto billlistDto = new BilllistDto();

        billlistDto.setId( entity.getId() );
        billlistDto.setBilllistName( entity.getBilllistName() );

        return billlistDto;
    }

    @Override
    public Billlist dto2entity(BilllistDto dto) {
        if ( dto == null ) {
            return null;
        }

        Billlist billlist = new Billlist();

        billlist.setId( dto.getId() );
        billlist.setBilllistName( dto.getBilllistName() );

        return billlist;
    }

    @Override
    public BilllistVo dto2vo(BilllistDto dto) {
        if ( dto == null ) {
            return null;
        }

        BilllistVo billlistVo = new BilllistVo();

        billlistVo.setId( dto.getId() );
        billlistVo.setBilllistName( dto.getBilllistName() );

        return billlistVo;
    }
}
