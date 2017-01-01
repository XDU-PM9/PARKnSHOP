/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : parknshop

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-31 15:12:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ordersId` int(11) NOT NULL AUTO_INCREMENT,
  `orderNumber` varchar(100) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `singleGoodId` int(11) DEFAULT NULL,
  `goodsId` int(11) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `goodsName` varchar(100) DEFAULT NULL,
  `goodsDescribe` varchar(200) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `paidTime` datetime DEFAULT NULL,
  `reciverPhone` varchar(50) DEFAULT NULL,
  `reciver` varchar(50) DEFAULT NULL,
  `postWay` varchar(50) DEFAULT NULL,
  `price` float(20,2) NOT NULL,
  `commission` float(20,2) NOT NULL,
  `commissionRate` float(20,2) NOT NULL,
  `comment` varchar(300) DEFAULT NULL,
  `commentTime` datetime DEFAULT NULL,
  `state` int(11) NOT NULL,
  `commentType` int(3) DEFAULT NULL,
  PRIMARY KEY (`ordersId`),
  KEY `FK_Reference_6` (`userId`),
  KEY `FK_Reference_7` (`goodsId`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`goodsId`) REFERENCES `goods` (`goodsId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
