package com.customer.beijing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.customer.beijing.*")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("*************************************************************");
        log.info("****************** BEIJING CUSTOMER SERVICE START ******************");
        log.info("*************************************************************");
    }
}
