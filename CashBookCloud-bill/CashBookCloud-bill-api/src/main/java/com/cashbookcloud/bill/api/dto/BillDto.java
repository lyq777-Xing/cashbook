package com.cashbookcloud.bill.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDate;

@ApiModel(value = "MerchantDTO", description = "商户信息")
@Data
public class BillDto {


    private static final long serialVersionUID = 1L;

    /**
     * 账单id
     */
    private Integer id;

    /**
     * 分类id
     */
    private Integer catId;

    /**
     * 该项支出/收入的价格
     */
    private Double billPrice;

    /**
     * 该项支出/收入的时间
     */
    private String billDate;

    /**
     * 该项支出/收入的方式
     */
    private String billMode;

    /**
     * 该列的描述
     */
    private String billDescribe;

    /**
     * 该项支出/收入的照片
     */
    private String billImg;

    /**
     * 该项收入/支出的备注、名称
     */
    private String billName;

    /**
     * 该项支出所属于的账单
     */
    private Integer billlistId;

    /**
     * 分类名称
     */
    private  String catName;

    /**
     * 该项支出所属于的账单名称
     */
    private String billlistName;


}
