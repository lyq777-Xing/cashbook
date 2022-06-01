package com.cashbookcloud.manager.service.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

//@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//@JsonSerialize(using = LocalDateTimeSerializer.class)
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ManagerVovv implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 该管理员的id
     */
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
    @JsonIgnore
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String mgDate;

    /**
     * 用户邮箱
     */
    private String mgEmail;

    /**
     * 用户头像
     */
    private String mgHeader;
    /**
     * 角色名称
     */
    private String roleName;

}
