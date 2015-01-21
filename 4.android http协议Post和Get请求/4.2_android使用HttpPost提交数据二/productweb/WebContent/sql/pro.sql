/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : mydb2
Target Host     : localhost:3306
Target Database : mydb2
Date: 2013-07-31 14:50:33
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for pro
-- ----------------------------
DROP TABLE IF EXISTS `pro`;
CREATE TABLE `pro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `price` varchar(64) DEFAULT NULL,
  `img` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pro
-- ----------------------------
INSERT INTO `pro` VALUES ('51', 'XX', 'XXX', '34', 'pro1.png');
