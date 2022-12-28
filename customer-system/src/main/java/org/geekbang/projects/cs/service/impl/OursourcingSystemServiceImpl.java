package org.geekbang.projects.cs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geekbang.projects.cs.entity.tenant.OutsourcingSystem;
import org.geekbang.projects.cs.infrastructure.page.PageObject;
import org.geekbang.projects.cs.mapper.OutsourcingSystemMapper;
import org.geekbang.projects.cs.service.IOutsourcingSystemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OursourcingSystemServiceImpl extends ServiceImpl<OutsourcingSystemMapper, OutsourcingSystem> implements IOutsourcingSystemService {

    @Override
    public PageObject<OutsourcingSystem> findPagedOutsourcingSystems(Long pageSize, Long pageIndex) {

        IPage<OutsourcingSystem> pagedResult = baseMapper.findPagedOutsourcingSystems(pageSize, pageIndex);

        PageObject<OutsourcingSystem> pagedObject = new PageObject<>();
        pagedObject.buildPage(pagedResult.getRecords(), pagedResult.getTotal(), pagedResult.getCurrent(), pagedResult.getSize());

        return pagedObject;
    }

    @Override
    public List<OutsourcingSystem> findAllOutsourcingSystems() {
        LambdaQueryWrapper<OutsourcingSystem> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public OutsourcingSystem findOutsourcingSystemById(Long staffId) {

        return baseMapper.selectById(staffId);
    }

    @Override
    public Boolean addOutsourcingSystem(OutsourcingSystem OutsourcingSystem) {

        return this.save(OutsourcingSystem);
    }

    @Override
    public Boolean updateOutsourcingSystem(OutsourcingSystem OutsourcingSystem) {

        return this.updateById(OutsourcingSystem);
    }

    @Override
    public Boolean deleteOutsourcingSystemById(Long staffId) {

        return this.removeById(staffId);
    }
}
