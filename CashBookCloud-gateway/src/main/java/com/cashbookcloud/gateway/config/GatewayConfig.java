///**
// *    https://gitee.com/gitsc/pro-cloud/
// *     @Author Aijm 2929793435@qq.com
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *    http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// */
//
//package com.cashbookcloud.gateway.config;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.core.ReactiveRedisTemplate;
//import org.springframework.data.redis.core.script.RedisScript;
//import org.springframework.validation.Validator;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//@Configuration
//public class GatewayConfig {
//
//    /**
//     *  根据uir进行限流。
//     * @return
//     */
//    @Bean
//    KeyResolver sysKeyResolver(){
//        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
//    }
//
//    /**
//     * 使用自己定义的限流类
//     * @param redisTemplate
//     * @param script
//     * @param validator
//     * @return
//     */
//    @Bean
//    @Primary
//    SysRedisRateLimiter sysRedisRateLimiter(
//            ReactiveRedisTemplate<String, String> redisTemplate,
//            @Qualifier(SysRedisRateLimiter.REDIS_SCRIPT_NAME) RedisScript<List<Long>> script,
//            Validator validator){
//        return new SysRedisRateLimiter(redisTemplate , script , validator);
//    }
//
//
//}
