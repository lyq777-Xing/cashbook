package com.cashbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("ck_cat")
public class CkCat implements Serializable {

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


}
