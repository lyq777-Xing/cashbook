package com.cashbookcloud.alipay.covert;

import com.cashbookcloud.alipay.dto.AliPayCheckResponseBeanDto;
import com.cashbookcloud.alipay.entity.AliPayCheckResponseBean;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-13T19:52:39+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
public class AlipayCovertImpl implements AlipayCovert {

    @Override
    public AliPayCheckResponseBeanDto entity2dto(AliPayCheckResponseBean entity) {
        if ( entity == null ) {
            return null;
        }

        AliPayCheckResponseBeanDto aliPayCheckResponseBeanDto = new AliPayCheckResponseBeanDto();

        aliPayCheckResponseBeanDto.setBuyer_logon_id( entity.getBuyer_logon_id() );
        aliPayCheckResponseBeanDto.setBuyer_user_type( entity.getBuyer_user_type() );
        aliPayCheckResponseBeanDto.setOut_trade_no( entity.getOut_trade_no() );
        aliPayCheckResponseBeanDto.setSend_pay_date( entity.getSend_pay_date() );
        aliPayCheckResponseBeanDto.setTotal_amount( entity.getTotal_amount() );
        aliPayCheckResponseBeanDto.setTrade_no( entity.getTrade_no() );

        return aliPayCheckResponseBeanDto;
    }

    @Override
    public AliPayCheckResponseBean dto2entity(AliPayCheckResponseBeanDto dto) {
        if ( dto == null ) {
            return null;
        }

        AliPayCheckResponseBean aliPayCheckResponseBean = new AliPayCheckResponseBean();

        aliPayCheckResponseBean.setBuyer_logon_id( dto.getBuyer_logon_id() );
        aliPayCheckResponseBean.setBuyer_user_type( dto.getBuyer_user_type() );
        aliPayCheckResponseBean.setOut_trade_no( dto.getOut_trade_no() );
        aliPayCheckResponseBean.setSend_pay_date( dto.getSend_pay_date() );
        aliPayCheckResponseBean.setTotal_amount( dto.getTotal_amount() );
        aliPayCheckResponseBean.setTrade_no( dto.getTrade_no() );

        return aliPayCheckResponseBean;
    }
}
