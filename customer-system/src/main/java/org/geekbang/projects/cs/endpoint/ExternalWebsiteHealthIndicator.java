package org.geekbang.projects.cs.endpoint;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author ZhangShenao
 * @date 2023/7/19 10:55 AM
 * @description: 自定义Health Endpoint
 */
@Component
public class ExternalWebsiteHealthIndicator implements HealthIndicator {
    
    @Override
    public Health health() {
        //访问外部网站,校验访问结果
        try {
            URL url = new URL("https://www.baidu.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int status = connection.getResponseCode();
            
            if (status >= 200 && status < 300) {
                return Health.up().build();
            } else {
                return Health.down().withDetail("External Website Error! code is: ", status).build();
            }
        } catch (Exception ex) {
            return Health.down(ex).build();
        }
    }
}
