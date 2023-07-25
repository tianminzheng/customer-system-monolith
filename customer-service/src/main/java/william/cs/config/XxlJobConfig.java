package william.cs.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShenao
 * @date 2023/7/24 1:54 PM
 * @description: xxl-job定时任务调度配置
 */
@Configuration
public class XxlJobConfig {
    
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    
    @Value("${xxl.job.accessToken}")
    private String accessToken;
    
    @Value("${xxl.job.executor.appname}")
    private String appName;
    
    @Value("${xxl.job.executor.logpath}")
    private String logPath;
    
    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;
    
    
    //注册xxl-job执行器
    @Bean
    public XxlJobSpringExecutor xxlJobSpringExecutor() {
        XxlJobSpringExecutor executor = new XxlJobSpringExecutor();
        executor.setAdminAddresses(adminAddresses);
        executor.setAccessToken(accessToken);
        executor.setAppname(appName);
        executor.setLogPath(logPath);
        executor.setLogRetentionDays(logRetentionDays);
        return executor;
    }
}
