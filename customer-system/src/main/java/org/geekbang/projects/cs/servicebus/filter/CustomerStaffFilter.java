package org.geekbang.projects.cs.servicebus.filter;


import org.geekbang.projects.cs.entity.staff.CustomerStaff;

public interface CustomerStaffFilter {

    CustomerStaff execute(CustomerStaff customerStaff);

    void setNext(CustomerStaffFilter filter);

    CustomerStaffFilter getNext();

    CustomerStaffFilter getLast();
}
