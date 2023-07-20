package org.geekbang.projects.cs.service;

import org.geekbang.projects.cs.entity.tenant.OutsourcingSystem;
import org.geekbang.projects.cs.infrastructure.page.PageObject;

import java.util.List;

/**
 * 外包客服Service接口
 */
public interface IOutsourcingSystemService {
    
    List<OutsourcingSystem> findAllOutsourcingSystems();
    
    PageObject<OutsourcingSystem> findPagedOutsourcingSystems(Long pageSize, Long pageIndex);
    
    OutsourcingSystem findOutsourcingSystemById(Long systemId);
    
    Boolean addOutsourcingSystem(OutsourcingSystem outsourcingSystem);
    
    Boolean updateOutsourcingSystem(OutsourcingSystem outsourcingSystem);
    
    Boolean deleteOutsourcingSystemById(Long systemId);
}
