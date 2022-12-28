package com.customer.shanghai.repository;

import com.customer.shanghai.entity.ShanghaiCustomerStaff;

import java.util.List;

public interface ShanghaiCustomerStaffRepository {

    List<ShanghaiCustomerStaff> findCustomerStaff();

    List<ShanghaiCustomerStaff> findCustomerStaffByUpdatedTime(Long updatedTime);

    Long createCustomerStaff(ShanghaiCustomerStaff customerStaff);

    Boolean updateCustomerStaff(ShanghaiCustomerStaff customerStaff);

    Boolean deleteCustomerStaffById(Long id);
}
