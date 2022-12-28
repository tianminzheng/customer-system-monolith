package com.customer.hangzhou;

import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import com.customer.hangzhou.repository.HangzhouCustomerStaffRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class CustomerStaffRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HangzhouCustomerStaffRepository customerStaffRepository;

    @Test
    public void testCustomerStaffCreationAndQuery() {
        HangzhouCustomerStaff customerStaff = new HangzhouCustomerStaff();
        customerStaff.setIsDeleted(false);
        customerStaff.setCreatedAt(new Date());
        customerStaff.setUpdatedAt(new Date());
        customerStaff.setNickname("tianyalan");
        customerStaff.setGender("MALE");

        this.entityManager.persist(customerStaff);

        List<HangzhouCustomerStaff> result = customerStaffRepository.findByIsDeletedFalse();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
    }
}
