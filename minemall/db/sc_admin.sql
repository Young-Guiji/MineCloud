/*
Navicat MySQL Data Transfer

Source Server         : 10.203.72.183
Source Server Version : 50646
Source Host           : 10.203.72.183:3306
Source Database       : sc_admin

Target Server Type    : MYSQL
Target Server Version : 50646
File Encoding         : 65001

Date: 2020-04-11 09:12:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` varchar(20) NOT NULL COMMENT 'id',
  `parent_id` varchar(20) NOT NULL COMMENT '用户组父id',
  `name` varchar(200) DEFAULT NULL COMMENT '用户组名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `deleted` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组表';

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('101', '-1', '总公司', '总公司', 'N', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `groups` VALUES ('102', '101', '上海分公司', '上海分公司', 'N', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `groups` VALUES ('103', '102', '研发部门', '负责产品研发', 'N', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `groups` VALUES ('104', '102', '产品部门', '负责产品设计', 'N', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `groups` VALUES ('105', '102', '运营部门', '负责公司产品运营', 'N', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `groups` VALUES ('106', '102', '销售部门', '负责公司产品销售', 'N', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `groups` VALUES ('107', '101', '北京分公司', '北京分公司', 'N', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(20) NOT NULL COMMENT 'id',
  `parent_id` varchar(20) NOT NULL COMMENT '父菜单id',
  `type` varchar(100) DEFAULT NULL COMMENT '菜单类型',
  `href` varchar(200) DEFAULT NULL COMMENT '菜单路径',
  `icon` varchar(200) DEFAULT NULL COMMENT '菜单图标',
  `name` varchar(200) DEFAULT NULL COMMENT '菜单名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `order_num` int(11) DEFAULT NULL COMMENT '创建时间',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('101', '-1', 'MENU', '/admin', 'setting', '系统管理', '系统设置管理', '0', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `menu` VALUES ('102', '101', 'MENU', '/admin/users', 'fa-user', '用户管理', '用户新增，修改，查看，删除', '10', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `menu` VALUES ('103', '101', 'MENU', '/admin/menus', 'category', '菜单管理', '菜单新增，修改，删除', '20', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` varchar(20) NOT NULL COMMENT 'id',
  `name` varchar(200) DEFAULT NULL COMMENT '岗位名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `deleted` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位表';

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('101', '首席执行官', '公司CEO，负责公司整体运转', 'N', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `position` VALUES ('102', '首席运营官', '公司COO，负责公司整体运营', 'N', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `position` VALUES ('103', '首席技术官', '公司CTO，负责公司整体运营', 'N', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` varchar(20) NOT NULL COMMENT '资源id',
  `code` varchar(100) NOT NULL COMMENT '资源code',
  `type` varchar(100) NOT NULL COMMENT '资源类型',
  `name` varchar(200) NOT NULL COMMENT '资源名称',
  `url` varchar(200) NOT NULL COMMENT '资源url',
  `method` varchar(20) NOT NULL COMMENT '资源方法',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_resource_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('101', 'user_manager:btn_add', 'button', '新增用户', '/user', 'POST', '新增用户功能', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('102', 'user_manager:btn_edit', 'button', '编辑用户', '/user/{id}', 'PUT', '编辑用户功能', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('103', 'user_manager:btn_del', 'button', '删除用户', '/user/{id}', 'DELETE', '根据用户id删除用户', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('104', 'user_manager:view', 'url', '查看用户', '/user/{id}', 'GET', '根据用户id获取用户', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('105', 'user_manager:query', 'url', '搜索用户', '/user/conditions', 'POST', '根据条件查询用户', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('106', 'user_manager:get', 'url', '获取用户', '/user', 'GET', '根据唯一标识获取用户', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('1204953492494508033', 'car_manager:addProduct', 'url', '购物车添加商品', '/cart/addProduct/{productId}/{count}', 'POST', '购物车添加商品', '2019-12-12 10:37:54', '2019-12-12 10:37:54', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `resource` VALUES ('1204953652012277761', 'car_manager:updateProduct', 'url', '购物车更新商品', '/cart/updateProduct/{productId}/{count}', 'POST', '购物车更新商品', '2019-12-12 10:38:32', '2019-12-12 10:38:32', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `resource` VALUES ('1204953769775751170', 'car_manager:deleteProduct', 'url', '购物车删除商品', '/cart/deleteProduct/{productIds}', 'POST', '购物车删除商品', '2019-12-12 10:39:00', '2019-12-12 10:39:00', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `resource` VALUES ('1204953875539320833', 'car_manager:selectAll', 'url', '购物车全选商品', '/cart/selectAllProduct', 'POST', '购物车全选商品', '2019-12-12 10:39:26', '2019-12-12 10:39:26', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `resource` VALUES ('1204954013502562306', 'car_manager:unSelectAll', 'url', '购物车反选全部商品', '/cart/unSelectAllProduct', 'POST', '购物车反选全部商品', '2019-12-12 10:39:58', '2019-12-12 10:39:58', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `resource` VALUES ('1204954146621382657', 'car_manager:select', 'url', '选中商品', '/cart/selectProduct/{productId}', 'POST', '选中商品', '2019-12-12 10:40:30', '2019-12-12 10:40:30', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `resource` VALUES ('1204954327051952130', 'car_manager:unSelect', 'url', '反选商品', '/cart/unSelectProduct/{productId}', 'POST', '反选商品', '2019-12-12 10:41:13', '2019-12-12 10:41:13', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `resource` VALUES ('201', 'role_manager:btn_add', 'url', '新增角色', '/role', 'POST', '新增角色功能', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('202', 'role_manager:btn_edit', 'url', '编辑角色', '/role/{id}', 'PUT', '编辑角色功能', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('203', 'role_manager:btn_del', 'url', '删除角色', '/role/{id}', 'DELETE', '根据id删除角色', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('204', 'role_manager:view', 'url', '查看角色', '/role/{id}', 'GET', '根据id获取角色', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('205', 'role_manager:user', 'url', '根据用户id查询角色', '/role/user/{userId}', 'GET', '根据用户id获取用户所拥有的角色集', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('206', 'role_manager:all', 'url', '获取所有角色', '/role/all', 'GET', '获取所有角色', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('207', 'role_manager:query', 'url', '搜索角色', '/role/conditions', 'POST', '根据条件查询角色', '2019-10-29 16:27:59', '2019-10-29 16:27:59', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('301', 'car_manager:merge', 'url', '合并购物车', '/cart/mergeUserCart', 'POST', '用户登录成功，将购物车信息存储', '2019-12-10 08:56:47', '2019-12-10 08:56:52', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('302', 'ship_manager:addr_query', 'url', '查询用户地址', '/shipping/queryUserShippingListWithPage', 'POST', '分页查询用户地址', '2019-12-11 10:14:40', '2019-12-11 10:14:43', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('303', 'order_manager:order_query', 'url', '查询用户订单', '/order/queryUserOrderListWithPage', 'POST', '分页查询用户订单', '2019-12-11 14:04:20', '2019-12-11 14:04:22', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('304', 'order_manager:car_query', 'url', '查询用户购物车商品', '/order/getOrderCartProduct', 'POST', '查询用户购物车商品', '2019-12-11 14:20:26', '2019-12-11 14:20:30', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('305', 'user_manager:user_query', 'url', '查看用户信息', '/user/getInformation', 'POST', '根据ID查看用户信息', '2019-12-11 15:08:15', '2019-12-11 15:08:18', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('306', 'user_manager:user_update', 'url', '修改用户信息', '/user/updateInformation', 'POST', '根据ID修改用户信息', '2019-12-11 15:09:52', '2019-12-11 15:09:55', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('307', 'order_manager:order_create', 'url', '创建订单', '/order/createOrderDoc/{shippingId}', 'POST', '创建订单', '2020-01-21 13:28:46', '2020-01-21 13:28:48', '101', 'admin', '101', 'admin', '0');
INSERT INTO `resource` VALUES ('308', 'order_manager:order_status', 'url', '查询订单状态', '/order/queryOrderPayStatus/{orderNo}', 'POST', '查询订单状态', '2020-01-21 15:00:47', '2020-01-21 15:00:50', '101', 'admin', '101', 'admin', '0');

-- ----------------------------
-- Table structure for role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_relation`;
CREATE TABLE `role_menu_relation` (
  `id` varchar(20) NOT NULL COMMENT 'id',
  `menu_id` varchar(20) NOT NULL COMMENT '菜单id',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关系表';

-- ----------------------------
-- Records of role_menu_relation
-- ----------------------------
INSERT INTO `role_menu_relation` VALUES ('101', '101', '101', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `role_menu_relation` VALUES ('102', '102', '101', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `role_menu_relation` VALUES ('103', '103', '101', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `role_menu_relation` VALUES ('104', '101', '102', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `role_menu_relation` VALUES ('105', '102', '102', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `role_menu_relation` VALUES ('106', '101', '103', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `role_menu_relation` VALUES ('107', '102', '103', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `role_menu_relation` VALUES ('108', '103', '103', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');

-- ----------------------------
-- Table structure for role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_resource_relation`;
CREATE TABLE `role_resource_relation` (
  `id` varchar(20) NOT NULL COMMENT '关系id',
  `resource_id` varchar(20) NOT NULL COMMENT '角色id',
  `role_id` varchar(20) NOT NULL COMMENT '资源id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和资源关系表';

-- ----------------------------
-- Records of role_resource_relation
-- ----------------------------
INSERT INTO `role_resource_relation` VALUES ('101', '101', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('102', '102', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('103', '103', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('104', '104', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('105', '104', '102', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('106', '101', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('107', '102', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('108', '103', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('109', '104', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('110', '105', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('111', '105', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('112', '106', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('113', '106', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('1204965485003710466', '1204953492494508033', '101', '2019-12-12 11:25:33', '2019-12-12 11:25:33', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `role_resource_relation` VALUES ('1204965485112762370', '1204953875539320833', '101', '2019-12-12 11:25:33', '2019-12-12 11:25:33', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `role_resource_relation` VALUES ('1204965485112762371', '1204954013502562306', '101', '2019-12-12 11:25:33', '2019-12-12 11:25:33', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `role_resource_relation` VALUES ('1204965485112762372', '1204954146621382657', '101', '2019-12-12 11:25:33', '2019-12-12 11:25:33', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `role_resource_relation` VALUES ('1204965485121150977', '1204954327051952130', '101', '2019-12-12 11:25:33', '2019-12-12 11:25:33', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `role_resource_relation` VALUES ('1204965485129539585', '1204953652012277761', '101', '2019-12-12 11:25:33', '2019-12-12 11:25:33', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `role_resource_relation` VALUES ('1204965485129539586', '1204953769775751170', '101', '2019-12-12 11:25:33', '2019-12-12 11:25:33', null, '超级管理员', null, '超级管理员', '0');
INSERT INTO `role_resource_relation` VALUES ('201', '201', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('202', '202', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('203', '203', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('204', '204', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('205', '205', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('206', '206', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('207', '207', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('210', '204', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('211', '205', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('212', '207', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('213', '203', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('214', '201', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('215', '202', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('216', '206', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `role_resource_relation` VALUES ('217', '301', '101', '2019-12-10 09:01:15', '2019-12-10 09:01:18', null, 'admin', null, 'admin', '0');
INSERT INTO `role_resource_relation` VALUES ('218', '302', '101', '2019-12-11 10:17:04', '2019-12-11 10:17:06', null, 'admin', null, 'admin', '0');
INSERT INTO `role_resource_relation` VALUES ('219', '303', '101', '2019-12-11 14:09:10', '2019-12-11 14:09:12', null, 'admin', null, 'admin', '0');
INSERT INTO `role_resource_relation` VALUES ('220', '304', '101', '2019-12-11 14:21:45', '2019-12-11 14:21:48', null, 'admin', null, 'admin', '0');
INSERT INTO `role_resource_relation` VALUES ('221', '305', '101', '2019-12-11 15:10:30', '2019-12-11 15:10:31', null, 'admin', null, 'admin', '0');
INSERT INTO `role_resource_relation` VALUES ('222', '306', '101', '2019-12-11 15:10:46', '2019-12-11 15:10:48', null, 'admin', null, 'admin', '0');
INSERT INTO `role_resource_relation` VALUES ('223', '307', '101', '2020-01-21 13:29:15', '2020-01-21 13:29:18', null, 'admin', null, 'admin', '0');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` varchar(20) NOT NULL COMMENT '角色id',
  `code` varchar(100) NOT NULL COMMENT '角色code',
  `name` varchar(200) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('101', 'ADMIN', '超级管理员', '公司IT总负责人', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `roles` VALUES ('102', 'FIN', '财务', '财务', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `roles` VALUES ('103', 'IT', 'IT', 'IT角色', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');

-- ----------------------------
-- Table structure for user_group_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_group_relation`;
CREATE TABLE `user_group_relation` (
  `id` varchar(20) NOT NULL COMMENT 'id',
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `group_id` varchar(20) NOT NULL COMMENT '用户组id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和组关系表';

-- ----------------------------
-- Records of user_group_relation
-- ----------------------------
INSERT INTO `user_group_relation` VALUES ('101', '101', '101', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `user_group_relation` VALUES ('102', '102', '101', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `group_id` bigint(20) DEFAULT NULL COMMENT '组织流水号',
  `group_name` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '组织名称',
  `log_type` varchar(10) CHARACTER SET utf8 DEFAULT '' COMMENT '日志类型',
  `log_name` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '日志类型名称',
  `action_id` bigint(20) DEFAULT NULL COMMENT '权限ID',
  `action_code` varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '权限编码',
  `action_name` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '权限名称',
  `os` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '操作系统',
  `browser` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '浏览器类型',
  `ip` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT 'IP地址',
  `location` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '登录位置',
  `mac` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '物理地址',
  `description` varchar(1000) CHARACTER SET utf8 DEFAULT '' COMMENT '详细描述',
  `request_data` varchar(4000) CHARACTER SET utf8 DEFAULT '' COMMENT '请求参数',
  `request_url` varchar(2000) CHARACTER SET utf8 DEFAULT '' COMMENT '请求地址',
  `response_data` varchar(4000) CHARACTER SET utf8 DEFAULT '' COMMENT '响应结果',
  `class_name` varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '类名',
  `method_name` varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '方法名',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `excute_time` int(11) DEFAULT NULL COMMENT '耗时,秒',
  `created_by` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '创建人',
  `created_id` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '最近操作人',
  `updated_id` varchar(20) DEFAULT NULL COMMENT '最后操作人ID',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1219493291138064386 DEFAULT CHARSET=utf8mb4 COMMENT='日志表';

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES ('1203957650803437570', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'system', null, '2019-12-09 16:40:47', '', null, '2019-12-09 16:40:46');
INSERT INTO `user_log` VALUES ('1204207048389148674', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'system', null, '2019-12-10 09:11:48', '', null, '2019-12-10 09:11:47');
INSERT INTO `user_log` VALUES ('1204240218840944642', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'system', null, '2019-12-10 11:23:37', '', null, '2019-12-10 11:23:36');
INSERT INTO `user_log` VALUES ('1204277934534217730', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'system', null, '2019-12-10 13:53:29', '', null, '2019-12-10 13:53:28');
INSERT INTO `user_log` VALUES ('1204309357295075330', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'system', null, '2019-12-10 15:58:20', '', null, '2019-12-10 15:58:20');
INSERT INTO `user_log` VALUES ('1204582289560870913', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'admin', null, '2019-12-11 10:02:52', '', null, '2019-12-11 10:02:52');
INSERT INTO `user_log` VALUES ('1204642366036758529', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'admin', null, '2019-12-11 14:01:36', '', null, '2019-12-11 14:01:35');
INSERT INTO `user_log` VALUES ('1204673005158940674', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'admin', null, '2019-12-11 16:03:21', '', null, '2019-12-11 16:03:20');
INSERT INTO `user_log` VALUES ('1204931331910295554', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'admin', null, '2019-12-12 09:09:51', '', null, '2019-12-12 09:09:50');
INSERT INTO `user_log` VALUES ('1204965727207989250', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'admin', null, '2019-12-12 11:26:31', '', null, '2019-12-12 11:26:31');
INSERT INTO `user_log` VALUES ('1210029788627001346', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'admin', null, '2019-12-25 20:49:17', '', null, '2019-12-26 10:49:16');
INSERT INTO `user_log` VALUES ('1211479568964165634', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-29 20:50:11', '2019-12-29 20:50:11', '201', '超级管理员', '101', '2019-12-29 20:50:12', '超级管理员', '101', '2019-12-29 20:50:12');
INSERT INTO `user_log` VALUES ('1211480039346970626', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-29 20:52:04', '2019-12-29 20:52:04', '24', '超级管理员', '101', '2019-12-29 20:52:04', '超级管理员', '101', '2019-12-29 20:52:04');
INSERT INTO `user_log` VALUES ('1211480954237882369', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-29 20:55:41', '2019-12-29 20:55:41', '171', '超级管理员', '101', '2019-12-29 20:55:42', '超级管理员', '101', '2019-12-29 20:55:42');
INSERT INTO `user_log` VALUES ('1211486184908263426', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-29 21:16:15', '2019-12-29 21:16:29', '13235', '超级管理员', '101', '2019-12-29 21:16:29', '超级管理员', '101', '2019-12-29 21:16:29');
INSERT INTO `user_log` VALUES ('1211487195232210946', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-29 21:20:24', '2019-12-29 21:20:28', '3933', '超级管理员', '101', '2019-12-29 21:20:28', '超级管理员', '101', '2019-12-29 21:20:28');
INSERT INTO `user_log` VALUES ('1211487748754509825', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-29 21:22:42', '2019-12-29 21:22:42', '30', '超级管理员', '101', '2019-12-29 21:22:42', '超级管理员', '101', '2019-12-29 21:22:42');
INSERT INTO `user_log` VALUES ('1211517315984773122', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-29 23:20:10', '2019-12-29 23:20:11', '181', '超级管理员', '101', '2019-12-29 23:20:11', '超级管理员', '101', '2019-12-29 23:20:11');
INSERT INTO `user_log` VALUES ('1211521376867962881', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-29 23:36:19', '2019-12-29 23:36:19', '152', '超级管理员', '101', '2019-12-29 23:36:20', '超级管理员', '101', '2019-12-29 23:36:20');
INSERT INTO `user_log` VALUES ('1211811626232172545', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-30 18:49:40', '2019-12-30 18:49:40', '346', '超级管理员', '101', '2019-12-30 18:49:41', '超级管理员', '101', '2019-12-30 18:49:41');
INSERT INTO `user_log` VALUES ('1211814689756008449', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-30 19:01:51', '2019-12-30 19:01:51', '79', '超级管理员', '101', '2019-12-30 19:01:51', '超级管理员', '101', '2019-12-30 19:01:51');
INSERT INTO `user_log` VALUES ('1211817349565501441', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-30 19:12:25', '2019-12-30 19:12:25', '84', '超级管理员', '101', '2019-12-30 19:12:25', '超级管理员', '101', '2019-12-30 19:12:25');
INSERT INTO `user_log` VALUES ('1211827915608674305', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-30 19:54:23', '2019-12-30 19:54:24', '313', '超级管理员', '101', '2019-12-30 19:54:24', '超级管理员', '101', '2019-12-30 19:54:24');
INSERT INTO `user_log` VALUES ('1211831430036967426', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-30 20:08:22', '2019-12-30 20:08:22', '283', '超级管理员', '101', '2019-12-30 20:08:22', '超级管理员', '101', '2019-12-30 20:08:22');
INSERT INTO `user_log` VALUES ('1211831809747308545', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-30 20:09:52', '2019-12-30 20:09:52', '63', '超级管理员', '101', '2019-12-30 20:09:53', '超级管理员', '101', '2019-12-30 20:09:53');
INSERT INTO `user_log` VALUES ('1211879972805922818', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-30 23:21:14', '2019-12-30 23:21:15', '761', '超级管理员', '101', '2019-12-30 23:21:16', '超级管理员', '101', '2019-12-30 23:21:16');
INSERT INTO `user_log` VALUES ('1211881064088326145', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-30 23:25:35', '2019-12-30 23:25:35', '295', '超级管理员', '101', '2019-12-30 23:25:36', '超级管理员', '101', '2019-12-30 23:25:36');
INSERT INTO `user_log` VALUES ('1211892847922499585', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-31 00:12:03', '2019-12-31 00:12:25', '21229', '超级管理员', '101', '2019-12-31 00:12:25', '超级管理员', '101', '2019-12-31 00:12:25');
INSERT INTO `user_log` VALUES ('1211895566175424514', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-31 00:23:09', '2019-12-31 00:23:13', '4397', '超级管理员', '101', '2019-12-31 00:23:13', '超级管理员', '101', '2019-12-31 00:23:13');
INSERT INTO `user_log` VALUES ('1211895979813490690', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-31 00:24:52', '2019-12-31 00:24:52', '79', '超级管理员', '101', '2019-12-31 00:24:52', '超级管理员', '101', '2019-12-31 00:24:52');
INSERT INTO `user_log` VALUES ('1211901704648380418', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-31 00:47:36', '2019-12-31 00:47:37', '318', '超级管理员', '101', '2019-12-31 00:47:37', '超级管理员', '101', '2019-12-31 00:47:37');
INSERT INTO `user_log` VALUES ('1211912239758561282', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-31 01:29:28', '2019-12-31 01:29:28', '96', '超级管理员', '101', '2019-12-31 01:29:29', '超级管理员', '101', '2019-12-31 01:29:29');
INSERT INTO `user_log` VALUES ('1211942848388173826', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-31 03:31:06', '2019-12-31 03:31:06', '69', '超级管理员', '101', '2019-12-31 03:31:06', '超级管理员', '101', '2019-12-31 03:31:06');
INSERT INTO `user_log` VALUES ('1211944522666250242', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2019-12-31 03:37:45', '2019-12-31 03:37:45', '250', '超级管理员', '101', '2019-12-31 03:37:46', '超级管理员', '101', '2019-12-31 03:37:46');
INSERT INTO `user_log` VALUES ('1214027174730809345', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2020-01-05 21:33:27', '2020-01-05 21:33:28', '515', '超级管理员', '101', '2020-01-05 21:33:28', '超级管理员', '101', '2020-01-05 21:33:28');
INSERT INTO `user_log` VALUES ('1214027671567089665', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2020-01-05 21:35:18', '2020-01-05 21:35:27', '8396', '超级管理员', '101', '2020-01-05 21:35:27', '超级管理员', '101', '2020-01-05 21:35:27');
INSERT INTO `user_log` VALUES ('1214052299203166210', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2020-01-05 23:13:15', '2020-01-05 23:13:18', '3564', '超级管理员', '101', '2020-01-05 23:13:19', '超级管理员', '101', '2020-01-05 23:13:19');
INSERT INTO `user_log` VALUES ('1214052725977792513', null, '', '10', '操作日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '0:0:0:0:0:0:0:1', '', '', '', '', '/product/save', '', 'com.springboot.cloud.mallgoods.controller.admin.AdminProductController', 'saveProduct', '2020-01-05 23:15:00', '2020-01-05 23:15:00', '41', '超级管理员', '101', '2020-01-05 23:15:01', '超级管理员', '101', '2020-01-05 23:15:01');
INSERT INTO `user_log` VALUES ('1219435703331491841', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'admin', null, '2020-01-20 19:45:02', '', null, '2020-01-21 09:45:00');
INSERT INTO `user_log` VALUES ('1219493291138064385', null, '', '20', '登录日志', null, '', '', 'Windows 10 or Windows Server 2016', 'Chrome', '192.168.137.1', '上海市-上海市', '', '', '', '/auth/form', '', '', '', null, null, null, 'admin', null, '2020-01-20 23:33:52', '', null, '2020-01-21 13:33:50');

-- ----------------------------
-- Table structure for user_position_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_position_relation`;
CREATE TABLE `user_position_relation` (
  `id` varchar(20) NOT NULL COMMENT 'id',
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `position_id` varchar(20) NOT NULL COMMENT '角色id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和岗位关系表';

-- ----------------------------
-- Records of user_position_relation
-- ----------------------------
INSERT INTO `user_position_relation` VALUES ('101', '101', '103', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');
INSERT INTO `user_position_relation` VALUES ('102', '102', '103', '2019-10-29 16:27:53', '2019-10-29 16:27:53', null, 'system', null, 'system', '0');

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation` (
  `id` varchar(20) NOT NULL COMMENT '关系id',
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关系表';

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation` VALUES ('101', '101', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `user_role_relation` VALUES ('102', '102', '101', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `user_role_relation` VALUES ('103', '102', '103', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '用户密码密文',
  `name` varchar(200) DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '用户手机',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `deleted` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否有效用户',
  `account_non_expired` tinyint(1) DEFAULT NULL COMMENT '账号是否未过期',
  `credentials_non_expired` tinyint(1) DEFAULT NULL COMMENT '密码是否未过期',
  `account_non_locked` tinyint(1) DEFAULT NULL COMMENT '是否未锁定',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人ID',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_users_username` (`username`),
  UNIQUE KEY `ux_users_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('101', 'admin', '$2a$10$vYA9wKn/hVGOtwQw2eHiceeIGNBdfLYpDmbzHgBSVmOfHXPH4iYdS', '超级管理员', '', null, 'N', '1', '1', '1', '1', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
INSERT INTO `users` VALUES ('102', 'zhoutaoo', '$2a$10$vYA9wKn/hVGOtwQw2eHiceeIGNBdfLYpDmbzHgBSVmOfHXPH4iYdS', '周涛', '15619841000', null, 'N', '1', '1', '1', '1', '2019-10-29 16:27:59', '2019-10-29 16:27:59', null, 'system', null, 'system', '0');
