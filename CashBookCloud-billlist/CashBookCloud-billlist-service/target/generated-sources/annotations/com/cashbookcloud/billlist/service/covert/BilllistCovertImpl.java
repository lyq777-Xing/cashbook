package com.cashbookcloud.billlist.service.covert;

import com.cashbookcloud.billlist.api.dto.BilllistDto;
import com.cashbookcloud.billlist.service.entity.Billlist;
import com.cashbookcloud.billlist.service.vo.BilllistVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-05T19:24:21+0800",
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
        billlistDto.setBilllistImg( entity.getBilllistImg() );
        billlistDto.setUserId( entity.getUserId() );

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
        billlist.setBilllistImg( dto.getBilllistImg() );
        billlist.setUserId( dto.getUserId() );

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
        billlistVo.setBilllistImg( dto.getBilllistImg() );
        billlistVo.setUserId( dto.getUserId() );
        billlistVo.setCount( dto.getCount() );
        billlistVo.setJieyu( dto.getJieyu() );
        billlistVo.setZhichu( dto.getZhichu() );
        billlistVo.setShouru( dto.getShouru() );

        return billlistVo;
    }

    @Override
    public BilllistDto vo2dto(BilllistVo billlistVo) {
        if ( billlistVo == null ) {
            return null;
        }

        BilllistDto billlistDto = new BilllistDto();

        billlistDto.setId( billlistVo.getId() );
        billlistDto.setBilllistName( billlistVo.getBilllistName() );
        billlistDto.setBilllistImg( billlistVo.getBilllistImg() );
        billlistDto.setUserId( billlistVo.getUserId() );
        billlistDto.setCount( billlistVo.getCount() );
        billlistDto.setJieyu( billlistVo.getJieyu() );
        billlistDto.setZhichu( billlistVo.getZhichu() );
        billlistDto.setShouru( billlistVo.getShouru() );

        return billlistDto;
    }
}
