package org.geekbang.projects.cs;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
@MapperScan("org.geekbang.projects.cs.mapper")
public class Application {

    @Bean
    public RestTemplate restTemplate() {
        //注入RestTemplate
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("*************************************************************");
        log.info("****************** CUSTOMER SYSTEM START ******************");
        log.info("*************************************************************");
    }
}
