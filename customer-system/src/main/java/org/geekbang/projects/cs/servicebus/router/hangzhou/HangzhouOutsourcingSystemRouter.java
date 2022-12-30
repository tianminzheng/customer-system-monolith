package org.geekbang.projects.cs.servicebus.router.hangzhou;

import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.geekbang.projects.cs.servicebus.router.OutsourcingSystemRouter;
import org.geekbang.projects.cs.servicebus.router.beijing.dto.BeijingCustomerStaff;
import org.geekbang.projects.cs.servicebus.router.hangzhou.dto.HangzhouCustomerStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class HangzhouOutsourcingSystemRouter implements OutsourcingSystemRouter<HangzhouCustomerStaff> {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<HangzhouCustomerStaff> fetchOutsourcingCustomerStaffs(String systemUrl) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Result> result = restTemplate.exchange(
                systemUrl,
                HttpMethod.GET,
                null,
                Result.class
        );

        List<HangzhouCustomerStaff> hangzhouCustomerStaff = (List<HangzhouCustomerStaff>)result.getBody().getData();

        return hangzhouCustomerStaff;
    }
}
