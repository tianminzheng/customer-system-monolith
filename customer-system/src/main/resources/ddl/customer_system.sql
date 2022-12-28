CREATE TABLE `customer_staff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` bigint(20) DEFAULT NULL COMMENT '分组Id',
  `staff_name` varchar(45) NOT NULL COMMENT '客服姓名',
  `nickname` varchar(45) NOT NULL COMMENT '昵称',
  `account_id` bigint(20) NOT NULL COMMENT '账号Id',
  `system_id` bigint(20) DEFAULT NULL COMMENT '外部系统Id，非空标识该客服人员数据来自外部系统',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `gender` varchar(32) NOT NULL COMMENT '性别，MALE=男性，FEMALE=女性',
  `good_at` varchar(255) DEFAULT NULL COMMENT '擅长领域',
  `is_auto_reply` tinyint(1) DEFAULT '0' COMMENT '是否自动回复，1=是,0=否',
  `welcome_message` varchar(255) DEFAULT NULL COMMENT '欢迎语',
  `status` varchar(32) NOT NULL COMMENT '状态，ONLINE=在线,OFFLINE=下线',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，1=删除,0=未删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服人员表'

CREATE TABLE `customer_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(45) NOT NULL COMMENT '分组名称',
  `group_description` varchar(200) DEFAULT NULL COMMENT '分组描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服分组表'

CREATE TABLE `outsourcing_system` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `system_name` varchar(45) NOT NULL COMMENT '系统名称',
  `description` varchar(255) DEFAULT NULL COMMENT '系统描述',
  `system_url` varchar(100) NOT NULL COMMENT '系统访问URL',
  `app_id` varchar(45) NOT NULL COMMENT '系统Id',
  `app_key` varchar(255) NOT NULL COMMENT '数据公钥',
  `app_secret` varchar(255) NOT NULL COMMENT '数据私钥',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，1=删除,0=未删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服外部系统'