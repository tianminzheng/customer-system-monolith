package org.geekbang.projects.cs.entity.tenant;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 客服外部系统
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("outsourcing_system")
public class OutsourcingSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 系统编码
     */
    private String systemCode;

    /**
     * 系统描述
     */
    private String description;

    /**
     * 系统访问URL
     */
    private String systemUrl;

    /**
     * 系统Id
     */
    private String appId;

    /**
     * 数据公钥
     */
    private String appKey;

    /**
     * 数据私钥
     */
    private String appSecret;

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
