package org.geekbang.projects.cs.endpoint;

import org.geekbang.projects.cs.mapper.CustomerStaffCountMapper;
import org.geekbang.projects.cs.mapper.CustomerStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Endpoint(id = "customerStaffCount", enableByDefault = true)
public class CustomerStaffCountEndpoint {

    @Autowired
    private CustomerStaffCountMapper customerStaffCountMapper;

    @WriteOperation
    public Long countCustomerStaffByPhoneNumber(@Selector String arg0) {

        return customerStaffCountMapper.countCustomerStaff(arg0);
    }
}
