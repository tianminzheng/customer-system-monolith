package org.geekbang.projects.cs.servicebus.filter.impl;

import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.servicebus.filter.AbstractCustomerStaffFilter;

import java.util.Objects;

public class CustomerStaffEmptyFilter extends AbstractCustomerStaffFilter {

    public CustomerStaff execute(CustomerStaff customerStaff) {
        if (Objects.isNull(customerStaff.getStaffName())) {
            return null;
        }

        if (getNext() != null) {
            return getNext().execute(customerStaff);
        }

        return customerStaff;
    }
}
