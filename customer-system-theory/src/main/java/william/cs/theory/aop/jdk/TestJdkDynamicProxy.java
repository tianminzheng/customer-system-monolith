package william.cs.theory.aop.jdk;

import william.cs.theory.aop.Account;
import william.cs.theory.aop.RealAccount;

import java.lang.reflect.Proxy;

/**
 * @author ZhangShenao
 * @date 2023/5/26 2:24 PM
 * Description JDK动态代理测试
 */
public class TestJdkDynamicProxy {
    public static void main(String[] args) {
        //创建被代理对象
        Account proxied = new RealAccount("William");

        //创建增强器
        AccountHandler handler = new AccountHandler(proxied);

        //创建代理对象
        //JDK动态代理要求被代理类必须实现了接口
        Account proxy = (Account) Proxy.newProxyInstance(proxied.getClass().getClassLoader(),
                proxied.getClass().getInterfaces(),
                handler);

        //调用代理对象的方法
        proxy.open();
    }
}
