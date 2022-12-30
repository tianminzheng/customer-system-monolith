package org.geekbang.projects.cs.servicebus.transformer;

import org.geekbang.projects.cs.entity.tenant.OutsourcingSystem;
import org.geekbang.projects.cs.servicebus.transformer.beijing.BeijingCustomerStaffTransformer;
import org.geekbang.projects.cs.servicebus.transformer.hangzhou.HangzhouCustomerStaffTransformer;
import org.geekbang.projects.cs.servicebus.transformer.shanghai.ShanghaiCustomerStaffTransformer;

public class CustomerStaffTransformerFactory {

    private static final String OUTSOURCING_SYSTEM_BEIJING = "beijing";
    private static final String OUTSOURCING_SYSTEM_SHAGNHAI = "shanghai";
    private static final String OUTSOURCING_SYSTEM_HANGZHOU = "hangzhou";

    public static CustomerStaffTransformer createCustomerStaffTransformer(OutsourcingSystem outsourcingSystem) {
        if(outsourcingSystem.getSystemCode().equals(OUTSOURCING_SYSTEM_BEIJING)) {
            return new BeijingCustomerStaffTransformer();
        } else if(outsourcingSystem.getSystemCode().equals(OUTSOURCING_SYSTEM_SHAGNHAI)) {
            return new ShanghaiCustomerStaffTransformer();
        } else if(outsourcingSystem.getSystemCode().equals(OUTSOURCING_SYSTEM_HANGZHOU)) {
            return new HangzhouCustomerStaffTransformer();
        }

        return null;
    }
}
