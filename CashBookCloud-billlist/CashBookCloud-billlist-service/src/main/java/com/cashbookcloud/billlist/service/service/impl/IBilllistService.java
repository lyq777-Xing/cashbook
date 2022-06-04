package com.cashbookcloud.billlist.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cashbookcloud.billlist.api.dto.BilllistDto;
import com.cashbookcloud.billlist.api.service.BilllistService;
import com.cashbookcloud.billlist.service.client.BillClient;
import com.cashbookcloud.billlist.service.covert.BilllistCovert;
import com.cashbookcloud.billlist.service.dto.KeepingDto;
import com.cashbookcloud.billlist.service.entity.Billlist;
import com.cashbookcloud.billlist.service.mapper.BilllistMapper;
import com.cashbookcloud.billlist.service.vo.CountVo;
import com.cashbookcloud.common.result.ResponseResult;
import net.sf.json.JSONObject;
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

    @Autowired
    private BillClient billClient;

    @Override
    public BilllistDto findById(Integer id) {
        Billlist billlist = billlistMapper.selectById(id);
        BilllistDto billlistDto = BilllistCovert.INSTANCE.entity2dto(billlist);
//            调用bill 查询该账本对应的记录单数
        // TODO: 2022/6/4
        ResponseResult result =billClient.getCount(billlistDto.getId());
        // 将数据转成json字符串
        JSONObject jsonObject= JSONObject.fromObject(result.getData());
        //将json转成需要的对象
        CountVo count = (CountVo) JSONObject.toBean(jsonObject, CountVo.class);
        billlistDto.setCount(count.getCount());
//            若记录单数为0 则无需调用bill查询支出结余 直接设置为0即可
        // TODO: 2022/6/4
        if(count.getCount().intValue() == 0){
            billlistDto.setZhichu(0.0);
            billlistDto.setJieyu(0.0);
            billlistDto.setShouru(0.0);
        }else {
//                调用微服务查询支出结余
            // TODO: 2022/6/4
            ResponseResult result1 = billClient.getKeeping(billlistDto.getId());
            // 将数据转成json字符串
            JSONObject jsonObject1= JSONObject.fromObject(result1.getData());
            //将json转成需要的对象
            KeepingDto keepingDto = (KeepingDto) JSONObject.toBean(jsonObject1, KeepingDto.class);
            billlistDto.setZhichu(keepingDto.getZhichu());
            billlistDto.setShouru(keepingDto.getShouru());
            billlistDto.setJieyu(keepingDto.getJieyu());
        }
        return billlistDto;
    }

    @Override
    public List<BilllistDto> getAllList(Integer userId) {
        QueryWrapper<Billlist> billlistQueryWrapper = new QueryWrapper<>();
        billlistQueryWrapper.eq("user_id",userId);
        List<Billlist> billlists = billlistMapper.selectList(billlistQueryWrapper);
        ArrayList<BilllistDto> billlistDtos = new ArrayList<>();
        for (int i = 0; i < billlists.size(); i++) {
            BilllistDto billlistDto = BilllistCovert.INSTANCE.entity2dto(billlists.get(i));
//            调用bill 查询该账本对应的记录单数
            // TODO: 2022/6/3
            ResponseResult result = billClient.getCount(billlistDto.getId());
            // 将数据转成json字符串
            JSONObject jsonObject= JSONObject.fromObject(result.getData());
            //将json转成需要的对象
            CountVo count = (CountVo) JSONObject.toBean(jsonObject, CountVo.class);
            billlistDto.setCount(count.getCount());
//            若记录单数为0 则无需调用bill查询支出结余 直接设置为0即可
            // TODO: 2022/6/3
            if(count.getCount().intValue() == 0){
                billlistDto.setZhichu(0.0);
                billlistDto.setJieyu(0.0);
                billlistDto.setShouru(0.0);
            }else {
//                调用微服务查询支出结余
                // TODO: 2022/6/3
                ResponseResult result1 = billClient.getKeeping(billlistDto.getId());
                // 将数据转成json字符串
                JSONObject jsonObject1= JSONObject.fromObject(result1.getData());
                //将json转成需要的对象
                KeepingDto keepingDto = (KeepingDto) JSONObject.toBean(jsonObject1, KeepingDto.class);
                billlistDto.setZhichu(keepingDto.getZhichu());
                billlistDto.setShouru(keepingDto.getShouru());
                billlistDto.setJieyu(keepingDto.getJieyu());
            }
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
