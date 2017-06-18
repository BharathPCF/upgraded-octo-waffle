package org.bharath.eureka.customer.shared.server.server;

import org.bharath.eureka.customer.shared.CustomerService;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableEurekaClient
@EnableWebMvc
@Configuration
public class Config {

}
