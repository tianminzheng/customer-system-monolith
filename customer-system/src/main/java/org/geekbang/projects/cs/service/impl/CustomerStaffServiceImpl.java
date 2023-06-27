package org.geekbang.projects.cs.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.entity.tenant.OutsourcingSystem;
import org.geekbang.projects.cs.infrastructure.exception.BizException;
import org.geekbang.projects.cs.infrastructure.page.PageObject;
import org.geekbang.projects.cs.integration.OutsourcingSystemClient;
import org.geekbang.projects.cs.mapper.CustomerStaffMapper;
import org.geekbang.projects.cs.service.ICustomerStaffService;
import org.geekbang.projects.cs.service.IOutsourcingSystemService;
import org.geekbang.projects.cs.servicebus.endpoint.CustomerStaffEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerStaffServiceImpl extends ServiceImpl<CustomerStaffMapper, CustomerStaff> implements ICustomerStaffService {


    @Resource
    private IOutsourcingSystemService outsourcingSystemService;

    @Resource
    private OutsourcingSystemClient outsourcingSystemClient;

    @Autowired
    private CustomerStaffEndpoint customerStaffEndpoint;

    @Override
    @DS("master")   //指定动态数据源
    public PageObject<CustomerStaff> findCustomerStaffs(Long pageSize, Long pageIndex) {
        LambdaQueryWrapper<CustomerStaff> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(CustomerStaff::getCreateTime);
        return getCustomerStaffPageObjectByWrapper(pageSize, pageIndex, queryWrapper);
    }

    @Override
    public List<CustomerStaff> findCustomerStaffs() {
        LambdaQueryWrapper<CustomerStaff> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public PageObject<CustomerStaff> findCustomerStaffsByName(String staffName, Long pageSize, Long pageIndex) {
        LambdaQueryWrapper<CustomerStaff> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(CustomerStaff::getStaffName, staffName);
        queryWrapper.orderByDesc(CustomerStaff::getCreateTime);
        return getCustomerStaffPageObjectByWrapper(pageSize, pageIndex, queryWrapper);
    }

    /**
     * 根据条件分页查询
     */
    private PageObject<CustomerStaff> getCustomerStaffPageObjectByWrapper(Long pageSize, Long pageIndex, LambdaQueryWrapper<CustomerStaff> queryWrapper) {
        IPage<CustomerStaff> page = new Page<>(pageIndex, pageSize);
        IPage<CustomerStaff> pagedResult = baseMapper.selectPage(page, queryWrapper);

        return PageObject.buildPage(pagedResult.getRecords(), pagedResult.getTotal(), pagedResult.getCurrent(), pagedResult.getSize());
    }


    @Override
    public CustomerStaff findCustomerStaffById(Long staffId) {

        try {
//            int a = 1;
//            int b = 0;
//            int c = a / b;  //模拟任务异常
//            Thread.sleep(5000L);  //模拟执行超时
            return getById(staffId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @DS("master")   //指定动态数据源
    public Boolean createCustomerStaff(CustomerStaff customerStaff) throws BizException {
        return save(customerStaff);
    }

    @Override
    public Boolean updateCustomerStaff(CustomerStaff customerStaff) {
        return updateById(customerStaff);
    }

    @Override
    public Boolean deleteCustomerStaffById(Long staffId) {
//        CustomerStaff existed = getById(staffId);
//        if (existed == null){
//            return false;
//        }
//        //通过更新操作实现逻辑删除
//        existed.setIsDeleted(true);
//        return updateById(existed);

        //通过逻辑删除为来进行逻辑删除
        return removeById(staffId);
    }

    @Override
    public void syncOutsourcingCustomerStaffsBySystemId(Long systemId) {

        //获取租户信息
        OutsourcingSystem outsourcingSystem = outsourcingSystemService.findOutsourcingSystemById(systemId);

        //根据租户远程获取客服信息
//        List<CustomerStaff> customerStaffs = outsourcingSystemClient.getCustomerStaffs(outsourcingSystem);


        List<CustomerStaff> customerStaffs = customerStaffEndpoint.fetchCustomerStaffs(outsourcingSystem);

        //保存客服信息
        saveOrUpdateBatch(customerStaffs);
    }
}
