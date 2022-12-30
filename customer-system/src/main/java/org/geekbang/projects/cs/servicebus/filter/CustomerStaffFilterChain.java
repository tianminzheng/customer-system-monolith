package org.geekbang.projects.cs.servicebus.filter;


import org.geekbang.projects.cs.entity.staff.CustomerStaff;

public class CustomerStaffFilterChain {

    private CustomerStaffFilter chain;

    public void addFilter(CustomerStaffFilter filter) {
        if (chain == null) {
            chain = filter;
        } else {
            chain.getLast().setNext(filter);
        }
    }

    public CustomerStaff execute(CustomerStaff customerStaff) {
        if (chain != null) {
            return chain.execute(customerStaff);
        } else {
            return null;
        }
    }
}
