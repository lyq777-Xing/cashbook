package com.cashbookcloud.user.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReportDto implements Serializable {
    private String name;
    private Integer value;
}
