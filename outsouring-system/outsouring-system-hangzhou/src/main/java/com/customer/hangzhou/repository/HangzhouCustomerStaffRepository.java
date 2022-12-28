package com.customer.hangzhou.repository;

import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HangzhouCustomerStaffRepository extends JpaRepository<HangzhouCustomerStaff, Long> {

    List<HangzhouCustomerStaff> findByIsDeletedFalse();

    List<HangzhouCustomerStaff> findByUpdatedAtAfter(Date updatedTime);

}
