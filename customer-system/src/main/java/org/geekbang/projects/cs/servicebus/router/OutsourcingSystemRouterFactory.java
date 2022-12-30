package org.geekbang.projects.cs.servicebus.router;

import org.geekbang.projects.cs.entity.tenant.OutsourcingSystem;
import org.geekbang.projects.cs.servicebus.router.beijing.BeijingOutsourcingSystemRouter;
import org.geekbang.projects.cs.servicebus.router.hangzhou.HangzhouOutsourcingSystemRouter;
import org.geekbang.projects.cs.servicebus.router.shanghai.ShanghaiOutsourcingSystemRouter;
import org.geekbang.projects.cs.servicebus.transformer.hangzhou.HangzhouCustomerStaffTransformer;
import org.springframework.stereotype.Component;

@Component
public class OutsourcingSystemRouterFactory {

    private static final String OUTSOURCING_SYSTEM_BEIJING = "beijing";
    private static final String OUTSOURCING_SYSTEM_SHAGNHAI = "shanghai";
    private static final String OUTSOURCING_SYSTEM_HANGZHOU = "hangzhou";

    public static OutsourcingSystemRouter createOutsourcingSystemRouter(OutsourcingSystem outsourcingSystem) {
        if(outsourcingSystem.getSystemCode().equals(OUTSOURCING_SYSTEM_BEIJING)) {
            return new BeijingOutsourcingSystemRouter();
        } else if(outsourcingSystem.getSystemCode().equals(OUTSOURCING_SYSTEM_SHAGNHAI)) {
            return new ShanghaiOutsourcingSystemRouter();
        } else if(outsourcingSystem.getSystemCode().equals(OUTSOURCING_SYSTEM_HANGZHOU)) {
            return new HangzhouOutsourcingSystemRouter();
        }

        return null;
    }
}
