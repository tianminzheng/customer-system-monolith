package com.customer.shanghai.service;

import com.customer.shanghai.entity.ShanghaiCustomerStaff;

import java.util.List;

public interface ShanghaiCustomerStaffService {

    List<ShanghaiCustomerStaff> findAllCustomerStaffs();

    List<ShanghaiCustomerStaff> findCustomerStaffsByUpdatedTime(Long updatedTime);

    Long createCustomerStaff(ShanghaiCustomerStaff customerStaff);

    Boolean updateCustomerStaff(ShanghaiCustomerStaff customerStaff);

    Boolean deleteCustomerStaffById(Long id);
}
