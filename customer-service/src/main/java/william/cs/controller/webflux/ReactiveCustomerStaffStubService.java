package william.cs.controller.webflux;


import lombok.extern.slf4j.Slf4j;
import william.cs.entity.staff.CustomerStaff;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于响应式编程模式的客服人员桩代码Service
 */
@Service
@Slf4j
public class ReactiveCustomerStaffStubService {
    /**
     * 使用内存Map模拟DB
     */
    private final Map<Long, CustomerStaff> CUSTOMER_STAFF_DB = new ConcurrentHashMap<>();

    public Mono<CustomerStaff> getCustomerStaffById(Long id) {
        return Mono.justOrEmpty(CUSTOMER_STAFF_DB.get(id));
    }

    public Flux<CustomerStaff> getCustomerStaffs() {
        return Flux.fromIterable(CUSTOMER_STAFF_DB.values());
    }

    public Mono<Void> createOrUpdateCustomerStaff(Mono<CustomerStaff> customerStaffMono) {
        return customerStaffMono.doOnNext(customerStaff -> {
                    log.info("Create Or Update Customer Staff Step1. Current Thread: {}", Thread.currentThread().getName());
                    CUSTOMER_STAFF_DB.put(customerStaff.getId(), customerStaff);
                })
                .doOnNext(customerStaff -> {
                    log.info("Create Or Update Customer Staff Step2. Current Thread: {}", Thread.currentThread().getName());
                    customerStaff.setIsDeleted(false);
                    LocalDateTime now = LocalDateTime.now();
                    customerStaff.setCreateTime(now);
                    customerStaff.setUpdateTime(now);
                })
                .doOnNext(customerStaff -> log.info("Create Or Update Customer Staff Step3. Current Thread: {}", Thread.currentThread().getName()))
                .doOnNext(customerStaff -> log.info("Create Or Update Customer Staff Step4. Current Thread: {}", Thread.currentThread().getName()))
                .doOnNext(customerStaff -> log.info("Create Or Update Customer Staff Step5. Current Thread: {}", Thread.currentThread().getName()))
                .doOnError(throwable -> log.error("Create Or Update Customer Error. Current Thread: {}", Thread.currentThread().getName(), throwable))
                .thenEmpty(Mono.empty());
    }

    public Mono<CustomerStaff> deleteCustomerStaffById(Long id) {
        return Mono.justOrEmpty(CUSTOMER_STAFF_DB.remove(id));
    }

}
