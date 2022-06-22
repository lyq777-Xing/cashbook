//package com.cashbookcloud.security.user.service;
//
//import com.cashbookcloud.security.user.client.RolePermissionClient;
//import com.cashbookcloud.security.user.getAuthorization.dto.RolePermissionDto;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class HystrixServiceImpl implements RolePermissionClient {
//
//    @Override
//    public List<RolePermissionDto> findByRid(Integer rid) {
//        try {
//            throw new HystrixLoadingException("服务器繁忙或者宕机/(ㄒoㄒ)/~~");
//        } catch (HystrixLoadingException e) {
//            e.printStackTrace();
//        }
////        "服务器繁忙或者宕机/(ㄒoㄒ)/~~"
//        return null;
//    }
//
//    private class HystrixLoadingException extends Throwable {
//        public HystrixLoadingException(String s) {
//            System.out.println(s);
//        }
//    }
//}
