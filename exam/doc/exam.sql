/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2011-10-25 09:47:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `exam_authority`
-- ----------------------------
DROP TABLE IF EXISTS `exam_authority`;
CREATE TABLE `exam_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(30) NOT NULL,
  `button_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_authority
-- ----------------------------
INSERT INTO `exam_authority` VALUES ('1', 'authPage', '浏览权限', 'AUTH_AUTHPAGE');
INSERT INTO `exam_authority` VALUES ('2', 'rolePage', '角色浏览', 'AUTH_ROLEPAGE');
INSERT INTO `exam_authority` VALUES ('3', 'authSave', '保存权限', 'AUTH_AUTHSAVE');
INSERT INTO `exam_authority` VALUES ('4', 'authUpdate', '修改权限', 'AUTH_AUTHUPDATE');
INSERT INTO `exam_authority` VALUES ('5', 'authDelete', '删除权限', 'AUTH_AUTHDELETE');
INSERT INTO `exam_authority` VALUES ('6', 'roleSave', '保存角色', 'AUTH_ROLESAVE');
INSERT INTO `exam_authority` VALUES ('7', 'roleUpdate', '修改角色', 'AUTH_ROLEUPDATE');
INSERT INTO `exam_authority` VALUES ('8', 'roleDelete', '删除角色', 'AUTH_ROLEDELETE');
INSERT INTO `exam_authority` VALUES ('9', 'userPage', '后台用户浏览', 'USER_USERPAGE');
INSERT INTO `exam_authority` VALUES ('10', 'userDelete', '后台用户删除', 'USER_USERDELETE');
INSERT INTO `exam_authority` VALUES ('11', 'userUpdate', '后台用户修改', 'USER_USERUPDATE');
INSERT INTO `exam_authority` VALUES ('12', 'userSave', '后台用户添加', 'USER_USERSAVE');

-- ----------------------------
-- Table structure for `exam_examination_pager`
-- ----------------------------
DROP TABLE IF EXISTS `exam_examination_pager`;
CREATE TABLE `exam_examination_pager` (
  `ex_id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  `invigilate_name` varchar(30) NOT NULL,
  PRIMARY KEY (`ex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_examination_pager
-- ----------------------------

-- ----------------------------
-- Table structure for `exam_question_bank`
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_bank`;
CREATE TABLE `exam_question_bank` (
  `qb_id` int(11) NOT NULL,
  `ex_id` int(11) NOT NULL,
  `qt_id` int(11) NOT NULL,
  `title` varchar(500) NOT NULL,
  `question` varchar(500) NOT NULL DEFAULT '',
  `answer` varchar(500) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`qb_id`),
  KEY `FK_exam_pager_r_question_bank` (`ex_id`),
  KEY `FK_question_type_r_question_bank` (`qt_id`),
  CONSTRAINT `FK_question_type_r_question_bank` FOREIGN KEY (`qt_id`) REFERENCES `exam_question_type` (`qt_id`),
  CONSTRAINT `FK_exam_pager_r_question_bank` FOREIGN KEY (`ex_id`) REFERENCES `exam_examination_pager` (`ex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_question_bank
-- ----------------------------

-- ----------------------------
-- Table structure for `exam_question_type`
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_type`;
CREATE TABLE `exam_question_type` (
  `qt_id` int(11) NOT NULL,
  `type_name` varchar(15) NOT NULL,
  PRIMARY KEY (`qt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_question_type
-- ----------------------------

-- ----------------------------
-- Table structure for `exam_role`
-- ----------------------------
DROP TABLE IF EXISTS `exam_role`;
CREATE TABLE `exam_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_role
-- ----------------------------
INSERT INTO `exam_role` VALUES ('2', '管理员');
INSERT INTO `exam_role` VALUES ('1', '超级管理员');

-- ----------------------------
-- Table structure for `exam_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `exam_role_authority`;
CREATE TABLE `exam_role_authority` (
  `role_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  KEY `fkae2434663fe97564` (`authority_id`),
  KEY `fkae243466de3fb930` (`role_id`),
  CONSTRAINT `fkae2434663fe97564` FOREIGN KEY (`authority_id`) REFERENCES `exam_authority` (`id`),
  CONSTRAINT `fkae243466de3fb930` FOREIGN KEY (`role_id`) REFERENCES `exam_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_role_authority
-- ----------------------------
INSERT INTO `exam_role_authority` VALUES ('1', '1');
INSERT INTO `exam_role_authority` VALUES ('1', '2');
INSERT INTO `exam_role_authority` VALUES ('1', '3');
INSERT INTO `exam_role_authority` VALUES ('1', '4');
INSERT INTO `exam_role_authority` VALUES ('1', '5');
INSERT INTO `exam_role_authority` VALUES ('1', '6');
INSERT INTO `exam_role_authority` VALUES ('1', '7');
INSERT INTO `exam_role_authority` VALUES ('1', '8');
INSERT INTO `exam_role_authority` VALUES ('1', '9');
INSERT INTO `exam_role_authority` VALUES ('1', '10');
INSERT INTO `exam_role_authority` VALUES ('1', '11');
INSERT INTO `exam_role_authority` VALUES ('1', '12');

-- ----------------------------
-- Table structure for `exam_student_answer`
-- ----------------------------
DROP TABLE IF EXISTS `exam_student_answer`;
CREATE TABLE `exam_student_answer` (
  `sa_id` int(11) NOT NULL,
  `qb_id` int(11) NOT NULL,
  `ex_id` int(11) NOT NULL,
  `qt_id` int(11) DEFAULT NULL,
  `answer` varchar(500) NOT NULL,
  `create_time` date NOT NULL,
  PRIMARY KEY (`sa_id`),
  KEY `FK_Reference_4` (`ex_id`),
  KEY `FK_question_bank_r_student_answer` (`qb_id`),
  KEY `FK_question_type_r_student_answer` (`qt_id`),
  CONSTRAINT `FK_question_type_r_student_answer` FOREIGN KEY (`qt_id`) REFERENCES `exam_question_type` (`qt_id`),
  CONSTRAINT `FK_question_bank_r_student_answer` FOREIGN KEY (`qb_id`) REFERENCES `exam_question_bank` (`qb_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`ex_id`) REFERENCES `exam_examination_pager` (`ex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_student_answer
-- ----------------------------

-- ----------------------------
-- Table structure for `exam_user`
-- ----------------------------
DROP TABLE IF EXISTS `exam_user`;
CREATE TABLE `exam_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT '',
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `sex` bit(1) NOT NULL DEFAULT b'1',
  `email` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_user
-- ----------------------------
INSERT INTO `exam_user` VALUES ('10', 'yseasony', '123456', '', '', '', 'yseasony@gmail.com');

-- ----------------------------
-- Table structure for `exam_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `exam_user_role`;
CREATE TABLE `exam_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `fkfe85cb3e836a7d10` (`user_id`),
  KEY `fkfe85cb3ede3fb930` (`role_id`),
  CONSTRAINT `fkfe85cb3e836a7d10` FOREIGN KEY (`user_id`) REFERENCES `exam_user` (`id`),
  CONSTRAINT `fkfe85cb3ede3fb930` FOREIGN KEY (`role_id`) REFERENCES `exam_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_user_role
-- ----------------------------
INSERT INTO `exam_user_role` VALUES ('10', '1');
