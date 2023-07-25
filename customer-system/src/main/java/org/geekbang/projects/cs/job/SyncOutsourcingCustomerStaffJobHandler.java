package org.geekbang.projects.cs.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.geekbang.projects.cs.service.ICustomerStaffService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ZhangShenao
 * @date 2023/7/24 1:58 PM
 * @description: 同步外包客服人员任务执行器
 */
@Component
@Slf4j
public class SyncOutsourcingCustomerStaffJobHandler {
    
    @Resource
    private ICustomerStaffService customerStaffService;
    
    //同步外部客服人员定时任务
    @XxlJob("syncOutsourcingCustomerStaff")
    public ReturnT<String> syncOutsourcingCustomerStaff() {
        log.info("Sync Outsourcing Customer Staff Job Started!");
        
        try {
            //获取动态任务参数
            String param = XxlJobHelper.getJobParam();
            if (!StringUtils.isNumeric(param)) {
                log.error("Invalid Job Param: {}", param);
                return new ReturnT<>(ReturnT.FAIL_CODE, "Invalid Job Param: " + param);
            }
            Long systemId = Long.parseLong(param);
            customerStaffService.syncOutsourcingCustomerStaffsBySystemId(systemId);
            log.info("Sync Outsourcing Customer Staff Job Finished!");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            log.error("Sync Outsourcing Customer Staff Job Error!", e);
            return new ReturnT<>(ReturnT.FAIL_CODE, e.getMessage());
        }
        
    }
}
