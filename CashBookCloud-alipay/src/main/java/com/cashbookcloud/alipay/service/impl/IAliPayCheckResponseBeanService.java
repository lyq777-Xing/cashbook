package com.cashbookcloud.alipay.service.impl;

import com.cashbookcloud.alipay.entity.AliPayCheckResponseBean;
import com.cashbookcloud.alipay.mapper.AlipayMapper;
import com.cashbookcloud.alipay.service.AliPayCheckResponseBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAliPayCheckResponseBeanService implements AliPayCheckResponseBeanService {

    @Autowired
    private AlipayMapper alipayMapper;

    @Override
    public void add(AliPayCheckResponseBean aliPayCheckResponseBean) {
        alipayMapper.insert(aliPayCheckResponseBean);
    }

    @Override
    public List<AliPayCheckResponseBean> getall() {
        List<AliPayCheckResponseBean> aliPayCheckResponseBeans = alipayMapper.selectList(null);
        return aliPayCheckResponseBeans;
    }
}
