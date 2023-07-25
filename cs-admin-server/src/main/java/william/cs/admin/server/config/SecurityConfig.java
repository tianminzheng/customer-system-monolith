package william.cs.admin.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ZhangShenao
 * @date 2023/7/25 3:12 PM
 * @description: 安全配置
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO 暂时跳过鉴权校验
        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
    }
}
