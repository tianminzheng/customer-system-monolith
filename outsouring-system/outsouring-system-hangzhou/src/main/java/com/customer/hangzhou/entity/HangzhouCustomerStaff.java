package com.customer.hangzhou.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity //标识为JPA实体类
@Table(name = "hangzhou_customer_staff")    //指定映射的表名
public class HangzhouCustomerStaff implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * 主键映射,并设置主键生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

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
    private String gender;

    /**
     * 擅长领域
     */
    private String goodAt;

    /**
     * 备注
     */
    private String remark;

    /***
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 是否删除，0：否，1：是
     **/
    private Boolean isDeleted;
}
