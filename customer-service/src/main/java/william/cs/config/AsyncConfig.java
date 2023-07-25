package william.cs.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 * @author ZhangShenao
 * @date 2023/5/29 11:22 AM
 * Description 异步配置
 */
@Configuration
@EnableAsync    //开启异步执行
public class AsyncConfig implements AsyncConfigurer {
    //注入异步执行器
    @Bean
    public Executor asyncExecutor() {
        return new ThreadPoolExecutor(10, 50,
                300L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200));
    }


    //注入异常处理器
    @Bean
    public AsyncUncaughtExceptionHandler asyncUncaughtExceptionHandler() {
        return (ex, method, params) -> System.out.println("Async Exec Error! ex: " + ex.getMessage() + ", method: " + method.getName());
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return asyncUncaughtExceptionHandler();
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }
}
