package com.customer.shanghai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShenao
 * @date 2023/7/25 3:28 PM
 * @description: 上海客服系统启动类
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.customer.shanghai.*")
public class ShanghaiCustomerSystemApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ShanghaiCustomerSystemApplication.class, args);
    }
}
