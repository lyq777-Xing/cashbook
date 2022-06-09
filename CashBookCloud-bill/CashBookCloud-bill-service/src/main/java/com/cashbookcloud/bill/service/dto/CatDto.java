package com.cashbookcloud.bill.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatDto implements Serializable {

    private static final long serialVersionUID = 1L;

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
