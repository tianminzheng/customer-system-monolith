package customer.system.basic.aop.cglib;

import customer.system.basic.aop.Account;
import customer.system.basic.aop.RealAccount;

/**
 * @author ZhangShenao
 * @date 2023/5/26 2:39 PM
 * Description cglib动态代理测试
 */
public class TestCglibDynamicProxy {
    public static void main(String[] args) {
        //创建被代理对象
        RealAccount proxied = new RealAccount("William");

        //创建方法拦截器
        AccountInterceptor interceptor = new AccountInterceptor();

        //创建代理对象
        //cglib是基于子类进行代理
        Account proxy = interceptor.createProxyInstance(proxied);

        //调用代理对象的方法
        proxy.open();
    }
}
