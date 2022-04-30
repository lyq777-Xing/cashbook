package com.cashbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("ck_bill")
public class CkBill implements Serializable {

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
    private LocalDate billDate;

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
