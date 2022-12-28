package org.geekbang.projects.cs.controller.vo.resp;

import lombok.Data;
import lombok.experimental.Accessors;
import org.geekbang.projects.cs.entity.staff.enums.Gender;
import org.geekbang.projects.cs.entity.staff.enums.Status;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CustomerStaffRespVO {

    private Long id;
    private Long groupId;
    private String nickname;
    private String staffName;
    private String avatar;
    private String phone;
    private Gender gender;
    private String goodAt;
    private String welcomeMessage;
    private String remark;
    private Status status;
    private LocalDateTime createTime;
}
