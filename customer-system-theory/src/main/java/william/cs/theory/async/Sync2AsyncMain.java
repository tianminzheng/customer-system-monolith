package william.cs.theory.async;

import william.cs.theory.async.executor.AsyncExecutor;
import william.cs.theory.async.proxy.AsyncInterceptor;
import william.cs.theory.async.service.SyncService;
import william.cs.theory.async.service.impl.SyncServiceImpl;

import java.lang.reflect.Proxy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShenao
 * @date 2023/5/29 10:45 AM
 * Description 同步转异步处理
 * 同步转异步三要素:1.Proxy 2.Executor 3.Future
 */
public class Sync2AsyncMain {
    public static void main(String[] args) {

        //创建同步处理实现类
        SyncService syncService = new SyncServiceImpl();

        //创建异步执行器
        AsyncExecutor executor = new AsyncExecutor(10, 50, 300L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200));

        //创建异步拦截器
        AsyncInterceptor interceptor = new AsyncInterceptor(syncService, executor);

        //创建动态代理对象,将同步方法转换成异步执行
        SyncService proxy = (SyncService) Proxy.newProxyInstance(syncService.getClass().getClassLoader(),
                syncService.getClass().getInterfaces(),
                interceptor);


        //执行动态代理对象的方法
        //调用方法和执行方法的线程不是同一个,实现了异步
        System.out.println("Invoke Sync Method. Thread: " + Thread.currentThread().getName());
        proxy.exec();

    }
}
