package com.cashbookcloud.manager.service.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

//@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//@JsonSerialize(using = LocalDateTimeSerializer.class)
@Data
public class ManagerVo implements Serializable {
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
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate mgDate;

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
