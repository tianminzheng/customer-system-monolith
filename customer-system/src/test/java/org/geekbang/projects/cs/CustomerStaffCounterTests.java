package org.geekbang.projects.cs;

import org.geekbang.projects.cs.metrics.CustomerStaffCount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerStaffCounterTests {

    @Test
    public void testCountCustomerStaffPhoneNumber() {

        CustomerStaffCount.countPhoneNumber("13355667788");
        CustomerStaffCount.countPhoneNumber("13355667788");

        CustomerStaffCount.countPhoneNumber("13355667789");

        assertThat(CustomerStaffCount.getPhoneNumberCount("13355667788")).isEqualTo(2);
    }

}
