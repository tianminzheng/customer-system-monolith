package com.customer.hangzhou.service.impl;

import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import com.customer.hangzhou.repository.HangzhouCustomerStaffRepository;
import com.customer.hangzhou.service.HangzhouCustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HangzhouCustomerStaffServiceImpl implements HangzhouCustomerStaffService {

    @Autowired
    HangzhouCustomerStaffRepository customerStaffRepository;

    @Override
    public List<HangzhouCustomerStaff> findAllCustomerStaffs() {

        return customerStaffRepository.findByIsDeletedFalse();
    }

    @Override
    public List<HangzhouCustomerStaff> findCustomerStaffsByUpdatedTime(Date updatedTime) {
        return customerStaffRepository.findByUpdatedAtAfter(updatedTime);
    }

    @Override
    public HangzhouCustomerStaff createCustomerStaff(HangzhouCustomerStaff customerStaff) {
        return customerStaffRepository.save(customerStaff);
    }

    @Override
    public HangzhouCustomerStaff updateCustomerStaff(HangzhouCustomerStaff customerStaff) {
        return customerStaffRepository.save(customerStaff);
    }

    @Override
    public HangzhouCustomerStaff deleteCustomerStaffById(Long id) {
        HangzhouCustomerStaff customerStaff = new HangzhouCustomerStaff().setId(id).setIsDeleted(true);

        return customerStaffRepository.save(customerStaff);
    }
}
