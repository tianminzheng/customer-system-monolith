package org.geekbang.projects.cs.controller.webflux;

import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/customerStaffs")
public class ReactiveCustomerStaffController {

    @Autowired
    StubCustomerStaffService stubCustomerStaffService;

    //新增CustomerStaff
    @PostMapping("/")
    public Mono<Void> addCustomerStaff(@RequestBody CustomerStaff customerStaff) {

        //调用Service层完成操作
        Mono<Void> result = stubCustomerStaffService.createOrUpdateCustomerStaff(Mono.just(customerStaff));

        return result;
    }

    @PutMapping("/")
    public Mono<Void> updateCustomerStaff(@RequestBody CustomerStaff customerStaff) {

        Mono<Void> result = stubCustomerStaffService.createOrUpdateCustomerStaff(Mono.just(customerStaff));

        return result;
    }

    @GetMapping("/{staffId}")
    public Mono<CustomerStaff> findCustomerStaffById(@PathVariable("staffId") Long staffId) {

        Mono<CustomerStaff> customerStaffMono = stubCustomerStaffService.getCustomerStaffById(staffId);

        return customerStaffMono;
    }


    @GetMapping("/")
    public Flux<CustomerStaff>findCustomerStaffs() {

        Flux<CustomerStaff> customerStaffFlux = stubCustomerStaffService.getCustomerStaffs();

        return customerStaffFlux;
    }

    @DeleteMapping("/{staffId}")
    public Mono<CustomerStaff> deleteCustomerStaffById(@PathVariable("staffId") Long staffId) {

        Mono<CustomerStaff> result = stubCustomerStaffService.deleteCustomerStaffById(staffId);

        return result;
    }
}
