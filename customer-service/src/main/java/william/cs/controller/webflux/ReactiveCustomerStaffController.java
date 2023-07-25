package william.cs.controller.webflux;

import william.cs.entity.staff.CustomerStaff;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 基于响应式编程模式的客服人员API
 */
@RestController
@RequestMapping("/reactive/customerStaffs")
public class ReactiveCustomerStaffController {

    @Resource
    private ReactiveCustomerStaffStubService reactiveCustomerStaffStubService;

    //新增CustomerStaff
    @PostMapping("/")
    public Mono<Void> addCustomerStaff(@RequestBody CustomerStaff customerStaff) {
        //调用Service层完成操作
        return reactiveCustomerStaffStubService.createOrUpdateCustomerStaff(Mono.just(customerStaff));
    }

    @PutMapping("/")
    public Mono<Void> updateCustomerStaff(@RequestBody CustomerStaff customerStaff) {
        return reactiveCustomerStaffStubService.createOrUpdateCustomerStaff(Mono.just(customerStaff));
    }

    @GetMapping("/{staffId}")
    public Mono<CustomerStaff> findCustomerStaffById(@PathVariable("staffId") Long staffId) {
        return reactiveCustomerStaffStubService.getCustomerStaffById(staffId);
    }


    @GetMapping("/")
    public Flux<CustomerStaff> findCustomerStaffs() {
        return reactiveCustomerStaffStubService.getCustomerStaffs();
    }

    @DeleteMapping("/{staffId}")
    public Mono<CustomerStaff> deleteCustomerStaffById(@PathVariable("staffId") Long staffId) {
        return reactiveCustomerStaffStubService.deleteCustomerStaffById(staffId);
    }
}
