package com.cashbookcloud.permission.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("ck_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限等级
     */
    private String permissionLevel;

    /**
     * 权限地址
     */
    private String permissionPath;

    /**
     * 权限pid
     */
    private Integer permissionPid;


}
