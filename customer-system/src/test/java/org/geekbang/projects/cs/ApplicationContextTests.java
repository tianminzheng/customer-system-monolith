package org.geekbang.projects.cs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * ApplicationContext测试用例
 */
@SpringBootTest //标识测试用例,并指定测试的Web环境
@ExtendWith(SpringExtension.class)  //引入SpringBoot容器环境
public class ApplicationContextTests {
    
    @Resource
    private ApplicationContext applicationContext;
    
    @Test
    public void testContextLoads() {
        assertNotNull(this.applicationContext);
    }
    
}
