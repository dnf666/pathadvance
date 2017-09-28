# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.17)
# Database: pms
# Generation Time: 2017-07-21 15:56:42 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

/**
	数据库表的更改内容：

	将 projectMember表
	    1.project_name改为了project_id 并且类型改为int
	    2.team_role改为project_role

*/


# Dump of table blog
# ------------------------------------------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table comment
# ------------------------------------------------------------

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



# Dump of table file
# ------------------------------------------------------------

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



# Dump of table notice
# ------------------------------------------------------------

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



# Dump of table project
# ------------------------------------------------------------

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



# Dump of table project_member
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_member`;

CREATE TABLE `project_member` (
  `project_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `team_name` varchar(255) NOT NULL,
  `project_role` varchar(50) NOT NULL DEFAULT '成员' COMMENT '负责人、成员',
  `join_time` mediumtext NOT NULL,
  `join_by` varchar(50) NOT NULL,
  `del_flag` tinyint(1) DEFAULT '0',
  `del_time` mediumtext,
  `del_by` varchar(50) DEFAULT NULL,
  `del_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table team
# ------------------------------------------------------------

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(255) NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_time` mediumtext NOT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `del_time` mediumtext DEFAULT NULL,
  `del_remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`team_name`),
  UNIQUE KEY `team_name_uindex` (`team_name`),
  UNIQUE KEY `team_id` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table team_master_history
# ------------------------------------------------------------

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



# Dump of table team_member
# ------------------------------------------------------------

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



# Dump of table user
# ------------------------------------------------------------

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



# Dump of table file_reference
# ------------------------------------------------------------

DROP TABLE IF EXISTS `file_reference`;
CREATE TABLE `file_reference` (
  `file_id` int(11) NOT NULL,

  `team_id` int(11) DEFAULT NULL,

  `project_id` int(11) DEFAULT NULL,

  `user_id` int(20) DEFAULT NULL,

  PRIMARY KEY (`file_id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
