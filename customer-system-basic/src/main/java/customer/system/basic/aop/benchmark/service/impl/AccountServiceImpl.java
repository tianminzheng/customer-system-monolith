package customer.system.basic.aop.benchmark.service.impl;

import customer.system.basic.aop.benchmark.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShenao
 * @date 2023/5/29 9:55 AM
 * Description Account 服务实现类
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public void transfer(String source, String dest, long amount) {
//        System.out.println("transfer " + amount + " from " + source + " to " + dest);
    }
}
