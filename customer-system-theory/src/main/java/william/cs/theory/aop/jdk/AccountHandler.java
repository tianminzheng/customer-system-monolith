package william.cs.theory.aop.jdk;

import william.cs.theory.aop.Account;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ZhangShenao
 * @date 2023/5/26 2:21 PM
 * Description Account类的JDK增强类
 */
public class AccountHandler implements InvocationHandler {
    private Account proxied;    //保持被代理对象

    public AccountHandler(Account proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        //前置增强
        preProcess();

        //调用目标对象的方法
        Object ret = method.invoke(proxied, args);

        //后置增强
        postProcess();

        //返回结果
        return ret;
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
