package com.customer.beijing.controller.vo.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BeijingCustomerStaffUpdateReqVO {

    private Long id;
    private String nickname;
    private String avatar;
    private String goodAt;
    private String remark;
}
