package org.geekbang.projects.cs.servicebus.router.hangzhou;

import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.geekbang.projects.cs.servicebus.router.OutsourcingCustomerSystemRouter;
import org.geekbang.projects.cs.servicebus.router.hangzhou.dto.HangzhouCustomerStaffDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

/**
 * @author ZhangShenao
 * @date 2023/7/25 10:57 AM
 * @description: 杭州外包客服系统路由器
 */
@Component
public class HangzhouOutsourcingCustomerSystemRouter
        implements OutsourcingCustomerSystemRouter<HangzhouCustomerStaffDTO> {
    
    @Override
    public Collection<HangzhouCustomerStaffDTO> fetchCustomerStaffs(RestTemplate restTemplate, String systemUrl) {
        //通过RestTemplate发起远程调用
        ResponseEntity<Result> result = restTemplate.exchange(systemUrl, HttpMethod.GET, null, Result.class);
        
        return (List<HangzhouCustomerStaffDTO>) result.getBody().getData();
    }
}
