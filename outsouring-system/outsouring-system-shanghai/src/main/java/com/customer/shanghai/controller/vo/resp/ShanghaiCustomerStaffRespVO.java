package com.customer.shanghai.controller.vo.resp;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ShanghaiCustomerStaffRespVO {

    private Long id;
    private String nickname;
    private String avatar;
    private String phone;
    private String gender;
    private String goodAt;
    private String remark;
    private Date createdAt;
}
