package william.cs.servicebus.endpoint.impl;

import lombok.extern.slf4j.Slf4j;
import william.cs.constants.OutsourcingSystemCode;
import william.cs.entity.staff.CustomerStaff;
import william.cs.entity.tenant.OutsourcingSystem;
import william.cs.servicebus.endpoint.CustomerStaffEndpoint;
import william.cs.servicebus.filter.chain.CustomerStaffFilterChain;
import william.cs.servicebus.filter.impl.StaffNameNotEmptyFilter;
import william.cs.servicebus.router.OutsourcingCustomerSystemRouter;
import william.cs.servicebus.router.OutsourcingCustomerSystemRouterFactory;
import william.cs.servicebus.transformer.OutsourcingCustomerStaffTransformer;
import william.cs.servicebus.transformer.OutsourcingCustomerStaffTransformerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author ZhangShenao
 * @date 2023/7/25 11:53 AM
 * @description: 客服人员Endpoint默认实现
 */
@Component
@Slf4j
public class DefaultCustomerStaffEndpointImpl implements CustomerStaffEndpoint {
    
    private CustomerStaffFilterChain filterChain;   //过滤器链
    
    @Resource
    private RestTemplate restTemplate;
    
    @PostConstruct
    public void init() {
        //构造过滤器链
        filterChain = new CustomerStaffFilterChain();
        filterChain.addFilter(new StaffNameNotEmptyFilter());
    }
    
    @Override
    public Collection<CustomerStaff> fetchOutsourcingCustomerStaffs(OutsourcingSystem outsourcingSystem) {
        OutsourcingSystemCode systemCode = OutsourcingSystemCode.ofCode(outsourcingSystem.getSystemCode());
        
        //1.创建Router,从外包系统拉取数据
        OutsourcingCustomerSystemRouter router = OutsourcingCustomerSystemRouterFactory.createRouter(systemCode);
        Collection<Object> externalDatas = router.fetchCustomerStaffs(restTemplate, outsourcingSystem.getSystemUrl());
        
        //2.创建Transformer,执行数据转换
        OutsourcingCustomerStaffTransformer transformer = OutsourcingCustomerStaffTransformerFactory.createTransformer(
                systemCode);
        Collection<CustomerStaff> customerStaffs = transformer.batchTransform(externalDatas);
        
        //3.调用FilterChain,执行过滤逻辑
        Collection<CustomerStaff> filtered = filterChain.doFilter(customerStaffs);
        
        //4.返回最终数据
        return filtered;
    }
}
