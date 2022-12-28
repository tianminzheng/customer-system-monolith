package org.geekbang.projects.cs.infrastructure.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.mooc.medicalcare"})
public class FeignConfiguration {


    @Bean
    Logger.Level feignLoggerlevel() {
        return Logger.Level.FULL;
    }


    @Bean
    FeignErrorDecoder errorDecoder(){
        return new FeignErrorDecoder();
    }
}
