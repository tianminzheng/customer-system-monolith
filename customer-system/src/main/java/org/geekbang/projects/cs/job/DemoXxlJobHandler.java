package org.geekbang.projects.cs.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DemoXxlJobHandler {

    private static Logger logger = LoggerFactory.getLogger(DemoXxlJobHandler.class);

    @XxlJob("testJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        logger.info(new Date() + "Test Xxl-Job~");

        return ReturnT.SUCCESS;
    }
}
