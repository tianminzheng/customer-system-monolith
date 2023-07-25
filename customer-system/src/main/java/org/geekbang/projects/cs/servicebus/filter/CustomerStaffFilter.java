package org.geekbang.projects.cs.servicebus.filter;

import org.geekbang.projects.cs.entity.staff.CustomerStaff;

import java.util.Collection;

/**
 * @author ZhangShenao
 * @date 2023/7/25 11:33 AM
 * @description: 客服人员过滤器
 */
public interface CustomerStaffFilter {
    
    /**
     * 执行过滤流程
     *
     * @param origin 原始客服人员
     * @return 过滤后满足条件的客服人员
     */
    Collection<CustomerStaff> doFilter(Collection<CustomerStaff> origin);
    
    /**
     * 过滤器优先级
     */
    int priority();
}
