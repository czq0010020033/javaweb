/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : bank

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-29 15:41:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `account_id` int(4) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `card_id` decimal(18,0) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountid` (`account_id`),
  CONSTRAINT `person_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`accountid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '1', '王志国', '32', '男', '320542000000000001', '北京', '15810000001');
INSERT INTO `person` VALUES ('2', '2', '赵强', '43', '男', '320542000000000002', '湖北武汉', '15810000002');
INSERT INTO `person` VALUES ('3', '3', '薛梅', '26', '女', '312556000000000003', '江苏南京', '15320000003');
INSERT INTO `person` VALUES ('4', '4', 'www', '43', '女', '312556000000000004', '江苏', '15320000004');
INSERT INTO `person` VALUES ('5', '5', 'eee', '34', '男', '312556000000000005', '湖北武汉', '15320000005');
INSERT INTO `person` VALUES ('6', '6', 'my', '35', '男', '312556000000000006', '北京', '15320000006');
INSERT INTO `person` VALUES ('7', '7', 'sss', '56', '男', '312556000000000007', '北京', '15320000007');
INSERT INTO `person` VALUES ('8', '8', 'vvv', '45', '女', '312556000000000008', '江苏无锡', '15320000008');
INSERT INTO `person` VALUES ('9', '9', 'xxx', '38', '男', '312556000000000009', '江苏无锡', '15320000009');
