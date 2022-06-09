package com.cashbookcloud.cat.service.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatVo implements Serializable {

    /**
     * 该分类的id
     */
    private Integer id;

    /**
     * 该分类的名称
     */
    private String catName;

    private String catImg;

    private String catDesc;
}
