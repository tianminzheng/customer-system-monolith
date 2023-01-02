package org.geekbang.projects.cs.entity.staff;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.geekbang.projects.cs.entity.staff.enums.Gender;
import org.geekbang.projects.cs.entity.staff.enums.Status;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 客服人员表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("customer_staff_count")
public class CustomerStaffCount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 客服人员计数
     */
    private Long count;
}
