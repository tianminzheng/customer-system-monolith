package org.geekbang.projects.cs.servicebus.router;

import org.geekbang.projects.cs.constants.OutsourcingSystemCode;
import org.geekbang.projects.cs.servicebus.router.hangzhou.HangzhouOutsourcingCustomerSystemRouter;

/**
 * @author ZhangShenao
 * @date 2023/7/25 11:13 AM
 * @description: 外包客服系统路由器工厂 工厂方法模式
 */
public class OutsourcingCustomerSystemRouterFactory {
    
    /**
     * 创建外包客服系统路由器
     *
     * @param code 外包系统System Code枚举
     * @return 路由器实例
     */
    public static OutsourcingCustomerSystemRouter createRouter(OutsourcingSystemCode code) {
        if (OutsourcingSystemCode.HANG_ZHOU == code) {
            return new HangzhouOutsourcingCustomerSystemRouter();
        }
        
        //TODO 实现其他外包客服系统
        return null;
    }
}
