package com.customer.shanghai.service.impl;

import com.customer.shanghai.entity.ShanghaiCustomerStaff;
import com.customer.shanghai.repository.ShanghaiCustomerStaffRepository;
import com.customer.shanghai.service.ShanghaiCustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShanghaiCustomerStaffServiceImpl implements ShanghaiCustomerStaffService {

    @Autowired
    private ShanghaiCustomerStaffRepository customerStaffRepository;

    @Override
    public List<ShanghaiCustomerStaff> findAllCustomerStaffs() {
        return customerStaffRepository.findCustomerStaff();
    }

    @Override
    public List<ShanghaiCustomerStaff> findCustomerStaffsByUpdatedTime(Long updatedTime) {
        return customerStaffRepository.findCustomerStaffByUpdatedTime(updatedTime);
    }

    @Override
    public Long createCustomerStaff(ShanghaiCustomerStaff customerStaff) {
        Long result = customerStaffRepository.createCustomerStaff(customerStaff);

        return result;
    }

    @Override
    public Boolean updateCustomerStaff(ShanghaiCustomerStaff customerStaff) {
        Boolean result = customerStaffRepository.updateCustomerStaff(customerStaff);

        return result;
    }

    @Override
    public Boolean deleteCustomerStaffById(Long id) {
        Boolean result = customerStaffRepository.deleteCustomerStaffById(id);

        return result;
    }
}
