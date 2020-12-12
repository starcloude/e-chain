/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3358
 Source Schema         : echain

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 11/12/2020 21:25:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `p_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '上级分类',
  `icon` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮图标',
  `type` tinyint(0) NOT NULL DEFAULT 1 COMMENT '类型 : 1导航;2菜单;3按钮',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `text` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文案',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标地址',
  `idx` int(0) NULL DEFAULT NULL COMMENT '序号',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `yn` tinyint(0) NULL DEFAULT NULL COMMENT '是否有效 0 ;有效 1 无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 'layui-icon-home', 2, '', '首页', '', 0, '2019-03-07 20:54:57', 'test', '2020-11-19 09:33:24', 'test', 0);
INSERT INTO `sys_menu` VALUES (2, 0, 'layui-icon-set', 1, '', '系统管理', '', 10, '2019-11-18 16:40:56', 'test', '2019-11-29 17:29:02', 'test', 0);
INSERT INTO `sys_menu` VALUES (3, 2, '', 2, 'role:view', '角色管理', '/system/role/index', 2, '2019-11-18 16:40:56', 'test', '2020-11-19 09:27:47', 'test', 0);
INSERT INTO `sys_menu` VALUES (4, 3, '', 3, 'role:add', '新增', '', 0, '2019-11-18 16:40:56', 'test', '2019-11-18 16:40:56', 'test', 0);
INSERT INTO `sys_menu` VALUES (5, 3, '', 3, 'role:edit', '修改', '', 0, '2019-11-18 16:40:56', 'test', '2019-11-18 16:40:56', 'test', 0);
INSERT INTO `sys_menu` VALUES (6, 3, 'null', 3, 'role:delete', '删除', 'null', 0, '2019-11-18 16:40:56', 'test', '2019-11-19 15:56:02', 'test', 0);
INSERT INTO `sys_menu` VALUES (7, 2, '', 2, 'menu:view', '菜单管理', '/system/menu/index', 4, '2019-11-18 16:40:56', 'test', '2020-11-19 09:28:04', 'test', 0);
INSERT INTO `sys_menu` VALUES (8, 7, '', 3, 'menu:add', '新增', '', 0, '2019-03-09 14:33:36', 'test', '2019-03-09 14:38:50', 'test', 0);
INSERT INTO `sys_menu` VALUES (9, 7, '', 3, 'menu:edit', '修改', '', 0, '2019-03-09 14:33:40', 'test', '2019-03-09 14:33:50', 'test', 0);
INSERT INTO `sys_menu` VALUES (10, 7, '', 3, 'menu:delete', '删除', '', 0, '2019-03-09 14:33:43', 'test', '2019-03-09 14:33:50', 'test', 0);
INSERT INTO `sys_menu` VALUES (11, 2, '', 2, 'rolemenu:view', '角色菜单', '/system/rolemenu/index', 3, '2019-03-09 16:11:16', 'test', '2020-11-19 09:27:55', 'test', 0);
INSERT INTO `sys_menu` VALUES (13, 11, '', 3, 'rolemenu:edit', '修改', '', NULL, '2019-03-09 16:29:53', 'test', '2019-11-18 16:40:56', 'test', 0);
INSERT INTO `sys_menu` VALUES (16, 2, '', 2, 'user:view', '用户管理', '/user/index', 0, '2019-03-11 19:40:20', 'test', '2020-11-19 09:27:40', 'test', 0);
INSERT INTO `sys_menu` VALUES (17, 16, '', 3, 'user:add', '新增', '', NULL, '2019-03-11 19:40:42', 'test', '2019-11-18 16:40:56', 'test', 0);
INSERT INTO `sys_menu` VALUES (18, 16, '', 3, 'user:edit', '修改', '', NULL, '2019-03-11 19:40:54', 'test', '2019-11-18 16:40:56', 'test', 0);
INSERT INTO `sys_menu` VALUES (19, 16, '', 3, 'user:delete', '删除', '', NULL, '2019-03-11 19:41:08', 'test', '2019-11-18 16:40:56', 'test', 0);
INSERT INTO `sys_menu` VALUES (20, 2, '', 2, 'log:view', '系统日志', '/system/log/index', 5, '2019-03-12 19:44:40', 'test', '2020-11-19 09:27:31', 'test', 0);
INSERT INTO `sys_menu` VALUES (21, 0, 'layui-icon-username', 1, '', '用户管理', '', 20, '2019-12-31 22:22:44', 'test', '2020-09-09 10:48:44', 'test', 0);
INSERT INTO `sys_menu` VALUES (24, 21, '', 2, 'vuser:view', '用户管理', '/business/user/index', 1, '2020-01-07 11:36:03', 'test', '2020-10-21 17:33:57', 'test', 0);
INSERT INTO `sys_menu` VALUES (25, 24, '', 3, 'vuser:add', '新增', '', 1, '2020-01-07 15:39:48', 'test', '2020-10-16 14:52:19', 'test', 0);
INSERT INTO `sys_menu` VALUES (26, 24, '', 3, 'vuser:edit', '修改', '', 2, '2020-01-07 15:40:48', 'test', '2020-10-16 14:52:55', 'test', 0);
INSERT INTO `sys_menu` VALUES (28, 24, '', 3, 'vuser:delete', '删除', '', 4, '2019-12-31 22:22:44', 'test', '2020-10-16 15:36:01', 'test', 0);
INSERT INTO `sys_menu` VALUES (38, 21, '', 2, 'vuser_auth:view', '用户认证管理', '/business/user/auth', 2, '2020-02-10 17:18:31', 'test', '2020-10-21 17:24:04', 'test', 0);
INSERT INTO `sys_menu` VALUES (39, 21, '', 2, 'vuser_cashout:view', '提现管理', '/business/user/cashout', 4, '2020-02-10 17:19:05', 'test', '2020-10-23 14:00:11', 'test', 0);
INSERT INTO `sys_menu` VALUES (40, 21, '', 2, 'vuser_wallet:view', '用户钱包', '/business/user/wallet', 3, '2020-02-10 17:19:42', 'test', '2020-10-26 19:23:08', 'test', 0);
INSERT INTO `sys_menu` VALUES (44, 38, '', 3, 'vuser_auth:approve', '审核', '', NULL, '2020-03-19 14:05:45', 'test', '2020-10-21 17:24:25', 'test', 0);
INSERT INTO `sys_menu` VALUES (50, 39, '', 3, 'vuser_cashout:edit', '审核&出款', '', NULL, '2020-03-19 14:42:51', 'test', '2020-10-23 14:00:28', 'test', 0);
INSERT INTO `sys_menu` VALUES (59, 21, '', 2, 'vuser_walletlog:view', '用户钱包日志', '/business/user/walletlog', 3, '2020-10-26 20:02:29', 'test', '2020-10-26 20:05:53', 'test', 0);
INSERT INTO `sys_menu` VALUES (60, 21, '', 2, 'vuser_question:view', '用户密码问题管理', '/business/user/question', 5, '2020-10-29 14:56:17', 'test', '2020-10-29 15:06:04', 'test', 0);
INSERT INTO `sys_menu` VALUES (61, 21, '', 2, 'recharge:view', '充值管理', '/business/recharge', 7, '2020-10-29 15:44:43', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (62, 61, '', 3, 'recharge:edit', '审核', '', NULL, '2020-10-29 15:45:03', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (63, 21, '', 2, 'transfer:view', '转账管理', '/business/transfer', 8, '2020-10-29 16:21:56', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (64, 21, '', 2, 'vuser_usdtwallet:view', '用户USDT钱包管理', '/business/user/usdtwallet', 9, '2020-10-29 16:35:58', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (65, 2, '', 2, 'config:view', '基础配置', '/system/config/', 50, '2020-11-02 16:45:21', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (66, 65, '', 3, 'config:edit', '编辑', '', NULL, '2020-11-02 16:45:53', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (67, 2, '', 2, 'config:worker:view', 'worker配置', '/system/config/worker', 51, '2020-11-02 16:46:42', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (68, 67, '', 3, 'config:worker:edit', '编辑', '', NULL, '2020-11-02 16:47:02', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (69, 21, '', 2, 'machine:view', '矿机管理', '/business/machine', 10, '2020-11-03 16:40:37', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (70, 69, '', 3, 'machine:edit', '编辑', '', NULL, '2020-11-03 16:41:52', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (71, 69, '', 3, 'machine:delete', '删除', '', NULL, '2020-11-03 16:42:03', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (72, 21, '', 2, 'vuser_machine:view', '用户矿机管理', '/business/user/machine', 11, '2020-11-03 17:56:03', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (73, 72, '', 3, 'vuser_machine:stop', '停止', '', NULL, '2020-11-03 20:13:09', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (74, 21, '', 2, 'chatmsg:view', '客服管理', '/business/chatmsg', 12, '2020-11-10 14:54:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (75, 74, '', 3, 'chatmsg:reply', '回复', '', NULL, '2020-11-10 14:56:28', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (76, 2, '', 2, 'config:editWallet', '收币钱包配置', '/system/config/adminWallet', 52, '2020-11-11 17:10:58', 'test', '2020-12-05 15:21:21', 'test', 0);
INSERT INTO `sys_menu` VALUES (77, 76, '', 3, 'config:editWallet', '编辑', '', NULL, '2020-11-11 17:11:28', 'test', '2020-12-05 15:21:31', 'test', 0);
INSERT INTO `sys_menu` VALUES (78, 2, '', 2, 'news:view', '资讯管理', '/business/news', 53, '2020-11-19 09:37:35', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (79, 78, '', 3, 'news:add', '新增', '', 1, '2020-11-19 09:38:28', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (80, 78, '', 3, 'news:edit', '修改', '', 2, '2020-11-19 09:38:48', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (81, 78, '', 3, 'news:delete', '删除', '', 3, '2020-11-19 09:39:07', 'test', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (82, 40, '', 3, 'vuser_wallet:resetTradePwd', '重置交易密码', '', NULL, '2020-11-24 20:29:36', 'test', '2020-11-24 20:29:54', 'test', 0);
INSERT INTO `sys_menu` VALUES (83, 21, '', 2, 'syscalc:view', '资金统计', '/business/syscalc', NULL, '2020-12-01 11:02:11', 'test', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `level` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志级别',
  `node` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点',
  `memo` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_pin` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `yn` tinyint(0) NULL DEFAULT NULL COMMENT '是否有效 0 ;有效 1 无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '2019-11-13 17:24:06', 'test', '2020-10-16 17:43:40', 'test', 0);
INSERT INTO `sys_role` VALUES (2, '普通管理员', '2019-11-19 07:55:23', 'test', '2019-12-31 23:20:32', 'test', 0);
INSERT INTO `sys_role` VALUES (3, '测试', '2019-12-31 23:21:39', 'test', '2019-12-31 23:22:56', 'test', 0);
INSERT INTO `sys_role` VALUES (4, '测试财务', '2020-12-01 15:16:59', 'test', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (5, '测试管理', '2020-12-01 15:24:42', 'test', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `r_id` bigint(0) NOT NULL COMMENT '角色ID',
  `m_id` bigint(0) NOT NULL COMMENT '菜单ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `yn` tinyint(0) NULL DEFAULT NULL COMMENT '是否有效 0 ;有效 1 无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1501 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (274, 1, 1, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (275, 1, 2, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (276, 1, 16, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (277, 1, 17, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (278, 1, 18, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (279, 1, 19, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (280, 1, 3, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (281, 1, 4, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (282, 1, 5, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (283, 1, 6, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (284, 1, 11, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (285, 1, 13, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (286, 1, 7, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (287, 1, 8, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (288, 1, 9, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (289, 1, 10, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (290, 1, 20, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (291, 1, 21, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (295, 1, 24, '2019-12-27 17:35:12', 'test', '2020-02-06 18:08:26', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (301, 1, 1, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (302, 1, 2, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (303, 1, 16, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (304, 1, 17, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (305, 1, 18, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (306, 1, 19, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (307, 1, 3, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (308, 1, 4, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (309, 1, 5, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (310, 1, 6, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (311, 1, 11, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (312, 1, 13, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (313, 1, 7, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (314, 1, 8, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (315, 1, 9, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (316, 1, 10, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (317, 1, 20, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (318, 1, 21, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (319, 1, 24, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (320, 1, 25, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (321, 1, 26, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (322, 1, 27, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (323, 1, 28, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (324, 1, 29, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (325, 1, 30, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (326, 1, 31, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (327, 1, 32, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (328, 1, 33, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (329, 1, 34, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (330, 1, 35, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (331, 1, 36, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (332, 1, 37, '2020-02-06 18:08:26', 'test', '2020-02-10 17:24:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (333, 1, 1, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (334, 1, 2, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (335, 1, 16, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (336, 1, 17, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (337, 1, 18, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (338, 1, 19, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (339, 1, 3, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (340, 1, 4, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (341, 1, 5, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (342, 1, 6, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (343, 1, 11, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (344, 1, 13, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (345, 1, 7, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (346, 1, 8, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (347, 1, 9, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (348, 1, 10, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (349, 1, 20, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (350, 1, 21, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (351, 1, 24, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (352, 1, 25, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (353, 1, 26, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (354, 1, 27, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (355, 1, 28, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (356, 1, 38, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (357, 1, 40, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (358, 1, 39, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (359, 1, 29, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (360, 1, 30, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (361, 1, 31, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (362, 1, 32, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (363, 1, 33, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (364, 1, 34, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (365, 1, 35, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (366, 1, 36, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (367, 1, 37, '2020-02-10 17:24:20', 'test', '2020-02-11 19:25:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (368, 1, 1, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (369, 1, 2, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (370, 1, 16, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (371, 1, 17, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (372, 1, 18, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (373, 1, 19, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (374, 1, 3, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (375, 1, 4, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (376, 1, 5, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (377, 1, 6, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (378, 1, 11, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (379, 1, 13, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (380, 1, 7, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (381, 1, 8, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (382, 1, 9, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (383, 1, 10, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (384, 1, 20, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (385, 1, 21, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (386, 1, 24, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (387, 1, 25, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (388, 1, 26, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (389, 1, 27, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (390, 1, 28, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (391, 1, 38, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (392, 1, 40, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (393, 1, 39, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (394, 1, 29, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (395, 1, 30, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (396, 1, 31, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (397, 1, 32, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (398, 1, 33, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (399, 1, 34, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (400, 1, 35, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (401, 1, 36, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (402, 1, 37, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (403, 1, 41, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (404, 1, 42, '2020-02-11 19:25:15', 'test', '2020-02-12 21:11:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (405, 1, 1, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (406, 1, 2, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (407, 1, 16, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (408, 1, 17, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (409, 1, 18, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (410, 1, 19, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (411, 1, 3, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (412, 1, 4, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (413, 1, 5, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (414, 1, 6, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (415, 1, 11, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (416, 1, 13, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (417, 1, 7, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (418, 1, 8, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (419, 1, 9, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (420, 1, 10, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (421, 1, 20, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (422, 1, 21, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (423, 1, 24, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (424, 1, 25, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (425, 1, 26, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (426, 1, 27, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (427, 1, 28, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (428, 1, 38, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (429, 1, 40, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (430, 1, 39, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (431, 1, 29, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (432, 1, 30, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (433, 1, 31, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (434, 1, 32, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (435, 1, 33, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (436, 1, 34, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (437, 1, 35, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (438, 1, 36, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (439, 1, 37, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (440, 1, 41, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (441, 1, 42, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (442, 1, 43, '2020-02-12 21:11:00', 'test', '2020-03-19 14:38:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (443, 1, 1, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (444, 1, 2, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (445, 1, 16, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (446, 1, 17, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (447, 1, 18, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (448, 1, 19, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (449, 1, 3, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (450, 1, 4, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (451, 1, 5, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (452, 1, 6, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (453, 1, 11, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (454, 1, 13, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (455, 1, 7, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (456, 1, 8, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (457, 1, 9, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (458, 1, 10, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (459, 1, 20, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (460, 1, 21, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (461, 1, 24, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (462, 1, 48, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (463, 1, 49, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (464, 1, 25, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (465, 1, 26, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (466, 1, 27, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (467, 1, 45, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (468, 1, 46, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (469, 1, 47, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (470, 1, 28, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (471, 1, 38, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (472, 1, 44, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (473, 1, 40, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (474, 1, 39, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (475, 1, 29, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (476, 1, 30, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (477, 1, 31, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (478, 1, 32, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (479, 1, 33, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (480, 1, 34, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (481, 1, 35, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (482, 1, 36, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (483, 1, 37, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (484, 1, 41, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (485, 1, 42, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (486, 1, 43, '2020-03-19 14:38:13', 'test', '2020-03-19 14:42:58', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (487, 1, 1, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (488, 1, 2, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (489, 1, 16, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (490, 1, 17, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (491, 1, 18, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (492, 1, 19, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (493, 1, 3, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (494, 1, 4, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (495, 1, 5, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (496, 1, 6, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (497, 1, 11, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (498, 1, 13, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (499, 1, 7, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (500, 1, 8, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (501, 1, 9, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (502, 1, 10, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (503, 1, 20, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (504, 1, 21, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (505, 1, 24, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (506, 1, 48, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (507, 1, 49, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (508, 1, 25, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (509, 1, 26, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (510, 1, 27, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (511, 1, 45, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (512, 1, 46, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (513, 1, 47, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (514, 1, 28, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (515, 1, 38, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (516, 1, 44, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (517, 1, 40, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (518, 1, 39, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (519, 1, 50, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (520, 1, 29, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (521, 1, 30, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (522, 1, 31, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (523, 1, 32, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (524, 1, 33, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (525, 1, 34, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (526, 1, 35, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (527, 1, 36, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (528, 1, 37, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (529, 1, 41, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (530, 1, 42, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (531, 1, 43, '2020-03-19 14:42:59', 'test', '2020-03-19 15:06:03', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (532, 1, 1, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (533, 1, 2, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (534, 1, 16, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (535, 1, 17, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (536, 1, 18, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (537, 1, 19, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (538, 1, 3, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (539, 1, 4, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (540, 1, 5, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (541, 1, 6, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (542, 1, 11, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (543, 1, 13, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (544, 1, 7, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (545, 1, 8, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (546, 1, 9, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (547, 1, 10, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (548, 1, 20, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (549, 1, 21, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (550, 1, 24, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (551, 1, 48, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (552, 1, 49, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (553, 1, 25, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (554, 1, 26, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (555, 1, 27, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (556, 1, 45, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (557, 1, 46, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (558, 1, 47, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (559, 1, 28, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (560, 1, 38, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (561, 1, 44, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (562, 1, 40, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (563, 1, 39, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (564, 1, 50, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (565, 1, 29, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (566, 1, 30, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (567, 1, 31, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (568, 1, 32, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (569, 1, 33, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (570, 1, 34, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (571, 1, 35, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (572, 1, 36, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (573, 1, 37, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (574, 1, 41, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (575, 1, 42, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (576, 1, 43, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (577, 1, 51, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (578, 1, 52, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (579, 1, 53, '2020-03-19 15:06:03', 'test', '2020-04-11 15:58:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (580, 1, 1, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (581, 1, 2, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (582, 1, 16, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (583, 1, 17, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (584, 1, 18, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (585, 1, 19, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (586, 1, 3, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (587, 1, 4, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (588, 1, 5, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (589, 1, 6, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (590, 1, 11, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (591, 1, 13, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (592, 1, 7, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (593, 1, 8, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (594, 1, 9, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (595, 1, 10, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (596, 1, 20, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (597, 1, 21, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (598, 1, 24, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (599, 1, 48, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (600, 1, 49, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (601, 1, 25, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (602, 1, 26, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (603, 1, 27, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (604, 1, 45, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (605, 1, 46, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (606, 1, 47, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (607, 1, 28, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (608, 1, 38, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (609, 1, 44, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (610, 1, 40, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (611, 1, 39, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (612, 1, 50, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (613, 1, 29, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (614, 1, 30, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (615, 1, 54, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (616, 1, 55, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (617, 1, 31, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (618, 1, 32, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (619, 1, 33, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (620, 1, 34, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (621, 1, 35, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (622, 1, 36, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (623, 1, 37, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (624, 1, 41, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (625, 1, 56, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (626, 1, 57, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (627, 1, 42, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (628, 1, 43, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (629, 1, 51, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (630, 1, 52, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (631, 1, 53, '2020-04-11 15:58:36', 'test', '2020-05-10 13:49:20', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (632, 1, 1, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (633, 1, 2, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (634, 1, 16, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (635, 1, 17, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (636, 1, 18, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (637, 1, 19, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (638, 1, 3, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (639, 1, 4, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (640, 1, 5, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (641, 1, 6, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (642, 1, 11, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (643, 1, 13, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (644, 1, 7, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (645, 1, 8, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (646, 1, 9, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (647, 1, 10, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (648, 1, 20, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (649, 1, 21, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (650, 1, 24, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (651, 1, 48, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (652, 1, 49, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (653, 1, 25, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (654, 1, 26, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (655, 1, 27, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (656, 1, 45, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (657, 1, 46, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (658, 1, 47, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (659, 1, 28, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (660, 1, 38, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (661, 1, 44, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (662, 1, 40, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (663, 1, 39, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (664, 1, 50, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (665, 1, 29, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (666, 1, 30, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (667, 1, 54, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (668, 1, 55, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (669, 1, 31, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (670, 1, 32, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (671, 1, 33, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (672, 1, 34, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (673, 1, 35, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (674, 1, 36, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (675, 1, 58, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (676, 1, 37, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (677, 1, 41, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (678, 1, 56, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (679, 1, 57, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (680, 1, 42, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (681, 1, 43, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (682, 1, 51, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (683, 1, 52, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (684, 1, 53, '2020-05-10 13:49:20', 'test', '2020-10-26 20:02:51', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (685, 1, 1, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (686, 1, 2, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (687, 1, 16, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (688, 1, 17, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (689, 1, 18, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (690, 1, 19, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (691, 1, 3, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (692, 1, 4, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (693, 1, 5, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (694, 1, 6, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (695, 1, 11, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (696, 1, 13, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (697, 1, 7, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (698, 1, 8, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (699, 1, 9, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (700, 1, 10, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (701, 1, 20, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (702, 1, 21, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (703, 1, 24, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (704, 1, 25, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (705, 1, 26, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (706, 1, 28, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (707, 1, 38, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (708, 1, 44, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (709, 1, 40, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (710, 1, 59, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (711, 1, 39, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (712, 1, 50, '2020-10-26 20:02:51', 'test', '2020-10-26 20:07:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (713, 1, 1, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (714, 1, 2, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (715, 1, 16, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (716, 1, 17, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (717, 1, 18, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (718, 1, 19, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (719, 1, 3, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (720, 1, 4, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (721, 1, 5, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (722, 1, 6, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (723, 1, 11, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (724, 1, 13, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (725, 1, 7, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (726, 1, 8, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (727, 1, 9, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (728, 1, 10, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (729, 1, 20, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (730, 1, 21, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (731, 1, 24, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (732, 1, 25, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (733, 1, 26, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (734, 1, 28, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (735, 1, 38, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (736, 1, 44, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (737, 1, 40, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (738, 1, 59, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (739, 1, 39, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (740, 1, 50, '2020-10-26 20:07:07', 'test', '2020-10-29 14:56:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (741, 1, 1, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (742, 1, 2, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (743, 1, 16, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (744, 1, 17, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (745, 1, 18, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (746, 1, 19, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (747, 1, 3, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (748, 1, 4, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (749, 1, 5, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (750, 1, 6, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (751, 1, 11, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (752, 1, 13, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (753, 1, 7, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (754, 1, 8, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (755, 1, 9, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (756, 1, 10, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (757, 1, 20, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (758, 1, 21, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (759, 1, 60, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (760, 1, 24, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (761, 1, 25, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (762, 1, 26, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (763, 1, 28, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (764, 1, 38, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (765, 1, 44, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (766, 1, 40, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (767, 1, 59, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (768, 1, 39, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (769, 1, 50, '2020-10-29 14:56:27', 'test', '2020-10-29 15:45:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (770, 1, 1, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (771, 1, 2, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (772, 1, 16, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (773, 1, 17, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (774, 1, 18, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (775, 1, 19, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (776, 1, 3, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (777, 1, 4, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (778, 1, 5, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (779, 1, 6, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (780, 1, 11, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (781, 1, 13, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (782, 1, 7, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (783, 1, 8, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (784, 1, 9, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (785, 1, 10, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (786, 1, 20, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (787, 1, 21, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (788, 1, 24, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (789, 1, 25, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (790, 1, 26, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (791, 1, 28, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (792, 1, 38, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (793, 1, 44, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (794, 1, 40, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (795, 1, 59, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (796, 1, 39, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (797, 1, 50, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (798, 1, 60, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (799, 1, 61, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (800, 1, 62, '2020-10-29 15:45:11', 'test', '2020-10-29 16:22:10', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (801, 1, 1, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (802, 1, 2, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (803, 1, 16, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (804, 1, 17, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (805, 1, 18, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (806, 1, 19, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (807, 1, 3, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (808, 1, 4, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (809, 1, 5, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (810, 1, 6, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (811, 1, 11, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (812, 1, 13, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (813, 1, 7, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (814, 1, 8, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (815, 1, 9, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (816, 1, 10, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (817, 1, 20, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (818, 1, 21, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (819, 1, 24, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (820, 1, 25, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (821, 1, 26, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (822, 1, 28, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (823, 1, 38, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (824, 1, 44, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (825, 1, 40, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (826, 1, 59, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (827, 1, 39, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (828, 1, 50, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (829, 1, 60, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (830, 1, 61, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (831, 1, 62, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (832, 1, 63, '2020-10-29 16:22:11', 'test', '2020-10-29 16:36:07', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (833, 1, 1, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (834, 1, 2, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (835, 1, 16, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (836, 1, 17, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (837, 1, 18, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (838, 1, 19, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (839, 1, 3, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (840, 1, 4, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (841, 1, 5, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (842, 1, 6, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (843, 1, 11, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (844, 1, 13, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (845, 1, 7, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (846, 1, 8, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (847, 1, 9, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (848, 1, 10, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (849, 1, 20, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (850, 1, 21, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (851, 1, 24, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (852, 1, 25, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (853, 1, 26, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (854, 1, 28, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (855, 1, 38, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (856, 1, 44, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (857, 1, 40, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (858, 1, 59, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (859, 1, 39, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (860, 1, 50, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (861, 1, 60, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (862, 1, 61, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (863, 1, 62, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (864, 1, 63, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (865, 1, 64, '2020-10-29 16:36:07', 'test', '2020-11-02 16:47:13', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (866, 1, 1, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (867, 1, 2, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (868, 1, 16, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (869, 1, 17, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (870, 1, 18, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (871, 1, 19, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (872, 1, 3, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (873, 1, 4, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (874, 1, 5, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (875, 1, 6, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (876, 1, 11, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (877, 1, 13, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (878, 1, 7, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (879, 1, 8, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (880, 1, 9, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (881, 1, 10, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (882, 1, 20, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (883, 1, 65, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (884, 1, 66, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (885, 1, 67, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (886, 1, 68, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (887, 1, 21, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (888, 1, 24, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (889, 1, 25, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (890, 1, 26, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (891, 1, 28, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (892, 1, 38, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (893, 1, 44, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (894, 1, 40, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (895, 1, 59, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (896, 1, 39, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (897, 1, 50, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (898, 1, 60, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (899, 1, 61, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (900, 1, 62, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (901, 1, 63, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (902, 1, 64, '2020-11-02 16:47:13', 'test', '2020-11-03 16:42:16', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (903, 1, 1, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (904, 1, 2, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (905, 1, 16, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (906, 1, 17, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (907, 1, 18, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (908, 1, 19, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (909, 1, 3, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (910, 1, 4, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (911, 1, 5, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (912, 1, 6, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (913, 1, 11, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (914, 1, 13, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (915, 1, 7, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (916, 1, 8, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (917, 1, 9, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (918, 1, 10, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (919, 1, 20, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (920, 1, 65, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (921, 1, 66, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (922, 1, 67, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (923, 1, 68, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (924, 1, 21, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (925, 1, 24, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (926, 1, 25, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (927, 1, 26, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (928, 1, 28, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (929, 1, 38, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (930, 1, 44, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (931, 1, 40, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (932, 1, 59, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (933, 1, 39, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (934, 1, 50, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (935, 1, 60, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (936, 1, 61, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (937, 1, 62, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (938, 1, 63, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (939, 1, 64, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (940, 1, 69, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (941, 1, 70, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (942, 1, 71, '2020-11-03 16:42:16', 'test', '2020-11-03 17:56:11', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (943, 1, 1, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (944, 1, 2, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (945, 1, 16, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (946, 1, 17, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (947, 1, 18, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (948, 1, 19, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (949, 1, 3, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (950, 1, 4, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (951, 1, 5, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (952, 1, 6, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (953, 1, 11, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (954, 1, 13, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (955, 1, 7, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (956, 1, 8, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (957, 1, 9, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (958, 1, 10, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (959, 1, 20, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (960, 1, 65, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (961, 1, 66, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (962, 1, 67, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (963, 1, 68, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (964, 1, 21, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (965, 1, 24, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (966, 1, 25, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (967, 1, 26, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (968, 1, 28, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (969, 1, 38, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (970, 1, 44, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (971, 1, 40, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (972, 1, 59, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (973, 1, 39, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (974, 1, 50, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (975, 1, 60, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (976, 1, 61, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (977, 1, 62, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (978, 1, 63, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (979, 1, 64, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (980, 1, 69, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (981, 1, 70, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (982, 1, 71, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (983, 1, 72, '2020-11-03 17:56:11', 'test', '2020-11-03 20:13:18', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (984, 1, 1, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (985, 1, 2, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (986, 1, 16, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (987, 1, 17, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (988, 1, 18, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (989, 1, 19, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (990, 1, 3, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (991, 1, 4, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (992, 1, 5, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (993, 1, 6, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (994, 1, 11, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (995, 1, 13, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (996, 1, 7, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (997, 1, 8, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (998, 1, 9, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (999, 1, 10, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1000, 1, 20, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1001, 1, 65, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1002, 1, 66, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1003, 1, 67, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1004, 1, 68, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1005, 1, 21, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1006, 1, 24, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1007, 1, 25, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1008, 1, 26, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1009, 1, 28, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1010, 1, 38, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1011, 1, 44, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1012, 1, 40, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1013, 1, 59, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1014, 1, 39, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1015, 1, 50, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1016, 1, 60, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1017, 1, 61, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1018, 1, 62, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1019, 1, 63, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1020, 1, 64, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1021, 1, 69, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1022, 1, 70, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1023, 1, 71, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1024, 1, 72, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1025, 1, 73, '2020-11-03 20:13:18', 'test', '2020-11-10 14:56:36', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1026, 1, 1, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1027, 1, 2, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1028, 1, 16, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1029, 1, 17, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1030, 1, 18, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1031, 1, 19, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1032, 1, 3, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1033, 1, 4, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1034, 1, 5, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1035, 1, 6, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1036, 1, 11, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1037, 1, 13, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1038, 1, 7, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1039, 1, 8, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1040, 1, 9, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1041, 1, 10, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1042, 1, 20, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1043, 1, 65, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1044, 1, 66, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1045, 1, 67, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1046, 1, 68, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1047, 1, 21, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1048, 1, 24, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1049, 1, 25, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1050, 1, 26, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1051, 1, 28, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1052, 1, 38, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1053, 1, 44, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1054, 1, 40, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1055, 1, 59, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1056, 1, 39, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1057, 1, 50, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1058, 1, 60, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1059, 1, 61, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1060, 1, 62, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1061, 1, 63, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1062, 1, 64, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1063, 1, 69, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1064, 1, 70, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1065, 1, 71, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1066, 1, 72, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1067, 1, 73, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1068, 1, 74, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1069, 1, 75, '2020-11-10 14:56:36', 'test', '2020-11-11 17:11:56', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1070, 1, 1, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1071, 1, 2, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1072, 1, 16, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1073, 1, 17, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1074, 1, 18, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1075, 1, 19, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1076, 1, 3, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1077, 1, 4, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1078, 1, 5, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1079, 1, 6, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1080, 1, 11, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1081, 1, 13, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1082, 1, 7, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1083, 1, 8, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1084, 1, 9, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1085, 1, 10, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1086, 1, 20, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1087, 1, 65, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1088, 1, 66, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1089, 1, 67, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1090, 1, 68, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1091, 1, 76, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1092, 1, 77, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1093, 1, 21, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1094, 1, 24, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1095, 1, 25, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1096, 1, 26, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1097, 1, 28, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1098, 1, 38, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1099, 1, 44, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1100, 1, 40, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1101, 1, 59, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1102, 1, 39, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1103, 1, 50, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1104, 1, 60, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1105, 1, 61, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1106, 1, 62, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1107, 1, 63, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1108, 1, 64, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1109, 1, 69, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1110, 1, 70, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1111, 1, 71, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1112, 1, 72, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1113, 1, 73, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1114, 1, 74, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1115, 1, 75, '2020-11-11 17:11:56', 'test', '2020-11-19 09:39:34', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1116, 1, 1, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1117, 1, 2, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1118, 1, 16, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1119, 1, 17, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1120, 1, 18, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1121, 1, 19, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1122, 1, 3, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1123, 1, 4, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1124, 1, 5, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1125, 1, 6, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1126, 1, 11, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1127, 1, 13, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1128, 1, 7, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1129, 1, 8, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1130, 1, 9, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1131, 1, 10, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1132, 1, 20, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1133, 1, 65, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1134, 1, 66, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1135, 1, 67, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1136, 1, 68, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1137, 1, 76, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1138, 1, 77, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1139, 1, 78, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1140, 1, 79, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1141, 1, 80, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1142, 1, 81, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1143, 1, 21, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1144, 1, 24, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1145, 1, 25, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1146, 1, 26, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1147, 1, 28, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1148, 1, 38, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1149, 1, 44, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1150, 1, 40, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1151, 1, 59, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1152, 1, 39, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1153, 1, 50, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1154, 1, 60, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1155, 1, 61, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1156, 1, 62, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1157, 1, 63, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1158, 1, 64, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1159, 1, 69, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1160, 1, 70, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1161, 1, 71, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1162, 1, 72, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1163, 1, 73, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1164, 1, 74, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1165, 1, 75, '2020-11-19 09:39:34', 'test', '2020-11-24 20:30:15', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1166, 1, 1, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1167, 1, 2, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1168, 1, 16, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1169, 1, 17, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1170, 1, 18, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1171, 1, 19, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1172, 1, 3, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1173, 1, 4, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1174, 1, 5, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1175, 1, 6, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1176, 1, 11, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1177, 1, 13, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1178, 1, 7, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1179, 1, 8, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1180, 1, 9, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1181, 1, 10, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1182, 1, 20, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1183, 1, 65, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1184, 1, 66, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1185, 1, 67, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1186, 1, 68, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1187, 1, 76, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1188, 1, 77, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1189, 1, 78, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1190, 1, 79, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1191, 1, 80, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1192, 1, 81, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1193, 1, 21, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1194, 1, 24, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1195, 1, 25, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1196, 1, 26, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1197, 1, 28, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1198, 1, 38, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1199, 1, 44, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1200, 1, 40, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1201, 1, 82, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1202, 1, 59, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1203, 1, 39, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1204, 1, 50, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1205, 1, 60, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1206, 1, 61, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1207, 1, 62, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1208, 1, 63, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1209, 1, 64, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1210, 1, 69, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1211, 1, 70, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1212, 1, 71, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1213, 1, 72, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1214, 1, 73, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1215, 1, 74, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1216, 1, 75, '2020-11-24 20:30:15', 'test', '2020-12-01 11:02:17', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1217, 1, 1, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1218, 1, 2, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1219, 1, 16, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1220, 1, 17, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1221, 1, 18, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1222, 1, 19, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1223, 1, 3, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1224, 1, 4, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1225, 1, 5, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1226, 1, 6, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1227, 1, 11, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1228, 1, 13, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1229, 1, 7, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1230, 1, 8, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1231, 1, 9, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1232, 1, 10, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1233, 1, 20, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1234, 1, 65, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1235, 1, 66, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1236, 1, 67, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1237, 1, 68, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1238, 1, 76, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1239, 1, 77, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1240, 1, 78, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1241, 1, 79, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1242, 1, 80, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1243, 1, 81, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1244, 1, 21, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1245, 1, 83, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1246, 1, 24, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1247, 1, 25, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1248, 1, 26, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1249, 1, 28, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1250, 1, 38, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1251, 1, 44, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1252, 1, 40, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1253, 1, 82, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1254, 1, 59, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1255, 1, 39, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1256, 1, 50, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1257, 1, 60, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1258, 1, 61, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1259, 1, 62, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1260, 1, 63, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1261, 1, 64, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1262, 1, 69, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1263, 1, 70, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1264, 1, 71, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1265, 1, 72, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1266, 1, 73, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1267, 1, 74, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1268, 1, 75, '2020-12-01 11:02:17', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1269, 4, 2, '2020-12-01 15:26:31', 'test', '2020-12-01 15:32:44', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1270, 4, 76, '2020-12-01 15:26:31', 'test', '2020-12-01 15:32:44', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1271, 4, 77, '2020-12-01 15:26:31', 'test', '2020-12-01 15:32:44', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1272, 4, 83, '2020-12-01 15:26:31', 'test', '2020-12-01 15:32:44', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1273, 4, 40, '2020-12-01 15:26:31', 'test', '2020-12-01 15:32:44', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1274, 4, 39, '2020-12-01 15:26:31', 'test', '2020-12-01 15:32:44', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1275, 4, 50, '2020-12-01 15:26:31', 'test', '2020-12-01 15:32:44', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1276, 4, 61, '2020-12-01 15:26:31', 'test', '2020-12-01 15:32:44', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1277, 4, 62, '2020-12-01 15:26:31', 'test', '2020-12-01 15:32:44', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1278, 4, 63, '2020-12-01 15:26:31', 'test', '2020-12-01 15:32:44', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1279, 5, 65, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1280, 5, 66, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1281, 5, 78, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1282, 5, 79, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1283, 5, 80, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1284, 5, 81, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1285, 5, 24, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1286, 5, 25, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1287, 5, 26, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1288, 5, 28, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1289, 5, 38, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1290, 5, 44, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1291, 5, 40, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1292, 5, 82, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1293, 5, 59, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1294, 5, 39, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1295, 5, 50, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1296, 5, 60, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1297, 5, 61, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1298, 5, 62, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1299, 5, 63, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1300, 5, 64, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1301, 5, 69, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1302, 5, 72, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1303, 5, 73, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1304, 5, 74, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1305, 5, 75, '2020-12-01 15:29:29', 'test', '2020-12-01 15:32:27', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1306, 5, 1, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1307, 5, 2, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1308, 5, 65, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1309, 5, 66, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1310, 5, 78, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1311, 5, 79, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1312, 5, 80, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1313, 5, 81, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1314, 5, 21, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1315, 5, 83, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1316, 5, 24, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1317, 5, 25, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1318, 5, 26, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1319, 5, 28, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1320, 5, 38, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1321, 5, 44, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1322, 5, 40, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1323, 5, 82, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1324, 5, 59, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1325, 5, 39, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1326, 5, 50, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1327, 5, 60, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1328, 5, 61, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1329, 5, 62, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1330, 5, 63, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1331, 5, 64, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1332, 5, 69, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1333, 5, 72, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1334, 5, 73, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1335, 5, 74, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1336, 5, 75, '2020-12-01 15:32:27', 'test', '2020-12-01 15:37:25', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1337, 4, 1, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1338, 4, 2, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1339, 4, 76, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1340, 4, 77, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1341, 4, 21, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1342, 4, 83, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1343, 4, 40, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1344, 4, 39, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1345, 4, 50, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1346, 4, 61, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1347, 4, 62, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1348, 4, 63, '2020-12-01 15:32:44', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1349, 5, 1, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1350, 5, 2, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1351, 5, 65, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1352, 5, 66, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1353, 5, 78, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1354, 5, 79, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1355, 5, 80, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1356, 5, 81, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1357, 5, 21, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1358, 5, 83, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1359, 5, 24, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1360, 5, 25, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1361, 5, 26, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1362, 5, 28, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1363, 5, 38, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1364, 5, 44, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1365, 5, 40, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1366, 5, 82, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1367, 5, 59, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1368, 5, 39, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1369, 5, 50, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1370, 5, 60, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1371, 5, 61, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1372, 5, 62, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1373, 5, 63, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1374, 5, 64, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1375, 5, 69, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1376, 5, 72, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1377, 5, 73, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1378, 5, 74, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1379, 5, 75, '2020-12-01 15:37:25', 'test', '2020-12-01 15:39:42', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1380, 5, 1, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1381, 5, 2, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1382, 5, 16, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1383, 5, 17, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1384, 5, 18, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1385, 5, 19, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1386, 5, 3, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1387, 5, 4, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1388, 5, 5, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1389, 5, 6, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1390, 5, 11, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1391, 5, 13, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1392, 5, 7, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1393, 5, 8, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1394, 5, 9, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1395, 5, 10, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1396, 5, 20, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1397, 5, 65, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1398, 5, 66, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1399, 5, 67, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1400, 5, 68, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1401, 5, 76, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1402, 5, 77, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1403, 5, 78, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1404, 5, 79, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1405, 5, 80, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1406, 5, 81, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1407, 5, 21, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1408, 5, 83, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1409, 5, 24, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1410, 5, 25, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1411, 5, 26, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1412, 5, 28, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1413, 5, 38, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1414, 5, 44, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1415, 5, 40, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1416, 5, 82, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1417, 5, 59, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1418, 5, 39, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1419, 5, 50, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1420, 5, 60, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1421, 5, 61, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1422, 5, 62, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1423, 5, 63, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1424, 5, 64, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1425, 5, 69, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1426, 5, 70, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1427, 5, 71, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1428, 5, 72, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1429, 5, 73, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1430, 5, 74, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1431, 5, 75, '2020-12-01 15:39:42', 'test', '2020-12-01 16:46:50', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1432, 5, 1, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1433, 5, 2, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1434, 5, 16, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1435, 5, 17, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1436, 5, 18, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1437, 5, 65, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1438, 5, 66, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1439, 5, 78, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1440, 5, 79, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1441, 5, 80, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1442, 5, 81, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1443, 5, 21, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1444, 5, 83, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1445, 5, 24, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1446, 5, 25, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1447, 5, 26, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1448, 5, 28, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1449, 5, 38, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1450, 5, 44, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1451, 5, 40, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1452, 5, 82, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1453, 5, 59, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1454, 5, 39, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1455, 5, 50, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1456, 5, 60, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1457, 5, 61, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1458, 5, 62, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1459, 5, 63, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1460, 5, 64, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1461, 5, 69, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1462, 5, 70, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1463, 5, 71, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1464, 5, 72, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1465, 5, 73, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1466, 5, 74, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1467, 5, 75, '2020-12-01 16:46:50', 'test', '2020-12-02 15:23:00', 'test', 1);
INSERT INTO `sys_role_menu` VALUES (1468, 5, 1, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1469, 5, 2, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1470, 5, 65, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1471, 5, 66, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1472, 5, 78, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1473, 5, 79, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1474, 5, 80, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1475, 5, 81, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1476, 5, 21, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1477, 5, 83, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1478, 5, 24, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1479, 5, 25, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1480, 5, 26, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1481, 5, 28, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1482, 5, 38, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1483, 5, 44, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1484, 5, 40, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1485, 5, 82, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1486, 5, 59, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1487, 5, 39, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1488, 5, 50, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1489, 5, 60, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1490, 5, 61, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1491, 5, 62, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1492, 5, 63, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1493, 5, 64, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1494, 5, 69, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1495, 5, 70, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1496, 5, 71, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1497, 5, 72, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1498, 5, 73, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1499, 5, 74, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);
INSERT INTO `sys_role_menu` VALUES (1500, 5, 75, '2020-12-02 15:23:00', 'test', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID 自增',
  `no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `pwd` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `r_id` bigint(0) NOT NULL COMMENT '角色ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `yn` tinyint(0) NULL DEFAULT NULL COMMENT '是否有效 0 ;有效 1 无效',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `pk_no`(`no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (2, 'test', '39F411807ACC92F562DD1EA3BBE8AAFB', '83526790', 'test001', 1, '2019-12-31 22:52:57', 'test', '2020-12-02 22:25:15', 'test', 0);
INSERT INTO `sys_user` VALUES (5, 'ceshicaiwu', '93A34D5616350572E5B06ABE99E209A9', '35938637', '财务', 4, '2020-12-01 15:29:52', 'test', '2020-12-07 10:41:24', 'test', 1);
INSERT INTO `sys_user` VALUES (6, 'ceshiguanli', '8B34192EEC58C7664E69EE12518ED597', '67765513', '管理', 5, '2020-12-01 15:30:22', 'test', '2020-12-07 10:41:28', 'test', 1);
INSERT INTO `sys_user` VALUES (7, 'caiwu001', '277133EE38BEF6B86745256BB6748E2F', '40958686', '财务', 4, '2020-12-07 10:41:12', 'test', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (8, 'guanli001', 'EEE4189DFAABE5DA9CED48D58D6DE515', '41098183', '管理', 5, '2020-12-07 10:41:50', 'test', NULL, NULL, 0);

-- ----------------------------
-- Table structure for v_chat_msg
-- ----------------------------
DROP TABLE IF EXISTS `v_chat_msg`;
CREATE TABLE `v_chat_msg`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自助ID',
  `chat_id` bigint(0) NULL DEFAULT NULL COMMENT '聊天室ID 对应的就是v_user.id',
  `from_user_id` bigint(0) NULL DEFAULT NULL COMMENT '发起人',
  `to_user_id` bigint(0) NULL DEFAULT NULL COMMENT '接受人',
  `to_user_nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人的昵称',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_chat_id_create_time`(`chat_id`, `create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '聊天内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_file
-- ----------------------------
DROP TABLE IF EXISTS `v_file`;
CREATE TABLE `v_file`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '文件类型 1图片;2附件',
  `data` mediumblob NULL COMMENT '文件',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_file
-- ----------------------------
INSERT INTO `v_file` VALUES ('38ee9604805e2cfcbba37058da9ef183', 'pic-2.jpg', 1, 0xFFD8FFE1001845786966000049492A00080000000000000000000000FFEC00114475636B7900010004000000500000FFE1032C687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F003C3F787061636B657420626567696E3D22EFBBBF222069643D2257354D304D7043656869487A7265537A4E54637A6B633964223F3E203C783A786D706D65746120786D6C6E733A783D2261646F62653A6E733A6D6574612F2220783A786D70746B3D2241646F626520584D5020436F726520362E302D633030322037392E3136343335322C20323032302F30312F33302D31353A35303A33382020202020202020223E203C7264663A52444620786D6C6E733A7264663D22687474703A2F2F7777772E77332E6F72672F313939392F30322F32322D7264662D73796E7461782D6E7323223E203C7264663A4465736372697074696F6E207264663A61626F75743D222220786D6C6E733A786D703D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F2220786D6C6E733A786D704D4D3D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F6D6D2F2220786D6C6E733A73745265663D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F73547970652F5265736F75726365526566232220786D703A43726561746F72546F6F6C3D2241646F62652050686F746F73686F702032312E31202857696E646F7773292220786D704D4D3A496E7374616E636549443D22786D702E6969643A35353735393233463334344431314542413634354645413337383841353341382220786D704D4D3A446F63756D656E7449443D22786D702E6469643A3535373539323430333434443131454241363435464541333738384135334138223E203C786D704D4D3A4465726976656446726F6D2073745265663A696E7374616E636549443D22786D702E6969643A3535373539323344333434443131454241363435464541333738384135334138222073745265663A646F63756D656E7449443D22786D702E6469643A3535373539323345333434443131454241363435464541333738384135334138222F3E203C2F7264663A4465736372697074696F6E3E203C2F7264663A5244463E203C2F783A786D706D6574613E203C3F787061636B657420656E643D2272223F3EFFEE000E41646F62650064C000000001FFDB0084000202020202020202020203020202030403020203040504040404040506050505050505060607070807070609090A0A09090C0C0C0C0C0C0C0C0C0C0C0C0C0C0C01030303050405090606090D0B090B0D0F0E0E0E0E0F0F0C0C0C0C0C0F0F0C0C0C0C0C0C0F0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0CFFC000110800AA00C803011100021101031101FFC4009D000100020301010101010000000000000000050706080904030A0201010101010101000000000000000000000000010203041000010303020402070506030607000000010203040011051206213113074108516171812214159132522309A1C1D1426272E13324B18292639316F1D2435373B3281101010002000601030305000000000000000111022131415161031281B1C1F07113A1D1224262FFDA000C03010002110311003F00EFE5028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A050629BAB7CECED8F0FE7F776E5C7EDF8C41520CC7D285AC0E7D36EFAD7EC48341E8DB3BBF6BEF3C78CA6D4CFC1DC103805C882FA1DD0A22E12E049BA15FD2A00FAA8323A05028140A05028140A05028140A05028140A05028141E39F91C7E2A2B93B293A3E36133FE6CB94EA19693FDCB59007DB41AB1DCCF395D9FEDDE37213DB9EF6E718D4954B7E0A14986D5BFF724949163E050950A5B85933C9CBAEE37EA7BDC7EE199587ECB619685B8A5371D9C734E192B48E1ABA9A16F70BF020357F4D4B5B9A77603DBA91DE7DCEA9597EF0E060C69D249718C92E6392320B24DC079A597929B7A7A80FAA932CED2745D989732D829ED65309909788C9302CD64213CB8EFA47A038DA92AB7A45ED5596D46C7F359BFF05D189BBE1C7DE501164AA5A8261CF48E57EA348E9396F416C13E2BA0DBFD97DF7EDBEF7E8B1133430D957884A70D97022BC547804B6B2A2D3A4FA1B5A8FAA82E3A05028140A05028140A05028140A050446677060B6EC5F9DCFE621E1A2F1097E63E8652A205EC9D646A3EA1C68282DCDE67B64E24AD9DBD8F9FBA64A4D83A9418516FE20B8FA7ABEF4B447AE832AD97DFBEDF6F05311173D7B6B30F1094E2B2FA58D6B3C02599014A65C24F2485EB3E291419CF70F2F3B01DBFDF39EC63C23E4B09B7F273F1EFA921610FC688E3ADA8A55C1565241B1E741C76CB67B74EED97F3DBCF724EDD32429451F32EA832D827936C8514A45BC2F6F5507AE2B119680CA5A42504692CE901363E1A795A83EB8BDAF87C4B6EB787C543C534FACB8F370D86D80B5A8DCA941B4A6E4FAE8B6DA9318BF42688FEBE97FD340FA60FC340FA58E234F022C41E44505A3B3BBA1DC1D91D267119D75FC7356030D3EF2A2691C92942CEA6C7A9A522836A3687997C0648351B76E2DEC04B364AA7C60A93109F151481D547B0257FDD41B1389CD6233B1133B0B938D9488AB7E7C5752EA4122F656926C7D20F1A093A05028140A050281411F92CB62F0EC19595C8C6C74717FCD92EA5B06C2E40D445CFA85053BB87BF5B571616D61E24ADC1213C94906347BF882E3835FD8D91EBA0A177277B7B839CEA33065B5B721AC14F4B1ED8EA904F253EEEB55FD68D1414B4D626646539372129F9F35EFF0036649716F3CAFEE71C2A51F79A0F02B180734DA83E0AC5850524A2E950B28117041E608F1A0C9A2EEADDF8EDB79CDA50F3921381CEE32562A4E2E47FA861B665B2B654594386ED148592036A48BF30795052ABDB395C7D8B444E69207C49F85761E949FDC683FD6162E1B75050B4F34A8588F71E341914552EC34AB58F41FE3419146D0E58286927C0FF001A0944C00A17039D07D3E987F0D07FA318AFC34018B57E1A0FEBE987F0D04A629FCBE0E5A6761E7C9C64B40B07E338A6D447E125245C7A8F0A0BE76EF982CEE2836C6EA88D6663A782A6340312ADE92123A6BF6694FB68369F6E67A0EE7C263F3D8E4BA885926CB8C21F484B80051490A00A85EE93C89A09BA050283C73723031AD75B2135884D1BD9C7DC4A01238D86A22E7D4282B6CCF76F6FE3F5231CC48CC3C39148E8B5EF5B8357D8834152E6BBA7BCB2816DC375AC2C75020A62A6EE107D2EAF51BFAD3A682A998DCC9F2172A7497A64A5FF009921F714E387DAA5124FDB411EBC773F8785079CE3ADFCB41FC2B1F61F7683CAB823F99341E5761DAF64D8789A08D7DB65A054B504DB9DCD06313726CB5A8343ACAFB07DB4182E4E54997219D4502DA884816E4078F3A094C4A89504B80A09E57E47D879506751E30501C2827A330B4580E29E7A4F2A09961B69CB058083FB2824463936B80083E2283FDFA68F4507C8C34025294F51639A13C6DED3C87BE83E4BC73AAE0A2965279E9E240F5A8FEEA0E2F67FCE2771B72798BC241DA396FA6F6E99DD11B070B6D869B5379188B9498AEBF296A495A96E82569B2868F86DE370FD3DF6AD911FB7FB6991C9B8EB03FEB2E82C0A0507C6412961F5036296D4411E040341A1101731C0D3EF38B71F5B692A924DDC3A8057C4557D5726FC68315EEAEE0EE4EDED9F3329DB2D918EDF3B9A382462E64D54349401F7D09D275ABFA0AD20F82BC2A5B7A37A4D6DE35A59DAAF3C19B44CC861BCC16C27367AF1EEA913B736358750D413AC2744E80F12F2424A87E637AC78DAA4B5BDBD73A70FD77742B0594C0EEBC4C3CFED9CBC2CFE132080B879580F25F61C49E3C168245FD20F11E35A972E565971526B843D0288F2AA15BC3DB41E3763A4732122DE3410929D6DBBE9487143D1418DCC92F281090103D54189CC4AD649512A23C4F1A0C72432B59D28495A8F8017A084918E92DBEC3CAB14A52E05A05C91702C491413788427A8028000F81F1FE3416363E08501D1568FE9E69FB3C3DD4193B509C4005C6EC9FC69F893FC47BE83D3F280804588F4D07D9A0FB0ABA4DC7883413B8F86AC8446A53FC03BAACC2382404A8A789E66F6A09218D42404A501291C9205044CC6E3AD33A147790F641B61578C8214A429C4A837AC7F282478D07337B33E4376BE0B7EFD7BB9B995647766D9FA6E7DADB98157CBE25121E7DE5B2E29D5212EACA171FE24A42124FA8DAA677BB5B64C74C7DACEFF00774DBF8FE3AFC739C71CF1FACBE7B747753B410D78FEDC6D982E6424E55715B7DB5642614A9F74892EF1594848BF870155CD65502823733323E3F0F959F2DC0D45830DF9125D3C025B69B52D47DC050736BB6D97757B2F03236FED8DC3271F3326CB18B772E4BEFCC8B30EB7A78716BD6D4665D52D3750242523E0D245675DA6D259CABA7B3D77D77179FDBC2D7C9EE6DAB88670EECFCCC66139E9831F87597129EB4936FCA49514FC5637D3CC8E40D69CD5E7773CBC76E7BC78E545DE183072086D6DC1DC7014636423858D2AD0FB7625247349BA4F8A4D4B1BD77BAFECF9F64BCBEF6C7B158C938BD8B027C37F221232537233DE9465293FCC124A5849FED6D269260DBD976E1D1743FD16AE2FA8FA055610B21C7177084848F4D041C8696AD5A95CF9D043BD1F81E02D4103223AD5AAC9B8F4F85044B9053CDC3A8FE11C050453ECE8B84A4247AA834B3CE665FB83B67636D1DC1DBACDBD82CE40DCCD071E8EF069C759763BC9D050AB8751AAC549208A0F27966EFA6F5DFDB853B0BB8D8DC6B7B8CE31CC843CDE37F28BA960A42D32A29E085106E14026FE8A0E88E0A23E95252B008F050E23F8FF00B682CE870EC8495274DC581F0BFB79507A578665DB94A7A4B278AD1C2FED1C8D047BF8794D71E987D1F89B1C7DE9E7F65E82576FBB0BE92C34DBC891223A9C44961A50529A51714425CE3F01B71B1E341EA90E28F350693F811CFDEAE7F65A8310838EC56DD8D2A2E29831D8993246424214E2DCD52252CB8EA8170A880547801C0780A0D76CDF74B13B1B7C6FFCC6F79589DAFB5E3A20416F393672532DEE8454BED2188494971D4A9725CF89279F0B70E3CF59AFCF6B2E6F09676E7F795E8F6FF24F569369FE3C6EB7BF2967D2CFEBE5D15EC1EEAC3EF6ED2ED0DD1B7E5A67E1F2CDCB7204B40290E36998FA02B494A08BE9BD88BFA78D7479D705028307EE738B6BB6DDC071A88F4F711B6F2A5B851D3A9E795F28ED90DA7C54AE42A6D6499BC9AD2676933852DB69AC5C9DB9816A1B2DFD2FE9D10C06922C94B5D1474F4F2B585AA6924D649CB1C3F637CFCAE79E5417982F2AD83EF46C8C76D9C665E46CE91B7F7135BAF092E0A02929C8329282975ABA6EDB893C7A650A0AF8BE237BE99599B21ACFC769EC366233EE2613F2598D2969210CB31BA48690164AF58585DD254A0A201F81205816D9D1299BC5459D90C5C19492EC777AEE3F102CA50E29A4A0A3AA9491A8026E01E17A23D0FC41A8929BDF99A08C7A3A53CF85FC28229F8CA3C93607C4F3FB2822DD88900EA1ABD17A0897E31E200F60A086911B8116F6DAC7ED3C87DB418F4A4845EC388F47F1FE028343BCF4CBCDC5ED661DBC1CD771F2A5E7DA42D4C2B49523A2E137FDC7C283543C85632740EFECA54F71C7DD95B7A5B9D671454A51B80AB93C683BC7816AE13C3D141694068E800A75022C6FCFD9FF008D04C261A176D0341FC3FE1FC0FBA83FD31148FBC9B007EF0E5FE1EFA0AC27BD84DA0C6E4CF378C71121F5AE4E695058764CA94B64A9280965B0A52D5C6C1291415D6EBEF2EC4DA1B662EEDDE19F6B69E326321F8D1B2A0B13177FE44C437754AE1C82683977DC0F3C4980FEE287D90C5CD8C8CF4B5C99FBBF71497673C5C3C2F0E2BAB710C22DF7524903859228342F706E2DD1DC19D3DDDFB9BC8EED9190929991F2EE90B938F9291A52E3772905A29B2548240B004588ACDD65B9E57BF8ED7BF8EB3A73B2F4D2E67C6DC4E7E33F8CF79F59DADEED279A7EFBF96CCA7CAED6DE5367489A965E8DB61683260CC43E74B4E3C87B810A1C8849500782856B318B2CE6FD237927EF3EEBEF9F67246EDDE999C76737163B704BC44F958D8C22B6DAD98F19F5C75A10A520AD95BEA6F526D7484DFE2D4491B2DBB379ED2D8789FAF6F4DC78EDAD85EBB517EA9939088CC759F5696DBEA3840BA8F2141A53DF8FD427B15DAEC3E5626DECEB1BEF71AA3BCDC1305D4A71487CA549417672BE171215CFE5D2E9F0E141A7FE5A7CF8F6F33982DBFB5F7BCE8FB79D60FD2E36E20E95C343C9514B6D4B0B01C610A4DBA4EAC6950E0B295037CEB3E3C3189D3F5F8FC3D5EDF57CB5FE4D33675FF9BE7C5FF5BD797395D3DC7651892CB2FB121B911E4212E4790D282DB710A174A90A4921408E44569E549B8DC791F16AD0EDAC1D4F03EC3E047B682998F8CDB0C77533AF6250E9DD4F4169FDE2F15BE595345BE9C2E987096D240D5A823D373419DBCC9503A881EA14116EB2948E1606822DE686924D923F11E03EDA08A90D2522E4837E57E03F89FB05041C90900DACA1EB161F60E7EF26831C97C6FA88E1C87F85062F293CF8D0683F9EC98E63BB6BB725B4C990B467D294B7FDCC2C506B479149F26777C96ECE4A5B5AB07350CB6937B5C03C4FBA83BB38048D28F7505A9000D09E34132DA53E9A0FE94F1095242B55C148B93C2FE82083FB6838B1E69FCD0F71FB339077B2FDA88515796DB501AFABEEC2C0EAA12F02E24C587A9CD094855AFF00191E27C4872993BC376EFBCF49C8EF19992DDD9E9AA2B919198E2A46917E20151294247AED413F8FC6627273E543C474E44DC732A95952874B715865040716F3894A8AB4DC0B21254A55929BA881417B2E04283B761A3071DEC389AB0CBA596FA79679600EAA5E703331E60A755CB119A538804759E41A2EB3364EEF0EEC7E6E3F37907E548969898969C69DEAFCFA9B5B919B0C33D16E675FAA1A0D1754A61C6DE4E953812791C698C706FDDB5BBDB79F8775FF004B0DB69DADE5926E39B07A4F6F6CBCB6165417A9B7988650A0A1C15F081C470F456DCD917EA5F36345F2C3908F322B0F46CBEE2C5E3DD98F589841EEB7FAB68106EB6F4F0F69E341F9A1CBED663212BE81975438F908CA2D898EE5A137906D6A55C071A79D4EA49BF04AC855B95040CCD8B90D9B2DE625C67223C404AA42904296857117D7C828588E163CC134C35AEF75CE2E33C1B3DD84F399DC9EC4CC8F8687933BB36705DE56CE9EB5BADB609E2633A2EA615FDBC3D293465DC0EC8F9A8EDA77BE0A0ED9CB7D3F71B480AC8ECEC894B53D936B9284F2791FD48F78141B12CE4512E5B080B1AC36ED93C351FBBCBC4DA83EEF24DC851B1FC1CD5F60FDF6A08890EB69BDEC4FB947FF0028FDB4109224712473F051E2AFB7F85041BEE0B9F12799A08290E037E36B785063F29D049B50637295C4FAE8341FCF9F1ED6EDEE16BEE26BFF00A5CA0D69F21EB8E9EF4CC5AD414B630325434F1D254A4A7C3D44D0774700F24A10A4282927911CA82CD8526C91C6DC2824BE7C049B1E439D0789CC80BFDEE141F9AEF3F8CE465799DDD52F1EF38CBD162C2525F69452A492CA6D622C45053BDBEDE2BC348C164774EDE67772A7651E852B1CE94B1F35A63FE4990A09FCC0952C937E3E8E36A0B27B7FB5518471ECBE31A0204390B7E7C4482D9F9E0A57E5AF81D4DC141086CA3527AA5C778A9B4A682C74E27252F179DDD69DBCF4BDB18B79A323FD3AA4474C9F88C643A10D494251A8FC376DDFC484247E7BD36CE383A7AF79A5CE33FDFBAA8CBC85CA7D08EB0910E0B9F30EE41B4B485CAC8A5CB9714A61A6DA52D2FADB2DB81B4AAC1F4383891493130C5B97E8E7F4E447FF009676A3DD24321FCAE5C21A6D21284B6C4A5466D2903C1286929F755441FEA671997FCB2BEF3C567E477462A44765B2038EBFA5F6D86D0A3709517169B13C8D0705DD654C450DB364332981226B2C3886E32D4459D70C65E3A5B25B52812172CDD6085150BDE83DADE1E36436C370C0659878E516E326C7A7092ED9C494A0B8F044670AC6B6D2E2DB45C3D1D7D3D49486BF4EC642C24E9F1B1B11A8F9A8CA59730720842D4527E3E81570558DFE1BDE8312C6E477FCDCD44DC5B7D5236FCEC32C29BCC34B31833A0DFE374A9094E93CAE6E3C283BB5E447BD7BB3BDB0A6B5BDB250B3B9BED83E035B8D96C3866373633AC11D5F80A56820DDC48BA9374AAFF7A83A29264294142E027F02780FB050413CE5C9E34110F39C0DCDCD044C876C2820A43A395063F25C24AAC780A0837D4542839FFF00A83C79ABECD6165465A5086372464AEEB095ABA8D38068078AAD6E36E541A99E412264A3F7633921F61D407302E965E524E95243A9048245880480683BAFB36736A29449494036BB82F6F7F3FDFEEA0B812D292D8723AC3CD9170073FF001F75047BB388B826C7D1411AECF37FBD41F9FCF3AAB94FF985DE12A2252E6A6A1A5C413626D1D16B5F87A6828ADBAF0623E3B2AFE2D529FC64A75D851CD87FAC794C458A08208B975D49F6034174E43764931F1D060751B663AC468CF8559C754121CD608E45C4DD77FC5C3D341BC79A53DB0BB57BA76E3B31982C428CEE371F0DB4308764E59F2F200592A4694B8F305C2129D411A124E848B45E4E7CC4C0CA7186984C15A141C79E763B6955C2A4BDD5B91CC2946CA23C3570AA8FD23FE9FADB08F2A3DB6723A838D3EF6696978710E5B2F2D1AC11CC2B4DC7AA82AFFD52242DBF2D58F8ADACB6AC8EF5C53095A4D940A234D7D247AC1681141C02C46664A8A61CF5C8965D901786831FE60BE5F082B5969315E8CE38BD014012E00DA194D8294AB505A98B5398B0EB60FD4E3CF2A1D22352946EA0E255743654AD4A5858284295721494ACBCA5053FBBF70627694BCDADFC0C7DCF93CAE3CE0912DE526F8F2E2BA912534E142F5BAA433D252936176D5C6EAA0A237AC3CDE4B704DC689721DC5432DA6143D47A4805B4A8D923873278D075BFF4A98ABC3B5DD386F0B2C082B1EC51788A0EBB3EFDC1E3410EF3DCF8F3A08979DF88DE821DF76F7B9E1E1410CFAEF7E1C050463ACAD4093F0057117E647A8733FECA08990A61AE16D67D763FB38A47EDA0AC775F65A3F98ACF6C9ED964F3FF00F6EE2A7645FC94F9062FCE757E42329C4B496D4B423A8415290564A4589D2AE541BBFDA0F25DD8EECAEE17B756D5C66527E766608EDFC8C8CC4F5CC61F8AB750F384C42131D0A5A9B4DC21B4A787048A0CBB71F6371E95BB90D9EB105C3752B0EF28F44F89E9386E53EA0AB8F5A45053D2D194C0CA7614E65D832DAE2EC67536B806DA803C142FC94381F0341F0732B165A4A6527A4E1FFD51CBEDFE3F6D0414D43ADDD6D2BACD9E22DCC0F58FDF41C0FF0037F9661AEFEEEBBEA79A79B88AF996ECA401F2E8B1E04DC1F550575B6D954EC3E312C27AAD0DCB064CA70102CCB287949B1F1BBBA387B3D1413525A9192CB40C5C1714866234CB6C94D94B6D488CB2FAF4123A859664B2F945EEA4855B91A0B39FDDEEE6E3B69C8E4E5EE1B7591060C87152188EB57C0EF5253AD6A5EA28BA917EA148D250B40E007320A6D38FC5A953266E5DD6EBB8DD9D1F1EC2A44F7253A8D0E3EDB2D9B943215F0F3017A12924058487E917CA376EF25DA6F2E5DADEDE65997D8C86D8C73F1DE6E4B6597885CC7DE6D4E3678A14A42D24A4F117B1E341417EA6300643CBC62DBD214B6779E35C66E6C75FCA4E426DEB3AAD41F9FF008FB764BD2A0488CDA56E435AC1EAF0694DB85B70870DC00821A50571FBA48E54199E4F23F4C5AA23719C85AE334FBDADC2B5A00486CB692B01694D92A40D435694A41A0AC3736023E7A32C896946B69E83A520EBEB2409312C7FE5B8CE91FF00C8A1E34186663298E852DC5BBA55216DB4A581C4925B49A0E95FE9AD93F9997DD19013A038CE3F4A4FA017AD41D537265F85ED41E376483E3411CE3BA8DAF41E65B2BBFE67E58FC27EF5BFB79FDB61411AFBCCB57D035287F35C13F6F21EE1EFA0C7A649250E2DC586DA48D4E2C9D2903D2A513FB49A0CD76A768B79EF32D486617D0F0EE58FD672695B6148363A988DF0BAEF03704E8428725D06D66C2ED06D5D86EA3251D2EE5F708694D2B3932DADB4B96EA263B69010D2556E36BA88E0A5AA82D5A05043E6F6FE1F7144F92CCC06E6B00DDB2ABA5C6D5F89B71242D07D6923D141ACBBD7B319DC5A5E9DB656ACFE3D3752A01D299CDA799B0F850F01FD3A55E012B341AED227C863E662DD4D290A5B1263BA9214DB9621495B6B174A85F885007D228380BE6D62CBC177B7390E0172541C563F1F117254DA120E861213A92D250DA4903925207A0014183ECFDDFF004AC2E390E0447565A4CB44790E25452C488E5B75875494DC940700D6071D05447116219CCC8F9454638D18E7D795CC3C5DC9411D153CC32CAC962210EC88C99096D655D27E3BC95A50108572B00E8B7966F20FDDDEE7428B99DF0CB9DBAD8B2743F1A4E5146465A524949D51982FC953685A471539254922DA109500B01D9DECEF963ED0F649C5E53696DA6646EC911D11666F3C825323225840B06197143FD3B22E4F4DA090492A5EB5952886C0D073DBF530C3E732DE5CE13D8390984ADBFBC7179BC8E41D6D6EB2C47C7B12DEBBA1BE3A56E04377F4A873E44389EDCFC4B98E7329870E3D85C8C56E7E25D6AFD475B755F0285828F51B73804807E249480A570215039372460E450A31E4C761A2F62E731164B6D3AD3AA1705D70BCC2D65C4B6969A8CE942429674A00D20315FA9A985210B72EEB39165D52EF74A94D65A3A144787C42FF006D0627BB1A658DC1924A02436DA9296D5FD094809E3EC141D1EFD376425323B9CA49BA54D40B2BFDE7A83A97F364DB4DD449B0141FD126FF009AAD04715213F787B78809FF00788A0F92E7B4CFF942EAF0524F1FF8F81FF840F6D045C89F66D6B79D4B4CA78AD4A21084FAC93C3DE68338DB3DADDE1BBBA721A85F42C53963F57CA216DEA49B71662FC2EB9C0DC1574D047DD59A0D91DA3DA0DA1B516C4E546567736C90B465F2212E16962DF1476400D3363C9494EBB702B5505A740A05028140A0AF37BF6BF68EFD6CB99784A8B96423446DC104866636072495D9497523C10EA549F1B5F8D06A0673F4E3EC66F185BAE46F67F2B9DDEBB94A046DF2C3BF29271CDB28D2CB51D805C654817BA92EA560F869A0E7DBDFA52772F01DC1C7E3B1F2B15BAB64B4320BC2EED6E4271E60C87D29F979136139D55EA6D490AD2C875248F006D41D0BF2C1FA7B768BCBC478D99CC4A99DD6EE22ACECBDD19F2A5C465F3C54A858E5296D37F1710B5EB73C42C72A0DFBA050283C790C7C0CB40998BCAC263258CC8B2B8D3F1F29B4BCC3ECB89295B6E36B052A4A8120822C450718FCC67E9F990D94CE7B3DD888BF37B065A9FC82FB7FAB53D867DD3A9F6E197350721BC45F42812CAC5CEA656E94871E7298F7B11BA5C9A9C42DBDC3897CA72BA21B3F52614B1A5D4BCA7E6C75475AD048EB4961C500752643DC16421F71C38D124B12195B6718EADB9B164B0AD4C2A3420CCB93D05FF3B71D9871D9D7FCEEB8AB1341566F3CDA5ACB2B5A4ADF723467563C3538CA55FBE83A19FA716724A5FDE9F024B5929B0E2CA252B250D25A90E021484A824EA48175D93E17D45370EB59C9A1B04322DE174DC7DAAFBC7DD6141E27B2074EB75D4B6D22D72A21084DF80F4017A0B336AF6A779EEBE948543FFB7712BB1FA9E51B5A1C527D2CC3BA1D5FB5C2D8F1495506CBED2ED3ED2DA6B626A232B339A67E2466723A5D71B57A586C252DB3ED424288FBCA55059940A05028140A05028140A05028140A05028141CFDF371E45B6777EE049DCDB571787C5F71E132B30D33E332614E50BA836E38A65E547528FF3A50A493F79049D403F3CFDC4ED9771B68CCDDBDBBDF3B3F31B7772ED9847252F0723A6F3CFE3212EEE2632A3B2CC71112549711D04E851172546C283C3B3FCBC6F1EF2EE68E8DA7859D9D7A5C58A1107171D72DF094349495381034328E1F7DD5253EBA0E88796DEC6EEEF2EDBC3B93B4F7AE157B5E7CC4E35CC76324486E4B921A710A57CCA1D63530A42D7740095A8EA4A936B8341D27D9FD99DEFBA437265C6FF00B5F12E71F9DC9B6AF985A7FE543BA1CFFAA5BF48D428369B67F69F67ECE5332E343565732CF14E7323A5E7D0A3C096521296D9F47E5A524FF3134165D028140A05028140A05028140A05028140A05028140A0A97BBDD95D8BDEADB5336FEF0C68321C8AFC6C66E28B66F2107AE9B28B0F817D26C0A906E85586A06C2C1F4ECAF67F6AF633B75B7BB73B45A2A8184634CAC93A94891364AC953B21F29E6A5289B0BFC29B24700282C87B178C91362E4A463A2BF9184952614F719429F652BFBC1B7082A483E36341EEA05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A0507FFFD9, '2020-12-02 12:35:40', 'test');
INSERT INTO `v_file` VALUES ('9b9450797293c83fdfef0398e33fa811', 'pic-1.jpg', 1, 0xFFD8FFE1001845786966000049492A00080000000000000000000000FFEC00114475636B7900010004000000500000FFE1032C687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F003C3F787061636B657420626567696E3D22EFBBBF222069643D2257354D304D7043656869487A7265537A4E54637A6B633964223F3E203C783A786D706D65746120786D6C6E733A783D2261646F62653A6E733A6D6574612F2220783A786D70746B3D2241646F626520584D5020436F726520362E302D633030322037392E3136343335322C20323032302F30312F33302D31353A35303A33382020202020202020223E203C7264663A52444620786D6C6E733A7264663D22687474703A2F2F7777772E77332E6F72672F313939392F30322F32322D7264662D73796E7461782D6E7323223E203C7264663A4465736372697074696F6E207264663A61626F75743D222220786D6C6E733A786D703D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F2220786D6C6E733A786D704D4D3D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F6D6D2F2220786D6C6E733A73745265663D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F73547970652F5265736F75726365526566232220786D703A43726561746F72546F6F6C3D2241646F62652050686F746F73686F702032312E31202857696E646F7773292220786D704D4D3A496E7374616E636549443D22786D702E6969643A34334439333138443334344431314542423131434636303930324646313733362220786D704D4D3A446F63756D656E7449443D22786D702E6469643A3433443933313845333434443131454242313143463630393032464631373336223E203C786D704D4D3A4465726976656446726F6D2073745265663A696E7374616E636549443D22786D702E6969643A3433443933313842333434443131454242313143463630393032464631373336222073745265663A646F63756D656E7449443D22786D702E6469643A3433443933313843333434443131454242313143463630393032464631373336222F3E203C2F7264663A4465736372697074696F6E3E203C2F7264663A5244463E203C2F783A786D706D6574613E203C3F787061636B657420656E643D2272223F3EFFEE000E41646F62650064C000000001FFDB0084000202020202020202020203020202030403020203040504040404040506050505050505060607070807070609090A0A09090C0C0C0C0C0C0C0C0C0C0C0C0C0C0C01030303050405090606090D0B090B0D0F0E0E0E0E0F0F0C0C0C0C0C0F0F0C0C0C0C0C0C0F0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0CFFC000110800AA00C803011100021101031101FFC400A100000104020301000000000000000000000005060709040802030A010101010101010000000000000000000000000102030410000103030301040607030907050000000102030400110521120607314122135161713214088191A162231509425224B1C17282B233442516D192A24393342653738354271101010002010401020602030000000000000111022131511203417181F06191A1B122C104E13213FFDA000C03010002110311003F00BFCA0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A0280A04CC9E6F0D85692F6632D0F14D2CD9B725BEDB2147D09DE45CFA8503226753F0E9BA70F8BCA6797A80E351FE1181E83E74D31D2A4FADBDF57099369FE6DCCB2174B4315C65B50B1D887728F58FED25C5FC23683ED6DC14E0E4DF9B855E713FF9166B29C91B3DB1E5CAF2A2A87705448698F1D7FD76D469930CEC5C4CA600213C6F352717191EEE2645E763FD490C3CA0E369FBAC3AD8F55321EF079D4F8F66F906117B00D72988DD29B2076A9714A448413FBADA5DB7EF503DF179AC4E69A5BD8AC8313D0D9097C34B056D288BED711EF215F75401A8A53A0280A0280A0280A0280A0280A0EB79E663B6A75F750C349F79C7141291ED26C28119FE458D6D254CADC9C400522320A92ABFA1D3B5AEFF00DEA04393CAE61DC22C06981FB2E48737A87B5B6EC9FA9CA047FF0054E71974B85C8D2117F1C55B4509B7A10B4A8A937F49DFECA217A1736C73D6464197718E697755F8AC13EA711AA40F4B894D158DCD399C6E378781918D3625B2928458B35D4AA4302EDB8E156D69682AF72DA287B6AC994B708F5798CBE79BF39CE49224C659B8671CE086D03EA54721FF00A14E9A5E0EAC163131633CB911E232CC977FBE96940F3977FDF74DD6AFA49A8ACD11544DCF7F69A0C8445A0C944709D537493DE28329017DE02FD63435464A128569D87D074350717B19164BCDC87984994C8B3335054D486C1EE43ED94B89FEAA850C15626433F8FB25A9C8CB471D9172236BA05AC12994CA6F61F7DB5A8F7AA81C71F944129BE49A770CA1EF3927698FEDF886CA9B03D1BCA55F7681C89525694AD0A0A4A80295037041EC20D07DA0280A0283838E36D214E3AB4B6DA0152DC5101200ED249ECA040572BC19B8872CE5542FA63DB5CA4DC7729C6829B49FE928530658ABCFE45EFF00B5C62223646AECD781701F48698F312A1ED7134184E3D947FF00EE72AE004594D446D11DB3F49F35D1F4382831530E2A561FF2439213D925E2A79DFF00A8E952FEDA0E2F249B926E7B6E68131E6E8139D4697F45023BAA4824255750EE4EA7ECA043C8E0E16551E5CEC5B4EA4AB785BBE03BAD6DDE1D6F636BF6D596C4B2536870C7F18B2FE132CEC476F70C39728F6050D7EBBD6BCFBB1E1D8B3179066F1C4379A81E7B634F8B67507D771FCE05312F4336753C60E571D904A4B0F8DC75F2D5A1A975C352CA574B63E8A8D3B437EAA23B52903BAA2B91008B117A0EC42948B58DC7EE9D47FB683252F376FC4496FEF5EE283987DA1653454BBF6291D87E9A0C7676C35A9F88B5634A373AE2632B6A17605477B44168955B53B377A08A093680A0280A08F3310214ECF4D724B4D3EFC46A2FC37C4212F21170E1BA50B0424DFBD363551D8A75D4001E41DA9D038DDD49B7B3B4545762549580A4A82C11A281B8A0FBDB41C56B4205DC5042476A94401F6D0603B398FF009614F1FB834FACD85027B8E3EE7BA94B43BAFE23FCC288C35474A8FE2A94E9F428F87EA161555F1486DB49D12DA076F62454092FCC8D621B517D43FF004C5C7FBDA27EDA2119F98F1B948432077ACEF3F50B0FB6AE13C8832A66E243AFADD4F7A776C4FF00BA9B7DB5B9AB376A89B11C9D3F1CF30CBC0169E5A54C9F0A92371B784D881E8AE8E69DF11987CB2DD9DDE08F715A8FB6B1756E6C7847C821D037A769F48D47FB6B1756E567F9AD848515A424F7DEB2D38FC4A6DE14950F49F08FB6838B725979C5B5F16D079001718688538907B0A81D45FD940C9E6B232B95E23911C0795C0C0E714E7951B90CB8C9C932D965D287DBF294B4A7712928BEBB4F71346A7F5BCC266379F7E5C9C5E17982626173726179D096CBE5D8B350C84A56585AD285EF1B828B653700E9B86B5CFF00F59E7E3659DB3D2F7E67F9C37AFA37BEBF3E2C9D71F1DB33B5EFD33C165EE41366469271F8C92B654C3A3E3A48F856402850DC3CEDAB57D09D6BA39363280A06C722E63C738AFC3A737924457E621D721C409538EBA9676F98A4A1009DA92B482A3602E2E6823495D61953CA9AE27C5A56495D8994FF00BA0FA76B774287B5E49AA886F91728EAEC6CEB79B90DE123E3DF406B25116E2D0A4213EE2DB4B61C1B9173A2D66F73E2A821CE6FD7EEB2E2DCE22C7068984E4B3D1C8A135CBB1ADB6EA5C5609D0B44A9403CA090A6D650A096D6A206EAA72DB1779FE123B739F7E4865DC7C077253037E1218653B96A24D907D84D24B788B2658FC0BA9103A93C5711CCF00653783CDA14E426E53062C9DA851412B6D57ED20DAC6C46A0906A4B96B7D6E9B5D6F59707A20B2E1BA7C4E7DFD55F6D197D71D69AB171C4A0F70275FABB683157306A5A656BFBCAF027EDD7ECA2309D90F2B553C9687A1A173FEF2BFD94523487D949DCAFC450EC538779FB6AA1BD3F351D81775E4360E802881F50AB12920BF939E3F82C73EE215D8FBC3C86FDB75D89FA056A466D74B98198E216E4F9E101292AF86869B93617B798BFE64D699A8998890E7AD912636A0FE195121D6F5EC0E0B28115A6527C1859682D36B81246459005E249210F01F75D036ABD8A03DB5161D78DCFB45E4C59495C1987FC2C84EC59FE8DF457F549ACB592D4ACA16A5590E2429C6901B4920127C44ED1ECEDB56368DEA8D392752938892B8AFECC5B6C3ECA2466B2A4350D692A4971B8E10B2F38E1413B086F6EEF78DAA2E5AFBD23E358EE9BF23EA766B11C9F39CFF39D4EC835325265A0B1120B4C799E5478AA2A5ACA405D8936BD8682A2A557B15D44CAC20C3D05AC5E37E257236639B2F292A5385DDEA4BB7513B8EE3B7BFB2AE524C1451D3BCECFE2CDC2E219EC6C2E45889BF9961B92064C89F0E5EC7104AD328A890A4B8A494AF403B0696ACEFE78FEB717F5FB7E3A37A5D65FED33C7C5C7E3E9F2D368394F988E07D4CC4F10F983E54BE65C47372DD1C7A7B121295379410A4BB15F525243A968A9051B0A1292A29D34B8D30BBEA8AD54F9A5F9991F2CD8DE099E97C397CBB0BCA32EEE332E234B11E5444259DEDBCDB4A6D61C4EE36512A484E9FBDA0522FCCDFEA31D7DE65C830194E07C2A174F71BC6A5ADDC365986C6524CA65D052E4694FB8D84F96E0DA548084DCA476D04FDD15FD4A5BE4102061BADD8093C2E5929439C9B0690F4550ECDCE455852DA07BCA2F6F450597F17CE749B9BE05AE5180CDC4E5B8D752929C9A5F5643C4AEC4ED4EE2857DD2907D54097C9786339A68AF1D867E2349D512A514C46C7AC20DD7FF0008A0D69EAB41E4BC738BE5508CB236651818769A65016E2CCA5801B4BAA257B49D081E9AE5EFDB7D75F2D7E2CB7E9999FDBF677FF5BD7E9DFD935F6F1AD9667ED71F8EE9BBA61D40E3B1F1FF00E9775F18B95835A21BA16D9662F98520A12DBA7F0C15017092ABD749AF8CC470CE7AA7A32829870DEFF86A2950F66841FE71547C44969B424A1212A291723B4E9E9EDA0479B9E871D41A71F4A9F57BB191771D27D4DA2EA3F55074819F9E07C362D515A3D8FCE57902DE90D8DCE1FA40A0E638C3CEDD593CBB8B4FED310D21847B379DCB3F4115644B594CE33138E3BA1C169A73BDF237B87DAE2EEAFB6B78632EB90F13737AA84593236851BEA05FEAAA882A0BBBE5EEBFBCE13F59BD6994C98F73F0DB17EE1AD4AA7096634C64312E3B72593DAD3A90A17F48BF61F5D65A3779174EE272484DC6633392C52D95EF61C8D256DB89D08284481775008241B1ECACD6A534E3F4FA371F9097A6E39536C46C98EAD21E24761122DE52CFFEE049ACB479231B8288C3B9B6E58C1060FF0017907B6C42DF7FE336F7E12D3E92951151503F31F9E9E8574F552B14F73085CEB90C556C4E3F8BA7E20DC69675C2AF2506FE85FD141A15D65FD46FAB3342E5749BA7384C1C9485A19CD652D3B20849D06D6D210DDCFA095506A0F1FF00999EB56739861F92758B92C8E573A33CF4A8186CA4966234CAD6C38CB7E4C569B0E00D79854024804F6DEA95E957E5A3E6013F313C0DBE66387CBE1F70DDA34892CC96DF0A5BADF98C94EC7529DCCAAC1D6D06C45AFADA0D6AFD4460C57311D2CC84A4B4E04CACC6322B6F8DCD19139A8AB6D4F0B13E5B7F0E5D5585FC02DADA829C659958D9AEA5E90E8921CF2F28CB850A4BBB0D9D6DD8CB9321B524588285A74EF75A3E20117751781939CC4E33058D9497794496E0E3236392A90A4CE927F0590CA88538DBA2E50A04285949502A41A08EB81C9EB974A3934CC970BE5F3786CCC24B5B1367B4FF00C34225B5596D49124B6D2803DA9B288A0BD2F94FF982E4DD51E994FE4DCFBFF2AE42CE59DC6457F08DADD88F86108F1B2DA51E00493DD40ADD5BCA734CF4FE2984678D2318DE5F2D1D579121A3223B711C4C85485464A8AB6A0A524DC83EAD6B8FBB4D76F19B5F9E3F3C7C7E9977F471E56CCCC599ED6FFD6FEB8FB650FCCEA6C9E87C8E419289B397E4F2728B597C5648F9519C6F636552CB214AF033B823DE1EF0F657A3599AF3DB88D84E87F5E388F54F15E4B6B5F4C39914A08E32B599D8A9CD3EAF29B7E28212509539E05241414288F78104B6984D6E5B698EE22C3C82BCBCBC84C536B536585911595041B6E0DB07714ABBB72CD65A3A23C3C763505BC7C262124FBDE4A0249F6902E7E9341D0F3F7ECAD48CE496F3D6B8AD336925E7BB75D2AA11A4C8B5C77F755420CC7F6B4E92AD7628FD86AA213C53977DBD6F735594D101CFC36FD80D468EA8AE5ED5161C3117620FA2B35A8CBCA281C36501014954672E93D845B5158ADC793FEA8F51398F56390E6B8CE5BA9F96423199495071BC51E94EB5019690FA928434D6ED8E0B7A5415EA3514993BA4D9FE987FA4E3E5314B91379BE31598E39324B888B8F5406D4A0F4B756952D68699082B595ED205B4B91413E70576462FC92A421965D8EB5B73551C3335D09405F9C4B81C5C46AC414348FC5DA52A90FB2549450351E718E53CA1C84FB71B298D2BF3733F18E05B0DC541BBAEB9213919E58B27DD5B8D8495593E6A090682E63F4C08331B6FAED9752DC7B0B9C99C7DCE30B750A6D4C4165A9ED26129B3EE2A2A92A6C8EDD3C5737340E9FD4DD992AE9EF4CA4C7716CFC2F257CF98DA8A5416622CA6C47F44D0555E122E47953D1959588EB890DF919298D122EFB03F0D7E5EFEC5A36ABC08B24F7EFBAC02AF54B0791E37C7224FC63C227246008F039034B3E6631C4901B5B445825E6DD085EFB7812561162A2682B939C2B31C897C7B2D2A54B9531FC721F9E64294B52A4F9EE8756E92755958B127B682F4BF4AF486BA1BC99A236A58CDBDE1ECDB709247D141B1B8C8FF00EAFE499EEA42C830627C66078BA415596DA1E4FC548B683571BF2D247724D624DBCAE71E3FD6EBFA5CFF002EDB6F27AE4D76BCE7CA63E65C4FE33F76A1F56E0BEE669FC818ED2B1B0F20E46CB3EB5007CA9516C5B50F7ACA093623F687A6D5D238535BA2B9D7F0DD54E9045C34A68C38798831E4B615E09313248319C2A0090AF7D2B00DF6AD3FBC0D6B66755E029DB2522FA586BF4561B273AF76FA3B856A466D263CF5BBF535A6492F3D7BDCE9DF5508F26401FCC2AA116448B5FD3DD5436B272BCB8B2D5BB54B2E1FF84D2254458476EF31DFD95594D301DF0235EC028A75C473B056561C515CB11DFDF51A66655CFF0024CA907B22B9FD9ACD6A57904C8E05F1D569B38F62F93BCF9F67C493FC9586DB8DD28C03F9FC3B327938567B1996489DF974F5A9D116234F29301A8BBC9F2F7B8C97968F717E5B485A4A554120BDC35B5C47B2C253CEC57D4B4E365B63F106854A2E2546E9711B8A8A8A9275242D0A2E2960D2C4700970185C3CBBA5D772EBF88CB071C71D26213F811ACE499494A5FB85BBB1765A3734E237A6F41701FA6DA509E09D4F0C8508ECF276588BB8DCF96DC268FDAA52947D64D02FFEA27023E53A4DC6E1AD45331BCE2E5C1D6C1463C378BA9F4DC3654AF624D056641CD60380E3B8FCFC8E497887B25B1894B716DB6DA1B7DBF0193E6784EC5A82ED7DC07BB756940D7E7796832A2A303942E3CD3A5C65C6F76D23627628F875BEE201A0D3CE7D02145CEA042650887262B5322A02400112FF0089B5BBB574D0597FC94655DC2FCB1F5A9E833D18A9699A63C29AA213E5B9210DB62C4DAC4EE36F5D67D9B78EB6F8F971D27674F4CDAFB35F1EB9E33F9738FBAC222E29181E19C7710DA42063B151595D80175869256A36EF528926A7AB49A693593124E9D93DBBF9EFB6D3E6DAD49E4933A7F1DCEA047EA72168E259A4378D565E3B6A726C296F36A5473153EE9528A49F169A58E86D5D2473307A03D05C38CF709E6DC7BA82DF51B85F10CF95B4BC5C77E3E55A5805F8F1B2716413E425B74872E16A0A4EE08001BD5BC24B95BA38F1DA8D6FE11FC9490AC079EF49AD339243CF77935509122476EB55088FC81AF79F45021C9936BEB727BEAA19BC824BC313942C2825D119CD855D80EDAB12A3CC039B9F635F45544D305DD11514EA88EF66BD952A9CD15CB81AD46A32B2AEFF009265BD1F08E7F2566ABCAFE45B42F98CE7368F30659EDA7FF955DB5CDD1B210B0D2F14E6471E8C8BDF091B26717090340DC586A4631205AC3FC3297ED26826BC1F21898AC2BF39DC82A134A61106338854749764AC17C94A9F3B2E94A92EA868A0140A75A068F2A9CDE4A02B218994F2DA9892892EBBABEDBCFAD4A5ADFF000A492A52D6A0ADA3777D977480B50FD3C5B620F0EE77876C9F89852F19232883FB1226C6724211ED11D4C93EB3551D9FA916560717E8FF0013E6F271590CA4EE19CA1BC86191115B62A1F5C392C2BF31371FC32DB716856BEF14D4552873BCA319AC463739819EECDC14D69C3124A5C5ADE696545C7634AF29485A5D64A825442937165EE0856A086724AC8F17C2F24B9757882BC4E6568174A8348F31979253BD2429B4941295AC05A1092B538F2A8208EA072B6449C63BE68D8D4754046C37DC71CA1115B4FA3736682C9FE48243BC8FE5B7AA512230669C867E230B8E6C4B81D911D8205F4FDB247B2B1ECCE38DB1CF576FF5E4F3E7AF367D6733F79CAD9392474C58698C93E088CA5A4ABD4DA4247F256DC6DCDCAB37AC4616533F221A1E725B2E4F1167351965486FCF8CB095A820E8E20A6E9BD8DAF6AB2E1291FA11936F86F3DE9CE213F15B3393A3E1B91B91DE75B250FA96A8CA21B5ED5797276EAABF814A4F7DEB5B33AAE4D6FD9B49EF205FEAAB12D253CFF6926AA121F91DBAD54223F23B75BDEAA1124C9B5F5B9EF34EA1BF26476EB550CEE452CA7139237FF0EE5BE9156218DC7DFF00E21AB9D74AB44D701EB84EBDC2B2A76C37741AD453A21BBD9F6D458CBCBB9FE499504FF8573F92A2BC938E43247547318E5A56A43BC86530958D47F7EA09FAAB93AB79F13988DCC313F9A629D694CE5D2ECD71F5B818430A908224979D568CF9320C94296AD1B59694AF0AAF40CEC7F2A12732DE3A1C9723C3C4EE8F0985A1D8EFB84ACB8EC8723079A7905D7095EC534EA5B076B6A2D80281FF0092E47C6B8663D1CDB2FB5C88A0E36C624AEC336F9B23E1F6A41023DEDE738916006C46D73686C2D57F4D7778F64B86F557926033D92E40E729E4513299E7B2518C7546C83F0D2A762B276A438D349290958163A81A0ABF09F2747EA40641E87F1C8BE6AD187C9F318B0794B3B8259771AE63F20B7D2F9569B125B4AC5F4DC94DF4A8AA41C0F0EC7F1AFCD995A9CC92A6A88C9629724F90B0D83E5AC5C9297109D52B2C3691E94A75A08F86698E379A9105C8D299C272075B813E5C8507B7842D27628A9B2B43D18AC388DCB2941B3890A1B55411675438762F85E619E273F9262D09E32E4B88DC9C7AD79012DB53FE636EC7F281DE1695024A9435BD058FFC86639E6B88338CE2799564632B951CB4E85F04F9793F06869C6C49602754296AF0ED5582AD75562E36BE3B4E3199F595DFC7C35BB6B8B8B27E58DA5CF17B77EF1641C8B8E729CF875CCFCD75119572A624B9E522DEA870D607FD47CFAC56DC1AEF90E1B80C93FCC787CCCFB5C4A33D090F61B32A4C78B019C836DBA5B5CA2366D0AB9482012413AD0419D1EE9175571BCF7A6F3393C76236331D9E604AE5E892D49C54C8509CF888EA8B2985AD2E38A28F240D0FB9B92800D6B2CE16F6E483B53BB43B4687BB4ADB04A7E46875A2115F917BEB7AD210E4CAB5EC7E9A0419327B75AA8419327B75D7BA8864F25947F28C958F6B247D640AB03538FBDFC4B763F455A268C7BFEE6BA11590F282F760BF65453B21BB702A5567E55D48C1E4D4A27608CBDE7BED6D7ECA34F29B93858F89D46CA64998B2062072398F42CB14A7F15B1217E275BBDD07B894923D95C5D533F2F9107A69C7F1D85C0F27C5E7A5729C7A331CB8E29C5AD087E759A18E428217B1610801E4EC5A5D26CB4D8245063E0570B1B0537C0C89139B25A1887641F828AF01B8B6E36DB2A49752082586197148FF00981BECA05EC87198D9DE4F05395CA4F9CFE58263E425C925D11986D0545B602A3B0DB694A12A094F9883FBA951D282ECBF4CCE5798E57D37EA4FE6B1DB82CF1AE50C603098C6DA4A1516042C6C6F219716903CD500BBF987DE041AA9F291FF0050F8A257CB7651253BBCACE63577EDDB72E209B77E8A208A8AA30E3B0F38EBB88F36E224C098539971C5590B4A77477D0853C124A91E1590DA96A5277A95BD62C0F3E4FD3C811B8DCF7393869309F75A5AF6F89528346CD2503421414AF2D4A3A25B5A893A26C1A61CE7A938A6720E722E9FF4FB1BC59ACE4892A8106530262B1886949063B0EBC9217B544F88A6E3B282DD7F4C6CD9CC714E5931DC8FE6D35D75A7729915A4074CA92D25C79A51B0D1BD894048D06DAC69B5DB3998B2D9F6F874F669AE9678DCE64B7EBFF1D1627C917F84EFB0D6DCD5C3D73DD2A7CBC4392C31072F362094CDBC45D6DA7FE19DDC08212DB86E6C75BEBA559D52F4247CB7F26C8F02CF711E2CF660C783CDF28DC4C9635E65B91159C838A5FC3CA0976E50B594F90A28B13B92A57BA2B7B72CEBC2D152A69842BCB0A487D45E702945477AC0BF6936F6569826C8917BEBA55422C893DBDD40852255AFAFB055420C893DBAD8D037A4C9BEED7E9AA864F2690A56266A100A94B4A4003527C42FA7B2810B8FBDFC4A0FDB56898F1EFE89D6B21E905F3E137ECA078427BB2C6A3450CCBB7E3D9AD7B61BBFD9A8B1E4D71CFE5D8EA764632243831792E412C4986AF134B4F9CE10762AE2F71A115C5D9BADC321F13EACFE4FC922F1AC6F4F339020270905AC739E198D63945B93950EB83F0E5B8E294C36B20A410B746ADD06764F862D94C96A2C36F1F8CC32BE1530548F2D210D12AF2C21D4AD212956A7CCB8B92A3E325E5847CEC796DB7F0EE460BCBF294B6992DB896DC2D635E55998EA2904944B5D94E23CC5B6A6428A76389A0BE0FD3AB1820F487904852149933B389F8B52F5716B8D1198E95B87BD650DA771EF3550FEF9E4644DE887E52A291F9CE7A1426CABB3CD5B5214C8FEB3A948FA69216AA470CFE27038C8AE6594CC70131D993E725C21C692B4EF0437657E1DCAB77626DADFB2A29B7D526A4E4E24C8BE621C5B095B2CB28EC4B614A4A9281FD1B5FD268348E7704992E5456BCB69D6CBB2A6BA54ADBB552CA14E5811D81E4B80D05A9FE9BB838FC5E175030F1D5BAD223CB788ECDEF36F5EDEA160282C3791BA94B6E5CDC5A8350F90F0697D41CC734C6E19D86C6561C0873716A982FE64D0A79B8ED367F64B84949274D413D941AA3D27C2F516675478161B3FC52763B91E3B92C463398C96C2D95476E0C84CAF8D2A71291B7C96CDD4090549D14A2B15AB59C2DB1D937424DF4DA3F92BAB9922448D35EEEEA210A4C9ED37D6AA10A449BDEC75A210A448BE9BC24EA35F4DA831E06372F9E99F0182C5CACC4CD373315B53851EB594821093E95587AE96E164CB61BA6FD02E4D07906139472A97171A9C3C8F8A6B0ED9F8879C504A93B1D52086D03C46F652EFD9A573DB774D74C1E9CEBE5CF8BE7E43D98E2DE5F17CD2EEA723B69FE05F51D7C4D27FBB27D28D3EE9A6BBD9D576D32D68CBF18E41C3E77C067B1CE4272E7C97ADB99740FDA6DC1E150F67677D74972E56585180FF0066BECA21E105EECD7B35A954A59A77FF001DCC907B613DFD9352AC798F18B12797154640325FC93C9613DE5C716B4A6DED26B8BBA71E35C465F168D2E2A540318E57C034F81AA63E3DD722EF1ED71A79FF005F9A7D341B33127B12B1D7CB3711F8D10B0CC272649723871E71484259516D0A5B8A0095848B2F6A55B556D2819B9F446C2E5A638C63D2A99917D52D53D012AF3D6A424175251E1B2CA0A8EDD2E49B6B416FBF206EFF00F98F32C712A53B81E5231D2C9FFEC27158F90F8FEAB8FA927D956A4F94ADF365D2DCB7577A3F3F8CE0DA9CFE52064A1672047C63ECC79AE3F8D597D94B0E48296AE5C09B85285D3700DED485508F54CF217587327C83033788655F3F0DC878B641A5467B11916B47622D2E27C2D9F7DA55ACB410A175514C0CDE4720AC471BC921F7909810998AEC23BD202924A233C94AD0D93E65C34A56DB0525B4DD4B5AAA064F37EA462E108D995EC71E5454C7534084A4ACAD7202891E96DC42BE9A0DFBFD35796FFAB57D469E59447295446FCB41241094BDB4EBEA550591F23415B6E8BFA688AACF9CAE4FCA3A7DC7DFE4BC5734FF001ECB42CCE1168CCC6DC56C22F2C15593ADAF61453FBE5D7E75F23D50E6FC4BA37D56E3EBE4592932632719CD22B423AFE21E6496512021410BDA5492542C7EE9EDA0B0271FB25373755ABBB892244902F756B6AAC9BD224124DBEBF6D03BB8FF004BB9D72729722E28E3A0BA07F98E4498EDD8EB74A482E2BD452823D759BB48B35B53B71AF97AE338E2DC9E4931EE472C58AA38BC68A0DBF750A2E2ADEB5D8FEED62EF5D27AE273C6E2F1B878A88389C7C7C6436FDC8B15A4B4D8F5ED4002F586D9F405027E53138DCD43771F9684D4F86F7BEC3C90A17F48EF047711AD25C1665AE5CAFA23220A9C9FC41C54A8DAA95877943CD477D9A59D163D46C7FA55D26FDDCAE9D914B48910DF5C694CB91A43276BAC3A929524F7820D88AE8C33734F7FE3D9A17ED84EFF0064D66AC798CE2BCD2335D476512C8D98ECF2DD526DD888CFA96AFF008515C5DDB7DC932E5C0D61F1294CD9D9A90EB71994AC203CC89AE85DDC3A212EA1C653BCE88F392B55937341D32A64ACAB11B0F8CC8BD20F1EBA4CA4AD6C99AF8BFC43AEC7F392B4851BED43ACAD28D6C54855A81F181CC46C463A366B91C772562CCB6E271FC6F94B7A4CCC9AD49080C369F1AD214429E03452341E25214A0B93F914E97736E94F4BF99E239EBCB9B98CF7389F9D8F975B2E46F8D8F320C0B3C1978071003A9711E202FB770F0904DA91BAD51519F52BA3FD3BEADE22461F9D71B8D9769F67C84CE03CA96D26E4A7CB908B2D3B49240BDAFADA82997AFDF213CEFA4ED3F98E9B449BD4BE9C27CD392C5C4690331062AECA7029960214F045B721D6D0E388372128BEEA0ADE95F2DFD4AE6BCE57D2DE2DC4E5F24E62CE59729E8CA094A0B12A330EB324804A1A67C9522D736401655AD4169DF237F2CFD50F974CDF3FC0F5070C9C6C8CD38CAF14869C4BA9905093E6A98D85414DA490377793A0D282CA074DF9066EFB9A4E3D85DFF1651283F42002AFAC0F6D118192F958E079EC4E6B1DCA596395A33ECB4C64F1F918A854275A6565C4A36789D41B9F7C39A7EEF7515A1AAFD3BE374C7E62FA79D5DE9BE6DD8380C66598566B83E56EE06D9689517A04E4E8E04A5360DAD2176B506CBC18595CECB7E16271D2A6C865D2DA9B6DA513A1D144F62526FEF2AC3D75DF3870C5A95B07D0ACBCE287F91E4518A64EAA851ECF48F6157F7683EB1BEB177ECD4F5F74DDC77A77C478C16DDC7625B726B7A8C94AFC791BBD2952F447F5024562ED6BA4D643DEA28A0280A0280A0281ADC8F87E0F93B446423044A48B33906AC9791E8F17ED0F52AE2ACB625D656B973CE9EE6B0386CBF969564B1CEC77108991DB52D690A161BDA4DD43E8B8F5DEBA794AE7E36579CDEA5FC9CF583A193F0BD40E75C67C8E2BC8F3539B899964A969F35C4BAE36879B5A10EB25D4125097102F6501722B9E1D3273E0FA79CFA348E3FC131FC733F93EA2E530AC391B0D1A1B92E6C0C54C6CB7100436973CB7571896DC74A5482C14B4EA2E9DC92AC9FA0BFA717307A3C2C9F587922B8CE35296D6CF0CC5F94B95B458ED75D46E698F459AD47DDA82CFF817433A5DD377999DC6389C46B32C3223B59F9491226A1A173E5B4EB80F928249252D04249BA882A24D04B74050140502263B8D71DC464B2F98C560A06372DC81C43B9DC9C68EDB522638DA4210A7DC42429C2948B0DC4D02D585C1B6A3B0D07DA0283A9F61894CB91E4B287D8746D71971214950F410683AA1C1878E8E889022B50A2B7FDDC76101081E9D1200D6832A80A0280A0280A0280A0280A02810F91F1AE3DCC30B3B8E72AC2C3E4381C9A3CB9F899ECA1F61D482143721608B820107B41D46B4097C3B8070DE9FE3BF2BE1BC7A2606228243DE424A9D7760B23CE7DC2A75CDA341BD46C341A5321E1405014050140501405014050140501405014050140501405014050140501405014050140501405014050140501405014050140501405014050140501405014050140501405014050140501405014050140501405014050140501405014050140507FFD9, '2020-12-02 12:35:55', 'test');
INSERT INTO `v_file` VALUES ('be5b713d6b7eebdaf5da2ee8413b59dc', 'pic-3.jpg', 1, 0xFFD8FFE1001845786966000049492A00080000000000000000000000FFEC00114475636B7900010004000000500000FFE1037C687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F003C3F787061636B657420626567696E3D22EFBBBF222069643D2257354D304D7043656869487A7265537A4E54637A6B633964223F3E203C783A786D706D65746120786D6C6E733A783D2261646F62653A6E733A6D6574612F2220783A786D70746B3D2241646F626520584D5020436F726520362E302D633030322037392E3136343335322C20323032302F30312F33302D31353A35303A33382020202020202020223E203C7264663A52444620786D6C6E733A7264663D22687474703A2F2F7777772E77332E6F72672F313939392F30322F32322D7264662D73796E7461782D6E7323223E203C7264663A4465736372697074696F6E207264663A61626F75743D222220786D6C6E733A786D704D4D3D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F6D6D2F2220786D6C6E733A73745265663D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F73547970652F5265736F75726365526566232220786D6C6E733A786D703D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F2220786D704D4D3A4F726967696E616C446F63756D656E7449443D22786D702E6469643A32363633633634372D316534382D343134622D623336312D3735656635363936326434372220786D704D4D3A446F63756D656E7449443D22786D702E6469643A38443530453533323334353331314542383636454241323137374244363643462220786D704D4D3A496E7374616E636549443D22786D702E6969643A38443530453533313334353331314542383636454241323137374244363643462220786D703A43726561746F72546F6F6C3D2241646F62652050686F746F73686F702032312E31202857696E646F777329223E203C786D704D4D3A4465726976656446726F6D2073745265663A696E7374616E636549443D22786D702E6969643A32363633633634372D316534382D343134622D623336312D373565663536393632643437222073745265663A646F63756D656E7449443D22786D702E6469643A32363633633634372D316534382D343134622D623336312D373565663536393632643437222F3E203C2F7264663A4465736372697074696F6E3E203C2F7264663A5244463E203C2F783A786D706D6574613E203C3F787061636B657420656E643D2272223F3EFFEE000E41646F62650064C000000001FFDB0084000202020202020202020203020202030403020203040504040404040506050505050505060607070807070609090A0A09090C0C0C0C0C0C0C0C0C0C0C0C0C0C0C01030303050405090606090D0B090B0D0F0E0E0E0E0F0F0C0C0C0C0C0F0F0C0C0C0C0C0C0F0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0CFFC000110800AA00C803011100021101031101FFC40091000100010403010100000000000000000000060205070803040A09010101010101000000000000000000000000000102031000010304000403050307090705000000010203040011050621121307314108516122321471812391A1B142522415C162B233437334350972829253638316D1A24417181101010100030101010000000000000000000111210212413113FFDA000C03010002110311003F00FBF940A05028140A05028140A0505A27677198FBA5F92953A3FB06FE35FDE0787DF41157B7773ABF81053D11FF00314798FE4E03F3D07663EED1D45424C171AB5ACA6D49583C38F03CA4505E58D9B0CFD87D57489FD5712A4FE7B5BF3D05D999B0E4005894D3B7FD9583FCB41D9A05028140A05028140A05028140A05028140A0E0912A34441724BE86103F596A03F4D044E76E70DABA6032A96AF275776DBFBAE2E7F25043E6E7B2B3EE1E925B68FF62CFC09FBEDC4FDE689AB3514A0505605A82A06C6E3C4785076E3E42745E11E5BCD241BF2859B7E43C282EECED5986AC14EA1F03C7A881FA536A0BB33BA39FDBC14ABDAA6D647E620FE9A0BBC7DB718EA399E0EC503FAC5AC0284FDAA068241165C79ACA644673AACAFE472C403F65C0A0EC5028140A05028140A050281418BB2DB6E69C9990878A8ECB6CE3E4AE23CE172CF15A1285129E6494588550435F9D39C7D4E4F85315C3FC491D737BF87E195585071A67C251B7D4A10AFD972ED9FC8B09A0C0BDF0F51980EC74243F3B43DDF7B98EA43888FAAE1644C8C841F12ECDE50C2081C6DCC4D675D3AF498C43A5FFA85FA79DBE433024FFE5BA9655E210AC7E5B05216A42C9E50166217CA78F0B902A7A4FE6D87D7BBFDD8FDA9E31705DD9D5A54E04A558D7F22D4394140904162516577045BC2B52C4BD2B2CC77E3496D2FC590CCA657C50FB0E21D41FB1482A1F9EAA6573D10A05055CB6485AC86D07C14BE00FB87993F650779980F2ECA5244641E21C9208511ED4303E23FEF14D05E23428AD942C3464BA8E297E4D95CBFEC363E04FE427DF413CC4294A8A4AD454AEA1E2A373E0282E940A05028140A05028140A0506BFC7783B94DB163CB3F311FF025B4FF002505D12BB027C0278A8F90F79A0372634A643A97D8971956FC5E64B8DF1F0E3C4528E8637F82E4997254286B8E843AE30B525B7622B9D06CAE0392E0F911C0F954ABBC2C39EED868DB425E19DD7B1D98FA94843FFC46146985491C002A75B2BFFDD4C5F75AE5B4FA08F4C9B64D627E43B5184FAA6148525511E9B8FE6083708525A79682924F8728A4987BAD87D5347C2F6FB091F5CD3B4B89ADE061DBA18CC406FA57B01CC41295A8D80E26E6A97B5ABE2A636DACA1F43B145810A7DB5A013ECB916FCF465DA6D4D3A96D6DBA9703BFD486FF00114BFF006529B9341DBE886CFE328463E49B075F3FEE83C88FBC93EEA0ED32A085F3466BA6E116329679DE23FDB57CBF6240A0B947638F32AE54AE2A51E24FDF41DD71C6A3B65C79C4B4DA4717166C2825D84536E4043ADBA8750EA8A92A4282AD70381B781F7505DE8140A05028140A050281411ECF6D7AEEB119D979CCB47C7B4D24A961C58E7B0FE68E3F9A83E7BECDEA9B0182DA725174EED9EE7DC1D7F2339D972739878C977A721F3774252BE44AD1703942564FB682AEE677570D2F07AEE5A5F71333E9DA6B6E3AA625EC78743ACCAFA96C2031211D4504F21171CAA04507ECE818DEE468DBF40D2BBB5AA6CD9DDB7518986C23D875C78AB89966DB5224E403CD3E1D50789429285F16F96C15C6A5928897A36ECEF78FB2E9D935AEE6E5A4E6F1AA0EBFAE4E125E910DA4B85AE6427AA4D94B73997604F0BF85306D7AB69D8B1B9BD630F91C74490CE71FCA352B248FA88DD16F1D1BAADBA1B521D490F2B87158F87E217E229A9A96E073ACE7221928434852084AD0CC96652788B8216CA8D81F62803EEAAABFA556B1068394387C2E6DE63CA823F31E7519579A6965B42E235CC94FC37F8D7ECF2A0A996BDD417640663B65E92E259427E65AC803F3D05A1DD91C90A547C04333569E0B98E7C0CA3DE49B7E7A0EB3786765B9F519B9ABC9BCA370C0250C23DC00B13F7014195F566DB671CE36D212DB697D4128480903E0478014124A05028140A05028140A08767FB87A2EAAA96DEC7B7E230CFC1692FCB8B2A5B4DBC86D5F292D1573FC5E5C38F9507C5BEF67AF38BA2F73374C861BB7B89DF748CA4C6A3A58C8E59E5CC71A8F7225B711C8FD38E95A94AB215CCBB5AFCA3E11A9D6A6AEFA5FFA97F6533ED260E6F48D974A79D4F217E1456A7B2DDF85D0B60A542DE5F055BD4D65ED7FBA5E9077D7D6EB7BD6164E6A62795E5ED454DCD55FF549C8250123DC82056315345FA6FEC3E6D2363C0E9981DA722D10EC418F79A6997177B82A7185F2DBCF8DFECA0B1E7BB3BDE0764373756EE4CCED92635FE9B07AD85BF13DC1F3294E7508F625091EEA0BAEB87D576AB242B6AEE26A39AD5984F34BC8E6718A8F2CA13C40BC65B29BFBD5F92983B8BF52CF6B7366BB9BEDFB235D6D25C9FBDC2908C74479C4F0096DA9A86DD7D56F34F0F61341DCD6FD737A75CEBBF4EFEDB2F5C91720A32D01F69BE06D70EB697136F61356CC1B4186DB75BCFC38D3F119A8B3E1CC6D2EC59085D83885782805729B1FB2A0FC94FC74E5A438EC86D0D221B254E150B0056E7FE941D4466DD9854D60E19964705CB73E0651EF24D85073378832149919794AC93DC4866E52C27DD6E055F9A82FA84A5084A129086D1F236901291F62470A0EC8F1A09D6B5FE05DFEFD5FD14D04868140A05028140A050283CD5FAAAD8345CFF71B7ACF617152B44D83B7BBFED78DDE272652D127633FC5A534D4817510B65AE542400029BF947C1C406ABB794DBB777526262F02D605682E3590DAC3686DE6AFCA551D242A43A2FF00ACDA4A7DF5A9D9317E4EB3AAE3994A274CD5B1F2DDE05CC7CCC8E359B9F20B991D4C8FBC814F4A8D6C583C060150FF008F4AC86BF1B27C719919B1DB9D8D9607898F9184B75872DE601B8F3029E873E2A364A1B4723DBEDBDA6E4344724AC364D70DD0A3E008E76AC4F90A09962BD57FAC2EDABADB7FF9966F298E65412DB19F8A8C83247B3AEE255C3DFCF5BF3196DBF69FD6DFA8BEF0CD4690CF67B1DBC64B943EEE5F0AC39CB0CA2C52E3FD6518C8F8ADC4A934F317ADE5B38CFA54DB3B879689B3F77F717C3C11CCE6AD875F3B6D959E62DAE639CC90949E012C22D6FD63E35C3AF5C75EDDA6F0CE3AB7A68ED2E99246470FA6C2732C0850CB4C06548E61FAC14F95D88F2B0AE9EAB9B281D45C904F492481F32D76E44FDAA3C056448319A963E21EAC9FDF5E5801600286AC9F007C14BB7DC2825894252942129096DBE0DB69012948F2E548E0283953E16A0AC798A0E61E468277AD7F8077FBF57F4534121A05028140A05028140A0F279DECCF38D7AA1EF964F338F8F9BC51EE26E58989AFCD21315C871B27206432125452AE46A222C506DF13DCA3884A92431ACFC7CDD6760C765B5FC8E467C8D9A38C9EA9B2B51133764CF4057F6EB320063171DBB74D4D800A6DFAC9B2A8329A36F959CC734EB19898D64927A73F12D6CF899EE34EF9B6633E8E83971C4279BDD41106971E2A33B14221408AB4A57B7C1918F761E396851FC33B26BA0ABA0DA89B23278E2434482AB50638FF00EB6CE63B33949DAC4103568A5B6B78C4E5DF42D5824BF629131F6C10FC75A0F5634A6810F238A473822ACA33677577DED8E5B46D022769F5A5E162EB38110F63CD3E472E7E6457399321D694B529C51B9528B8010084FB86E54AFAA1E97BBAFDABEDC7A58EC1E53B91B7623459BDC38735F8EFCB518CDCE96D4A743CB3D34140E44F20BA85870ACEDAB1BA3AF6DFAA6D91912F53DCB0DB3C55A6E85E3A64697C3FECB971F78ACA7D4871D3E24D133A05B90F4094E4299CAABA1B79A092526DC49B28123CA8AB9DCA88E73CD6F945AC07D8070141C809BF8DFDB415D0549F3A0AC78D072A7D9413CD67FC03BFDFABFA29A0915028140A05028140A0507939F504DC3CB7A94F512F3AC056262F71B60853220E017030F2E4E5A73571C7F7A9CF239A82D38282BC963E6EA1B3FEFCD6C73197E4482B71B076596CA5F4B2E290A4146363474A112509B0F955FA82C15E1B53CCC05E4F1B293364B515661E4B178AD4F1EF625A71BB1506DB70A24484A01F9D26EA06E3C41AD64127569C13021E520E55AC3E17088726C5D86329E96C61DBB96DE9B88EBA4C8E91502D4FC3C9BFC2AEA37F0DC89C0BB2F63C2EBD82560A1E2BF86E260894D4BC132B0EBBC91897A7442E5D7CFD269C13A0F2A8A10DF3348B80498354A6C16B199FDA75B294BAC04AA5E39C6BFAA05FE421E6878723ED3895A7D97ADCA95B2BEA79A5C3F45FE88E3A545B53313644F327811FBDA3CEAC8562BF45EEBCEFAA5EC7214DB311B97B0C62E4788E2D3F86B89212A43B75735D5CBCC524F9FB2D4A47A72D6F16BC433988E5C42D89197972E1A53CC4A1A7B90842CABC54083C470B5AB9AA4C3CA839282B06E283F68393C6839124F978D04FB59E38F70FFD757F4534122A05028140A05028140A0F297DF7918FC077C7D4E312595CCCA3BDCBDDD716037C39C4C48C8B214B3C075131DC48B03C45A8316CFDEB2AEA33CF6331718F3B1B0AA0A55D45AD464E3E24E6CA48238BB13A807B926D41B5F16162363C66B99793838AEFD165E1EBFAE65162420C8C3AF1E9931E2C579875A3F5094DD4D3BC6FCE1B26E2D5A8227BF6C6B33B2488C95213AEB8F3D05878DDC72563A2C795D796A201724C886FB91A5157CD6F2F0A60D5ECA661582933C4770BC9D7D6F2A2297C79C6B92DB7237313FB78B9C5857B4278D66F02D3958A96558D98D2AEAC5C7CB6B6B27892DE2A534F4124F9FEEB2909FB135A895F52A27A519BEAC7D1EFA676206E9174D9DA7E3B2EEB089911C94C4A33262D3652DB5A54DF2F4BC42557BF853D2A05D87F401DFCECF7A85ED76ED9C7F05B2EABAD66D13F33B2C2CA153888CC4775B424469086DD27E200017FC94BD931F6C63DACF7BDE57F25655DA49F2A0E51E141503415D07E836A0AC1F650641D5CDF1CE7F7EAFE8A6824740A05028140A05028141E59BD4DE2642BD40777337092DAA63DBF66B1D77480DA3251F332A5619C74FF00CB91CCEC2593E1CE9F6D062BD735FC640871761CA3CF42D7E2B0DCEC045095198E44C7C857D3487DA4853A86F12E3CEC0C89E424B04290156BA432747DA7258D8EA716A918656261B49C462707948F889B8C83CC1C6A163DC7EF03298EE7575223E921E45ECB37A68EBEDFB0E2BB84CC8CBE35C4C6CE18EB89B66204988F2B1DFC40A1B979FC83B10F4CAA4B6DF214A6DCAE1F9473A79AE8D72DF35EC8418D9ACB34D38FE33230F3190664049FC31985C6C7E3A3B89E242D4DB7CDEC238D4A3827BB68D946B9F9C2F60CDF408F310E263A2B8A1F6B89FCD5A895BB3DBBF547DCEEC276AF49C46A73311998D90D760AFE873826C9C760952B21314D2832CDDD5C892DA08E9B3F876B29765581922B627B5DFEA2DB3499E8C0774B40C44C732AB547D6378D6E6391716A9608022E4DA94971D8A6C7C4279BC3F0C837AB60FA7BA666A5EC5ADE373B3B052B5A95944090EE0E6A92A911F9D293CAE1481C7ED00DBC40AC8948E0682B07CE839282A07CA82AA0A81B50643D5FFCB57EF7D56FF8534123A05028140A05028140A0F343DFBD5735A4F7A3BAD97DE9AC6E5759EE5EFDB61D67005FEA2A6C4396921C8B27947E1293D22F23F581E4F0BD060CCD1CBAB38CCE726CDCDCFC8C8FACC1E4602911A64D762A3A499D864B84B4CE4A3324313B1AAB3729B016D83CC080EBE713F59A0E459C4329C8C054A6552A2E1716D65F1E9714E7338A5EBD34A64639C27FAC4347901F9385063CD5B2397D7A5A64338A9B3712C058998B99878FA86B6B43A92858C8AD6AEA484F29364F8DEC526F419BD89B0F9A064B15255B16173923EB35D9CB4FC7979EDA8B4D454B5CA9B37055C1774807828582D2006BC6FB370A36B7311AF869C8FAA60A53394991C7E13D9193253224AC5B81BB848BF9D856A25656545C8C5EDCF6AF7046531CBD7F29A3AF1B9B425E487E14A5CE79965B9362151CBDD2B25D514A520A57CDC28A806F93E539A0E231739A53121AD7243D967FEAC493F590BA4A8CB90F24AC755B7165B68739BB4BB8E0AAA95E983B3B366E4BB4FDB3C864DE7646467EAB85913E43E4975C79CC7C752D6B27895126E6F585649A0FD078FDB41CA0FB682AA0A81A0AA8322EAC6F8BB7ECBCB1FA2824740A05028140A05028141E703D6646C66C3DC6D876B83B3E2F7D9CCEF7B060F74D7DE869888D67F874B7E3C14A1F8C52E75DC658495158BB97E60AE06AFC1A7B96DE75C5B8E600B327248C916C4CC3371959166429AF917D14290F25C6CF143CD28388F255AE0C825590C33F99C048672323AECC8423E90EEF07258E9CC16C8525233519B6DE7909B70EBA3987ED1AB4623CBEA31F0BD1C8E4206B6A6993CEC6495273BB4A136FF96C74D2C13ECEA2ADEDA828C6F7132729B9B84C5B595C8E3F2B619BDAA638DC29CCA5082D018A8ED053105096D45253F115A7E15148F0BC8BCE7BB539AEDC6163C899014EEB5B9E25791D57767072B19264BAD8E609B95B452955D61638F1E52A02F56256D776A7B93DB9D03B3D85C1771BB7190EE935B96AD1D3ABBB8B969853DA663E52605BAD485BA5B8D192A0A5A54E212A52EE9E55A09A99F5AE11FD4F55F4D3B8CFC9E1DCC96EFA76ED1E53199D63B3FB4C0C5A31B9B5B6E81FBB4EC543B4A7FA6541A0EB601F3E64DE97F11F75FB55919996EDC69993C864E2E6674EC54676565613263477D7D348E66D9296FA6001629E54D88360070A8321837A0FDA0A81F6D07203E541550540FB7F2D0646D5BFCB15FDFAFF0040A0925028140A05028140A0507942EFA6671B27D4A7A8AC064321235AD2E4F7036389B5E621A4BAF2A6C9CABE21A5B642545F70BE80A08B704052B804F10826BB91CFF6B367C9C26B15F4CF623F0363C061DD61965869C214DBB93D926005C7568B2D0966C8B1B0B710033C64F638794C744CCE0B6275D625A79A349C5ED4943CAE3620265B6A8EB20DC10AE14185F3D098CABF90131D9783CEC464CA91B1E2E09C5E6E1B03FF009595C246518D94876F9E5403D440F896950E141881CD973D81C9C8D67774E332489AC21EC6E55E6DA910E5C777E26643521A08EB30E81C160DC79D9408AD4A367BBDBA4EE3AFF643B5A9DAB6288606D5AE2DED3F0709F5BE700C87799C88E8753720A15CD649F86F6FB68C70D612445D13B62D4AC0AF2BACECDA49930B2EF33CEC3B2B1B31F69A752D2C292B2C1EAF220DCACF322C79AA7E2445B62CB311346D6E5E095FC3334E629FCFB329863E9FA1361043AA311B0A2969B5A3990FA509482EA45C703567257A5FED2E61CD83B5DDBCCF3EC311646735BC4E464468AD86984392A132EB81B6D36094F328D80F0AC2B2150560DE83F682A07DB41583415DC50648D57FCACFF7CBFD02824940A05028140A05028141E4E7D44E2D7FFE9BEFB637172032F43EE2E7845C81F959CB67E7C9952671B789858E404A2FF2A8DC71A08E41C4C7DCF018BC41891D190D6612E676FA1E490EBD111834AFA60E41A4ABF7A9CF2829C8A850371CCDDB97A690173D424ED260CBC4B913659F8B9CD89105C97170886F88B598C339CAAE9B80DC00AF2E1E35A8381EC74D8420371A3BEEB299CBFE0B0B14A7632C645A495ADBC409177F0F9869375FD1B8551E62014A2FC054A326E1745C0B78596E6C49C6CFCCCA79B7B089623B6887879B21E2A44F88DB80860CE75B097E37C4D469408B7C45298354E7CCCE29CD8B059AC83997C9638485B9959AA52DF7E33AA241F889E55874FE2016E3E3700574952BEA9FA65F4A9AFF0079BD296993B64D81F82FE6B14A8DAFC3618E68F8E918EC9CA526538853A14E38B51B1534A69405ACAE617AC76FD6BE31C6E1FE9E7DDD89127BF93D9B13B2EB11243664A312FE527E7A4C271D6D2F311D125B4F49921216EA12B528F2DD254AE056A3EBC76B84A6FB7BA8312F575692F42C6468635453BD7305B8CCA196DAE720288E4402398730FD6E3504FC1BFDB41FA282B06F41FB4006D415837A0C99AA7F957FDE5FF2504968140A05028140A050283CB3779316C33DFEF54F92CACB6E0B51BB97B69EBBCAB7C2F430B4148F12432564002F6BD04155B8E8F01F932D12A4494E1E4C9C836A6A3AF94B983C632B8005ED74B49783A3858D06587F53C16792CEC4D378C97327CE8903714CEC6BD2D52B2D31869E135931DD4B8C4670B812A281743973F2A9206A0EF6D3261EB4CC9C6A24BB266C58A2367F30990A7E4458D18B4B7A1C599642DF760A5F4CA892D692A0DF334855AEAA0D7EC96D8EB66541CAA90C478CA908CBB11474DA43665271D9A4B294FCA9EA2A3E41A03E524DAA60C63B01912361899B9763232D8B9ACE64A3E5393C63C224D501E5D52843D6FE7D20FB7BE8ABBCBDB4C0FA7AD3354D836789AB4CD57192B21909D9775A890551E4E51E6D25A92B5F29505AD29520D95722C0837A9791BBFAF6D7ACEF18B9391D1F6BC4ECD11214D0C9E2653339A69D20DBA9D252AC478D956A60E0D45397447CF359C5B8ECF67392DBFA85A1486DC68219E9AD84ABC1B20F0B70BDE8257415837A0FDA0AB9BDB41F9CD7F0A0E56FE236F33E5419435729389472A4A47517E3E7C7C6824540A05028140A05028141E54FD51C15A7D46F7972CB6552D899BB679C721A3FB597869B2BAB1C0FDB93897DC4A3F694DDA83106B1A94DCACD4E361B4DE45B8423A274D75450C3F1DB8AA8C8716B014529CAE21F059363CD21AE98055C2836335BD9B178AC3E0B1788FA6C9C487F4536166F2B2C621CD99BC7A030CCD8F3561D8C84A5B4F425C07825C42D3D424AFC2C168EE545831DC999EC12D2F6032B0253E10DC86A4B309D5E3DAC5C6C236F36B507DE697F32D3F323957EDB5D4C6A8EE6A9D1A66C6C4B694D3A1CD85879278730630B0DA7D43DA9EBA0711E26A5577E63DF530320A707E347CDC958F719F8584F3E3EF71BB9AB12B22494C4CBE8FDB3C14A760B0E45D4CE470CC3915A790E3ADE46497D6F32B296E43EF29D436D29E243764802CA349C2B8206565F6C62E37B9DAABB23172B2F09C9391D7F1E178C899CC532B48971F2715875C6029170A4841B389B9E54F2F1A3D18766DC80FF006B7449789C94ECB6227E1A14DC44DC95BEAFE96530DBED36F117B96D2BE4B924D80B9358193281415037F1A0FC26F41FA90B529286D256B59B210917249F601413CC2EA8AF864E57CEC510C1FE991FA0504F1294A129421210848B252916000F20282AA05028140A05028140A0F2FF00DEF448C9FA82F5030B34C4BC0E19BEE1E65FC7ED698EB2233D1320A5C79D1AE2CF2D8797C8B40F9D2A520F8F00C599894DC54AB5B4E36361B5FC6494C2560E5482CC6FA890B5486E24E9882AE82642942561E7B6434C28966C073505DB2AE4D8FA9ED623A723272530B69CBC310A2399275E754941732D81984477E4147054A8AA09787C47E234106D433732028E37666F258FD71C703B172B9EC6C1C062F072DABF4B210F1CC95A9F7D37E5504F15209162426C353ACE69788DA23AF01994B98F9AA8CAC6C4C985A5525AC695A64CD9CB23992512794145FC52781F02430AEE7877F5C9B0213F29B91FF92FF1CD95494028534CBDD08B0DB5A0F872B0CDF87B6B512B707B77D89DF3BC7E9FF48DAB4AD3A3C895A76BD2E360B3BD1297F25D4CAC812A1A5B5484FD494B4A365B7D35A6D645D6137CEF2AD61CE685B9C86D9D261E8F97859CC4B6EE163E3170B2BD04B7392230913654B84C941652A286DB1CC540A7C549F8B5A3D197636042C4F67BB6B88C76618D82161F5CC6E3D9CDC60A4B32BE922B4C2DC425612A00A904594011E601E15919568141F87C0D077F1B8D999578B315BB8411D5795C128FB4FF00250652C46061E2521491D69447C725438FD891E4282F940A05028140A05028140A0507CF6F53BE85DFEFACB8B95D63B9F234B938EC9C9CCC4C34982DCC8665CC257207510A69E0DBAE294E2924ABE25122C38507CF4EE1FA15F51DA8476DC6B588DBC45C7C676345CEEB4E353DD6E2BA79DE852F1734B0A9B09C57C4A638A90AF8D950570506A96EDADEC7AEEA195D677ED366EAE223283020EC38E9F92C2A434B0A08872421BC9431C0D98745D07C09A0D6680AC3C696DBD8B774E6270500CC8C741CE67A724FFD08B352B692AF6730E1419DA26E9162631981B32A635B037C89C563B2F2586B39B1B2095A634D0094C1690E10590E2EEA4DD1F1108E5B830EE41CD8B63CAEC9B6EDFF00B8E5DE86FC58B86292DFD332D29282D25B571486F8037F3AB12B781AF515DD1F4EDE8AFD27653B69996318BCEE476D6F391E4C3665A2537167DDA4283C93600B84FC24134C5651ECEFFA886D5DE0DB750ECCF75FB6FAC6D58AEE16571F839D91C6487984B466292A6DD5C7529E6D45B558909524A543810454B3EA47D7DD7B1D8FC363D787C4E31BC3E2F1325D898FC7B494A1B4348E5B16C027E155C9E3C4F89E35157CA0FC2424124D80F126825785D5E4CFE491382A243362945ACEB83EC3F28F79E3FA68325C68B1E1B288F19A4B2CA3E54247E73ED3EFA0E7A05028140A05028140A05028140A0505B72786C4E663AE2E5F19172719C494AD894CA1D4907CACB07DB41A99DD3F42DD82EE93524BF8CCCE953E4248391D532D2B19626E6E584AD4C1FB0B741F3DF6CFF47A5E1173F23DACEE446CE49739971206DF19495F31BFCF262DC2CFBCB62AE88AECDE867BC3B6AFB59AA647B649C0EE9199FE0DDCBEEDC598C4B813A1A9F45A59FC40A5745A40286DC6C2EE396E42AAE8847FA9AF66F17D9CED2F603B6DA43396CA6B7A64DCEA7F89CCBC978B925111F71C90EB4DA5B41716A510901290382458520F9FBE944B0BF51DD8854760B3198DC308C979694216E3A978A9C52F94906C4D82BD96BF1AD5CC1EA8F1F29892EE612C3C878C5C93CC48E4E3C8E250DA8A49F6D940D731766187E53E88B15954890E714B48F21ED51F048F79A0C9385D558845B953CA65CD4F1423FB268FF341F123F68FDD6A0975028140A05028140A05028140A05028140A05028140A08CE6355C6667EA0C84FF008A1690D2D2879972C00F8DA752A49E02DE541AD798F495DA37366C76EA8ED16A53766C2C8FACC666E0444E3A5A24004073A6D9434B50B920A95C0F1ABA25FA068399848CE63DC86E6171473525FC79920979319C6D9012015AC2D5CC955957E5B5BCEE2A0CF58DC542C4B1D086D72737175D57C4E38AF6AD4789A0B8D028140A05028140A05028140A05028140A05028140A05028140A05028140A05028140A05028141FFFD9, '2020-12-02 12:35:20', 'test');
INSERT INTO `v_file` VALUES ('feb7c04ceb0181263bb93cb485f5a204', 'pic-4.jpg', 1, 0xFFD8FFE1001845786966000049492A00080000000000000000000000FFEC00114475636B7900010004000000500000FFE1037C687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F003C3F787061636B657420626567696E3D22EFBBBF222069643D2257354D304D7043656869487A7265537A4E54637A6B633964223F3E203C783A786D706D65746120786D6C6E733A783D2261646F62653A6E733A6D6574612F2220783A786D70746B3D2241646F626520584D5020436F726520362E302D633030322037392E3136343335322C20323032302F30312F33302D31353A35303A33382020202020202020223E203C7264663A52444620786D6C6E733A7264663D22687474703A2F2F7777772E77332E6F72672F313939392F30322F32322D7264662D73796E7461782D6E7323223E203C7264663A4465736372697074696F6E207264663A61626F75743D222220786D6C6E733A786D704D4D3D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F6D6D2F2220786D6C6E733A73745265663D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F73547970652F5265736F75726365526566232220786D6C6E733A786D703D22687474703A2F2F6E732E61646F62652E636F6D2F7861702F312E302F2220786D704D4D3A4F726967696E616C446F63756D656E7449443D22786D702E6469643A32363633633634372D316534382D343134622D623336312D3735656635363936326434372220786D704D4D3A446F63756D656E7449443D22786D702E6469643A38303231443945393334353331314542413837364139334131433230433836342220786D704D4D3A496E7374616E636549443D22786D702E6969643A38303231443945383334353331314542413837364139334131433230433836342220786D703A43726561746F72546F6F6C3D2241646F62652050686F746F73686F702032312E31202857696E646F777329223E203C786D704D4D3A4465726976656446726F6D2073745265663A696E7374616E636549443D22786D702E6969643A32363633633634372D316534382D343134622D623336312D373565663536393632643437222073745265663A646F63756D656E7449443D22786D702E6469643A32363633633634372D316534382D343134622D623336312D373565663536393632643437222F3E203C2F7264663A4465736372697074696F6E3E203C2F7264663A5244463E203C2F783A786D706D6574613E203C3F787061636B657420656E643D2272223F3EFFEE000E41646F62650064C000000001FFDB0084000202020202020202020203020202030403020203040504040404040506050505050505060607070807070609090A0A09090C0C0C0C0C0C0C0C0C0C0C0C0C0C0C01030303050405090606090D0B090B0D0F0E0E0E0E0F0F0C0C0C0C0C0F0F0C0C0C0C0C0C0F0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0CFFC000110800AA00C803011100021101031101FFC400A600000103050101000000000000000000000003040602050708090A010101010101010100000000000000000000000102030405100001030302040207030707090901000001020304001105120621311307412251617181911408321509A1B14252728223C16292A2732416F0D133435383449425E1B2C2D2B35474A42617110101010100020202010401050000000000011102213141120304516181911371A1C1223242FFDA000C03010002110311003F00EFE5014050140501408B9218646A79F6DA4F2D4B5048FCA682DEEE7F04C2B43F9A80CAED7D2B92D24D8FA8AA83E35B8300FA8218CDE3DE59E484496947873E01541716A4C77C5D97DB787A50B0AFCC6816A0280A0280A0280A0280A0280A0280A0280A0280A0C17DE8EE2EE2D8236F9C1351169C92A47CD2A4B6A59FE1046909D2A4DBED506BE2FEA5778AF30C625E8C5B7E54472632EC769298DA595A1B5A0B8B42C85F9C1D27C281D9EF36764925EC96519D64EBE9AD2458F3B04145A83E277EB32D3A2566E6ADB27CCD492E9BFB78AA81FC7CD626400A6E4447543EC95BA5247B964503D4BA8510B0D8582ABA94D94ABFF3503B4CDC6DB448664055800A3A7DF6000B56009998B6CACB310D89B5EFA546DE37D5CE81FA7727CBA10888DCB8C947101B9CF2003EAB2AB5028ADF5BA10F0723E5A53494F1D0A77AC0FB75823F2550FA2773B78357EA4A43DAB921D69B36B7AD00505E99EEE6E042025C8909E5026EB285A6FEE0BA0BA35DE490928F98C136A1C3ABD27C83EBB0283F9E82E2D779B16556918696D26DF69B5A17C7D874D05CD8EEFED476FD544D8C4720B682AFECD0A35345DD8EE5ECD7D095FDE8A6757E8BAC3A923DBE434D12CC6E571B9760C9C64D667309568538CA82825560749F41B106C6A8B850140501405014050141AC9F52ECB7F756D392E2DB45A7BD1DB0E2824A96EB60A529BF32741E0283525E82D4A6D2D3C0A9095A5C0949527CC83749BA483C0F8503C0C9F11ED268140C0F613E8A0544749E62FEDA06190C66464BD89771B967712981351227B4D212A4CC8E12A4AE3AEFC520920820DC114128665E4DAB74A73E8B721D4511F024D4C1716F2F944DB53E87BFB469B3F974834C0F866E4A8597158511E28D6D9BFEEAE98174E61642754509E1CC2CF11EF069E420FEE8C5C3938D85366A20CBCB3C58C5C679684A9F700D4A434091A881C6C2A79D1873707D53763F6ACA978ECF6F1E8E5A0902662198EE3B21A24900292916F0BF3AD0C21B83F10CECA625C2DE3B1999CB149B6B59623249BD85838BD5C7D94135C6FD5843DC703153B05B2E5308CB3097A389AB405006418E6E525C06C524F01E8AE77F2656BEA4247793BC79773A781D8E86A310D5A6965D2DF9D442FCEB0CA3C8054FBE9887E4B76F78645D39EEE160768A0A16A71A54C8C95837FD4683CE704F3E3F969A63A85F49D1DE4767713324E791B99FC9CB932179B46BD2F8D412920B812A2004D85C7856F9F495B2D5A4140501405014050141AE5F52984C7E676BED9FBC22A65271D9E66644D5705B90CB6E2DA7124106E929FE43C28356830A3E07D540B08FC38D02C98E3D740AA638F01C071E340B062DC79502A18E3C4502A191E03978502C1926D64937A0422E25A8D2674A6D7214EE41C4B8FA1D7DD75B414A420069A5A8A5B161C4200B9B9ABA149383C64F918E959080C4C7F13204BC63AF20294C3C05BA8D93C526C6DC2A60F3CBF546D3B0FBB5DD38497FAE22652347EB7D82B6F4A9CF369B72BDA835872909862463DC8D8D978D8B3D9438C998E21E53DE6D2B710A4211E42AE00589F59A0ECAEC77BB9F1F626CF6067F07B231AD6221FC895BF0D87CB05C42F5B9D24B8EDD4DAC71B024AB8F1359B1A5C3248C1C84B2F6E6EE7E5735253D64CC8D05A7DD6D6A69DD4024C95B69490920136E2788E16A990270626DA9123A1B77B719BDD8EEA29F9896FB81B51582AE2888D803DEBA970769BE98A1BD07B2FB458918C6F0AF86965FC43414111577B2D94EA528F91408E24D6B9F4CD67FAD0280A0280A0280A02830FF007B984BDB310A29054CE4185214798252B4F0F8D06A4067DB40A06472B7C68150CDEDCE81611D5C780F7D02A98C6D7FC940AA5848E76F59A0552D24724DFDD40B06957E405056966FCCFBA8140C5813E8E37A0E0CFD4876BB7F64FBDDDCC5E3F6BE59BC7E772119D853BA4EA634B61D6D28212F70428294A4829BDAE40F4566F58B2312E23E9BE5A9E8B2B3594818569C1D7402F29F594B6871EE0DC54BC4DD2CAAC2E39549DEAF5C5E7DB7CB0D23616030D8DC46336E66F763B8E82D4453AA2D6321BCA692C2B584B49717A4A8F0D4BE57F76BCA69E4EEE2378C2FAE2C0D99B21297649F9891FF00509B62016D5774485152AD7360390F554CDF66A1C7BCF8ACBEB6F37B8F796E246A474E0E28370E2AF4A6C6C4AB8026EAFB14BCE1AEAF7D39FD5A7D3C43D89B3B603DBDD386DCB0184C55E065C796E38D124E84AA4219534AB26C0AF5004FA29CFA46EF35BBF6B3FD3E96E080BEB341E6FF008E81742B91E26B42F6CCB8B24031E4B4F850D492DAD2AB83E3C09A071405014050141438E36CB6B75D712D34D82A71C59094A40E6493C05060CEE66F3D939DC2E4F6CE337861A7EE486B62539828B3587A5842569BA94CA1656004AAF722A7DA18D7511D2399AA160CA47102E68140CF2F2DAD40A064FB28150C1F6D02A98DFCDB502A239F1F8D02C98E28150CA47E8D04732BBAF6F617706DCDAB929A6367778A65FF0087627496A4C830D016EA7A8905295595C0288BF8534722BBA9BED99BDCEDDEE1C3E5E7E5513FE5D3196EAA2C768C538C6804A4275E9FEEA851E4090BF03713C1358A25EEECEC35C28C4E0F69A5010865AD48912EC10E2426EA2EACFDA48B00399F49ABF790BD757DDD4CF0DB3F7C6F77DC9D8F8DB8325094B2A8C67752145536B4029E997C052C6917E09B79BD749D4A467B83F4F78E6B0294CA94D62B723B626516D12D962DC17643AB25D245C03E5039D88E06F599E1BF0996D5FA7BFA7A697197BC9BDE1BCF3284E998B9328220A9C36E2D4587D048413E0A07878D4F861BB580DB7B336B6221C3D978763051022CA8F1A2B3145BC2E5A4A54A3FB5527882E6B0DAD252E203893F682B97BEF4D172C6E233135C03110E4AD4916D6C829091EB5F003E353C89BA93BC36EB4D993BBD9C648D3A5AC6BF902A78A7D096BCF7F70AD607037F771716C30FC89B1DF8CBF2A7E6DA415AEE7814A53A17EF2284F2B9B3DF2C9C409FBD31D01694A7CEB4B8A6544FA40257F9A84BACCDB1F7731BD709F7C3114C4097DC8EB68AB50D4800DD26C936F3788A18E6BFE26DF54BDC9EC3E3FB6BB3FB6D9C56CD9DBF57324E5778B51C49911E3C25B08E9B28525639BDA95617B00070269EA6C234BFE92B0BB9FBFABDFF00B83BCDDDA9DDE1DB6C86A145873B2D9B5C66653A7AAEA5D8A911D96C3A8558A082A1A41000E358E7BBDDF3E16CC6F46CFEC3EC4D8B987F39B3F07222495473163271B2D996D466976D69658752DAD21600D40955EB5CF13D96EA712644D84F74C88EF0E166668731B23D9FC60A68FB962AA1519743280BC8E2E7E39AFF00DD2982FC7F6F5E3971BB7BE82C5BDF7D63768F6FF796F980867721DA58795951878F21285C83191ABA5A80594157A749F650473B09DE2C577E3619DF18BC0CADB89672523152F172DD43EA4BD1D0DACA90EB61214950745AE907D2283370680E49A0AC367D141586BD3C28154B43D66F41586BD09F79A68A5701879D8EFBD19A75F88A52E23CB6D2A5B4A50D2A2DA8825248E04834C086476DE0F3CDFCAE7B0D033319CF2B8CCE8CD484949E163D44A8D4C1C41C7F77D7DB9CF6631388EDE76E3270E3E627188C3F8A8E8CA37AE4B85203EC2A24829B2C84953CA4A470F2F0A9F6832AC4FAC9DB285286EBECC64718D36E86A44FDB39579A4A563800E33363CA6D37F00640F51A7813FC17D4BFD32EE0E9F5375EE0DB322DA6D9CC4FCEB0855B91918B7E49E1C4F16C1F555FA8CCB81DC5DBBDCAA6D1B53BB7B232E5D20B7151966604829E007F0323F28E5C7B29F51B44D62B28980CC8671AF4A8E52087D801D66DCEE1D6B5A08F61A60BB62730D63949D7878929D160A53C925C17F6922FEEA607D9FEE0E1E3476464DD77161B0744213830D280E3F61B6FACAF60354612CA77BF12C7555B761FCC28581C83284C364959D20196F5D6A2491C35DF8D346BC61FEA697BAF7CE476A647079DDA78A8E56C3DB925C15B24C80B08084B254A7D60824A56748E1EBAF9FF00BFFBB7F5F8D8F47E1FC1FEDF7E24642777C6C58DBA91B0715BAE66E0DCFF0024BC895C98E10D3B19BD0145B7D360E286A06C45C837F0AE1FA1FBB7F625FE8F57ECFE9F7F8B8E7BB3FF001EB7EBFDBDBA2FD8049FFF009BC078DBFBCCC96E0B7A9CD1FF0086BEB4F4F9D6EB903F8D6C3900F623240A83094E6A336BBF04ACAA22D56F412026FECAA8D4AFC37721935774B706366CE7444C9ED592F32C7542429C624C721C0959E2A09278817B7320507651C666347A887D2E253C83E9B7C162E3FAD4D0EDADC5958A8D1203EA8DE8244866DE8D2E05A7F2D03C8796C04873ACBC6C78EF9FF8A82B731EEDFF0069A2A6CFC2AFC0773F6F60370B252E64A530A58B19126336FAAC41053F391343FA4836358B3446766F65206C5C63F8BEDCAD18984FC95CC7E2E0244575871E58014E2F1B39B4F9884804A1C493614CC12D71ADC58CB3792761A9C1C1432312561DC27F6FFBDC7BFEF814DA1E225C90DF5A4E1678600BAA6434A3251C7AFA9094ED87B522AE8730F23899CBE944C8C67DD1C0C74AC7547A8B66CB1EF15760BB74ADC0A48F68B1F8505496CF826816430B7084A5254A3FA20127E0281411D48559492952789491C780F1A0F387B8DF726CE9F15F665B915DCF4A534E165A971C0EAB8548430D90F1513C5483C17FA26B3D4F96BED73104938B42DBCAAA32D94A50B4865969D7E08D2A579826028949482922CAF326DC2A32BC40C04BCCE6E732C16672E36204850CBB51A59D09F4AC2DB6C5ADE5701240AD45D595E63013B13B5F1EE65632B21326AD79480D12871865C17429C90BD694295E809B55DC29BE13716EDD9AF64A5ECCDD3B8F0220482DB72B1AF3CCA7CAA3A43D2223CD21A5DBD4A49AB3AB51BB5F4F9DF6EF56EE5EE383BC7B8D99DC703191184E386456971F616FBB6594495203A6E94D8DD47853AF44633DE3DEACD3FDF4C7F6D92A7DC0ACCB119DC87CC38C84C775414A48E880E38B3A38971CD06F6D36AE365BEEB793D373B6C7659BDC5B5B1DBBB72379BDD032CF63A3B3B31CC92198501EC03EA9CA933F2163AD2E3AF242D2DA01213A4280B574E79DF6CF5E08EE4DFF00DAAC6203F8CFBA979E88F4C6B734BC1C1792CBD284853ACA7E6DFD46474DB2121C2B22F709E559FC9C73DDCFF0D4EBAFED1AB9BAF7D498BBA711B9F031DAC0BD126A24E2E65D0E84C6CA9FBBF20DBAA5008486E43ED3E5247975ACDED5E1EFF14E7F678FC93C4EF675FF0033FF005FFBBEAF1F9AFE6FD5BF87AFFE2FDA7FD75E843E9E71F2F1DD95EDD3391587B28EE219919490161CEAC97AEB71CD6382B5137B8E1E8E15EFE6DCF2F8D1CC4FC66B1CB9FDBDEC8943408FF1264192F78A4BB1D9B26FEB0927DD5A573F3E85E5FDDDF5238CC7C4514477F6FE571FE5360434C21EE36F4AD23E141DB274ADB513A428F82BECABFA49B1F8D4C0CCB8C156A5A4B6BFD7B71FE9B7D35FE7AA125428F255C9B90B5789016BF88E8BDFF7A8124635C65DFEECFBAC3FC2C842C2D5FD077A2F7C14681FA66E5987036E962611C9A7BF82E9B7884C90D93EE70D4C12889BE7278A4A5992ECEC6B645BA4F9529936F0099214D1F72EA8BD339BDBF38A644CC3635D7D5C44F8E178E7EFFDB307413ECA60B9BD136FE55B097E6CD0DFE837988B1F351C7A83A417C0F5822A640823672AC3EE47997537BA1184CA391D5FF23910FB63D808AA1AC8879EC61B4978B407E86671CF45FF00ED413219F796C506BDFD5163F71EEDEC6EF0DAB8091F7066F70984C62B71429C5F8EDADA98CBEE053902F29B0A6DB50B9680E3C4817A9D5C9A49AC33F4299AEE1E1F6677176E77A3754E772D033A84ECD6772640BCF2E1262A90EFC9B925656B6CBC8E02FC0F80BD3EDB1731CBCC86072C8CCCD98FE0DC84DC779F9B93C84DC74984DB718BE07557914D838C85389B8035209E1C2B1DF533256A4BFC2F7F238F56DDEE1CB94BC336E6B8F2F14FAA6A9B5AFACEA5A5260474957CCF004F980B8278DEB3CCBB3CA59E586322C21332732D2E2A0C78FA5B427E67086EAF3292CC656B0A5F1BA9B5F955C48E75D529ABDD7FFA3A1D4CD508CC95454CB8AD64A368E4AE8C68E7A8B6EDF690E714F3A92A2D8D9695067942601529D07A2994EC67422F6BB70AFA14D1F0BF993EEAE9CFB1B51F4ECEB18F677664A6BA22470DC4EAADC55D29014A04DF8DF891CAAF626A7656C58DBC267739A8F27716E5C8490A88F3AB2CC36485755A019479D652523893C7D15C2F1B44E25E57B839BC6448DB973A76F6CE8EB4C98F8A94E7CB44BAC85385115B20B85561CC1F0F5574C588CB59CC140CC9C7C131F25899094B51C2D0F3369834A4AB48B7F0CA6C902E397C67ABE3E1AB988A7D416372076248C967327030CE44C64E8B88DB2E2DA8D2247DE1D10551A226CB2125904DC71F4DEBC7FB7F8EF538B3E3B96FF8BE5D7F0F796DFE667F67A2CEC13CFBFD90ED23929F89265FF8470E996F41505C72F221B697020A6C3CAA041F5D7B275CF5E79F4E1D4CAD0EFC59F1ECCBEC46C192B68A9D81BF22AD0F0B7950BC74E4A87BD5A7E1551C90FA346FEEDFA91D87214FEAFBC1C9F1C208B101D8724D89E5CC8A968EEEBACDC5F4937E3545A5E8D7241491EB2282DCEC52395BDE283E2644C6525BEA171AFF0062E00E23FA0B0A140E9BCBE84749E8AA4B67ED2585908F7B4E871BF824505C62CAC72FCAC4910C9E0A402E4327DA1BEAB07F790281DFDCEC2C29F4B2D9BDC994868B47FE671CA29F7A9BF6D00CE365A2EE43932081C4AD011351EF722A9A7C7EF20D03D626E56FA074325A4FFA36968717EF65DE83C3F2D04861EF39F8F29615264C0572319E52922FEA6A484A7E17A968BCB99DC3E4805E5B098CC82962DF31D3311E3FEF11604FB054F61A3F81D93360BF8F6CCEC14592E0797156D33363F56FABA890505C4AAE3ED24034B2086623B330711919595DBCF6037A4E93AF435B835CB79AEA12A525B449585A01BD8A41D36B0B580AE7FEB8D7DAB19777FB3B86CE448731FF00A72DAC772448670CACE695B6D47C538DBADAD315A61BD216D7554B682D564ABCC085006AD9FC13FAB44FB97F4D1F4FFDBED831F2D92DEFBEB19B823A17237545C3E2D39453F0DC5B60878E49EF2A2310A08750E5F8DD40900092D9ED6C9F0E73B7030EF6602B113A1BF0D2E3896A530BFB9A5BE94AAED38F0EA95F54A79A9238F25575D609C869A8B0130B26CCE80D4D9E3AD29D8FAA3F4D4E79897882E25613C48E4AE62F5651B0DDA7C6E2570B74C2912DB4E30680B9905464278BF18E969C521054094E9BE91CE9D7919DE34FC94840C5EC5C37CBB6D2DB4AA7B880E3962AD3A96E2EE904DBC05674639DE996EDDEC65B92BBA7BF0CCCD34957FD071AE7CE4D528F101649D2DF2FD329AD0C4DB9FBA3BFB3D867311B0BB47FE0EDA99A6C1464724231CB641AB8285326729BE7604749B27F55545D6B5A71197DCBBDF1F033B93918C96DC854ACECCCE21D71719A80854A90A90D387AAB2969B3647359B253CEACE76A4F6F461F873770264AD950B63E4334E67E36E7DBE8EE3ED990E488CE1C7A7233171F27860D4751E888AF86DF08294E9127481648AE3C4BCDFAF8CF8C76FCB7ED9526FC4EE3C677E9727BEF9097616E6C33B154796A2E2D0AE3E1E452ABAB8B87FF004C123E57EA13B4AE1FB2BCEA1927FB665D6EDFD6ACD1DFD0916B72F004D5D14299D5C071AA19B917C6D40D570EF7BA6E2819AE0249E56F6D02271FE3A682A6A3C88EE0723BAB65C1FA48241F8820D05E1BC94C363299666A93C96F207507B1C4E973FAD5345E5192892921B96DBA07825E0898D8F60780713EE5D357171690CBA80D3120141FF8769FF2FF00CB4D0A49F6255537422E62586D5E765B8AB578A7A98D70FF00EA30AA2115E3A44546B43EFC768F1D721925B3FEFE21293ED526A0442B26504B6CA720CA05CAE329B943DE86CA143DE9340F616F2C863ECC33907A22926C62A964A471FF0066F8481EE49AA391DF88AEF1DDD3B70618E5E621BC6693136DB2A82C41529C7412FBA92DDD6FB4100F9D7A7CFC026C2E526F935C9789B2379EEA8F272785C1CFDC98E654438AC62912E4376F172324976C7D3A6D52F73E56736AD4D643726DF74C28F979D8675A3A578D9A1D62C7D0A65E0A47E4AB2CA5967C3713B31DD1DA9B47646E491BF4BB98DC5929D18C1C3E3637CB698D1925652EAF4B51D05C7ACA5292144809F656D11DDF1F52DBFB71B12F1D8261BD9DB5E24752042C62D4992E1B590DBB2AC1C3C3ED04691EAA8312B3B7B3DB4731219C9B31A3F722336D4FDC399CC91F21B4997D2971BEB2149583394140E929529B2742505EBE9D5F02D9BAA676D725360AB3FB8F75EE0CAC88E95C9DDA88F14B6B5ACDBAA634A75521C1C2F653A8511E03954A360BB3AE3781772FBB77A621BEE7EDEED6EDE7F3DB1B26C4AF96FBDDB7DA7A3C7C7759E42DD058B3D2436525C60C775362836A41E967E97BB2DDA8ED976D36366F6176E60ECBCBEE1DA98A39698A28959571A723B6F8625E43A6DADF2951173A520917D2385627326E7CAEB127E25F885E53E8F3B9921BBF5304F62B229B7F32732C9F80749AD23CEAFD34EE46DAEFB767E628DFAFB9F18DAF8F2714FA11CBD3FE7ACD1E9AFE50DF4E9F2DC820F87B2A2BE2A12471008B5370365C355EDF6AB41154450E69A686EB8BEAF65027F2BEAE1415A62A4F029BFA2B215F9149E43F270A2BE7C87B2DECA9A3E7C981CBCB4B43A61F9B113A5892A4A0FF00ABBF90FB526E3E22A6874D64834AD6B8A96D679BD154A8EBF7E8F29F7A6AE98A9C918F94A4A9D536A70710A98C5943D92236958F7A6AA2B5C65486AC95BD2594F349E9655903F655A5E47BA8638ADF89908CC6EDD871586A2B5A20C871E3163C866E42491ACC83E62351E08E09E4789ADF28E46E37228C6CE12C3F2E1BC8E2DC884E96D693E9052527F2D4BCCABB6331C7EE9EE1C8C538E9BB97FC4D116D96FE473D1DB98EA5245BF84EC84296850F029570AC7FAE7C2DEED98CADB0778E21EDBBBAFB7D9585B65DCA6FD524E01ECC4675B931E5476418CDB1922B447612E3974DDD3A428EA3E046BAE7F82624DD9ED8B97C4E56466A7E150BDCF819ECE336560F2894A5991BA25A14FB126585DD3F2D8E8E854F7946E9D29681F2AEB526B2C59BD30D186E1851F178A9FBBA0E594EE4B6AC6CA3AA6D7BA2425C711337565D45C4A9B8CFBC8714CA14A4FF000C799406B52F61BCDCBF705E71C8F85DF1B53747C845067F6CB1E865C88E4769BD4F34CC35456A2C90120929616A72C2E8248BD6065E95060EC5DA7B230B8F80E61CC79F0FB8397C63924C8E92F32E23133F16AB9E50A23EC7058D63A8BD5726BA6641EB1365625181D9BB4B06DBE25230D8581051293C9C11E3B6D058BFEB69BD731ADDF5DD887735F491DF088CF15318033543F990DF6A42FF00AAD9A0F279D9176663FBC1DAE9CD21696D8DD987714AB1D26D35A1C08F1A9D7A1EBB171C36EB89B5C6B5588E239D65A52636AE1E340918841BD81AA29F932798F68E54420EC2491C53545B570D42E53C47A2B2A492C9B8B8E540F9A601A581C88C2D709BFAAB3450A8C9E3C2DEA35020A89E8140D9C89C2FA68183B1471E1CF95345ADD654950293A543911C08F655D1C6CFC4D1C5B9BD7602DE92F3EFAF05394E179D5AC242148420202D4748B73B0E7C6B7CFA4AE49C4C7B93A425B1F64DD4E2FF00552399F856913189894B3D074234290DFCDBA3C52970E8693EF06E7D941F33616EA5D43692E2E3B7E4B0BD96EA74D87AD2807DE6837EF682D0D6DB8BB273CFBCECDDB98995124BB1D4EBD919D8888CC693BB31D8D6186C843CE3BD2C730EA8DCB69908BD92915A9E063EEEBED5992A62B239CC5BD37299C9BF77EE9C6629EE97CFE5E3B4D29BDB18C0D859463F06C74633AB42482F5C71213A75444367EC45E5B7B431B9BB57176BC7DB4C3DB9B1F91C54875B44A5624A56DE35E75C9321B7CCB7D4D3092148792A5788E1599EC39CCCE91BD3339D2D3856F6E89922744502A500ADCD021E496949249B264B06D73E14DF23D726C374BFB1B663C57D42F60B1CB2E7EB6A8CD9BFBEB22DFDCFD8B13B9DDBADEDDBB9D3DEC5C3DE986978793908E94A9C6512DA5365694AF81B5F978FA473A0E22ED4FC2ABBC7B465CBCBE477BED1948DA2FB793DB0BC6A26B9272661BC1E434F32EA184455A92800292EAC5EDEDA97475992B5B8350E21D01563CC6AE3636F6FFDB590E9BE360A16BF2E3453A6D015C0D14E12C0B72A04D7181E43DD40D95001E3C2A0FBF20DF228BFAEA8A7E45B4F24FC2A03A000F0E1E069026A60DB88B8A5082A3A79F1159C08298E0385FD42981939181362282DAFC3173E5BDE83871F89B384773F69C5D5C236D7794127C3A8F71FCD5D39F495CDEC4C24B301C2400FCD5371D27C407480AB7B137AD2242168722E524A400244A4B6C81E0DB074240F55C13417CD958E61ACEC2C84D40991F1AFBB99991C8E0E260A0C843241E616A6D0DFEF56A0CF381C9CC6734CB91A62959685222FC8CA69D532A725631E08880B882084C9DC1907253963E64B1C780AE8D4CF94F3B969C3CFDA58ADDF1074B16F967188443B4792D307A8B4E2B0B1D4E3CB2726EA9FC9C890E26E595B69E3C52ACC8CDF68334DE2F63EC6C0378D812715FE389B88DEAE2664943AEC56B1F97631F162A8B696814B8D4976492B6D274AD9FD5B9C8C4BB133ACA376ED68AC365E4B4E63C249E0902245710949B713E038551EC7B6BE39387DB5B7710873AC8C5E322444BD629D618650D8558F117D37AC0BED021263B52E3488AFA12E3325B534F36A4852549582920A54082083C88A0C293B6767B16A71D65B44E8C0950F97B0284F80E99B0E03D16B5416B66482AE9BCD9439E2920857BD26C7F3D05CDB4256016D571FE5FE5E14B039174F05F11E15315574F50F211EB07FCF5052A0A49F3248B722395140008E07DD41F348BDA813537EB07DB41468BF0B58507C2D0F11EDA064EA00E039FA2B14355A0F1F4DB9D05B1F6D649E045BC6A0E0CFE24604AEFBE260B8AD29636C30924F1FB6FAD57B7BABAF1E92B405F84E33D02C381E4472A7966DA49569294A52937B9E35A454CB0F4682C4690D16DC4A41215E24DC9FCA682498DC90C4B12DD3A00E932971C50FB286DC4C95D8DFC7A001F51AD4086373F29A795A5D21C46B2D2FC52A658712957B52F4F5AFF6AC7C2AE8CF7B177067B3FF007960A26DF973B139A4987226E3DE6623D0D518171A7D335D428C72C458896752483D12EA53E650A73E463BEEF37BC666EBCCB9B9A0AB1F25C90E49FBB1852971D98D72F34DB4B51254D74A120B4544EA6F42BF4812BE44CFE95BB693F797D43765F6C749769DBAE1BD3420A75218C438654AD417E5166E0B86C6F706DC6F6A83D7C564140501416BC8E1719956CB736221D3CD2E5ACB49F4855041E76C9971D4A77112C3A8FD18D20F987A82F9FC6F411F5AE4415F4B2911C8847FAC50F21F5858E1F1B5677F90F90843802DA21639820FF9A982BB280B1E5E83FE56FCD5026B690B3F674ABC081FC9C0FC28BA494CB80128216073038DBDDCE8A4BC388B0F4D053617E74011E1E9A06EB4055EE2B3435533E07978D4C0DDC640F2DE983CFC7E220BEA7D4BCB654757CB6DE80129F40515AAD5D39F495A3AEEA084DB99E75A432585E94824E9E7A7C2FECA0752B15225619C29242E5873A2802E54846841E1EB2A2055D137EDDEC9FBD321026E6D3D0C43D3E0A646A246A8B94CC2A0EB26E080954720D3466F65F6F6E76F1DC9AF2A8DAB94DD6CB380DC98B6D769CF63700F2E3BEA80C252B529DC8CA4A5A0E592505A706AB2B8EF89E064C978163318D4B0FE2A1B9B936EB0EAA0E22059C8EEB51DC8A5FC5C7284ADC90716F8878F5C8715A4B4893A7FD12AB520D8AFC3AF6C207D4942C8212896319B632D29992A0012D3E22B21F4FF00396CB915C3FF00C85563B1DF5AC0280A0280A028117E3B12505B7DA4BA83FA2A17A0884ED9915454FE29E5E3A4137D293FC327D62C47E4A94479E67318D0539181F32D0E1F32C73E3EABDBDC0DFD5530119D85341119F1AD3F6D857948F6A556FE4A8145B0B411A9278723CEDECE20FC09A04C8041D49D607327891ED50171FBC28A6EB8ED2BCC9568BF227ECDFD4A1E5F8DA869B3CCBAD7DA4903C0FA7F92810171704548AF8403C85CD50D5C45CA8FA7C2B3479D4FC40A705FD526EA0AB94C4C762E371FE7357FCE6BA73322569C3AE368504BAAD1FC32EEA3F6424100927DA6AA1BB96529094F0E3F9CD067863090F1786C5E71E5057DDDB7F6FEE0653C400CBF9F10E4A48F63741779D8A932F228D8F1008E8DC593DC7B05B964128839285B87EF9C5BEFE9B10D74E4D96A1C9BD6AFD1AB204B7F67F3CEE767E4A7ED96B67E376D36DE230DDC1CBFCC2A5390A102D30FC18AEB85A7E74AF33BAD942BCEB52EE9E2E56A0CA5B2A72B21B730F3E3B8AC6236FB284AD6DB4A5A223D02338BF934B448F9818C80FBAF480494BF91C836855C58574F63A0FF0087FED8953FEA0BB83BCDA8831F80C26CA6B1B1A0B6E075B8F98C8651D3968A1C4A108508AE424C76F40D3D16DA009B1AE5D5F23B215905014050140501405004022C45C1E62823B92DB18BC8D965911DF4FD879B1A48F8588F711530465EC3E7F1855F2EE0C9C4BFFA272E556F68171EF07DB530304E4A03CA0D4C42F1EFDEC03BF66E3C12B06DF034C0F1715606B6C8701FD349E27F780E3FBC281A2925BB807A617E1C120FC6EDABF21A8AA14DA5474A9B0951E41234ABFA0A363FBA6810310927A6A0B239A47050F6A4F1A060B469569570A955E65FF1039A55F525DD17F51598D271AD057EC308245FD55D12B5051911290D82B0A2F32EB055FDAA2E9FEB24511276E489AC4571BB13A12A3E9BD81FCF419FF1BFFE936C6C6C6A53AD5BAB646E3D9A940F1C962B28BCB426AFFACE1719427D6A14174C5671A56DCDD3DD495127E431DB8E3C2DBB9EC744BB0B899E7222E3CCCA37216D3ADB64C2B94288E2B92A6D5CAB5062A8B84C2ABE633FB5A0CC43509369BBEF71968C5C3A08E7022470A6DC94AE4D82A52AFC50DA4F9D2833C6C5CAE4F0DB411136F6232597DCC27B58AD91B6BA61F95272D21C3331901B69172B5B6FA8E5B20A3FA6DC464F015D77C0EDB7D007D2D6E2FA7CED9B391EE138A67B8FB9D2E3B99C4B320BAD4561D73AADB724A4A90EC90492B583617D22F62A579F3C8E8155050140501405014050140501416F998B813D253263217ABED280B13ED239FBE821F2369CC824BB8498A426F7F95558A6DFB2A207C0A6A516A5E465455747338E5B64F390CA54A040E1729B6AB7AEC47AEA072CA61CB6B5C190875951FB0920A6FF00B26E3E16A8295C75A2C9522FA797350F81F38F7134082925CB6A01D4820127F896FDE165A7DF425794EFADF9EAC977E3BB92D4504399D6D090D15A901286EDE52E252AB70F102B691A798A89364AF4B4A28687E9FAF98B7B0F1A2B23E19A938E6E5B92521F4324BAC348E6A01254522FCBCD419536664DBCCE0E7ED3833CC3DC303211B74EC058586D6A9A8425A7E236A3C9C90D86D4D7A5D6528E6A14132DD7BAB10CE66363F6CF72226C6DC3B49B938EDC90D4CCA8D8E999390F17F2B2623F091252A438F9E9969D42400DA745D3603625DDA0D85DC3FA80DE90768F6ED733BA1BA23292A95BBE634F47DB1B65851F34B21F4A0BAEFEA02D2137E4870F113EC3D107D36FD2376EBE9DF1109E84D1DCFBF7E5DC6F27BDF200B8FEA90BEAC81152B24321D5805653E6734A7592129025BA36BAA0280A0280A0280A0280A0280A0280A02810911A3CB6CB525943CD9E3A562FC7D23D141129FB321BCB32203CB8524FFAC0A209FDE1C4FEF6AA9823923FC4587BA66C51928A93C1C00257C3C6E3C87DFA4FAAA604C66B0AE21D7E4CB4E30C56D4F4932C96BA6840D4A52946C424017241228AF283F569222E53BDBDC997F3C97A06477097D8C924BAF36E3250121C4AD63A8B1EB22E7D156DCF86794331BB45D3896F2585663E7B189490E4EC4B9F35D352402A4BACE94BC829BF1D4DF0F4D67FD9FCB7F5AB4A969690FBA9293D2697C798B816E23D55679F2CDD3CDB0206CCC4E137EE5DF41C8BEECD95B260B48EABCC96D4B61A98F799212DB4F8538DA38A94A45F8279E874ABE94BE8BF7F7D47CAC46FDEEE6DCC035DB8506A42B7ACFC7C88BB8770365374F4BA0EC76DEBD805C890DA89BF0EA1B917477D3B7DDB5D85DAADBB1F6A76EB69E3768602312A463B1AC2594A9679B8E10352D67C54A249A8271405014050140501405014050140501405014050140117E0788F45045739B3B0B9D852E1BED2A2896DAD05F8E425682B16D680414A5439DC0A0E77ECAFC353B613F77E6B7977A6137BB75CB51C16D6893652201690F38A43B34A7A2A7545B2D8D02C9E0A2BEA155C679967CAD6F64AECAF69266D489B1DEEDBEDE4ED3C7A3463B06CC0618662D8582A3F4929534BFE7A0857AEAD92FB35C5BFAC6FA10CCE03B99B5B71F6B769E5B31DA9CBA2233BA4C54AB33331D21133F8A171EC243CD38D2D3A492B3C149528796A7AF44ADB6ED97E1CFDBB7FB8388EED7752046CD7DDB89C5B3B77B70DC75C6C64795113ACC897194E380F121223025A4DBCDAEA71B2795EAEBA70D34D30D34C30D219619425B6596D212842122C94A5238000700056D929405014050140501405014050140501405014050140501405014050140501405014050141FFD9, '2020-12-02 12:35:29', 'test');

-- ----------------------------
-- Table structure for v_machine
-- ----------------------------
DROP TABLE IF EXISTS `v_machine`;
CREATE TABLE `v_machine`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '框架型号',
  `price` decimal(10, 4) NULL DEFAULT NULL COMMENT '价格',
  `profit` decimal(10, 4) NULL DEFAULT NULL COMMENT '单日收益',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标地址',
  `running_days` int(0) NULL DEFAULT NULL COMMENT '运行天数',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `yn` tinyint(0) NULL DEFAULT NULL COMMENT '是否有效 0 有效 1 无效',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_pk_key_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100670 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '矿机表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_machine
-- ----------------------------
INSERT INTO `v_machine` VALUES (100666, 'G28S-64T', 310.0000, 3.6000, 'be5b713d6b7eebdaf5da2ee8413b59dc', 180, '2020-10-20 11:25:34', 'test', '2020-12-02 12:35:22', 'test', 0);
INSERT INTO `v_machine` VALUES (100667, 'G28S-64TS', 600.0000, 9.3000, 'feb7c04ceb0181263bb93cb485f5a204', 140, '2020-10-20 11:32:57', 'test', '2020-12-02 12:35:31', 'test', 0);
INSERT INTO `v_machine` VALUES (100668, 'G28S-66T', 1150.0000, 20.8000, '38ee9604805e2cfcbba37058da9ef183', 120, '2020-11-03 17:27:04', 'test', '2020-12-02 12:35:42', 'test', 0);
INSERT INTO `v_machine` VALUES (100669, 'G28S-66TS', 2350.0000, 55.5000, '9b9450797293c83fdfef0398e33fa811', 90, '2020-11-26 14:30:13', 'test', '2020-12-02 12:35:56', 'test', 0);

-- ----------------------------
-- Table structure for v_news_info
-- ----------------------------
DROP TABLE IF EXISTS `v_news_info`;
CREATE TABLE `v_news_info`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(0) NULL DEFAULT NULL COMMENT '资讯类别 1币圈',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资讯标题',
  `author` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `introduce` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `link` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `state` int(0) NULL DEFAULT NULL COMMENT '资讯状态 0待审核 1通过 2未通过',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资讯管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_news_info
-- ----------------------------
INSERT INTO `v_news_info` VALUES (1, 2, '数字人民币红包又来了 这次选择落地苏州', '金色财经', '继今年10月在深圳推出之后，数字人民币红包近日又在苏州启动试点。记者12月5日获悉， “双12苏州购物节”将面向符合条件的苏州市民发放2000万元数字人民币消费红包，每个红包金额为200元，红包数量共计10万个。', '<p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">继今年10月在深圳推出之后，数字人民币红包近日又在苏州启动试点。记者12月5日获悉，&nbsp;<strong style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">“双12苏州购物节”将面向符合条件的苏州市民发放2000万元数字人民币消费红包，每个红包金额为200元，红包数量共计10万个。</strong></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">据了解，数字人民币消费红包将采取“摇号抽签”形式发放，抽签报名通道自2020年12月5日0时正式开启，持续至12月6日24时。</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">申领对象方面，参与此次苏州数字人民币消费红包活动，需满足以下条件之一：近3年（按月）在苏州大市范围内有一笔（或以上）正常社保缴费记录；户籍或暂住地在苏州大市范围内。</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">伴随技术的成熟，离线钱包测试也将在此次苏州试点活动中开展。市民在通过“苏周到”APP预约登记抽取数字人民币消费红包时，可根据本人意愿选择报名参加离线钱包体验计划。</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">预约活动完成后，苏州市政府将会同运营机构从报名参加离线钱包体验计划的人员中选择部分人员作为离线钱包体验人员，体验人员人数控制在1000人之内。离线支付体验即在无网或弱网条件下，用户进行交易或者转款时不连接后台系统，而是在钱包中验证用户身份、确认交易信息并进行支付的方式。</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">央行数字货币研究所所长穆长春在今年10月就曾表示，“双离线”钱包已经开发完毕，使用时也较为顺畅，在后续试点过程中会加以应用。&nbsp;</strong>他还表示，数字人民币是以广义账户体系为基础，只要能够成为唯一身份的标识，都可以开立数字钱包，所以今后也会出现以其他认证形式开立的数字钱包。</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">据了解，在苏州开展本次数字人民币消费红包活动，只是数字人民币研发过程中的一次常规性测试，并不意味着数字人民币正式落地。</strong></p><p><br/></p>', '', 1, '2020-12-07 10:27:25', NULL);
INSERT INTO `v_news_info` VALUES (2, 2, '什么是IPFS，一次性为您说个明白', 'THKB1112', '了解区块链的朋友都知道，区块链是一种分布式记账的互联网技术。而IPFS也是区块链的技术之一。IPFS是一个分布式存储系统，你可以先把它理解为是很多个分布在世界各地的网络存储空间相互连接组成的数据文件存储系统。人们把这个系统命名为星级文件系统，IPFS就是星际文件系统的英文缩写。', '<p><span style=\"color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; text-indent: 35px; background-color: rgb(255, 255, 255);\">了解区块链的朋友都知道，区块链是一种分布式记账的互联网技术。而IPFS也是区块链的技术之一。IPFS是一个分布式存储系统，你可以先把它理解为是很多个分布在世界各地的网络存储空间相互连接组成的数据文件存储系统。人们把这个系统命名为星级文件系统，IPFS就是星际文件系统的英文缩写。</span></p><p><span style=\"color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; text-indent: 35px; background-color: rgb(255, 255, 255);\"></span></p><p>分布式存储<a style=\"box-sizing: border-box; margin: 0px; padding: 0px; color: inherit;\"></a></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">这个文件系统跟现在大家浏览网页的网站空间有什么区别呢？首先，咱们现在浏览网页的网站空间是把很多数据集中存储在在一个服务器或者一个由某公司统一管理的机房。但IPFS是把数据文件分别存储到分布在世界各地的不同服务器里。这就是分布式存储。那么为什么分布式存储更有优势呢？</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">首先，分布式存储相当于把数据文件同时备份在很多地方，一个地方出了问题并不影响你的数据安全。但是在传统的中心化存储服务器里，只要服务器出了问题，文件安全就会受到威胁，甚至有可能造成数据的永久性丢失，这是第一点。</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">第二、中心化存储的服务器容易受人控制和操纵。比如你在银行的系统里存了一大笔钱，如果银行系统受到黑客的攻击，你的钱可能被偷偷转走。但是在IPFS里，黑客不可能同时攻击分布在世界各地的大部分服务器，所以你的资金就非常安全。</p><p><span style=\"color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; text-indent: 35px; background-color: rgb(255, 255, 255);\"><br/></span></p><p>区块链<a style=\"box-sizing: border-box; margin: 0px; padding: 0px; color: inherit;\"></a></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">接下来就是大家所听到内容寻址。什么是内容寻址呢？这样的专业术语行外人根本看不懂。但是我举个例子你就明白了。假设你要访问一个网站，每个页面都有一个唯一的网址，你要访问某个具体的文件是输入这个唯一的地址去找到存在特定服务器位置的文件。这就是传统的http协议，简而言之就是你向固定的服务器地址发送一个唯一的指令，然后这个服务器就把你要的文件传输到你浏览的地方。但是IPFS不同，IPFS是每个文件都有一个唯一的身份标识，就像每一个人都有一个人唯一的身份证号码一样。你要浏览某个文件的时候，只要通过这个唯一标识就可以找到相应的数据文件。这种传输模式的好处是速度更快，可以节省更多带宽。如果你还不理解，那么你可以想一想打车的例子，传统模式相当于车停在固定的位置，你只能到指定的位置找到你要的车。IPFS 模式是车分布在各个地方，你只要指定你要的车的车牌号是什么，相应的车就会找到你。这就是所谓的内容寻址。</p><p><span style=\"color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; text-indent: 35px; background-color: rgb(255, 255, 255);\"><br/></span></p>', '', 1, '2020-12-07 10:34:58', NULL);
INSERT INTO `v_news_info` VALUES (3, 1, '三大所的BTC都有所流失，火币稳定币入金最多', '火币', '随着OKEx重新开放提币，国内三大主要交易所的“流量”之争也日益激烈。在OKEx推出用户回馈方案后，火币和币安也上线了优惠计划。', '<section><span style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">随着OKEx重新开放提币，国内三大主要交易所的“流量”之争也日益激烈。在OKEx推出用户回馈方案后，火币和币安也上线了优惠计划。11月26日，火币推出为期一个月的“VIP点亮计划”，充值10 BTC及以上的用户，可按级别享受现货3个月的VIP费率优惠。同一天，币安也推出了充值享VIP费率的活动。但对比来看，火币在现货与合约方面给到的手续费费率更低，且面向所有新老用户，优惠使用期限长达3个月，而币安则针对散户和VIP都推出了优惠活动，但手续费优惠力度更小，优惠使用期限只有2个月。</span></section><p><section><span style=\"box-sizing: border-box; margin: 0px; padding: 0px;\"></span></section><section><span style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">从11月20日至11月30日三大交易所的资金流动情况来看，&nbsp;</span><span style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">用户“用脚投票”的结果也十分明显，火币是入金最多的交易所&nbsp;</span><span style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">。</span></section><section><span style=\"box-sizing: border-box; margin: 0px; padding: 0px;\"></span></section><section><span style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">根据资产追踪平台CoinHelmes的统计，11月20日至11月30日期间，币安总流入了50828 BTC，总流出了108384 BTC，总计净流出了57556 BTC，是三家交易所中净流出比特币资产最多的一家，相当于平均每天净流出5232 BTC。按照此前PAData对交易所之间资金流动的观察，币安流出的资金很大一部分将进入Coinbase。</span></section><section><span style=\"box-sizing: border-box; margin: 0px; padding: 0px;\"></span></section><section><span style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">其次，OKEx总流入了13691 BTC，总流出了21904 BTC，净流出了8212 BTC，可见，大规模用户回馈计划见效甚微，主流资产仍然在外流。另外，&nbsp;</span><span style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">火币在此期间总流入了32091 BTC，总流出了33480 BTC，只净流出了1389 BTC是三家交易所中流出比特币资产最少的一家，甚至总净流出额比币安日均净流出额都低。</span></section></p><p><br/></p>', '', 1, '2020-12-07 10:36:40', NULL);
INSERT INTO `v_news_info` VALUES (4, 2, '苏州数字人民币红包正式启动预约，首次接入京东线上消费场景', 'KPI', '12月5日消息，今日苏州市人民政府联合中国人民银行开展的数字人民币红包试点工作正式启动预约。该试点结合“双十二苏州购物节”，将面向所有符合条件的苏州市民发放总计2000万元的数字人民币红包，红包数量共计10万个，每个红包200元，并于12月11日20：00时正式生效。', '<p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; font-size: medium;\">12月5日消息，今日苏州市人民政府联合中国人民银行开展的数字人民币红包试点工作正式启动预约。该试点结合“双十二苏州购物节”，将面向所有符合条件的苏州市民发放总计2000万元的数字人民币红包，红包数量共计10万个，每个红包200元，并于12月11日20：00时正式生效。</span></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; font-size: medium;\">其中，&nbsp;<strong style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">京东商城支持市民在购买自营爆品时使用数字人民币支付，成为试点中首个接入数字人民币的线上场景&nbsp;</strong>；同时市民可在苏州市辖区内近万家线下商户无门槛消费，京东旗下五星电器、京东之家、京东便利店等线下场景均有门店支持使用数字人民币。此外，京东与苏州市相城区联合打造了数字人民币特色支付场景，收货地址在相城区的市民可选择京东商城自营商品货到付款场景使用数字人民币支付，足不出户即可体验数字人民币的便捷支付服务。&nbsp;<br/></span></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; font-size: medium;\">此前，京东数科与人民银行数字货币研究所已达成战略合作，双方以数字人民币项目为基础，共同推动移动基础技术平台、区块链技术平台等研发建设，并结合京东各大场景，共同促进数字人民币的移动应用功能创新及线上、线下场景的落地应用。</span></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2; overflow: hidden; text-indent: 35px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; font-size: medium;\">苏州市相城区具备数字金融、工业互联网发展基础，正在加快进度、加大力度推进制造业智能化改造和数字化转型。12月11日晚，苏州市将正式启动“双十二苏州购物节”活动，围绕吃、住、行、游、购、娱六大要素和夜间经济、品牌经济两大经济形态，增强内需对区域经济的拉动作用，并加快形成“双循环”新发展格局，联动近万家线下商户及电商等参与数字人民币红包活动，并会同工、农、中、建、交、邮储六大行及京东数科、华为、vivo等众多商业企业，打造继党费缴纳、智慧停车、交通补贴发放、智慧商圈建设、税费缴纳之后的又一创新应用场景。</span></p><p><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; font-size: medium;\"><br/></span></p><p><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Monospaced Number&quot;, &quot;Chinese Quote&quot;, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: medium; background-color: rgb(255, 255, 255);\"></span></p><p><br/></p>', '', 1, '2020-12-07 10:38:12', NULL);

-- ----------------------------
-- Table structure for v_user
-- ----------------------------
DROP TABLE IF EXISTS `v_user`;
CREATE TABLE `v_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `head_img` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像图片',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `account_type` tinyint(0) NULL DEFAULT NULL COMMENT '账号类型 1手机:2邮箱',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐',
  `state` tinyint(0) NULL DEFAULT NULL COMMENT '状态: 0 正常;1禁止登陆;2禁止提现 ;',
  `type` tinyint(0) NULL DEFAULT 0 COMMENT '类型 0 普通玩家;1 代理',
  `invited_id` bigint(0) NULL DEFAULT NULL COMMENT '邀请者',
  `tag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `yn` tinyint(0) NULL DEFAULT NULL COMMENT '是否有效 0 ;有效 1 无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100066 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '玩家基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_user_authentication
-- ----------------------------
DROP TABLE IF EXISTS `v_user_authentication`;
CREATE TABLE `v_user_authentication`  (
  `id` bigint(0) NOT NULL COMMENT '用户ID,v_user.id',
  `real_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实名称',
  `id_card_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `id_card_img1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面',
  `id_card_img2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证反面',
  `id_card_with_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手持身份证',
  `state` tinyint(0) NULL DEFAULT NULL COMMENT '0待审核;1:审核通过;2审核不通过',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户认证信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_user_cash_out
-- ----------------------------
DROP TABLE IF EXISTS `v_user_cash_out`;
CREATE TABLE `v_user_cash_out`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID v_user.id',
  `amount` decimal(10, 4) NULL DEFAULT NULL COMMENT '提现金额',
  `fee` decimal(10, 4) NULL DEFAULT NULL COMMENT '手续费',
  `usdt` decimal(10, 4) NULL DEFAULT NULL COMMENT '提现的usdt',
  `chain` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链名称',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包地址',
  `state` tinyint(0) NULL DEFAULT NULL COMMENT '状态  0:提现中;1:成功;2:取消 ',
  `memo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提现表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_user_machine
-- ----------------------------
DROP TABLE IF EXISTS `v_user_machine`;
CREATE TABLE `v_user_machine`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id v_user.id',
  `parent_user_id` bigint(0) NULL DEFAULT NULL COMMENT '上级用户id v_user.id',
  `parent_machine_id` bigint(0) NULL DEFAULT NULL COMMENT '上级矿机id v_user_machine.id',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '框架型号',
  `machine_id` bigint(0) NULL DEFAULT NULL COMMENT '矿机ID',
  `price` decimal(10, 4) NULL DEFAULT NULL COMMENT '价格',
  `profit` decimal(10, 4) NULL DEFAULT NULL COMMENT '单日收益',
  `multiple` decimal(10, 4) NULL DEFAULT NULL COMMENT '倍数',
  `invited_code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  `children_count` int(0) NULL DEFAULT NULL COMMENT '子矿机数量',
  `state` tinyint(0) NULL DEFAULT NULL COMMENT '状态 0 待支付;1运行中;2已停止',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标地址',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '截止时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `yn` tinyint(0) NULL DEFAULT NULL COMMENT '是否有效 0 ;有效 1 无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户矿机表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_user_question
-- ----------------------------
DROP TABLE IF EXISTS `v_user_question`;
CREATE TABLE `v_user_question`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id v_user.id',
  `question_id` bigint(0) NULL DEFAULT NULL COMMENT '问题id v_user_question_dict.id',
  `answer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答案内容',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户密保问题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_user_recharge
-- ----------------------------
DROP TABLE IF EXISTS `v_user_recharge`;
CREATE TABLE `v_user_recharge`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID v_user.id',
  `amount` decimal(10, 4) NULL DEFAULT NULL COMMENT '充值金额',
  `usdt` decimal(10, 4) NULL DEFAULT NULL COMMENT '充值的usdt币数',
  `chain` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链名称',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包地址',
  `state` tinyint(0) NULL DEFAULT NULL COMMENT '状态  0:充值中;1:成功;2:失败',
  `serial_no` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流水号',
  `memo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '充值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_user_transfer
-- ----------------------------
DROP TABLE IF EXISTS `v_user_transfer`;
CREATE TABLE `v_user_transfer`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '玩家v_user.id',
  `amount` decimal(20, 4) NULL DEFAULT NULL COMMENT '划转数量',
  `fee` decimal(20, 4) NULL DEFAULT NULL COMMENT '手续费',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `to_user_id` bigint(0) NULL DEFAULT NULL COMMENT '对方用户ID',
  `to_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对方账号',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_user_id_create_time`(`user_id`, `create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '转账记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_user_usdt_wallet
-- ----------------------------
DROP TABLE IF EXISTS `v_user_usdt_wallet`;
CREATE TABLE `v_user_usdt_wallet`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '玩家ID v_user.id',
  `chain` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链名称',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包地址',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uk_user_chain`(`user_id`, `chain`) USING BTREE COMMENT '唯一性'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户usdt 钱包地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_user_wallet
-- ----------------------------
DROP TABLE IF EXISTS `v_user_wallet`;
CREATE TABLE `v_user_wallet`  (
  `id` bigint(0) NOT NULL COMMENT '用户ID v_user.id',
  `trade_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易密码',
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐',
  `total_amount` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '累计总金额',
  `usable_amount` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '可用余额',
  `frozen_amount` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '冻结余额',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户钱包' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_user_wallet_log
-- ----------------------------
DROP TABLE IF EXISTS `v_user_wallet_log`;
CREATE TABLE `v_user_wallet_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自助ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID v_user.id',
  `amount` decimal(20, 4) NULL DEFAULT NULL COMMENT '变动金额',
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '变动类型 ;0:收益;1:交易;2:转账;3:充值;4:提现;5:平台奖励;6:提成',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `PK_USER_ID_CREATETIME`(`user_id`, `create_time`) USING BTREE COMMENT '用户ID+create_Time'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '钱包日志\r\n' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
