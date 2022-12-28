package org.geekbang.projects.cs.controller.webflux;


import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StubCustomerStaffService {

    private final Map<Long, CustomerStaff> customerStaffMap = new ConcurrentHashMap<>();

    public Mono<CustomerStaff> getCustomerStaffById(Long id) {
        return Mono.justOrEmpty(customerStaffMap.get(id));
    }

    public Flux<CustomerStaff> getCustomerStaffs() {
        return Flux.fromIterable(customerStaffMap.values());
    }

    public Mono<Void> createOrUpdateCustomerStaff(Mono<CustomerStaff> customerStaffMono) {
        return customerStaffMono.doOnNext( customerStaff -> {
            customerStaffMap.put(customerStaff.getId(), customerStaff);
        }).thenEmpty(Mono.empty());
    }

    public Mono<CustomerStaff> deleteCustomerStaffById(Long id) {
        return Mono.justOrEmpty(customerStaffMap.remove(id));
    }

}
