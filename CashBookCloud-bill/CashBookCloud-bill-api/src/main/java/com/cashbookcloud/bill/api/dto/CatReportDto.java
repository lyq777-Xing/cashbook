package com.cashbookcloud.bill.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatReportDto implements Serializable {
    private String name;
    private Double value;
}
