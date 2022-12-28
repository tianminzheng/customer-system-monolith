package org.geekbang.projects.cs.controller.webmvc;

import org.geekbang.projects.cs.controller.vo.req.AddCustomerStaffReqVO;
import org.geekbang.projects.cs.controller.vo.req.UpdateCustomerStaffReqVO;
import org.geekbang.projects.cs.controller.vo.resp.CustomerStaffRespVO;
import org.geekbang.projects.cs.converter.CustomerStaffConverter;
import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.infrastructure.page.PageObject;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.geekbang.projects.cs.service.ICustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.List;

@RestController
@RequestMapping("/customerStaffs")
public class CustomerStaffController {

    @Autowired
    ICustomerStaffService customerStaffService;

    //新增CustomerStaff
    @PostMapping("/")
    public Result<Long> addCustomerStaff(@RequestBody AddCustomerStaffReqVO addCustomerStaffReqVO) {

        //数据转换
        CustomerStaff customerStaff = CustomerStaffConverter.INSTANCE.convertCreateReq(addCustomerStaffReqVO);

        //调用Service层完成操作
        customerStaffService.createCustomerStaff(customerStaff);

        return Result.success(customerStaff.getId());
    }

    @PutMapping("/")
    public Result<Boolean> updateCustomerStaff(@RequestBody UpdateCustomerStaffReqVO updateCustomerStaffReqVO) {

        CustomerStaff customerStaff = CustomerStaffConverter.INSTANCE.convertUpdateReq(updateCustomerStaffReqVO);

        Boolean isUpdated = customerStaffService.updateCustomerStaff(customerStaff);

        return Result.success(isUpdated);
    }

    @PutMapping("/status")
    public Result<Boolean> updateCustomerStaffStatus(@RequestBody UpdateCustomerStaffReqVO updateCustomerStaffReqVO) {

        CustomerStaff customerStaff = CustomerStaffConverter.INSTANCE.convertUpdateReq(updateCustomerStaffReqVO);

        Boolean isUpdated = customerStaffService.updateCustomerStaff(customerStaff);

        return Result.success(isUpdated);
    }

    @GetMapping("/{staffId}")
    public Result<CustomerStaffRespVO> findCustomerStaffById(@PathVariable("staffId") Long staffId) {

        CustomerStaff customerStaff = customerStaffService.findCustomerStaffById(staffId);

        CustomerStaffRespVO customerStaffRespVO = CustomerStaffConverter.INSTANCE.convertResp(customerStaff);

        return Result.success(customerStaffRespVO);
    }


    @GetMapping("/async/{staffId}")
    public WebAsyncTask<CustomerStaffRespVO> asyncFindCustomerStaffById(@PathVariable("staffId") Long staffId) {

        System.out.println("The main Thread name is" + Thread.currentThread().getName());

        //启动一个异步Web任务
        WebAsyncTask<CustomerStaffRespVO> task = new WebAsyncTask<CustomerStaffRespVO>(5 * 1000L, () -> {
            System.out.println("The working Thread name is" + Thread.currentThread().getName());

            Thread.sleep(10 * 1000L);

            CustomerStaff customerStaff = customerStaffService.findCustomerStaffById(staffId);

            CustomerStaffRespVO customerStaffRespVO = CustomerStaffConverter.INSTANCE.convertResp(customerStaff);
            return customerStaffRespVO;
        });

        //任务超时设置：添加类似熔断的效果
        task.onTimeout( () -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Timeout");

            return new CustomerStaffRespVO();
        });

        //任务完成时的执行效果
        task.onCompletion(() -> {
            System.out.println("Finished");
        });

        //任务执行异常时
        task.onError(() -> {
            System.out.println("Error");
            return new CustomerStaffRespVO();
        });

        //可以继续执行其他操作
        System.out.println("Task继续执行中");

        return task;
    }

    @GetMapping("/page/{pageSize}/{pageIndex}")
    public Result<PageObject<CustomerStaffRespVO>> findCustomerStaffs(@PathVariable("pageSize") Long pageSize, @PathVariable("pageIndex") Long pageIndex) {

        PageObject<CustomerStaff> pagedCustomerStaff = customerStaffService.findCustomerStaffs(pageSize, pageIndex);

        List<CustomerStaffRespVO> customerStaffRespVOs = CustomerStaffConverter.INSTANCE.convertListResp(pagedCustomerStaff.getList());

        PageObject<CustomerStaffRespVO> result = new PageObject<CustomerStaffRespVO>()
                .setList(customerStaffRespVOs)
                .setTotal(pagedCustomerStaff.getTotal())
                .setPageIndex(pagedCustomerStaff.getPageIndex())
                .setPageSize(pagedCustomerStaff.getPageSize());

        return Result.success(result);
    }

    @DeleteMapping("/{staffId}")
    public Result<Boolean> deleteCustomerStaffById(@PathVariable("staffId") Long staffId) {

        Boolean isDeleted = customerStaffService.deleteCustomerStaffById(staffId);

        return Result.success(isDeleted);
    }

    @GetMapping("/sync/{systemId}")
    public Result<Boolean> syncOutsourcingCustomerStaffsBySystemId(@PathVariable("systemId") Long systemId) {

        //触发远程调用，获取客服信息并保存
        customerStaffService.syncOutsourcingCustomerStaffsBySystemId(systemId);

        return Result.success(true);
    }
}
