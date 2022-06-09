package com.cashbookcloud.cat.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("ck_cat")
public class Cat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 该分类的id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 该分类的名称
     */
    private String catName;

    private String catImg;

    private String catDesc;


}
