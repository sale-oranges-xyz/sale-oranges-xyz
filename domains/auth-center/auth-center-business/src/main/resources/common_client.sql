/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50131
Source Host           : localhost:3306
Source Database       : common_client

Target Server Type    : MYSQL
Target Server Version : 50131
File Encoding         : 65001

Date: 2018-05-20 00:23:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for service_allow_clients
-- ----------------------------
DROP TABLE IF EXISTS `service_allow_clients`;
CREATE TABLE `service_allow_clients` (
  `id` bigint(20) NOT NULL,
  `service_id` bigint(20) DEFAULT NULL COMMENT '服务id',
  `is_locked` smallint(1) DEFAULT NULL COMMENT '是否禁止访问',
  `client_name` varchar(50) DEFAULT NULL COMMENT '允许访问服务名称',
  `secret_key` varchar(50) DEFAULT NULL COMMENT '密钥',
  `create_time` bigint(20) DEFAULT NULL,
  `modify_time` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service_allow_clients
-- ----------------------------

-- ----------------------------
-- Table structure for service_client
-- ----------------------------
DROP TABLE IF EXISTS `service_client`;
CREATE TABLE `service_client` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `service_name` varchar(50) DEFAULT NULL COMMENT '服务名称',
  `code` varchar(50) DEFAULT NULL COMMENT '会定时刷新的服务访问凭证',
  `description` varchar(50) DEFAULT NULL COMMENT '描述信息',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) DEFAULT NULL COMMENT '修改人',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间戳',
  `modify_time` bigint(20) DEFAULT NULL COMMENT '修改时间戳',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service_client
-- ----------------------------
