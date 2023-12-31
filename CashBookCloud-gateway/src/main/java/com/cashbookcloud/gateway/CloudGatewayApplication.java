/**
 *    https://gitee.com/gitsc/pro-cloud/
 *     @Author Aijm 2929793435@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.cashbookcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableDiscoveryClient
//@EnableZuulProxy
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableHystrix
@EnableCircuitBreaker
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(CloudGatewayApplication.class);
		// 该设置方式 也可以解决我的前一个问题
		application.setWebApplicationType(WebApplicationType.REACTIVE);
		application.run(args);
//		SpringApplication.run(CloudGatewayApplication.class, args);
	}

}
