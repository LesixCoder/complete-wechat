-- add
DROP TABLE IF EXISTS `coach`;
CREATE TABLE `coach` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `gym_id` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` int(11) DEFAULT '1',
  `phone` varchar(20) DEFAULT NULL,
  `img_url` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birth` varchar(30) DEFAULT NULL,
  `certificate` varchar(300) DEFAULT NULL,
  `coach_year` int(11) DEFAULT NULL,
  `flag` int(11) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `coach_album`;
CREATE TABLE `coach_album` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `coach_id` bigint(20) DEFAULT NULL,
  `img_url` varchar(200) DEFAULT NULL,
  `sort` int(11) DEFAULT '99',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_deposit`;
CREATE TABLE `user_deposit` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_id` bigint(20) DEFAULT NULL,
  `biz_id` bigint(20) DEFAULT NULL,
  `biz_type` int(11) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `bonus_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_deposit_draw`;
CREATE TABLE `user_deposit_draw` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_id` bigint(20) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `account` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '1 已申请  2平台已审核  3失败退还  4已打款到账',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gym`;
CREATE TABLE `gym` (
  `id` bigint(20) NOT NULL,
  `area_id` bigint(20) DEFAULT NULL COMMENT '域区id',
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '名字',
  `introduce` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '介简',
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `img_url` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '首图',
  `img_array` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '多张图以英文逗号分隔',
  `img_text` text,
  `address` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `simple_address` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `business_hours` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '营业时间',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `is_open` int(11) DEFAULT '1' COMMENT '0开店 1关店',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `flag` int(11) DEFAULT '1',
  `sort` int(11) DEFAULT '99' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `course_meal`;
CREATE TABLE `course_meal` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `course_id` bigint(20) DEFAULT NULL,
  `gym_id` bigint(20) DEFAULT NULL,
  `coach_id` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `people_number` int(11) DEFAULT '1',
  `course_number` int(11) DEFAULT '1',
  `str_time` varchar(50) DEFAULT NULL COMMENT '文字上课时间（例：周二、周四18:00）',
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT '1' COMMENT '1 普通课  2团课',
  `create_time` datetime DEFAULT NULL,
  `sort` int(11) DEFAULT '99',
  `flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `course_meal_time`;
CREATE TABLE `course_meal_time` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `course_meal_id` bigint(20) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `course_order`;
CREATE TABLE `course_order` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_id` bigint(20) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `course_meal_id` bigint(20) DEFAULT NULL,
  `gym_id` bigint(20) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `pay_price` double(10,2) DEFAULT NULL COMMENT '支付价格(扣除优惠）',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `code` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '唯一订单号',
  `pay_type` int(11) DEFAULT '0' COMMENT '支付方式： 1支付宝 2微信',
  `status` int(11) unsigned zerofill DEFAULT NULL COMMENT '订单状态 1待付款  2已付款  3 已退款  4进行中 5已完成',
  `is_bonus` int(11) DEFAULT '0' COMMENT '用户拉新是否分红(0否 1是 ）',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_number_Unique` (`code`) USING BTREE,
  KEY `user_id_Normal` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `course_order_refund`;
CREATE TABLE `course_order_refund` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL COMMENT '业务主键id',
  `pay_price` double DEFAULT NULL,
  `refund_price` double DEFAULT NULL COMMENT '退款价格',
  `out_refund_no` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '商户退款单号(微信/支付唯一)',
  `out_refund_id` varchar(50) DEFAULT NULL COMMENT '第三方退款单号',
  `type` int(11) DEFAULT NULL COMMENT '1.用户主动退款  2.人员不够全额退款  3满团优惠退款',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `course_gym_middle`;
CREATE TABLE `course_gym_middle` (
  `gym_id` bigint(20) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- update
alter table user add deposit double null default 0; 
alter table user add invite_user_id bigint null; 


