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
}
