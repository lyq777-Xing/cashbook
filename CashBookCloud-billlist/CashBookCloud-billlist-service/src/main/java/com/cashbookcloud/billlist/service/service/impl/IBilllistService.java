package com.cashbookcloud.billlist.service.service.impl;

import com.cashbookcloud.billlist.api.dto.BilllistDto;
import com.cashbookcloud.billlist.api.service.BilllistService;
import com.cashbookcloud.billlist.service.entity.Billlist;
import com.cashbookcloud.billlist.service.mapper.BilllistMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@org.springframework.stereotype.Service
@Transactional
public class IBilllistService implements BilllistService {

    @Autowired
    private BilllistMapper billlistMapper;

    @Override
    public BilllistDto findById(Integer id) {
        Billlist billlist = billlistMapper.selectById(id);
        return null;
    }
}
