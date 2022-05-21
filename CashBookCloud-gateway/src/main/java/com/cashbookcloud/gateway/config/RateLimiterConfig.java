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
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Data
//@Component
//@ConfigurationProperties(prefix = "ratelimiter-config")
//public class RateLimiterConfig {
//    /**
//     * 处理速度
//     */
//    private static final String DEFAULT_REPLENISHRATE="default.replenishRate";
//    /**
//     * 容量
//     */
//    private static final String DEFAULT_BURSTCAPACITY="default.burstCapacity";
//
//    private Map<String , Integer> rateLimitMap = new ConcurrentHashMap<String , Integer>(){
//        {
//            put(DEFAULT_REPLENISHRATE , 10);
//            put(DEFAULT_BURSTCAPACITY , 100);
//        }
//    };
//
//
//
//}
