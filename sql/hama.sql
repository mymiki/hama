/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.29 : Database - hama
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hama` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `hama`;

/*Table structure for table `h_on_salce_task_map` */

DROP TABLE IF EXISTS `h_on_salce_task_map`;

CREATE TABLE `h_on_salce_task_map` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `on_sale_task_id` bigint(20) DEFAULT NULL COMMENT '任务计划id',
  `spu_id` bigint(20) DEFAULT NULL COMMENT 'spu_id',
  `sku_id` bigint(20) DEFAULT NULL COMMENT 'sku_id',
  `create_time` datetime DEFAULT NULL COMMENT '增加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '增加人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除 :0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='执行计划中胡商品列表';

/*Table structure for table `h_on_sale_task` */

DROP TABLE IF EXISTS `h_on_sale_task`;

CREATE TABLE `h_on_sale_task` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `begin_time_hour` tinyint(2) DEFAULT NULL COMMENT '开始小时数:如:13 ->13:00',
  `end_time_hour` tinyint(2) DEFAULT NULL COMMENT '结束小时数:如:20 ->20:00',
  `begin_date` date DEFAULT NULL COMMENT '开始日期',
  `last_days` tinyint(3) DEFAULT NULL COMMENT '持续天数:如:365天（1年）',
  `task_staus` tinyint(1) DEFAULT NULL COMMENT '状态：0停止 1启用',
  `create_time` datetime DEFAULT NULL COMMENT '增加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '增加人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除 :0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='上架任务执行计划表';

/*Table structure for table `h_order` */

DROP TABLE IF EXISTS `h_order`;

CREATE TABLE `h_order` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `order_sn` varchar(35) DEFAULT NULL COMMENT '编号',
  `order_status` tinyint(1) DEFAULT NULL COMMENT '0未支付 1已支付 2取消',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `create_time` datetime DEFAULT NULL COMMENT '增加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '增加人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除 :0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `h_order_item` */

DROP TABLE IF EXISTS `h_order_item`;

CREATE TABLE `h_order_item` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `order_sn` varchar(30) CHARACTER SET latin1 DEFAULT NULL COMMENT '订单编号',
  `spu_id` bigint(20) DEFAULT NULL COMMENT '货品名称',
  `sku_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `create_time` datetime DEFAULT NULL COMMENT '增加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '增加人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除 :0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `h_sku` */

DROP TABLE IF EXISTS `h_sku`;

CREATE TABLE `h_sku` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `spu_id` bigint(20) DEFAULT NULL COMMENT 'spu_id',
  `cost_price` decimal(10,3) DEFAULT NULL COMMENT '成本价',
  `sale_price` decimal(10,3) DEFAULT NULL COMMENT '售价',
  `show_price` decimal(10,3) DEFAULT NULL COMMENT '展示用价（原价）',
  `lower_price` decimal(10,3) DEFAULT NULL COMMENT '最低价',
  `upper_price` decimal(10,3) DEFAULT NULL COMMENT '最高价',
  `cost_ticket_count` int(10) DEFAULT NULL COMMENT '消耗购买票券数量',
  `is_income` tinyint(1) DEFAULT '1' COMMENT '是否产生收益 0否 1是',
  `income_rate` decimal(4,2) DEFAULT '0.00' COMMENT '收益比率: 10.25 -> 10.25%/1天',
  `create_time` datetime DEFAULT NULL COMMENT '增加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '增加人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除 :0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `h_spu` */

DROP TABLE IF EXISTS `h_spu`;

CREATE TABLE `h_spu` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `spu_name` varchar(30) DEFAULT NULL COMMENT 'spu名称',
  `spu_title` varchar(30) DEFAULT NULL COMMENT 'spu标注',
  `create_time` datetime DEFAULT NULL COMMENT '增加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '增加人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除 :0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `h_user` */

DROP TABLE IF EXISTS `h_user`;

CREATE TABLE `h_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `user_card_no` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `iphone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `fist_pwd` varchar(80) DEFAULT NULL COMMENT '一级密码',
  `second_pwd` varchar(80) DEFAULT NULL COMMENT '二级密码',
  `from_id` bigint(20) DEFAULT NULL COMMENT '邀请人id',
  `from_iphone` varchar(11) DEFAULT NULL COMMENT '邀请人手机号',
  `area_id` bigint(20) DEFAULT NULL COMMENT '区县ID',
  `create_time` datetime DEFAULT NULL COMMENT '增加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '增加人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除 :0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Table structure for table `h_user_capital` */

DROP TABLE IF EXISTS `h_user_capital`;

CREATE TABLE `h_user_capital` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `total_money` decimal(10,2) DEFAULT '0.00' COMMENT '总资产',
  `extend_money` decimal(10,2) DEFAULT '0.00' COMMENT '推广所得资产',
  `contract_money` decimal(10,2) DEFAULT '0.00' COMMENT '合约收益',
  `ticket_count` int(11) DEFAULT NULL COMMENT '消费用的票',
  `create_time` datetime DEFAULT NULL COMMENT '增加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '增加人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除 :0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户资产表';

/*Table structure for table `h_user_map` */

DROP TABLE IF EXISTS `h_user_map`;

CREATE TABLE `h_user_map` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `leaf_user_id` bigint(20) DEFAULT NULL COMMENT '子级用户id',
  `create_time` datetime DEFAULT NULL COMMENT '增加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '增加人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除 :0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户树关系表';

/*Table structure for table `h_user_pay_info` */

DROP TABLE IF EXISTS `h_user_pay_info`;

CREATE TABLE `h_user_pay_info` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `account_name` varchar(20) DEFAULT NULL COMMENT '账号名称',
  `account` varchar(30) DEFAULT NULL COMMENT '账号',
  `account_type` tinyint(1) DEFAULT NULL COMMENT '账号类型:0支付宝 1微信 2银行卡',
  `create_time` datetime DEFAULT NULL COMMENT '增加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '增加人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '是否删除 :0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
