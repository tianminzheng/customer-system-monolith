package william.cs.theory.stopwatch;

import org.springframework.util.StopWatch;

/**
 * @author ZhangShenao
 * @date 2023/7/19 11:48 AM
 * @description: 使用StopWatch实现性能监控
 */
public class StopWatchDemo {
    
    public static void main(String[] args) throws InterruptedException {
        //创建StopWatch
        StopWatch stopWatch = new StopWatch("monitor-1");
        
        //启动StopWatch,监控task-1
        stopWatch.start("task-1");
        Thread.sleep(200L);
        //停止StopWatch
        stopWatch.stop();
        
        //启动StopWatch,监控task-2
        stopWatch.start("task-2");
        Thread.sleep(1000L);
        //停止StopWatch
        stopWatch.stop();
        
        //打印统计结果
        System.out.println("Performance Report: " + stopWatch.prettyPrint());
        
    }
}
