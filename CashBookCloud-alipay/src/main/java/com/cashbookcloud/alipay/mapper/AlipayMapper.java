package com.cashbookcloud.alipay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cashbookcloud.alipay.entity.AliPayCheckResponseBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlipayMapper extends BaseMapper<AliPayCheckResponseBean> {
}
