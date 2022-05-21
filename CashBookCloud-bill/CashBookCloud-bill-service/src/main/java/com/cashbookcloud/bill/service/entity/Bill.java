package com.cashbookcloud.bill.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@TableName("ck_bill")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单id
     */
    @TableId(value = "id", type = IdType.AUTO)
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


}
