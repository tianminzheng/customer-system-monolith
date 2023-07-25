package william.cs.theory.aop.benchmark.config;

import william.cs.theory.aop.benchmark.service.AccountService;
import william.cs.theory.aop.benchmark.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

/**
 * @author ZhangShenao
 * @date 2023/5/29 9:53 AM
 * Description AOP配置
 */
@Configuration
@EnableAspectJAutoProxy
public class AOPConfig {
    //JDK动态代理实现AOP
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.NO)
    public AccountService noProxyAccountService() {
        return new AccountServiceImpl();
    }

    //JDK动态代理实现AOP
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.INTERFACES)
    public AccountService jdkProxyAccountService() {
        return new AccountServiceImpl();
    }

    //JDK动态代理实现AOP
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AccountService cglibProxyAccountService() {
        return new AccountServiceImpl();
    }
}
