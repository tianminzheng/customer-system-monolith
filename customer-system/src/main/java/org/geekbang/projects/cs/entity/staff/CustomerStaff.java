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
@TableName("customer_staff")
public class CustomerStaff implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分组Id
     */
    private Long groupId;

    /**
     * 客服人员姓名
     */
    private String staffName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 账号Id
     */
    private Long accountId;

    /**
     * 外部系统Id，非空标识该客服人员数据来自外部系统
     */
    private Long systemId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 擅长领域
     */
    private String goodAt;

    /**
     * 是否自动回复，1=是,0=否
     */
    private Boolean isAutoReply;

    /**
     * 欢迎语
     */
    private String welcomeMessage;

    /**
     * 状态，1=在线,0=下线
     */
    private Status status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除，1=删除,0=未删除
     */
    @TableLogic
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
