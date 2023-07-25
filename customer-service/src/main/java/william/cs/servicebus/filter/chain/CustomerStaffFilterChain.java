package william.cs.servicebus.filter.chain;

import lombok.extern.slf4j.Slf4j;
import william.cs.entity.staff.CustomerStaff;
import william.cs.servicebus.filter.CustomerStaffFilter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhangShenao
 * @date 2023/7/25 11:34 AM
 * @description: 客服人员过滤器链
 */
@Slf4j
public class CustomerStaffFilterChain {
    
    private List<CustomerStaffFilter> chain;
    
    public CustomerStaffFilterChain() {
        this.chain = new ArrayList<>();
    }
    
    /**
     * 添加过滤器
     */
    public void addFilter(CustomerStaffFilter filter) {
        chain.add(filter);
    }
    
    /**
     * 执行过滤
     *
     * @param customerStaffs 客服人员列表
     */
    public Collection<CustomerStaff> doFilter(Collection<CustomerStaff> customerStaffs) {
        if (CollectionUtils.isEmpty(chain)) {
            log.warn("Empty Filter Chain!");
            return Collections.emptyList();
        }
        
        //按照优先级对过滤器排序
        chain = chain.stream().sorted(Comparator.comparingInt(CustomerStaffFilter::priority))
                .collect(Collectors.toList());
        
        //依次执行过滤
        for (CustomerStaffFilter filter : chain) {
            customerStaffs = filter.doFilter(customerStaffs);
        }
        
        return customerStaffs;
    }
}
