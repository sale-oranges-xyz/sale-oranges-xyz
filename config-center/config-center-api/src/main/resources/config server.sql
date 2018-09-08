/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50131
Source Host           : localhost:3306
Source Database       : common_config_server

Target Server Type    : MYSQL
Target Server Version : 50131
File Encoding         : 65001

Date: 2018-08-25 18:43:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_properties
-- ----------------------------
DROP TABLE IF EXISTS `sys_properties`;
CREATE TABLE `sys_properties` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `key` varchar(100) DEFAULT NULL COMMENT '键',
  `value` text COMMENT '值',
  `application` varchar(100) DEFAULT NULL,
  `profile` varchar(100) DEFAULT NULL,
  `lable` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_properties
-- ----------------------------
INSERT INTO `sys_properties` VALUES ('1', 'test', 'this is test', 'cloud-licent', 'test', 'master');
