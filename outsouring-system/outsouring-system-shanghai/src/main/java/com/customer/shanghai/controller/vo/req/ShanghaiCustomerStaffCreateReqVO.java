package com.customer.shanghai.controller.vo.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ShanghaiCustomerStaffCreateReqVO implements Serializable {

    private String nickname;
    private String avatar;
    private String phone;
    private String gender;
    private String goodAt;
    private String remark;
}
