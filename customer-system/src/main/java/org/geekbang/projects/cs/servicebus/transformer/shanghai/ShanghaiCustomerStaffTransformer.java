package org.geekbang.projects.cs.servicebus.transformer.shanghai;

import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.servicebus.router.beijing.dto.BeijingCustomerStaff;
import org.geekbang.projects.cs.servicebus.transformer.CustomerStaffTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShanghaiCustomerStaffTransformer implements CustomerStaffTransformer<BeijingCustomerStaff> {

    @Override
    public List<CustomerStaff> transformCustomerStaffs(List<BeijingCustomerStaff> customerStaffs) {
        return null;
    }
}
