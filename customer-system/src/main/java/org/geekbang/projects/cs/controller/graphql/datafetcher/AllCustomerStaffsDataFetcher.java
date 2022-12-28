package org.geekbang.projects.cs.controller.graphql.datafetcher;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.mapper.CustomerStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllCustomerStaffsDataFetcher  implements DataFetcher<List<CustomerStaff>> {

    @Autowired
    private CustomerStaffMapper customerStaffMapper;

    @Override
    public List<CustomerStaff> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        LambdaQueryWrapper<CustomerStaff> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(CustomerStaff::getIsDeleted, false);
        return customerStaffMapper.selectList(queryWrapper);
    }
}
