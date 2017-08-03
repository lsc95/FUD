/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : 401

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-08-03 18:33:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for p_user
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `realName` varchar(100) NOT NULL,
  `photoName` varchar(100) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_user
-- ----------------------------
INSERT INTO `p_user` VALUES ('2', '李四', 'admin', 'BAT Java要求.txt', '35b0effc-2b30-4736-82d8-459261b907e6.txt', 'text/plain');
INSERT INTO `p_user` VALUES ('3', '李四', 'admin', '333.png', '0d5d3835-ce0e-4bb8-bfb2-b69d62ae52a4.png', 'image/png');
INSERT INTO `p_user` VALUES ('4', '赵六', 'admin', '333.png', '48756375-1c6d-4478-a5ba-a7fea95d35de.png', 'image/png');
