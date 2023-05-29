package customer.system.basic.aop.benchmark;

import customer.system.basic.aop.benchmark.config.AOPConfig;
import customer.system.basic.aop.benchmark.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * @author ZhangShenao
 * @date 2023/5/29 10:01 AM
 * Description AOP性能测试
 */
public class BenchmarkMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AOPConfig.class);
        int times = 500;
        createInstanceAndInvoke(ctx, ScopedProxyMode.NO, times);
        createInstanceAndInvoke(ctx, ScopedProxyMode.INTERFACES, times);
        createInstanceAndInvoke(ctx, ScopedProxyMode.TARGET_CLASS, times);
    }

    //创建实例并执行
    private static void createInstanceAndInvoke(AnnotationConfigApplicationContext ctx, ScopedProxyMode mode, int times) {
        AccountService[] services = new AccountService[times];
        long start = System.nanoTime();
        if (ScopedProxyMode.NO == mode) {
            for (int i = 0; i < times; i++) {
                services[i] = ctx.getBean("noProxyAccountService", AccountService.class);
            }
        } else if (ScopedProxyMode.INTERFACES == mode) {
            for (int i = 0; i < times; i++) {
                services[i] = ctx.getBean("jdkProxyAccountService", AccountService.class);
            }
        } else {
            for (int i = 0; i < times; i++) {
                services[i] = ctx.getBean("cglibProxyAccountService", AccountService.class);
            }
        }
        long createInstanceEnd = System.nanoTime();
        long createInstanceCosts = createInstanceEnd - start;

        for (int i = 0; i < times; i++) {
            services[i].transfer("zhangsan", "lisi", 100);
        }
        long end = System.nanoTime();
        long invokeMethodCost = end - createInstanceEnd;

        long totalCosts = end - start;
        System.out.println("Proxy Mode: " + mode + " createInstanceCosts: "
                + createInstanceCosts + " invokeMethodCost: " + invokeMethodCost + " totalCosts: " + totalCosts);

    }
}
