package com.cashbookcloud.user.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@TableName("ck_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户电话
     */
    private String userPhone;

    /**
     * 用户创建时间
     */
    private LocalDate userCreatedate;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 用户头像
     */
    private String userHeader;

    /**
     * 用户邮箱
     */
    private String userEmail;


}
