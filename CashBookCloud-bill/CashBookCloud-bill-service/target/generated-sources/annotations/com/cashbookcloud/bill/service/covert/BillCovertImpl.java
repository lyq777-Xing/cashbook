package com.cashbookcloud.bill.service.covert;

import com.cashbookcloud.bill.api.dto.BillDto;
import com.cashbookcloud.bill.service.entity.Bill;
import com.cashbookcloud.bill.service.vo.BillVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-05T19:24:07+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
public class BillCovertImpl implements BillCovert {

    @Override
    public BillDto entity2dto(Bill entity) {
        if ( entity == null ) {
            return null;
        }

        BillDto billDto = new BillDto();

        billDto.setId( entity.getId() );
        billDto.setCatId( entity.getCatId() );
        billDto.setBillPrice( entity.getBillPrice() );
        billDto.setBillDate( entity.getBillDate() );
        billDto.setBillMode( entity.getBillMode() );
        billDto.setBillDescribe( entity.getBillDescribe() );
        billDto.setBillImg( entity.getBillImg() );
        billDto.setBillName( entity.getBillName() );
        billDto.setBilllistId( entity.getBilllistId() );
        billDto.setUserId( entity.getUserId() );

        return billDto;
    }

    @Override
    public Bill dto2entity(BillDto dto) {
        if ( dto == null ) {
            return null;
        }

        Bill bill = new Bill();

        bill.setId( dto.getId() );
        bill.setCatId( dto.getCatId() );
        bill.setBillPrice( dto.getBillPrice() );
        bill.setBillDate( dto.getBillDate() );
        bill.setBillMode( dto.getBillMode() );
        bill.setBillDescribe( dto.getBillDescribe() );
        bill.setBillImg( dto.getBillImg() );
        bill.setBillName( dto.getBillName() );
        bill.setBilllistId( dto.getBilllistId() );
        bill.setUserId( dto.getUserId() );

        return bill;
    }

    @Override
    public BillVo dto2vo(BillDto dto) {
        if ( dto == null ) {
            return null;
        }

        BillVo billVo = new BillVo();

        billVo.setId( dto.getId() );
        billVo.setCatId( dto.getCatId() );
        billVo.setBillPrice( dto.getBillPrice() );
        billVo.setBillDate( dto.getBillDate() );
        billVo.setBillMode( dto.getBillMode() );
        billVo.setBillDescribe( dto.getBillDescribe() );
        billVo.setBillImg( dto.getBillImg() );
        billVo.setBillName( dto.getBillName() );
        billVo.setBilllistId( dto.getBilllistId() );
        billVo.setCatName( dto.getCatName() );
        billVo.setBilllistName( dto.getBilllistName());
        billVo.setUserId( dto.getUserId() );

        return billVo;
    }

    @Override
    public BillDto vo2dto(BillVo vo) {
        if ( vo == null ) {
            return null;
        }

        BillDto billDto = new BillDto();

        billDto.setId( vo.getId() );
        billDto.setCatId( vo.getCatId() );
        billDto.setBillPrice( vo.getBillPrice() );
        billDto.setBillDate( vo.getBillDate() );
        billDto.setBillMode( vo.getBillMode() );
        billDto.setBillDescribe( vo.getBillDescribe() );
        billDto.setBillImg( vo.getBillImg() );
        billDto.setBillName( vo.getBillName() );
        billDto.setBilllistId( vo.getBilllistId() );
        billDto.setCatName( vo.getCatName() );
        billDto.setBilllistName( vo.getBilllistName()  );
        billDto.setUserId( vo.getUserId() );

        return billDto;
    }
}
