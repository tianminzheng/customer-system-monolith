package org.geekbang.projects.cs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ApplicationContextTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        assertNotNull(this.applicationContext);
    }

}
