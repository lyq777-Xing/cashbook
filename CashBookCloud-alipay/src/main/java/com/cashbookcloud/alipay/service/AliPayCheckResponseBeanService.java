package com.cashbookcloud.alipay.service;

import com.cashbookcloud.alipay.entity.AliPayCheckResponseBean;

import java.util.List;

public interface AliPayCheckResponseBeanService {
    void add(AliPayCheckResponseBean aliPayCheckResponseBean);

    List<AliPayCheckResponseBean> getall();
}
