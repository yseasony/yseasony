/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.236_3306
Source Server Version : 50149
Source Host           : 192.168.1.236:3306
Source Database       : mz_analytics

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2011-01-07 15:26:33
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ma_promotion_pkg`
-- ----------------------------
DROP TABLE IF EXISTS `ma_promotion_pkg`;
CREATE TABLE `ma_promotion_pkg` (
  `pkg_number` char(10) NOT NULL,
  `version` char(10) NOT NULL,
  `os` varchar(20) NOT NULL,
  `down_add` varchar(300) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`pkg_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ma_promotion_pkg
-- ----------------------------
INSERT INTO `ma_promotion_pkg` VALUES ('0000000000', '220', '', null, '2011-01-07 14:58:20');
