package org.geekbang.projects.cs;

import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.mapper.CustomerStaffMapper;
import org.geekbang.projects.cs.service.ICustomerStaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CustomerStaff业务逻辑层测试用例
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)    //标记SpringBoot测试用例,并启动Mock的Web容器环境
@ExtendWith(SpringExtension.class)
public class CustomerStaffServiceTests {
    
    @MockBean
    private CustomerStaffMapper customerStaffMapper;    //注入Mock组件
    
    @Resource
    private ICustomerStaffService customerStaffService;
    
    //创建Mock方法
    @BeforeEach
    public void createMockMethod() {
        CustomerStaff customerStaff = new CustomerStaff();
        customerStaff.setId(1L);
        customerStaff.setNickname("test-customer-staff-1");
        customerStaff.setIsDeleted(false);
        Mockito.when(customerStaffMapper.selectById(1L)).thenReturn(customerStaff);
        Mockito.when(customerStaffMapper.selectById(2L)).thenReturn(null);
    }
    
    @Test
    public void testFindCustomerStaffById() {
        CustomerStaff staff1 = customerStaffService.findCustomerStaffById(1L);
        assertThat(staff1).isNotNull();
        assertThat(staff1.getId()).isEqualTo(1L);
        
        CustomerStaff staff2 = customerStaffService.findCustomerStaffById(2L);
        assertThat(staff2).isNull();
    }
    
}
