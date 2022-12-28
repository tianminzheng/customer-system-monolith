package org.geekbang.projects.cs.endpoint;

import org.geekbang.projects.cs.mapper.CustomerStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Endpoint(id = "customerStaff", enableByDefault = true)
public class CustomerStaffEndpoint {

    @Autowired
    private CustomerStaffMapper customerStaffMapper;

    @ReadOperation
    public Map<String, Object> getCustomerStaffByPhoneNumber(@Selector String arg0) {
        Map<String, Object> result = new HashMap<>();
        result.put(arg0, customerStaffMapper.findCustomerStaffByPhoneNumber(arg0));
        return result;
    }
}
