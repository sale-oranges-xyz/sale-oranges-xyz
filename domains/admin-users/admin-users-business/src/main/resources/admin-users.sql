/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50131
Source Host           : localhost:3306
Source Database       : common_admin_users

Target Server Type    : MYSQL
Target Server Version : 50131
File Encoding         : 65001

Date: 2018-05-13 22:37:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) unsigned NOT NULL,
  `permission` varchar(255) NOT NULL COMMENT '权限标识',
  `url` varchar(255) NOT NULL COMMENT '跳转连接',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `modify_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '1522077681444', 'ROLE_ADMIN', '/success', '', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `create_time` bigint(20) unsigned DEFAULT NULL,
  `modified_time` bigint(20) unsigned DEFAULT NULL,
  `status` smallint(1) DEFAULT '0',
  `is_super_admin` smallint(1) DEFAULT '0' COMMENT '是否超级管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '1522077681444', '1522077681444', '1', '0');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` varchar(32) NOT NULL,
  `permission_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `login_name` varchar(15) NOT NULL COMMENT '登录名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `user_name` varchar(15) NOT NULL COMMENT '用户名',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `modified_time` bigint(20) DEFAULT NULL COMMENT '最近一次账号修改时间',
  `login_time` bigint(20) DEFAULT NULL COMMENT '最近一次登录时间',
  `status` smallint(1) DEFAULT '0',
  `password_reset_time` bigint(20) DEFAULT NULL COMMENT '密码修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$05$x3NZTsQBngYzyxaOcNCFseJ/PirLpC5tqQktAXkCO8r2lvrhoZdDG', 'admin', '1522077681444', '1522077681444', null, '1', '1522077681444');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
