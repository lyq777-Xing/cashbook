package com.cashbookcloud.bill.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BilllistDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String billlistName;

    private String billlistImg;

    private Integer userId;

    /**
     * 一共记录了多少笔
     */
    private Integer count;

    /**
     * 该账本的结余金额
     */
    private Double jieyu;

    /**
     * 该账本的支出金额
     */
    private Double zhichu;

    /**
     * 该账本的收入金额
     */
    private Double shouru;


}
