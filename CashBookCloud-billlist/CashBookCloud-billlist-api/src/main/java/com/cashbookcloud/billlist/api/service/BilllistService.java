package com.cashbookcloud.billlist.api.service;

import com.cashbookcloud.billlist.api.dto.BilllistDto;

import java.util.List;

public interface BilllistService {
    /**
     * 根据id查询账单
     * @param id
     * @return
     */
    BilllistDto findById(Integer id);

    List<BilllistDto> getAllList();
}
