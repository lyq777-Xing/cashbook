package com.cashbookcloud.role.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色keywrod
     */
    private String roleKeyword;

    /**
     * 角色描述
     */
    private String roleDescribe;

    /**
     * 判断角色是admin的还是user的
     */
    private String rolePojo;

    private List<PermissionDto> children;


}
