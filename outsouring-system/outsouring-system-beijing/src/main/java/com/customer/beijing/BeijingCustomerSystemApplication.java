package com.customer.beijing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShenao
 * @date 2023/7/25 3:27 PM
 * @description: 北京客服系统启动类
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.customer.beijing.*")
public class BeijingCustomerSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeijingCustomerSystemApplication.class, args);
        log.info("*************************************************************");
        log.info("****************** BEIJING CUSTOMER SYSTEM STARTED ******************");
        log.info("*************************************************************");
    }
}
