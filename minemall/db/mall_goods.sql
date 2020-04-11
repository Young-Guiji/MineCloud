/*
Navicat MySQL Data Transfer

Source Server         : 10.203.72.183
Source Server Version : 50646
Source Host           : 10.203.72.183:3306
Source Database       : mall_goods

Target Server Type    : MYSQL
Target Server Version : 50646
File Encoding         : 65001

Date: 2020-04-11 09:11:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pc_mdc_address
-- ----------------------------
DROP TABLE IF EXISTS `pc_mdc_address`;
CREATE TABLE `pc_mdc_address` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `pid` varchar(50) DEFAULT NULL COMMENT '父ID',
  `city_code` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '城市编码',
  `ad_code` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '区域编码',
  `name` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '地址名称',
  `level` tinyint(1) NOT NULL COMMENT '级别（省市区县）',
  `polyline` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '行政区边界坐标点',
  `center` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '城市中心点',
  `created_by` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `created_id` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '最近操作人',
  `updated_id` varchar(20) DEFAULT NULL COMMENT '最后操作人ID',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `I_PARENT_ID` (`pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='4级地址';

-- ----------------------------
-- Records of pc_mdc_address
-- ----------------------------

-- ----------------------------
-- Table structure for pc_mdc_product
-- ----------------------------
DROP TABLE IF EXISTS `pc_mdc_product`;
CREATE TABLE `pc_mdc_product` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `product_code` varchar(20) NOT NULL COMMENT '商品列号',
  `category_id` varchar(32) NOT NULL COMMENT '分类id',
  `name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '商品名称',
  `subtitle` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品副标题',
  `main_image` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '产品主图,url相对地址',
  `sub_images` text CHARACTER SET utf8 COMMENT '图片地址,json格式,扩展用',
  `detail` text CHARACTER SET utf8 COMMENT '商品详情',
  `price` decimal(20,2) NOT NULL COMMENT '价格,单位-元保留两位小数',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  `status` int(6) DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
  `created_by` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `created_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '最近操作人',
  `updated_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '最后操作人ID',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of pc_mdc_product
-- ----------------------------
INSERT INTO `pc_mdc_product` VALUES ('1214027172767887361', '0', '374006077478473728', '100006', '这就是一个测试', '商品的描述', '314169969338228736', '314169969338228736,314169988875296768', '<p><img src=\"http://img.paascloud.net/paascloud/picture/wangEditor/312426781187514368.png\" style=\"max-width:100%;\"><br></p>', '100.00', '99', '0', '超级管理员', null, '2020-01-05 21:33:28', '超级管理员', null, '2020-01-05 21:33:28');
INSERT INTO `pc_mdc_product` VALUES ('1214027636473360385', '0', '374006542639370240', '100006', '这就是一个测试', '商品的描述', '314169969338228736', '314169969338228736,314169988875296768', '<p><img src=\"http://img.paascloud.net/paascloud/picture/wangEditor/312426781187514368.png\" style=\"max-width:100%;\"><br></p>', '100.00', '99', '0', '超级管理员', null, '2020-01-05 21:35:19', '超级管理员', null, '2020-01-05 21:35:19');
INSERT INTO `pc_mdc_product` VALUES ('1214052285219368962', '0', '374031190496186368', '100006', '这就是一个测试', '商品的描述', '314169969338228736', '314169969338228736,314169988875296768', '<p><img src=\"http://img.paascloud.net/paascloud/picture/wangEditor/312426781187514368.png\" style=\"max-width:100%;\"><br></p>', '100.00', '99', '0', '超级管理员', null, '2020-01-05 23:13:15', '超级管理员', null, '2020-01-05 23:13:15');
INSERT INTO `pc_mdc_product` VALUES ('1214052725910695938', '0', '374031632097677312', '100006', '这就是一个测试', '商品的描述', '314169969338228736', '314169969338228736,314169988875296768', '<p><img src=\"http://img.paascloud.net/paascloud/picture/wangEditor/312426781187514368.png\" style=\"max-width:100%;\"><br></p>', '100.00', '99', '0', '超级管理员', null, '2020-01-05 23:15:00', '超级管理员', null, '2020-01-05 23:15:01');
INSERT INTO `pc_mdc_product` VALUES ('26', '0', '26', '100002', 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', 'iPhone 7，现更以红色呈现。', '314168622983421952', '314168622983421952,314168653241131008,314168677257715712,314168716944220160', '<p><img alt=\"10000.jpg\" src=\"http://img.happymmall.com/00bce8d4-e9af-4c8d-b205-e6c75c7e252b.jpg\" width=\"790\" height=\"553\"><br></p><p><img alt=\"20000.jpg\" src=\"http://img.happymmall.com/4a70b4b4-01ee-46af-9468-31e67d0995b8.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"30000.jpg\" src=\"http://img.happymmall.com/0570e033-12d7-49b2-88f3-7a5d84157223.jpg\" width=\"790\" height=\"365\"><br></p><p><img alt=\"40000.jpg\" src=\"http://img.happymmall.com/50515c02-3255-44b9-a829-9e141a28c08a.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"50000.jpg\" src=\"http://img.happymmall.com/c138fc56-5843-4287-a029-91cf3732d034.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"60000.jpg\" src=\"http://img.happymmall.com/c92d1f8a-9827-453f-9d37-b10a3287e894.jpg\" width=\"790\" height=\"525\"><br></p><p><br></p><p><img alt=\"TB24p51hgFkpuFjSspnXXb4qFXa-1776456424.jpg\" src=\"http://img.happymmall.com/bb1511fc-3483-471f-80e5-c7c81fa5e1dd.jpg\" width=\"790\" height=\"375\"><br></p><p><br></p><p><img alt=\"shouhou.jpg\" src=\"http://img.happymmall.com/698e6fbe-97ea-478b-8170-008ad24030f7.jpg\" width=\"750\" height=\"150\"><br></p><p><img alt=\"999.jpg\" src=\"http://img.happymmall.com/ee276fe6-5d79-45aa-8393-ba1d210f9c89.jpg\" width=\"790\" height=\"351\"><br></p>', '6999.00', '9987', '1', '超级管理员', '1', '2017-07-12 14:01:30', '超级管理员', '1', '2018-03-10 11:17:15');
INSERT INTO `pc_mdc_product` VALUES ('27', '0', '27', '100006', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', '送品牌烤箱，五一大促', '314167466638974976', '314167466638974976,314167478794067968,314167500805775360', '<p><img alt=\"miaoshu.jpg\" src=\"http://img.happymmall.com/9c5c74e6-6615-4aa0-b1fc-c17a1eff6027.jpg\" width=\"790\" height=\"444\"><br></p><p><img alt=\"miaoshu2.jpg\" src=\"http://img.happymmall.com/31dc1a94-f354-48b8-a170-1a1a6de8751b.jpg\" width=\"790\" height=\"1441\"><img alt=\"miaoshu3.jpg\" src=\"http://img.happymmall.com/7862594b-3063-4b52-b7d4-cea980c604e0.jpg\" width=\"790\" height=\"1442\"><img alt=\"miaoshu4.jpg\" src=\"http://img.happymmall.com/9a650563-dc85-44d6-b174-d6960cfb1d6a.jpg\" width=\"790\" height=\"1441\"><br></p>', '3299.00', '8848', '1', '超级管理员', '1', '2017-07-12 14:01:30', '超级管理员', '1', '2018-03-10 11:14:48');
INSERT INTO `pc_mdc_product` VALUES ('28', '0', '28', '100012', '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', 'NOVA青春版1999元', '314169253622194176', '314169253622194176,314169275776507904,314169295019974656', '<p><img alt=\"11TB2fKK3cl0kpuFjSsziXXa.oVXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/5c2d1c6d-9e09-48ce-bbdb-e833b42ff664.jpg\" width=\"790\" height=\"966\"><img alt=\"22TB2YP3AkEhnpuFjSZFpXXcpuXXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/9a10b877-818f-4a27-b6f7-62887f3fb39d.jpg\" width=\"790\" height=\"1344\"><img alt=\"33TB2Yyshk.hnpuFjSZFpXXcpuXXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/7d7fbd69-a3cb-4efe-8765-423bf8276e3e.jpg\" width=\"790\" height=\"700\"><img alt=\"TB2diyziB8kpuFjSspeXXc7IpXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/1d7160d2-9dba-422f-b2a0-e92847ba6ce9.jpg\" width=\"790\" height=\"393\"><br></p>', '1999.00', '9981', '1', '超级管理员', '1', '2017-07-12 14:01:30', '超级管理员', '1', '2018-03-10 11:18:20');
INSERT INTO `pc_mdc_product` VALUES ('29', '0', '29', '100008', 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '门店机型 德邦送货', '314169831807000576', '314169831807000576,314169860747698176,314169878858702848', '<p><img alt=\"1TB2WLZrcIaK.eBjSspjXXXL.XXa_!!2114960396.jpg\" src=\"http://img.happymmall.com/ffcce953-81bd-463c-acd1-d690b263d6df.jpg\" width=\"790\" height=\"920\"><img alt=\"2TB2zhOFbZCO.eBjSZFzXXaRiVXa_!!2114960396.jpg\" src=\"http://img.happymmall.com/58a7bd25-c3e7-4248-9dba-158ef2a90e70.jpg\" width=\"790\" height=\"1052\"><img alt=\"3TB27mCtb7WM.eBjSZFhXXbdWpXa_!!2114960396.jpg\" src=\"http://img.happymmall.com/2edbe9b3-28be-4a8b-a9c3-82e40703f22f.jpg\" width=\"790\" height=\"820\"><br></p>', '4299.00', '9992', '1', '超级管理员', '1', '2017-07-12 14:01:30', '超级管理员', '1', '2018-03-10 11:19:31');
INSERT INTO `pc_mdc_product` VALUES ('312415946738247680', '0', '312415946721470464', '100006', '1', '2', '314170064850919424', '314170064850919424,315163180122775552', '<p><br></p>', '3.00', '1', '1', '超级管理员', '1', '2018-03-08 01:14:43', 'xxxx', '305120257926767617', '2018-03-14 19:04:27');
INSERT INTO `pc_mdc_product` VALUES ('312426496268445696', '0', '312426496260057088', '100006', '这就是一个商品', '商品的描述', '314169969338228736', '314169969338228736,314169988875296768', '<p><img src=\"http://img.paascloud.net/paascloud/picture/wangEditor/312426781187514368.png\" style=\"max-width:100%;\"><br></p>', '1.00', '1', '2', '超级管理员', '1', '2018-03-08 01:35:41', '超级管理员', '1', '2018-03-10 11:19:43');

-- ----------------------------
-- Table structure for pc_mdc_product_category
-- ----------------------------
DROP TABLE IF EXISTS `pc_mdc_product_category`;
CREATE TABLE `pc_mdc_product_category` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `category_code` varchar(100) NOT NULL DEFAULT '' COMMENT '类别序列号',
  `img_id` bigint(20) DEFAULT NULL COMMENT '首图的流水号',
  `pid` varchar(20) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `created_by` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `created_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '最近操作人',
  `updated_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '最后操作人ID',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- ----------------------------
-- Records of pc_mdc_product_category
-- ----------------------------
INSERT INTO `pc_mdc_product_category` VALUES ('100001', '0', '100001', null, '0', '家用电器', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100002', '0', '100002', null, '0', '数码3C', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100003', '0', '100003', null, '0', '服装箱包', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100004', '0', '100004', null, '0', '食品生鲜', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100005', '2', '100005', null, '0', '酒水饮料', '1', '1', 'admin', '1', '2017-07-12 14:01:10', '超级管理员', '1', '2018-03-05 23:22:59');
INSERT INTO `pc_mdc_product_category` VALUES ('100006', '0', '100006', null, '100001', '冰箱', '1', '2', 'admin', '1', '2017-07-12 14:01:10', '超级管理员', '1', '2018-03-05 23:14:54');
INSERT INTO `pc_mdc_product_category` VALUES ('100007', '0', '100007', null, '100001', '电视', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100008', '0', '100008', null, '100001', '洗衣机', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100009', '0', '100009', null, '100001', '空调', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100010', '0', '100010', null, '100001', '电热水器', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100011', '0', '100011', null, '100002', '电脑', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100012', '0', '100012', null, '100002', '手机', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100013', '0', '100013', null, '100002', '平板电脑', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100014', '0', '100014', null, '100002', '数码相机', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100015', '0', '100015', null, '100002', '3C配件', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100016', '0', '100016', null, '100003', '女装', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100017', '0', '100017', null, '100003', '帽子', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100018', '0', '100018', null, '100003', '旅行箱', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100019', '0', '100019', null, '100003', '手提包', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100020', '0', '100020', null, '100003', '保暖内衣', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100021', '0', '100021', null, '100004', '零食', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100022', '0', '100022', null, '100004', '生鲜', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100023', '0', '100023', null, '100004', '半成品菜', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100024', '0', '100024', null, '100004', '速冻食品', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100025', '0', '100025', null, '100004', '进口食品', '1', '1', 'admin', '1', '2017-07-12 14:01:10', 'admin', '1', '2017-07-12 14:01:10');
INSERT INTO `pc_mdc_product_category` VALUES ('100026', '2', '100026', null, '100005', '白酒', '1', '1', 'admin', '1', '2017-07-12 14:01:10', '超级管理员', '1', '2018-03-05 23:22:59');
INSERT INTO `pc_mdc_product_category` VALUES ('100027', '2', '100027', null, '100005', '红酒', '1', '1', 'admin', '1', '2017-07-12 14:01:10', '超级管理员', '1', '2018-03-05 23:22:59');
INSERT INTO `pc_mdc_product_category` VALUES ('100028', '4', '100028', null, '100005', '饮料', '1', '1', 'admin', '1', '2017-07-12 14:01:10', '超级管理员', '1', '2018-03-05 23:23:12');
INSERT INTO `pc_mdc_product_category` VALUES ('100029', '2', '100029', null, '100005', '调制鸡尾酒', '1', '1', 'admin', '1', '2017-07-12 14:01:10', '超级管理员', '1', '2018-03-05 23:22:59');
INSERT INTO `pc_mdc_product_category` VALUES ('100030', '2', '100030', null, '100005', '进口洋酒', '1', '1', 'admin', '1', '2017-07-12 14:01:10', '超级管理员', '1', '2018-03-05 23:22:59');

-- ----------------------------
-- Table structure for pc_mq_message_data
-- ----------------------------
DROP TABLE IF EXISTS `pc_mq_message_data`;
CREATE TABLE `pc_mq_message_data` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `message_key` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '消息key',
  `message_queue` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT 'topic',
  `message_tag` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT 'tag',
  `message_body` longtext CHARACTER SET utf8 COMMENT '消息内容',
  `message_type` int(11) DEFAULT '10' COMMENT '消息类型: 10 - 生产者 ; 20 - 消费者',
  `delay_level` int(11) DEFAULT '0' COMMENT '延时级别 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h',
  `order_type` int(11) DEFAULT '0' COMMENT '顺序类型 0有序 1无序',
  `status` int(11) DEFAULT '10' COMMENT '消息状态',
  `created_by` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `created_id` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '最近操作人',
  `updated_id` varchar(20) DEFAULT NULL COMMENT '最后操作人ID',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `yn` int(11) DEFAULT '0' COMMENT '是否删除 -0 未删除 -1 已删除',
  PRIMARY KEY (`id`),
  KEY `idx_message_key` (`message_key`) USING BTREE,
  KEY `idx_created_time` (`created_time`) USING BTREE,
  KEY `idx_updated_time` (`updated_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息记录表';

-- ----------------------------
-- Records of pc_mq_message_data
-- ----------------------------
INSERT INTO `pc_mq_message_data` VALUES ('374006077574942720', '0', 'PRODUCT_PIC_QUEUE_UPDATE_ATTACHMENT_374006077478473728--1245478273', 'PRODUCT_PIC_QUEUE', 'UPDATE_ATTACHMENT', '{\"refNo\":\"374006077478473728\",\"attachmentIdList\":[\"314169969338228736\",\"314169988875296768\"]}', '10', '0', '1', '10', '超级管理员', null, '2020-01-05 21:33:28', '超级管理员', null, '2020-01-05 21:33:28', '0');
INSERT INTO `pc_mq_message_data` VALUES ('374006542706479104', '0', 'PRODUCT_PIC_QUEUE_UPDATE_ATTACHMENT_374006542639370240-752684838', 'PRODUCT_PIC_QUEUE', 'UPDATE_ATTACHMENT', '{\"refNo\":\"374006542639370240\",\"attachmentIdList\":[\"314169969338228736\",\"314169988875296768\"]}', '10', '0', '1', '10', '超级管理员', null, '2020-01-05 21:35:19', '超级管理员', null, '2020-01-05 21:35:19', '0');
INSERT INTO `pc_mq_message_data` VALUES ('374031190554906624', '0', 'PRODUCT_PIC_QUEUE_UPDATE_ATTACHMENT_374031190496186368-1402181194', 'PRODUCT_PIC_QUEUE', 'UPDATE_ATTACHMENT', '{\"refNo\":\"374031190496186368\",\"attachmentIdList\":[\"314169969338228736\",\"314169988875296768\"]}', '10', '0', '1', '10', '超级管理员', null, '2020-01-05 23:13:15', '超级管理员', null, '2020-01-05 23:13:15', '0');
INSERT INTO `pc_mq_message_data` VALUES ('374031632118648832', '0', 'PRODUCT_PIC_QUEUE_UPDATE_ATTACHMENT_374031632097677312--1420085214', 'PRODUCT_PIC_QUEUE', 'UPDATE_ATTACHMENT', '{\"refNo\":\"374031632097677312\",\"attachmentIdList\":[\"314169969338228736\",\"314169988875296768\"]}', '10', '0', '1', '10', '超级管理员', null, '2020-01-05 23:15:00', '超级管理员', null, '2020-01-05 23:15:00', '0');
