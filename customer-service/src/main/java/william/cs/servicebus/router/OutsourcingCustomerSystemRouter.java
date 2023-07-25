package william.cs.servicebus.router;

import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * @author ZhangShenao
 * @date 2023/7/25 10:53 AM
 * @description: 外包客服系统路由器 用于发起HTTP请求,从外包客服系统中获取数据
 */
public interface OutsourcingCustomerSystemRouter<T> {
    
    /**
     * 获取客服人员数据
     *
     * @param restTemplate Rest客户端
     * @param systemUrl    系统调用URL
     * @return 客服人员数据列表
     */
    Collection<T> fetchCustomerStaffs(RestTemplate restTemplate, String systemUrl);
}
