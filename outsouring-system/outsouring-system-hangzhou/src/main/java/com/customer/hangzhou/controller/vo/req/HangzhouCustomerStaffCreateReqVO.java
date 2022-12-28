package com.customer.hangzhou.controller.vo.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class HangzhouCustomerStaffCreateReqVO implements Serializable {

    private String nickname;
    private String avatar;
    private String phone;
    private String gender;
    private String goodAt;
    private String remark;
}
