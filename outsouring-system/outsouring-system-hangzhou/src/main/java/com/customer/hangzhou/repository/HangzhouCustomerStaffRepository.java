package com.customer.hangzhou.repository;

import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * 杭州客服人员Repository,基于Spring Data JPA实现
 */
public interface HangzhouCustomerStaffRepository extends JpaRepository<HangzhouCustomerStaff, Long> {
    /**
     * 查询所有未被删除的客服人员
     * 方法名衍生查询
     */
    List<HangzhouCustomerStaff> findByIsDeletedFalse();

    /**
     * 查询更新时间在指定时间之后的客服人员
     * 方法名衍生查询
     */
    List<HangzhouCustomerStaff> findByUpdatedAtAfter(Date updatedTime);

}
