package org.geekbang.projects.cs.servicebus.router.hangzhou.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author ZhangShenao
 * @date 2023/7/25 10:56 AM
 * @description: 杭州客服人员DTO
 */
@Data
public class HangzhouCustomerStaffDTO {
    
    private Long id;
    
    private String nickname;
    
    private String avatar;
    
    private String phone;
    
    private String gender;
    
    private String goodAt;
    
    private String remark;
    
    private Date createdAt;
}
