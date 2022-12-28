package com.customer.beijing.repository;

import com.customer.beijing.entity.BeijingCustomerStaff;

import java.util.List;

public interface BeijingCustomerStaffRepository {

    List<BeijingCustomerStaff> findCustomerStaff();

    List<BeijingCustomerStaff> findCustomerStaffByUpdatedTime(Long updatedTime);

    Long createCustomerStaff(BeijingCustomerStaff customerStaff);

    Boolean updateCustomerStaff(BeijingCustomerStaff customerStaff);

    Boolean deleteCustomerStaffById(Long id);
}
