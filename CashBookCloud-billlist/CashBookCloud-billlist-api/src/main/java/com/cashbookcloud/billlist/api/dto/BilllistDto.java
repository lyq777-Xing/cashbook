package com.cashbookcloud.billlist.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BilllistDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String billlistName;


}
