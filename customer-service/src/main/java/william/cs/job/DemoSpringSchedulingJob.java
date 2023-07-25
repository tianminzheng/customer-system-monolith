package william.cs.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author ZhangShenao
 * @date 2023/7/24 12:07 PM
 * @description: 使用Spring自带的任务调度框架
 */
@EnableScheduling   //开启任务调度
//@Component
public class DemoSpringSchedulingJob {
    
    @Scheduled(fixedDelay = 1000L * 60, initialDelay = 1000L * 5)   //声明一个定时任务
    public void demoTask() {
        System.out.println("Exec Spring Scheduling Demo Task");
    }
}
