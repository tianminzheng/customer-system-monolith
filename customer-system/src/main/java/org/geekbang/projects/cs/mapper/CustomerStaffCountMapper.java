package org.geekbang.projects.cs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.geekbang.projects.cs.entity.staff.CustomerStaffCount;

public interface CustomerStaffCountMapper extends BaseMapper<CustomerStaffCount> {

    default Long countCustomerStaff(String phoneNumber) {

        LambdaQueryWrapper<CustomerStaffCount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerStaffCount::getPhone, phoneNumber);

        CustomerStaffCount customerStaffCount = selectOne(queryWrapper);

        if(customerStaffCount == null) {
            CustomerStaffCount newCustomerStaffCount = new CustomerStaffCount();
            newCustomerStaffCount.setPhone(phoneNumber);
            newCustomerStaffCount.setCount(1L);
            this.insert(newCustomerStaffCount);
            return 1L;
        } else {
            customerStaffCount.setCount(customerStaffCount.getCount() + 1);
            this.updateById(customerStaffCount);
            return customerStaffCount.getCount();
        }
    }
}
