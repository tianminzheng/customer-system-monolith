package org.geekbang.projects.cs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.springframework.stereotype.Repository;

/**
 * 客服人员Mapper
 * 继承MyBatis-Plus的BaseMapper,内置了多种CRUD接口
 */
@Repository
public interface CustomerStaffMapper extends BaseMapper<CustomerStaff> {

    //建议大家在Mapper层做数据访问操作的封装
    default CustomerStaff findCustomerStaffByPhoneNumber(String phoneNumber) {

        LambdaQueryWrapper<CustomerStaff> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerStaff::getPhone, phoneNumber);
        queryWrapper.eq(CustomerStaff::getIsDeleted, false);

        return selectOne(queryWrapper);
    }


}
