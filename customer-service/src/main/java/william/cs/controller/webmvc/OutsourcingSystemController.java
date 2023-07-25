package william.cs.controller.webmvc;

import william.cs.controller.vo.req.AddOutsourcingSystemReqVO;
import william.cs.controller.vo.resp.OutsourcingSystemRespVO;
import william.cs.converter.OutsourcingSystemConverter;
import william.cs.entity.tenant.OutsourcingSystem;
import org.geekbang.projects.cs.infrastructure.page.PageObject;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import william.cs.service.IOutsourcingSystemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 外包客服系统（租户）API
 */
@RestController
@RequestMapping("/outsourcingSystems")
public class OutsourcingSystemController {
    
    @Resource
    private IOutsourcingSystemService outsourcingSystemService;
    
    //外包客服入驻
    @PostMapping("/")
    public Result<Long> addOutsourcingSystem(@RequestBody AddOutsourcingSystemReqVO addOutsourcingSystemReqVO) {
        
        //数据转换
        OutsourcingSystem outsourcingSystem = OutsourcingSystemConverter.INSTANCE.convertCreateReq(
                addOutsourcingSystemReqVO);
        
        //调用Service层完成操作
        outsourcingSystemService.addOutsourcingSystem(outsourcingSystem);
        
        return Result.success(outsourcingSystem.getId());
    }
    
    @GetMapping("/{systemId}")
    public Result<OutsourcingSystemRespVO> findOutsourcingSystemById(@PathVariable("systemId") Long systemId) {
        
        OutsourcingSystem outsourcingSystem = outsourcingSystemService.findOutsourcingSystemById(systemId);
        
        OutsourcingSystemRespVO outsourcingSystemRespVO = OutsourcingSystemConverter.INSTANCE.convertResp(
                outsourcingSystem);
        
        return Result.success(outsourcingSystemRespVO);
    }
    
    @GetMapping("/page/{pageSize}/{pageIndex}")
    public Result<PageObject<OutsourcingSystemRespVO>> findOutsourcingSystems(@PathVariable("pageSize") Long pageSize,
            @PathVariable("pageIndex") Long pageIndex) {
        
        PageObject<OutsourcingSystem> pagedOutsourcingSystem = outsourcingSystemService.findPagedOutsourcingSystems(
                pageSize, pageIndex);
        
        List<OutsourcingSystemRespVO> outsourcingSystemRespVOs = OutsourcingSystemConverter.INSTANCE.convertListResp(
                pagedOutsourcingSystem.getList());
        
        PageObject<OutsourcingSystemRespVO> result = new PageObject<OutsourcingSystemRespVO>().setList(
                        outsourcingSystemRespVOs).setTotal(pagedOutsourcingSystem.getTotal())
                .setPageIndex(pagedOutsourcingSystem.getPageIndex()).setPageSize(pagedOutsourcingSystem.getPageSize());
        
        return Result.success(result);
    }
    
    @DeleteMapping("/{systemId}")
    public Result<Boolean> deleteOutsourcingSystemById(@PathVariable("systemId") Long systemId) {
        
        Boolean isDeleted = outsourcingSystemService.deleteOutsourcingSystemById(systemId);
        
        return Result.success(isDeleted);
    }
}
