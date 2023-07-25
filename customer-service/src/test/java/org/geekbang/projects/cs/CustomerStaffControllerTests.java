package org.geekbang.projects.cs;

import william.cs.entity.staff.CustomerStaff;
import william.cs.service.ICustomerStaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * CustomerStaff Web API层测试用例 使用@AutoConfigureMockMvc注解
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)    //标记SpringBoot测试用例,并启动Mock的Web容器环境
@AutoConfigureMockMvc   //Web MVC测试
public class CustomerStaffControllerTests {
    
    @Resource
    private MockMvc mvc;    //注入MockMvc测试工具
    
    @MockBean
    private ICustomerStaffService customerStaffService;     //注入Mock组件
    
    //创建Mock方法
    @BeforeEach
    public void createMockMethod() {
        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setId(1L);
        customerStaff.setNickname("test-customer-staff-1");
        customerStaff.setIsDeleted(false);
        given(customerStaffService.findCustomerStaffById(1L)).willReturn(customerStaff);
    }
    
    @Test
    public void testFindCustomerStaffById() throws Exception {
        mvc.perform(get("/customerStaffs/" + 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
