package org.geekbang.projects.cs.servicebus.transformer;

import org.geekbang.projects.cs.entity.staff.CustomerStaff;

import java.util.List;

public interface CustomerStaffTransformer<T> {

    List<CustomerStaff> transformCustomerStaffs(List<T> customerStaffs);
}
