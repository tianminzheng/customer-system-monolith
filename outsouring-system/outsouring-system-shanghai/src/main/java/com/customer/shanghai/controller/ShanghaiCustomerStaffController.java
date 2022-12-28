package com.customer.shanghai.controller;

import com.customer.shanghai.controller.vo.req.ShanghaiCustomerStaffCreateReqVO;
import com.customer.shanghai.controller.vo.req.ShanghaiCustomerStaffUpdateReqVO;
import com.customer.shanghai.controller.vo.resp.ShanghaiCustomerStaffRespVO;
import com.customer.shanghai.converter.ShanghaiCustomerStaffConverter;
import com.customer.shanghai.entity.ShanghaiCustomerStaff;
import com.customer.shanghai.service.ShanghaiCustomerStaffService;
import lombok.extern.slf4j.Slf4j;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customerStaffs/shanghai/")
public class ShanghaiCustomerStaffController {

    @Autowired
    private ShanghaiCustomerStaffService customerStaffService;

    @PostMapping("/")
    public Result<Long> addCustomerStaff(@RequestBody @Validated ShanghaiCustomerStaffCreateReqVO customerStaffCreateReqVO) {
        ShanghaiCustomerStaff customerStaff = ShanghaiCustomerStaffConverter.INSTANCE.convertCreateReq(customerStaffCreateReqVO);

        return Result.success(customerStaffService.createCustomerStaff(customerStaff));
    }

    @PutMapping("/")
    public Result<Boolean> updateCustomerStaff(@RequestBody @Validated ShanghaiCustomerStaffUpdateReqVO customerStaffUpdateReqVO) {
        ShanghaiCustomerStaff customerStaff = ShanghaiCustomerStaffConverter.INSTANCE.convertUpdateReq(customerStaffUpdateReqVO);

        return Result.success(customerStaffService.updateCustomerStaff(customerStaff));
    }

    @DeleteMapping("/")
    public Result<Boolean> deleteCustomerStaff(@RequestParam ("id") Long id) {
        return Result.success(customerStaffService.deleteCustomerStaffById(id));
    }

    @GetMapping("/")
    public Result<List<ShanghaiCustomerStaffRespVO>> getAllCustomerStaffs() {
        List<ShanghaiCustomerStaff> customerStaffs = customerStaffService.findAllCustomerStaffs();

        return Result.success(ShanghaiCustomerStaffConverter.INSTANCE.convertListResp(customerStaffs));
    }

    @GetMapping("/updated")
    public Result<List<ShanghaiCustomerStaffRespVO>> getCustomerStaffsByUpdatedTime(@RequestParam ("updatedTime") Long updatedTime) {

        List<ShanghaiCustomerStaff> customerStaffs = customerStaffService.findCustomerStaffsByUpdatedTime(updatedTime);

        return Result.success(ShanghaiCustomerStaffConverter.INSTANCE.convertListResp(customerStaffs));
    }
}
