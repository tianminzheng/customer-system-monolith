package customer.system.basic.async.service.impl;

import customer.system.basic.async.service.SyncService;

/**
 * @author ZhangShenao
 * @date 2023/5/29 10:45 AM
 * Description 同步处理实现类
 */
public class SyncServiceImpl implements SyncService {
    @Override
    public void exec() {
        System.out.println("Exec Sync Method. Thread: " + Thread.currentThread().getName());
    }
}
