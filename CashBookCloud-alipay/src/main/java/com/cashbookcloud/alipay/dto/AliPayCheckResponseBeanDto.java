package com.cashbookcloud.alipay.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AliPayCheckResponseBeanDto implements Serializable {
    private String code;
    private String msg;
    private String buyer_logon_id;
    private String buyer_pay_amount;
    private String buyer_user_id;
    private String buyer_user_type;
    private String invoice_amount;
    private String out_trade_no;
    private String point_amount;
    private String receipt_amount;
    private String send_pay_date;
    private String total_amount;
    private String trade_no;
    private String trade_status;
}
