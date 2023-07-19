package org.geekbang.projects.cs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用鉴权校验
        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
    }
}