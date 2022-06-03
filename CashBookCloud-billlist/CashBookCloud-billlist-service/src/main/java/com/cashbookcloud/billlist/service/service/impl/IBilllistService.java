package com.cashbookcloud.billlist.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cashbookcloud.billlist.api.dto.BilllistDto;
import com.cashbookcloud.billlist.api.service.BilllistService;
import com.cashbookcloud.billlist.service.covert.BilllistCovert;
import com.cashbookcloud.billlist.service.entity.Billlist;
import com.cashbookcloud.billlist.service.mapper.BilllistMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<BilllistDto> getAllList(Integer userId) {
        QueryWrapper<Billlist> billlistQueryWrapper = new QueryWrapper<>();
        billlistQueryWrapper.eq("user_id",userId);
        List<Billlist> billlists = billlistMapper.selectList(billlistQueryWrapper);
        ArrayList<BilllistDto> billlistDtos = new ArrayList<>();
        for (int i = 0; i < billlists.size(); i++) {
            BilllistDto billlistDto = BilllistCovert.INSTANCE.entity2dto(billlists.get(i));
            billlistDtos.add(billlistDto);
        }
        return billlistDtos;
    }

    @Override
    public BilllistDto add(BilllistDto billlistDto) {
        Billlist billlist = BilllistCovert.INSTANCE.dto2entity(billlistDto);
        billlistMapper.insert(billlist);
        BilllistDto billlistDto1 = BilllistCovert.INSTANCE.entity2dto(billlist);
        return billlistDto1;
    }

    @Override
    public void del(Integer id) {
        billlistMapper.deleteById(id);
    }

    @Override
    public BilllistDto upd(BilllistDto billlistDto) {
        Billlist billlist = BilllistCovert.INSTANCE.dto2entity(billlistDto);
        billlistMapper.updateById(billlist);
        BilllistDto billlistDto1 = BilllistCovert.INSTANCE.entity2dto(billlist);
        return billlistDto1;

    }

    @Override
    public BilllistDto findByName(String name,Integer userId) {
        QueryWrapper<Billlist> wrapper = new QueryWrapper<>();
        wrapper.eq("billlist_name",name).eq("user_id",userId);
        Billlist billlist = billlistMapper.selectOne(wrapper);
        BilllistDto billlistDto = BilllistCovert.INSTANCE.entity2dto(billlist);
        return billlistDto;
    }
}
