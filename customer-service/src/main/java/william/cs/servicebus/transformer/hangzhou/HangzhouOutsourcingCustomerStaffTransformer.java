package william.cs.servicebus.transformer.hangzhou;

import com.alibaba.fastjson.JSON;
import william.cs.entity.staff.CustomerStaff;
import william.cs.entity.staff.enums.Gender;
import william.cs.entity.staff.enums.Status;
import william.cs.servicebus.router.hangzhou.dto.HangzhouCustomerStaffDTO;
import william.cs.servicebus.transformer.OutsourcingCustomerStaffTransformer;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ZhangShenao
 * @date 2023/7/25 11:21 AM
 * @description: 杭州外包客服人员转换器
 */
@Component
public class HangzhouOutsourcingCustomerStaffTransformer
        implements OutsourcingCustomerStaffTransformer<HangzhouCustomerStaffDTO> {
    
    @Override
    public Collection<CustomerStaff> batchTransform(Collection<HangzhouCustomerStaffDTO> outsourcingCustomerStaffs) {
        List<CustomerStaff> customerStaffs = new ArrayList<>();
        
        //把LinkedHashMap转换为List<HangzhouCustomerStaff>
        String string = JSON.toJSONString(outsourcingCustomerStaffs);
        List<HangzhouCustomerStaffDTO> list = JSON.parseArray(string, HangzhouCustomerStaffDTO.class);
        
        for (HangzhouCustomerStaffDTO hangzhouCustomerStaff : list) {
            CustomerStaff customerStaff = new CustomerStaff();
            
            //填充StaffName
            customerStaff.setStaffName(hangzhouCustomerStaff.getNickname());
            customerStaff.setNickname(hangzhouCustomerStaff.getNickname());
            customerStaff.setPhone(hangzhouCustomerStaff.getPhone());
            customerStaff.setRemark(hangzhouCustomerStaff.getRemark());
            customerStaff.setGoodAt(hangzhouCustomerStaff.getGoodAt());
            customerStaff.setAvatar(hangzhouCustomerStaff.getAvatar());
            
            //转换性别
            if (hangzhouCustomerStaff.getGender() != null) {
                customerStaff.setGender(Gender.valueOf(hangzhouCustomerStaff.getGender()));
            }
            
            //转换时间
            if (hangzhouCustomerStaff.getCreatedAt() != null) {
                ZoneId zone = ZoneId.systemDefault();
                Instant createdTimeInstance = hangzhouCustomerStaff.getCreatedAt().toInstant();
                LocalDateTime createdTimeLocalDateTime = LocalDateTime.ofInstant(createdTimeInstance, zone);
                customerStaff.setCreateTime(createdTimeLocalDateTime);
            }
            
            //初始化AccountId和Status
            customerStaff.setAccountId(-1L);
            customerStaff.setStatus(Status.OFFLINE);
            
            customerStaffs.add(customerStaff);
        }
        
        return customerStaffs;
    }
}
