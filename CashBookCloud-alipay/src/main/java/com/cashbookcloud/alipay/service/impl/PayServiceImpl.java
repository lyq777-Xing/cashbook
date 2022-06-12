//package com.cashbookcloud.alipay.service.impl;
//
//
//
//import com.alipay.api.AlipayApiException;
//
//
////import com.cashbookcloud.alipay.config.Alipay;
//import com.cashbookcloud.alipay.entity.AliPayBean;
//import com.cashbookcloud.alipay.service.PayService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
///**
// * 支付服务接口
// */
//@Service
//public class PayServiceImpl implements PayService {
//
//    /**日志对象*/
//    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
//
//    @Autowired
////    @Qualifier()
//    private Alipay alipay;
//
//    @Override
//    public String aliPay(AliPayBean aliPayBean) throws AlipayApiException {
//        logger.info("调用支付服务接口...");
//        return alipay.pay(aliPayBean);
//    }
//}
//
//
