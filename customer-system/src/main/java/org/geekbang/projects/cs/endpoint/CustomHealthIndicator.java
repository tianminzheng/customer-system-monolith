package org.geekbang.projects.cs.endpoint;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        try {
            URL url = new URL("http://XXX:8080/healthcheck/");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            int status = connection.getResponseCode();

            if(status >= 200 && status < 300) {
                return Health.up().build();
            } else {
                return Health.down().withDetail("Failed! code is: ", status).build();
            }
        } catch (Exception ex) {
            return Health.down(ex).build();
        }
    }
}
