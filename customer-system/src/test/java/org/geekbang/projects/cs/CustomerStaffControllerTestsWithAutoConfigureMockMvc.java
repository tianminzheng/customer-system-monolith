package org.geekbang.projects.cs;

import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.service.ICustomerStaffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CustomerStaffControllerTestsWithAutoConfigureMockMvc {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ICustomerStaffService customerStaffService;

    @Test
    public void testFindCustomerStaffById() throws Exception {

        Long staffId = 1L;

        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setId(staffId);
        customerStaff.setNickname("tianyalan");
        customerStaff.setIsDeleted(false);

        given(customerStaffService.findCustomerStaffById(staffId)).willReturn(customerStaff);

        mvc.perform(get("/customerStaffs/" + staffId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
