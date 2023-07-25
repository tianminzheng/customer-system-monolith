package william.cs.theory.async.proxy;

import william.cs.theory.async.executor.AsyncExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

/**
 * @author ZhangShenao
 * @date 2023/5/29 10:46 AM
 * Description 异步拦截器,拦截同步方法,转换成异步处理
 */
public class AsyncInterceptor implements InvocationHandler {
    private Object proxied; //被拦截对象
    private AsyncExecutor executor; //异步执行器


    public AsyncInterceptor(Object proxied, AsyncExecutor executor) {
        this.proxied = proxied;
        this.executor = executor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            try {
                return method.invoke(proxied);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return future.get();
    }
}
