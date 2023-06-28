package org.geekbang.projects.cs.controller.webmvc;

import org.geekbang.projects.cs.controller.vo.req.AddCustomerStaffReqVO;
import org.geekbang.projects.cs.controller.vo.req.UpdateCustomerStaffReqVO;
import org.geekbang.projects.cs.controller.vo.resp.CustomerStaffRespVO;
import org.geekbang.projects.cs.converter.CustomerStaffConverter;
import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.infrastructure.page.PageObject;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.geekbang.projects.cs.service.ICustomerStaffService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.annotation.Resource;
import java.util.List;

/**
 * 传统Web MVC模式的客服人员API
 */
@RestController
@RequestMapping("/customerStaffs")
public class CustomerStaffController {

    @Resource
    private ICustomerStaffService customerStaffService;

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
        //打印请求接收线程
        System.out.println("The Request Receiver Thread is : " + Thread.currentThread().getName());

        //启动一个异步Web任务
        WebAsyncTask<CustomerStaffRespVO> task = new WebAsyncTask<CustomerStaffRespVO>(2 * 1000L, () -> {
            //打印请求处理线程
            System.out.println("The Worker Thread is: " + Thread.currentThread().getName());

            CustomerStaff customerStaff = customerStaffService.findCustomerStaffById(staffId);

            return CustomerStaffConverter.INSTANCE.convertResp(customerStaff);
        });

        //设置任务执行成功回调
        task.onCompletion(() -> System.out.println("Task Finished! Thread: " + Thread.currentThread().getName()));

        //设置任务超时回调,可以实现类似熔断的效果,返回降级的结果
        task.onTimeout(() -> {
            System.out.println("Task Timeout! Thread: " + Thread.currentThread().getName());
            return new CustomerStaffRespVO();
        });


        //设置任务执行异常回调
        task.onError(() -> {
            System.out.println("Task Exec Error! Thread: " + Thread.currentThread().getName());
            return new CustomerStaffRespVO();
        });

        //请求接受线程继续执行
        System.out.println("Request Receiver Thread Exec Next... Thread is: " + Thread.currentThread().getName());

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
