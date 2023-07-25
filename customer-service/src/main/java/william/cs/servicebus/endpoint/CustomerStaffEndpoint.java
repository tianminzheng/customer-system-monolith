package william.cs.servicebus.endpoint;

import william.cs.entity.staff.CustomerStaff;
import william.cs.entity.tenant.OutsourcingSystem;

import java.util.Collection;

/**
 * @author ZhangShenao
 * @date 2023/7/25 11:49 AM
 * @description: 客服人员集成端点 控制Router、Transformer和Filter的行为
 */
public interface CustomerStaffEndpoint {
    
    /**
     * 从外包客服系统中获取客服人员
     *
     * @param outsourcingSystem 外包客服系统
     * @return 客服人员列表
     */
    Collection<CustomerStaff> fetchOutsourcingCustomerStaffs(OutsourcingSystem outsourcingSystem);
}
