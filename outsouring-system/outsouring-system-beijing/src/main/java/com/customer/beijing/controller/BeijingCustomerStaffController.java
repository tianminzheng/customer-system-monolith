package com.customer.beijing.controller;

import com.customer.beijing.controller.vo.req.BeijingCustomerStaffCreateReqVO;
import com.customer.beijing.controller.vo.req.BeijingCustomerStaffUpdateReqVO;
import com.customer.beijing.controller.vo.resp.BeijingCustomerStaffRespVO;
import com.customer.beijing.converter.BeijingCustomerStaffConverter;
import com.customer.beijing.entity.BeijingCustomerStaff;
import com.customer.beijing.service.BeijingCustomerStaffService;
import lombok.extern.slf4j.Slf4j;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customerStaffs/beijing/")
public class BeijingCustomerStaffController {

    @Autowired
    private BeijingCustomerStaffService customerStaffService;

    @PostMapping("/")
    public Result<Long> addCustomerStaff(@RequestBody @Validated BeijingCustomerStaffCreateReqVO customerStaffCreateReqVO) {
        BeijingCustomerStaff customerStaff = BeijingCustomerStaffConverter.INSTANCE.convertCreateReq(customerStaffCreateReqVO);

        return Result.success(customerStaffService.createCustomerStaff(customerStaff));
    }

    @PutMapping("/")
    public Result<Boolean> updateCustomerStaff(@RequestBody @Validated BeijingCustomerStaffUpdateReqVO customerStaffUpdateReqVO) {
        BeijingCustomerStaff customerStaff = BeijingCustomerStaffConverter.INSTANCE.convertUpdateReq(customerStaffUpdateReqVO);

        return Result.success(customerStaffService.updateCustomerStaff(customerStaff));
    }

    @DeleteMapping("/")
    public Result<Boolean> deleteCustomerStaff(@RequestParam ("id") Long id) {
        return Result.success(customerStaffService.deleteCustomerStaffById(id));
    }

    @GetMapping("/")
    public Result<List<BeijingCustomerStaffRespVO>> getAllCustomerStaffs() {
        List<BeijingCustomerStaff> customerStaffs = customerStaffService.findAllCustomerStaffs();

        return Result.success(BeijingCustomerStaffConverter.INSTANCE.convertListResp(customerStaffs));
    }

    @GetMapping("/updated")
    public Result<List<BeijingCustomerStaffRespVO>> getCustomerStaffsByUpdatedTime(@RequestParam ("updatedTime") Long updatedTime) {

        List<BeijingCustomerStaff> customerStaffs = customerStaffService.findCustomerStaffsByUpdatedTime(updatedTime);

        return Result.success(BeijingCustomerStaffConverter.INSTANCE.convertListResp(customerStaffs));
    }
}
