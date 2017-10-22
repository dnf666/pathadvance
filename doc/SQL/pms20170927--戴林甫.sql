/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : pms1

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-27 20:12:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `context` text NOT NULL,
  `create_time` mediumtext NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `del_time` mediumtext,
  `is_private` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blog_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', 'title2', 'context2', '2017/09/16 22-23-54', '戴林甫', '1', '2017/09/16 22-40-55', '1');
INSERT INTO `blog` VALUES ('2', 'title', 'context', '2017/09/16 22-24-49', '戴林甫', '0', null, '0');
INSERT INTO `blog` VALUES ('10', '题目', '测试', '2017/09/16 22-38-38', '戴林甫', '0', null, '0');

-- ----------------------------
-- Table structure for b_user_role
-- ----------------------------
DROP TABLE IF EXISTS `b_user_role`;
CREATE TABLE `b_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `master_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`,`master_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_user_role
-- ----------------------------
INSERT INTO `b_user_role` VALUES ('1', '1', '1');
INSERT INTO `b_user_role` VALUES ('2', '1', '1');
INSERT INTO `b_user_role` VALUES ('3', '1', '3');
INSERT INTO `b_user_role` VALUES ('3', '1', '戴林甫');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) NOT NULL,
  `comment_id` int(11) DEFAULT NULL,
  `context` varchar(255) DEFAULT NULL,
  `create_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL COMMENT '存储在服务器上面的地址',
  `class` varchar(50) NOT NULL,
  `size` double DEFAULT NULL COMMENT '文件的大小，单位MB',
  `create_by` varchar(50) NOT NULL,
  `create_time` mediumtext NOT NULL,
  `del_flag` tinyint(1) DEFAULT '0',
  `del_time` mediumtext NOT NULL,
  `is_privater` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for file_reference
-- ----------------------------
DROP TABLE IF EXISTS `file_reference`;
CREATE TABLE `file_reference` (
  `file_id` int(11) NOT NULL,
  `team_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file_reference
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_time` mediumtext NOT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `del_time` mediumtext,
  `context` varchar(500) NOT NULL,
  `team_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `notice_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) NOT NULL,
  `team_name` varchar(255) NOT NULL,
  `project_info` varchar(255) DEFAULT NULL COMMENT '项目的简单介绍\n',
  `create_by` varchar(50) NOT NULL,
  `create_at` mediumtext NOT NULL,
  `del_flag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `project_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------

-- ----------------------------
-- Table structure for project_member
-- ----------------------------
DROP TABLE IF EXISTS `project_member`;
CREATE TABLE `project_member` (
  `project_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `team_name` varchar(255) NOT NULL,
  `team_role` varchar(50) NOT NULL DEFAULT '成员' COMMENT '负责人、成员',
  `join_time` mediumtext NOT NULL,
  `join_by` varchar(50) NOT NULL,
  `del_flag` tinyint(1) DEFAULT '0',
  `del_time` mediumtext,
  `del_by` varchar(50) DEFAULT NULL,
  `del_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_member
-- ----------------------------

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(255) NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_time` mediumtext NOT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `del_time` mediumtext,
  `del_remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`team_name`),
  UNIQUE KEY `team_name_uindex` (`team_name`),
  UNIQUE KEY `team_id` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------

-- ----------------------------
-- Table structure for team_master_history
-- ----------------------------
DROP TABLE IF EXISTS `team_master_history`;
CREATE TABLE `team_master_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(255) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `to_role` varchar(50) DEFAULT NULL COMMENT '修改后的职务',
  `from_role` varchar(50) DEFAULT NULL COMMENT '修改前的职务',
  `modify_by` varchar(50) NOT NULL,
  `modify_at` mediumtext NOT NULL COMMENT ' 修改的时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_master_history_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_master_history
-- ----------------------------

-- ----------------------------
-- Table structure for team_member
-- ----------------------------
DROP TABLE IF EXISTS `team_member`;
CREATE TABLE `team_member` (
  `user_name` varchar(50) NOT NULL,
  `team_name` varchar(255) NOT NULL,
  `team_role` varchar(50) NOT NULL DEFAULT '成员',
  `team_privelige` tinyint(1) DEFAULT '0',
  `join_time` mediumtext NOT NULL,
  `join_by` varchar(50) NOT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `del_time` mediumtext,
  `del_by` varchar(50) DEFAULT NULL,
  `del_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_member
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_name` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  `picture` varchar(255) DEFAULT 'user.jpg',
  `stu_id` int(20) NOT NULL,
  `peofession` varchar(100) NOT NULL DEFAULT '' COMMENT '专业',
  `create_at` date NOT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `del_remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
