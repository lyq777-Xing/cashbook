package com.cashbookcloud.bill.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cashbookcloud.bill.service.entity.Bill;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillMapper extends BaseMapper<Bill> {
}
