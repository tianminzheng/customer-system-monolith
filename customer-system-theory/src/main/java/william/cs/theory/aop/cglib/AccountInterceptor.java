package william.cs.theory.aop.cglib;

import william.cs.theory.aop.RealAccount;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ZhangShenao
 * @date 2023/5/26 2:31 PM
 * Description Account类的cglib拦截器
 */
public class AccountInterceptor implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //放行无需拦截的方法
        if (!"open".equals(method.getName())) {
            return methodProxy.invokeSuper(o, objects);
        }
        //前置增强
        preProcess();

        //调用目标对象的方法
        Object ret = methodProxy.invokeSuper(o, objects);

        //后置增强
        postProcess();

        //返回结果
        return ret;
    }

    //创建代理对象
    public RealAccount createProxyInstance(RealAccount proxied) {
        enhancer.setSuperclass(RealAccount.class);
        enhancer.setCallback(this);
        RealAccount proxy = (RealAccount) enhancer.create();    //TODO 属性拷贝,可以通过更优雅的方式实现,这里只是简单演示
        proxy.setName(proxied.getName());
        return proxy;
    }

    //前置处理
    private void preProcess() {
        System.out.println("account pre process");
    }

    //后置处理
    private void postProcess() {
        System.out.println("account post process");
    }
}
