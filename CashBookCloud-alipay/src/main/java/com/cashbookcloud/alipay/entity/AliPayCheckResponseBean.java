package com.cashbookcloud.alipay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("ck_alipay")
public class AliPayCheckResponseBean implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String buyer_logon_id;
    private String buyer_user_type;
    private String out_trade_no;
    private String send_pay_date;
    private String total_amount;
    private String trade_no;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBuyerLogonId(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public void setBuyerUserType(String buyer_user_type) {
        this.buyer_user_type = buyer_user_type;
    }

    public void setOutTradeNo(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public void setSendPayDate(String send_pay_date) {
        this.send_pay_date = send_pay_date;
    }

    public void setTotalAmount(String total_amount) {
        this.total_amount = total_amount;
    }

    public void setTradeNo(String trade_no) {
        this.trade_no = trade_no;
    }
}
