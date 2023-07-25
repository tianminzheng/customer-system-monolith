package william.cs.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShenao
 * @date 2023/7/25 3:11 PM
 * @description: Admin后台服务启动类
 */
@SpringBootApplication
@EnableAdminServer  //开启Admin后台服务
public class AdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }
}
