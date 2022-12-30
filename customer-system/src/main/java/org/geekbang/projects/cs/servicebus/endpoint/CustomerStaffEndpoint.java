package org.geekbang.projects.cs.servicebus.endpoint;

import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.entity.tenant.OutsourcingSystem;

import java.util.List;

public interface CustomerStaffEndpoint {

    List<CustomerStaff> fetchCustomerStaffs(OutsourcingSystem outsourcingSystem);
}
