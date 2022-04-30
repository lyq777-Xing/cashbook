package com.cashbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("ck_permission_api")
public class CkPermissionApi implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * api的id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限的id
     */
    private Integer permissionId;

    /**
     * 权限的keyword
     */
    private String permissionKeyword;


}
