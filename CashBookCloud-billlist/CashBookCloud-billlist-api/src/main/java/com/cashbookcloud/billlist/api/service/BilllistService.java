package com.cashbookcloud.billlist.api.service;

import com.cashbookcloud.billlist.api.dto.BilllistDto;
import io.swagger.models.auth.In;

import java.util.List;

public interface BilllistService {
    /**
     * 根据id查询账单
     * @param id
     * @return
     */
    BilllistDto findById(Integer id);

    List<BilllistDto> getAllList(Integer userId);

    BilllistDto add(BilllistDto billlistDto);

    void del(Integer id);

    BilllistDto upd(BilllistDto billlistDto);

    BilllistDto findByName(String name,Integer userId);

}
