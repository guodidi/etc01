/*
Navicat MySQL Data Transfer

Source Server         : guo
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : freeflowetc

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-05-30 15:23:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '郭垚辉', '123');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `vehicle_id` varchar(20) DEFAULT NULL COMMENT '车牌号码',
  `vehicle_type` varchar(10) DEFAULT NULL COMMENT '车辆类型',
  `rsu_id` varchar(30) DEFAULT NULL COMMENT 'rsu的编号',
  `road_id` varchar(30) DEFAULT NULL COMMENT 'rsu的名称',
  `trade_status` varchar(100) DEFAULT NULL,
  `trade_time` datetime DEFAULT NULL COMMENT '交易时间',
  `fee` bigint(20) DEFAULT NULL COMMENT '收费金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('17', '闽C', '小型车', '10', '3', '交易成功', '2016-04-30 23:04:24', '20');
INSERT INTO `record` VALUES ('18', '闽D', '大型车', '10', '3', '交易成功', '2016-04-30 23:16:18', '500');
INSERT INTO `record` VALUES ('19', '闽D', '大型车', '10', '3', '交易成功', '2016-04-30 23:17:25', '500');
INSERT INTO `record` VALUES ('21', '闽D', '大型车', '10', '3', '交易成功', '2016-04-30 23:17:33', '500');
INSERT INTO `record` VALUES ('23', '闽D', '大型车', '10', '3', '交易成功', '2016-04-30 23:17:33', '500');
INSERT INTO `record` VALUES ('24', '闽D', '大型车', '10', '3', '交易成功', '2016-04-30 23:17:33', '500');
INSERT INTO `record` VALUES ('26', '京·F23565', '大型车', '10', '3', '交易成功', '2016-05-01 00:05:27', '50');
INSERT INTO `record` VALUES ('27', '闽C', '小型车', '10', '3', '交易成功', '2016-05-01 00:08:54', '20');
INSERT INTO `record` VALUES ('28', '闽C', '小型车', '12', '3', '交易成功', '2016-05-01 00:09:21', '20');
INSERT INTO `record` VALUES ('29', '闽C', '小型车', '10', '3', '交易成功', '2016-05-01 00:12:26', '20');
INSERT INTO `record` VALUES ('32', '闽C', '小型车', '10', '3', '交易成功', '2016-05-01 00:30:31', '20');
INSERT INTO `record` VALUES ('34', '闽C', '小型车', '10', '3', '交易成功', '2016-05-01 07:52:53', '20');
INSERT INTO `record` VALUES ('35', '闽C', '小型车', '10', '3', '交易成功', '2016-05-02 15:22:35', '20');
INSERT INTO `record` VALUES ('36', '闽C', '小型车', '10', '3', '交易成功', '2016-05-02 19:07:36', '20');
INSERT INTO `record` VALUES ('38', '闽C', '小型车', '10', '3', '交易成功', '2016-05-03 00:28:30', '20');
INSERT INTO `record` VALUES ('40', '闽C', '小型车', '10', '3', '交易成功', '2016-05-03 01:10:28', '20');
INSERT INTO `record` VALUES ('41', '闽C', '小型车', '10', '3', '交易成功', '2016-05-03 01:17:07', '20');
INSERT INTO `record` VALUES ('42', '闽C', '小型车', '10', '3', '交易成功', '2016-05-03 01:17:38', '20');
INSERT INTO `record` VALUES ('43', '闽C', '小型车', '10', '3', '交易成功', '2016-05-03 01:17:53', '20');
INSERT INTO `record` VALUES ('44', '闽C', '小型车', '10', '3', '交易失败，设备不匹配', '2016-05-03 01:23:21', '0');
INSERT INTO `record` VALUES ('45', '闽C', '小型车', '10', '3', '交易失败，设备不匹配', '2016-05-03 01:23:34', '0');
INSERT INTO `record` VALUES ('47', '闽C', '小型车', '10', '3', '交易成功', '2016-05-03 01:25:34', '20');
INSERT INTO `record` VALUES ('48', '闽C', null, '10', '3', '交易失败，车上无OBU', '2016-05-03 01:30:16', '0');
INSERT INTO `record` VALUES ('49', '闽C', null, '10', '3', '交易失败，车上无OBU', '2016-05-03 01:30:27', '0');
INSERT INTO `record` VALUES ('50', '闽C', '小型车', '10', '3', '交易成功', '2016-05-03 03:09:56', '20');
INSERT INTO `record` VALUES ('51', '闽C', '小型车', '10', '3', '交易失败，设备不匹配', '2016-05-03 03:10:10', '0');
INSERT INTO `record` VALUES ('52', '闽C', null, '10', '3', '交易失败，车上无OBU', '2016-05-03 03:10:18', '0');
INSERT INTO `record` VALUES ('53', '闽C', null, '10', '3', '交易失败，车上无OBU', '2016-05-03 03:10:28', '0');
INSERT INTO `record` VALUES ('54', '闽C', '小型车', '10', '4', '交易成功', '2016-05-03 06:47:44', '20');
INSERT INTO `record` VALUES ('56', '闽C', '小型车', '10', '56', '交易成功', '2016-05-03 06:47:52', '20');
INSERT INTO `record` VALUES ('57', '闽C', '小型车', '10', '4', '交易成功', '2016-05-03 23:22:24', '20');
INSERT INTO `record` VALUES ('58', '闽C', null, '10', '4', '交易失败，车上无OBU', '2016-05-03 23:22:35', '0');
INSERT INTO `record` VALUES ('59', '闽C', null, '10', '4', '交易失败，车上无OBU', '2016-05-03 23:22:41', '0');
INSERT INTO `record` VALUES ('60', '闽C', null, '10', '4', '交易失败，车上无OBU', '2016-05-03 23:22:52', '0');
INSERT INTO `record` VALUES ('61', '闽C', '小型车', '10', '4', '交易失败，设备不匹配', '2016-05-03 23:23:13', '0');
INSERT INTO `record` VALUES ('62', '闽C', '小型车', '10', '4', '交易成功', '2016-05-10 16:39:00', '20');
INSERT INTO `record` VALUES ('63', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 17:20:30', '20');
INSERT INTO `record` VALUES ('64', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:30:31', '20');
INSERT INTO `record` VALUES ('65', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:30:39', '20');
INSERT INTO `record` VALUES ('66', '闽C', '小型', '10', '4', '交易失败，设备不匹配', '2016-05-12 21:30:54', '0');
INSERT INTO `record` VALUES ('67', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:37:19', '20');
INSERT INTO `record` VALUES ('68', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:37:19', '20');
INSERT INTO `record` VALUES ('69', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:37:19', '20');
INSERT INTO `record` VALUES ('70', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:37:19', '20');
INSERT INTO `record` VALUES ('71', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:37:20', '20');
INSERT INTO `record` VALUES ('72', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:48:59', '20');
INSERT INTO `record` VALUES ('73', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:49:00', '20');
INSERT INTO `record` VALUES ('74', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:49:00', '20');
INSERT INTO `record` VALUES ('75', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:49:01', '20');
INSERT INTO `record` VALUES ('76', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:49:01', '20');
INSERT INTO `record` VALUES ('77', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:49:02', '20');
INSERT INTO `record` VALUES ('78', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:53:18', '20');
INSERT INTO `record` VALUES ('79', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:53:19', '20');
INSERT INTO `record` VALUES ('80', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:53:19', '20');
INSERT INTO `record` VALUES ('81', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:53:19', '20');
INSERT INTO `record` VALUES ('82', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 21:53:19', '20');
INSERT INTO `record` VALUES ('83', '闽C', null, '10', '8', '交易失败，车上无OBU', '2016-05-12 21:59:27', '0');
INSERT INTO `record` VALUES ('84', '闽C', null, '10', '4', '交易失败，车上无OBU', '2016-05-12 21:59:27', '0');
INSERT INTO `record` VALUES ('85', '闽C', null, '10', '4', '交易失败，车上无OBU', '2016-05-12 21:59:28', '0');
INSERT INTO `record` VALUES ('86', '闽C', null, '10', '4', '交易失败，车上无OBU', '2016-05-12 21:59:28', '0');
INSERT INTO `record` VALUES ('88', '闽C', '小型车', '10', '4', '交易成功', '2016-05-12 22:31:23', '20');
INSERT INTO `record` VALUES ('89', '闽C', '小型车', '10', '4', '交易成功', '2016-05-13 01:28:37', '20');
INSERT INTO `record` VALUES ('90', '闽C', '小型车', '10', '4', '交易成功', '2016-05-13 01:28:37', '20');
INSERT INTO `record` VALUES ('91', '闽C', '小型车', '10', '4', '交易成功', '2016-05-13 01:28:38', '20');
INSERT INTO `record` VALUES ('92', '闽C', '小型车', '10', '4', '交易成功', '2016-05-13 02:44:35', '20');
INSERT INTO `record` VALUES ('93', '闽C', '小型车', '10', '4', '交易成功', '2016-05-13 02:47:53', '20');
INSERT INTO `record` VALUES ('94', '闽C', '小型车', '10', '4', '交易成功', '2016-05-13 02:52:51', '20');
INSERT INTO `record` VALUES ('95', '闽C', '小型车', '10', '4', '交易成功', '2016-05-13 02:52:51', '20');
INSERT INTO `record` VALUES ('96', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 02:02:51', '20');
INSERT INTO `record` VALUES ('97', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 02:02:51', '20');
INSERT INTO `record` VALUES ('98', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 02:02:51', '20');
INSERT INTO `record` VALUES ('99', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 02:23:54', '20');
INSERT INTO `record` VALUES ('100', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 03:00:35', '20');
INSERT INTO `record` VALUES ('101', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 03:00:45', '20');
INSERT INTO `record` VALUES ('102', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 03:20:28', '20');
INSERT INTO `record` VALUES ('103', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 03:56:56', '20');
INSERT INTO `record` VALUES ('104', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 03:57:12', '20');
INSERT INTO `record` VALUES ('105', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 04:10:56', '20');
INSERT INTO `record` VALUES ('106', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 04:10:59', '20');
INSERT INTO `record` VALUES ('107', '闽C', '小型车', '10', '4', '交易成功', '2016-05-16 04:12:45', '20');
INSERT INTO `record` VALUES ('108', '闽C', '小型车', '10', '4', '交易成功', '2016-05-17 01:03:58', '20');
INSERT INTO `record` VALUES ('109', '闽C', null, '10', '4', '交易失败，车上无OBU', '2016-05-17 01:04:18', '0');
INSERT INTO `record` VALUES ('110', '闽C', null, '10', '4', '交易失败，车上无OBU', '2016-05-17 01:04:30', '0');
INSERT INTO `record` VALUES ('111', '闽C', '小型车', '10', '4', '交易成功', '2016-05-17 03:20:18', '20');
INSERT INTO `record` VALUES ('112', '闽C', '小型车', '10', '4', '交易成功', '2016-05-17 03:40:17', '20');
INSERT INTO `record` VALUES ('113', '闽C', '小型车', '10', '4', '交易成功', '2016-05-17 03:40:17', '20');
INSERT INTO `record` VALUES ('114', '闽C', '小型车', '10', '4', '交易成功', '2016-05-17 13:37:08', '20');
INSERT INTO `record` VALUES ('115', '闽C', '小型车', '10', '4', '交易成功', '2016-05-17 13:37:11', '20');
INSERT INTO `record` VALUES ('116', '闽C', '小型车', '10', '4', '交易成功', '2016-05-17 13:37:45', '20');
INSERT INTO `record` VALUES ('117', '闽C', '小型车', '10', '4', '交易成功', '2016-05-18 04:45:56', '20');
INSERT INTO `record` VALUES ('118', '闽C', '小型车', '10', '4', '交易成功', '2016-05-18 04:45:58', '20');
INSERT INTO `record` VALUES ('119', '闽C', '小型车', '10', '4', '交易成功', '2016-05-18 04:45:58', '20');
INSERT INTO `record` VALUES ('120', '闽C', '小型车', '10', '4', '交易成功', '2016-05-18 15:18:52', '20');
INSERT INTO `record` VALUES ('121', '闽C', '小型车', '10', '4', '交易成功', '2016-05-18 15:18:55', '20');
INSERT INTO `record` VALUES ('122', '闽C', '小型车', '10', '4', '交易成功', '2016-05-18 15:18:56', '20');
INSERT INTO `record` VALUES ('123', '闽C', '小型车', '10', '4', '交易成功', '2016-05-18 15:21:35', '20');
INSERT INTO `record` VALUES ('124', '闽C', null, '11', '6', '交易失败，车上无OBU', '2016-05-18 15:21:48', '0');
INSERT INTO `record` VALUES ('125', '闽D', '中型车', '11', '6', '交易成功', '2016-05-18 15:22:27', '42');
INSERT INTO `record` VALUES ('126', '闽C', '小型车', '10', '4', '交易成功', '2016-05-19 04:34:19', '20');
INSERT INTO `record` VALUES ('127', '闽C', '小型车', '10', '4', '交易成功', '2016-05-19 13:22:17', '20');
INSERT INTO `record` VALUES ('128', '闽C', '小型车', '10', '4', '交易成功', '2016-05-19 15:30:24', '20');
INSERT INTO `record` VALUES ('129', '闽C', null, '10', '4', '交易失败，车上无OBU', '2016-05-19 15:30:53', '0');
INSERT INTO `record` VALUES ('130', '闽B·3456G', 'B1', '10', '4', '交易成功', '2016-05-22 22:29:32', '50');
INSERT INTO `record` VALUES ('131', '闽B·3456G', null, '10', '4', '交易失败，车上无OBU', '2016-05-22 22:29:57', '0');
INSERT INTO `record` VALUES ('132', '闽D·6589A', 'B1', '11', '2', '交易成功', '2016-05-22 22:32:31', '50');
INSERT INTO `record` VALUES ('133', '闽C', '小型车', '10', '4', '交易失败，设备不匹配', '2016-05-22 22:51:17', '0');
INSERT INTO `record` VALUES ('134', '闽C', '小型车', '10', '4', '交易失败，设备不匹配', '2016-05-22 22:51:24', '0');
INSERT INTO `record` VALUES ('135', '闽C', '小型车', '10', '4', '交易失败，设备不匹配', '2016-05-22 23:27:14', '0');
INSERT INTO `record` VALUES ('136', '闽D·6589A', 'B1', '10', '4', '交易成功', '2016-05-22 23:31:32', '50');
INSERT INTO `record` VALUES ('137', '闽D·6589A', null, '10', '4', '交易失败，车上无OBU', '2016-05-22 23:31:42', '0');
INSERT INTO `record` VALUES ('138', '闽B·3456G', 'B1', '11', '3', '交易成功', '2016-05-22 23:32:26', '50');
INSERT INTO `record` VALUES ('139', '闽D·6589A', 'B1', '10', '4', '交易成功', '2016-05-25 10:01:10', '50');
INSERT INTO `record` VALUES ('140', '闽D·6589A', 'B1', '10', '4', '交易成功', '2016-05-25 10:01:11', '50');
INSERT INTO `record` VALUES ('141', '闽D·6589A', 'B1', '10', '4', '交易成功', '2016-05-30 15:20:42', '50');
INSERT INTO `record` VALUES ('142', '闽D·6589A', 'B1', '10', '4', '交易成功', '2016-05-30 15:20:42', '50');

-- ----------------------------
-- Table structure for rsu
-- ----------------------------
DROP TABLE IF EXISTS `rsu`;
CREATE TABLE `rsu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `rsu_id` varchar(30) DEFAULT NULL,
  `rsu_name` varchar(50) DEFAULT NULL,
  `rsu_site` varchar(50) DEFAULT NULL,
  `road_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rsu
-- ----------------------------
INSERT INTO `rsu` VALUES ('1', '闽-GXHH', '福-FF', '福建泉州', null);
INSERT INTO `rsu` VALUES ('4', '闽-YLS', '泉港2', '福建泉州', null);
INSERT INTO `rsu` VALUES ('5', '闽-HDHH', '福-HH3', '福建漳州', null);

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `vehicle_id` varchar(20) DEFAULT NULL COMMENT '车牌号码',
  `vehicle_owner` varchar(30) DEFAULT NULL COMMENT '车主名称',
  `vehicle_type` varchar(10) DEFAULT NULL COMMENT '车辆类型',
  `obu_mac` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES ('12', '闽D·6589A', '庄水金', 'B1', '0D5435A4F8B04962B179C64ACB85B6A8');
INSERT INTO `vehicle` VALUES ('13', '闽B·3456G', '郑明秋', 'B1', '70B45FB3671341FA92BAB84915BACBD2');
INSERT INTO `vehicle` VALUES ('14', '闽A·25146', '刘峰', 'C1', 'BDA8642B736C498EB0E2F5C3CAA5688D');
INSERT INTO `vehicle` VALUES ('15', '闽B·9562B', '李浩然', 'C1', 'BDA86986426C498E8c2b3a57CAA5688D');

-- ----------------------------
-- Table structure for vehicle_type
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_type`;
CREATE TABLE `vehicle_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(10) DEFAULT NULL COMMENT '收费类型',
  `fee` int(11) DEFAULT NULL COMMENT '不同的车辆类型收费的金额不同',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicle_type
-- ----------------------------
INSERT INTO `vehicle_type` VALUES ('1', 'A1', '100');
INSERT INTO `vehicle_type` VALUES ('2', 'B1', '50');
INSERT INTO `vehicle_type` VALUES ('3', 'C1', '20');
