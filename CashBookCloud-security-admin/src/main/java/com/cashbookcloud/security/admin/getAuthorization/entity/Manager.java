package com.cashbookcloud.security.admin.getAuthorization.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@TableName("ck_manager")
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 该管理员的id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String mgName;

    /**
     * 密码
     */
    private String mgPassword;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 用户电话
     */
    private String mgPhone;

    /**
     * 用户创建时间
     */
    private LocalDate mgDate;

    /**
     * 用户邮箱
     */
    private String mgEmail;

    /**
     * 用户头像
     */
    private String mgHeader;


}

