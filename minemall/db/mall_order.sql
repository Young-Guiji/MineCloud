/*
Navicat MySQL Data Transfer

Source Server         : 10.203.72.183
Source Server Version : 50646
Source Host           : 10.203.72.183:3306
Source Database       : mall_order

Target Server Type    : MYSQL
Target Server Version : 50646
File Encoding         : 65001

Date: 2020-04-11 09:11:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pc_mall_cart
-- ----------------------------
DROP TABLE IF EXISTS `pc_mall_cart`;
CREATE TABLE `pc_mall_cart` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `product_id` varchar(20) NOT NULL COMMENT '商品ID',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `checked` int(11) DEFAULT NULL COMMENT '是否选择,1=已勾选,0=未勾选',
  `created_by` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `created_id` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '最近操作人',
  `updated_id` varchar(20) DEFAULT NULL COMMENT '最后操作人ID',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- ----------------------------
-- Records of pc_mall_cart
-- ----------------------------

-- ----------------------------
-- Table structure for pc_mall_order
-- ----------------------------
DROP TABLE IF EXISTS `pc_mall_order`;
CREATE TABLE `pc_mall_order` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `order_no` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '订单号',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `shipping_id` varchar(20) DEFAULT NULL COMMENT '收货人ID',
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `payment_type` int(4) NOT NULL DEFAULT '1' COMMENT '支付类型,1-在线支付',
  `postage` int(10) DEFAULT '0' COMMENT '运费,单位是元',
  `status` int(10) DEFAULT '10' COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `created_by` varchar(20) CHARACTER SET utf32 DEFAULT '' COMMENT '创建人',
  `created_id` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '最近操作人',
  `updated_id` varchar(20) DEFAULT NULL COMMENT '最后操作人ID',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of pc_mall_order
-- ----------------------------

-- ----------------------------
-- Table structure for pc_mall_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `pc_mall_order_detail`;
CREATE TABLE `pc_mall_order_detail` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `detail_no` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '订单明细序列号',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `order_no` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '订单号',
  `product_id` varchar(20) DEFAULT NULL COMMENT '商品ID',
  `product_name` varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '商品名称',
  `product_image` varchar(500) CHARACTER SET utf8 DEFAULT '' COMMENT '商品图片地址',
  `current_unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
  `created_by` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '创建人',
  `created_id` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '最近操作人',
  `updated_id` varchar(20) DEFAULT NULL COMMENT '最后操作人ID',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_user_id_index` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- ----------------------------
-- Records of pc_mall_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for pc_mall_shipping
-- ----------------------------
DROP TABLE IF EXISTS `pc_mall_shipping`;
CREATE TABLE `pc_mall_shipping` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户id',
  `receiver_name` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '收货姓名',
  `receiver_phone_no` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '收货固定电话',
  `receiver_mobile_no` varchar(11) CHARACTER SET utf8 DEFAULT '' COMMENT '收货移动电话',
  `province_id` varchar(32) DEFAULT NULL COMMENT '收货人省ID',
  `province_name` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '省份',
  `city_id` varchar(20) DEFAULT NULL COMMENT '收货人城市ID',
  `city_name` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '收货人城市名称',
  `district_name` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '区/县',
  `district_id` varchar(32) CHARACTER SET utf8 DEFAULT '' COMMENT '区/县 编码',
  `street_id` varchar(32) CHARACTER SET utf8 DEFAULT '' COMMENT '街道ID',
  `street_name` varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '接到名称',
  `detail_address` varchar(200) CHARACTER SET utf8 DEFAULT '' COMMENT '详细地址',
  `receiver_zip_code` varchar(6) CHARACTER SET utf8 DEFAULT '' COMMENT '邮编',
  `default_address` int(1) DEFAULT '0' COMMENT '默认地址',
  `created_by` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '创建人',
  `created_id` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '最近操作人',
  `updated_id` varchar(20) DEFAULT NULL COMMENT '最后操作人ID',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货人信息表';

-- ----------------------------
-- Records of pc_mall_shipping
-- ----------------------------
INSERT INTO `pc_mall_shipping` VALUES ('29', '0', '101', '吉利', '13800138000', '13800138000', null, '北京', null, '北京', '海淀区', '', '', '背二街', '海淀区中关村', '100000', '1', 'admin', '1', '2017-07-12 14:01:35', 'admin', '1', '2017-07-12 14:01:35');
INSERT INTO `pc_mall_shipping` VALUES ('4', '0', '101', 'jack', '13800138000', '18688888888', null, '北京', null, '北京市', '海淀区', '', '', '西二街', '中关村', '100000', '0', 'admin', '1', '2017-07-12 14:01:35', 'admin', '1', '2017-07-12 14:01:35');
INSERT INTO `pc_mall_shipping` VALUES ('7', '0', '101', 'Rosen', '13800138000', '13800138000', null, '北京', null, '北京', null, '', '', '东二街', '中关村', '100000', '1', 'admin', '1', '2017-07-12 14:01:35', 'admin', '1', '2017-07-12 14:01:35');

DROP TABLE IF EXISTS `pc_mall_pay_info`;
CREATE TABLE `pc_mall_pay_info` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `order_no` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '订单号',
  `pay_platform` int(11) DEFAULT '0' COMMENT '支付平台:1-支付宝,2-微信',
  `platform_number` varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '支付宝支付流水号',
  `platform_status` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '支付宝支付状态',
  `created_by` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '创建人',
  `created_id` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '最近操作人',
  `updated_id` varchar(20) DEFAULT NULL COMMENT '最后操作人ID',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `order_no_user_id_index` (`order_no`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付信息表';
