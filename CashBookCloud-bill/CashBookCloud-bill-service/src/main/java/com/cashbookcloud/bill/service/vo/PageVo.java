package com.cashbookcloud.bill.service.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageVo implements Serializable {
    private Integer pagenum;
    private Integer pagesize;
    private String query;
}
