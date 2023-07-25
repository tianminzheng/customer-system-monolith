package org.geekbang.projects.cs.servicebus.filter.impl;

import org.apache.commons.lang3.StringUtils;
import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.servicebus.filter.CustomerStaffFilter;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author ZhangShenao
 * @date 2023/7/25 11:34 AM
 * @description: 客服名称不为空过滤器
 */
public class StaffNameNotEmptyFilter implements CustomerStaffFilter {
    
    
    @Override
    public Collection<CustomerStaff> doFilter(Collection<CustomerStaff> origin) {
        if (CollectionUtils.isEmpty(origin)) {
            return Collections.emptyList();
        }
        return origin.stream().filter(x -> StringUtils.isNotBlank(x.getStaffName())).collect(Collectors.toList());
    }
    
    @Override
    public int priority() {
        return 0;
    }
}
