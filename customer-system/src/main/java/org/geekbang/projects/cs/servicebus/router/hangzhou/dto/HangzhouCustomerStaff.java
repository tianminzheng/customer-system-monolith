package org.geekbang.projects.cs.servicebus.router.hangzhou.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HangzhouCustomerStaff implements Serializable {

    private Long id;
    private String nickname;
    private String avatar;
    private String phone;
    private String gender;
    private String goodAt;
    private String remark;
    private Date createTime;
}
