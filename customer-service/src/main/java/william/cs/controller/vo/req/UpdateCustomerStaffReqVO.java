package william.cs.controller.vo.req;

import lombok.Data;
import lombok.experimental.Accessors;
import william.cs.entity.staff.enums.Status;

@Data
@Accessors(chain = true)
public class UpdateCustomerStaffReqVO {

    private Long id;
    private Long groupId;
    private String nickname;
    private String avatar;
    private Status status;
    private String goodAt;
    private String welcomeMessage;
    private String remark;
}
