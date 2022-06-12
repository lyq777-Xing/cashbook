package com.cashbookcloud.alipay.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.alipay.entity.AliPayBean;
//import com.cashbookcloud.alipay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AliPayController {
//    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

//    @Autowired
//    private PayService payService;
    @PostMapping("/pay")
    public ResponseResult alipay(@RequestBody AliPayBean aliPayBean) throws AlipayApiException {


        String out_trade_no = String.valueOf((long) ((Math.random()*9+1)*1000000000));
//        logger.info("商户订单号为{},订单名称为{},付款金额为{},商品描述{}", outTradeNo, subject, totalAmount, body);
        AliPayBean alipayBean = new AliPayBean();
        alipayBean.setOut_trade_no(out_trade_no);
        alipayBean.setSubject(aliPayBean.getSubject());
        alipayBean.setTotal_amount(aliPayBean.getTotal_amount());
        alipayBean.setBody(aliPayBean.getBody());
//        String order = payService.aliPay(alipayBean);
        ResponseResult<Object> result=new ResponseResult<>();
//        System.out.println(order);
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do", "2021000119678947", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChpGrUHTfQ3yoY1AehLIabnpO+2ioZZNO0RPq9YGRnws3tyENi0ZNQSmdte7DDGHIVr5pOmfL7XTyC90pHPP8h90UbWhbzm+TELN/5O6lnk5yluHm6Syp9O/2b7rqXhfeEJcbl+B/L6TmDyJllyaTqxsw6IsvBoD23i2/dzXxLvwrrDPRvJOZpUaAh1VvVFuH1Rl2CMW7248GaNQZ1avo65qMNYyz8fZDCSHn+QlulqzVAKfggRWjgs6Qtwa9bniTb+VS3ucBMZg1Y3idbBqPnkHoH96SsMD0bD0vfGVW0hmKxMfp1Dzp+wYqca/wr3R5paFeA3id5Iu7T8BLi/xVfAgMBAAECggEADpioROs2hRgB1ygeYEGDDj5tnQhaUNy3+UlZUwyHRI9kxRoWa6/9G3z8hfhrZ9KnSqoGxx5DwMsUkcb+4Bvz9f88MFIcTHdBWy2qM/ldPMsplBVqeJYdCh4GsrQGCXTBex9mLJIRXPp8hRfyMizZRZIaEw3dVGjB6fcfbOoHVVeQOLSwJ1MOobscXcIHkcxk7LmTcDIPmIgM+UdHDmL+RVC5jX6ehbtfqjfh0oPm8H8jrSWL4gctBfR4ckQK/bm/i4BZeAw086xjZmfVrqizG7D3etZDGsh7L9+MXthVp4uxYBYRF9AiBd6WwbMFz5fCbwAVmDqjAh6elAcZMtHm4QKBgQDYC3TkPaAePFhLGm5yhBSWHSS9OJTbJLh5oplPsaIPWeVSEFy0jblDZsP6hDl8A6gQ7cU3bjdNuPLNy24ynhsLeWWrpGhcwnryFcozK2pgm4IJe2s2KLbGtEakaWVkwBpZjmK26FDhoRCHQdpkz6KXyY1DfVt6w3qKxtfPd1Yl9wKBgQC/iUxpTdfs6SgiCJiZuruU5tE+953MNB7B+8AJOpGbAvZ2323xR6qL7Q59H6M9vqVDzRtBn1Joc5Acml384UwxeAmKFe1rgNzfGWkU3hiYla2T8UOWhm8U+C9WdswOuKr6solO43c9AgBoTh64NalDzSroDNgZQQz6eVcFTkiR2QKBgBQBs5hzUIGcSvzhfLrkGlfwaPeQ5tAIbYDuGs5zf7N2OrNHyp35xd9FA/8OAZjHaefvbOT+MubiqrF+Fj1fwNdmLZEgj32PkP0cIXZtndNGc1tXb407ZfmutiABlp8m3wnOErwndV3/oV01LvlI9SCjTI6kbDVJJu71DQUsYvDvAoGAcLJ3bEpqLzJs88F9EI8HyjAZlGQby8grBNZ9ArEcdUnD/OOwC9SwK467BLI12aGwGfm3/N+inGTKuZm2jZF+xGitrDsErElxLDAGXUnkzPrc2/yMWSgWeg8hw004+bx8le+wzOMYCKSB2t2EQ70HMCarBUhxakKVQVdd9Xg/SNkCgYEAy6UMKmTD61RM8a5lxlkqYTffehLrBrnlx7DcpS3QRd/UhvG4o3JdwKopA2Umy720a7/uGeb8xFB3ySuW6+mp0l7zWhEKXaRI0qfMKbK/PzdbhZzpUnTDBydnGSULXbJ/2k3dnjczgg06j2xoYYh92CbivO6Y45rUUpT0vadXk5U=", "json", "utf-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkdKmcmg++2toaZZWhE1Gh9vwRGlgay7AOjIgS9a9gcfMxcNJA/1whizgIDrKAxw4dSahavAm+HGsH2t4Xkj1x7TACMRYxk3kFsy3EGnPt48fzYQvZS8BRd3Ad3ihxdjGJWFcuFa1tKQzVfVFKqbZMb4w/Y6jvnwfX/d3NX1Ty6SSKM+xI54lRArgikpdRNIOekc4OadXiPt5odAf2zXS0abEQ6OwmMamy/orch5+ZGuJz4T1e7wqsHsT6r3DhC0++XSNpmisPDEz23UQPgsgLMnbNeutiyAylI+x30AcFx7UYDqI5gnsym0stgrtT7TkwxO2rRrb1pUEk0lJqtappwIDAQAB", "RSA2");

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl("");
//        alipayRequest.setNotifyUrl(notifyUrl);

        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
//        logger.info("封装请求支付宝付款参数为:{}", JSON.toJSONString(alipayRequest));

        String result1 = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println(alipayBean);
        result.Success("获取成功",result1);
        return result;
    }

}
