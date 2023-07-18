package com.customer.hangzhou;

import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import com.customer.hangzhou.repository.HangzhouCustomerStaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest    //使用Spring Data JPA测试引擎
@AutoConfigureTestDatabase  //使用内存数据库,会自动加载H2引擎
public class CustomerStaffRepositoryTests {
    
    @Resource
    private TestEntityManager entityManager;    //可以实现数据持久化相关的操作
    
    @Resource
    private HangzhouCustomerStaffRepository customerStaffRepository;
    
    //测试用例前置操作——初始化测试数据
    @BeforeEach
    public void initData() {
        HangzhouCustomerStaff customerStaff = new HangzhouCustomerStaff();
        customerStaff.setIsDeleted(false);
        customerStaff.setCreatedAt(new Date());
        customerStaff.setUpdatedAt(new Date());
        customerStaff.setNickname("test-staff");
        customerStaff.setGender("MALE");
        this.entityManager.persist(customerStaff);
    }
    
    @Test
    public void testCustomerStaffCreationAndQuery() {
        List<HangzhouCustomerStaff> result = customerStaffRepository.findByIsDeletedFalse();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getNickname()).isEqualTo("test-staff");
    }
}
