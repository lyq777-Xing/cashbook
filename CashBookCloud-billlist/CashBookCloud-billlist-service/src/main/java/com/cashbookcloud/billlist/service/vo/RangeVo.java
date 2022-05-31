package com.cashbookcloud.billlist.service.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class RangeVo implements Serializable {
    private Integer value;
    private String text;
}
