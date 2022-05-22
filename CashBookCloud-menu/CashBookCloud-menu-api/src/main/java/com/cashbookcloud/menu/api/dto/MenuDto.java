package com.cashbookcloud.menu.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class MenuDto {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String menuName;

    private String menuLevel;

    private Integer menuPid;

    private String menuPath;

    private List<MenuDto> children;
}
