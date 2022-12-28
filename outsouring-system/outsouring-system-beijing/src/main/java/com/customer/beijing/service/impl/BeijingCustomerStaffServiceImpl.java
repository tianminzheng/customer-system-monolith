package com.customer.beijing.service.impl;

import com.customer.beijing.entity.BeijingCustomerStaff;
import com.customer.beijing.repository.BeijingCustomerStaffRepository;
import com.customer.beijing.service.BeijingCustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeijingCustomerStaffServiceImpl implements BeijingCustomerStaffService {

    @Autowired
    private BeijingCustomerStaffRepository customerStaffRepository;

    @Override
    public List<BeijingCustomerStaff> findAllCustomerStaffs() {
        return customerStaffRepository.findCustomerStaff();
    }

    @Override
    public List<BeijingCustomerStaff> findCustomerStaffsByUpdatedTime(Long updatedTime) {
        return customerStaffRepository.findCustomerStaffByUpdatedTime(updatedTime);
    }

    @Override
    public Long createCustomerStaff(BeijingCustomerStaff customerStaff) {
        Long result = customerStaffRepository.createCustomerStaff(customerStaff);

        return result;
    }

    @Override
    public Boolean updateCustomerStaff(BeijingCustomerStaff customerStaff) {
        Boolean result = customerStaffRepository.updateCustomerStaff(customerStaff);

        return result;
    }

    @Override
    public Boolean deleteCustomerStaffById(Long id) {
        Boolean result = customerStaffRepository.deleteCustomerStaffById(id);

        return result;
    }
}
