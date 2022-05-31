//package com.cashbookcloud.gateway.util;
//
//import cn.hutool.json.JSONUtil;
//import com.cashbookcloud.common.result.ResponseCode;
//import com.cashbookcloud.common.result.ResponseResult;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//
///**
// * 异常响应工具类
// *
// * @Author haoxr
// * @Date 2021/01/29 13:30
// */
//public class ResponseUtils {
//
//    public static Mono<Void> writeErrorInfo(ServerHttpResponse response, ResponseCode resultCode) {
//        switch (resultCode) {
//            case ACCESS_UNAUTHORIZED:
//            case TOKEN_INVALID_OR_EXPIRED:
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                break;
//            case TOKEN_ACCESS_FORBIDDEN:
//                response.setStatusCode(HttpStatus.FORBIDDEN);
//                break;
//            default:
//                response.setStatusCode(HttpStatus.BAD_REQUEST);
//                break;
//        }
//        ResponseResult<Object> result = new ResponseResult<>();
//        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        response.getHeaders().set("Access-Control-Allow-Origin", "*");
//        response.getHeaders().set("Cache-Control", "no-cache");
////        String body = JSONUtil.toJsonStr(result.FAIL_LOGIN("error",resultCode));
//        String body = JSONUtil.toJsonStr(resultCode);
//        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
//        return response.writeWith(Mono.just(buffer))
//                .doOnError(error -> DataBufferUtils.release(buffer));
//    }
//
//}
