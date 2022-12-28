package org.geekbang.projects.cs.controller.graphql.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.mapper.CustomerStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerStaffDataFetcher implements DataFetcher<CustomerStaff> {

    @Autowired
    private CustomerStaffMapper customerStaffMapper;

    @Override
    public CustomerStaff get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        String id = String.valueOf(dataFetchingEnvironment.getArguments().get("id"));

        return customerStaffMapper.selectById(Long.valueOf(id));
    }
}
