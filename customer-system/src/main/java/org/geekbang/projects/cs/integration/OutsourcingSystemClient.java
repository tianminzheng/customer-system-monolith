package org.geekbang.projects.cs.integration;

import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.entity.tenant.OutsourcingSystem;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OutsourcingSystemClient {

    @Autowired
    RestTemplate restTemplate;

    public List<CustomerStaff> getCustomerStaffs(OutsourcingSystem outsourcingSystem) {

        //通过RestTemplate发起远程调用
        ResponseEntity<Result> result = restTemplate.exchange(
                outsourcingSystem.getSystemUrl(),
                HttpMethod.GET,
                null,
                Result.class
        );

        List<CustomerStaff> customerStaffs = (List<CustomerStaff>)result.getBody().getData();

        return customerStaffs;
    }
}
