package org.geekbang.projects.cs;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.mapper.CustomerStaffMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CustomerStaff数据访问层测试用例,使用外部数据库
 */
@ExtendWith(SpringExtension.class)
@MybatisPlusTest    //使用MyBatisPlus测试引擎
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    //使用外部数据库
public class ExternalDatabaseCustomerStaffMapperTests {
    
    @Resource
    private CustomerStaffMapper customerStaffMapper;
    
    @Test
    public void testQueryCustomerStaffById() {
        CustomerStaff customerStaff = customerStaffMapper.selectById(1L);
        
        assertNotNull(customerStaff);
        assertThat(customerStaff.getStaffName()).isEqualTo("客服1");
    }
}
