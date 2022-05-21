package com.cashbookcloud.gateway.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;

/**
 *自定义异常处理
 */
public class GlobalWebExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        ServerHttpResponse response = exchange.getResponse();
        return response
                .writeWith(Mono.fromSupplier(() -> {
                    DataBufferFactory bufferFactory = response.bufferFactory();

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("errCode", "000");
                    map.put("errMsg", "当前在线人数较多");

                    //返回json异常原因给前端
                    return bufferFactory.wrap(JSON.toJSONBytes(map));
                }));
    }
}
