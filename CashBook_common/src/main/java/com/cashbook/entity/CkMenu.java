package com.cashbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("ck_menu")
public class CkMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String menuName;

    private String menuLevel;

    private Integer menuPid;

    @TableField(exist = false)
    private List<CkMenu> children;


}
