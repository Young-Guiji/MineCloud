/*
Navicat MySQL Data Transfer

Source Server         : 10.203.72.183
Source Server Version : 50646
Source Host           : 10.203.72.183:3306
Source Database       : sc_gateway

Target Server Type    : MYSQL
Target Server Version : 50646
File Encoding         : 65001

Date: 2020-04-11 09:12:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route`;
CREATE TABLE `gateway_route` (
  `id` varchar(20) NOT NULL COMMENT 'id',
  `route_id` varchar(100) NOT NULL COMMENT '路由id',
  `uri` varchar(100) NOT NULL COMMENT 'uri路径',
  `predicates` text NOT NULL COMMENT '判定器',
  `filters` text COMMENT '过滤器',
  `orders` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` varchar(1) DEFAULT 'Y' COMMENT '状态：Y-有效，N-无效',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_gateway_routes_uri` (`uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网关路由表';

-- ----------------------------
-- Records of gateway_route
-- ----------------------------
INSERT INTO `gateway_route` VALUES ('101', 'authorization-server', 'lb://authorization-server:8000', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/authorization-server/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', '100', '授权认证服务网关注册', 'Y', '2019-10-29 16:26:25', '2019-10-29 16:26:25', 'system', 'system');
INSERT INTO `gateway_route` VALUES ('102', 'authentication-server', 'lb://authentication-server:8001', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/authentication-server/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', '100', '签权服务网关注册', 'Y', '2019-10-29 16:26:25', '2019-10-29 16:26:25', 'system', 'system');
INSERT INTO `gateway_route` VALUES ('1197420912727560194', 'mall-goods', 'lb://mall-goods:31002', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/mall-goods/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', '0', '商品服务路由', 'Y', '2019-11-21 15:46:07', '2019-11-21 15:46:07', 'system', 'system');
INSERT INTO `gateway_route` VALUES ('1197422703422738434', 'mall-order', 'lb://mall-order:31003', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/mall-order/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', '0', '订单服务路由', 'Y', '2019-11-21 15:53:14', '2019-11-21 15:53:14', 'system', 'system');
INSERT INTO `gateway_route` VALUES ('1197423757208064001', 'mall-user', 'lb://mall-user:31001', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/mall-user/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', '0', '用户服务路由', 'Y', '2019-11-21 15:57:25', '2019-11-21 15:57:25', 'system', 'system');
