package com.cashbookcloud.user.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
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
     * 用户角色
     */
    private String roleName;
}
