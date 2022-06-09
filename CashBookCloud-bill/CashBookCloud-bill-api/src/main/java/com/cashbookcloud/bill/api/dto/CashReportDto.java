package com.cashbookcloud.bill.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CashReportDto implements Serializable {
    private List<String> x;
    private List<Double> y;
}
