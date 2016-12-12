/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : parknshop

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-12-12 16:52:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `addressId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `others` varchar(255) DEFAULT NULL,
  `phone` bigint(11) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `postcode` int(6) DEFAULT NULL,
  PRIMARY KEY (`addressId`),
  KEY `FK_Reference_15` (`userId`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `userImage` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`adminId`),
  KEY `FK_Reference_11` (`roleId`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '/resources/images/a.png', 'admin', '123456', '4');

-- ----------------------------
-- Table structure for advert
-- ----------------------------
DROP TABLE IF EXISTS `advert`;
CREATE TABLE `advert` (
  `advertId` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `typeId` int(11) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `position` int(11) NOT NULL,
  `price` float(20,2) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`advertId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advert
-- ----------------------------

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `cartId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `goodsId` int(11) NOT NULL,
  `singleGoodId` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`cartId`),
  KEY `FK_Reference_5` (`userId`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `collectionId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`collectionId`),
  KEY `FK_Reference_12` (`goodsId`),
  KEY `FK_Reference_8` (`userId`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`goodsId`) REFERENCES `goods` (`goodsId`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection
-- ----------------------------

-- ----------------------------
-- Table structure for collectshop
-- ----------------------------
DROP TABLE IF EXISTS `collectshop`;
CREATE TABLE `collectshop` (
  `scollectId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `shopId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`scollectId`),
  KEY `FK_Reference_13` (`userId`),
  KEY `FK_Reference_14` (`shopId`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`shopId`) REFERENCES `shop` (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collectshop
-- ----------------------------

-- ----------------------------
-- Table structure for commission
-- ----------------------------
DROP TABLE IF EXISTS `commission`;
CREATE TABLE `commission` (
  `commissionId` int(11) NOT NULL AUTO_INCREMENT,
  `rate` float(20,2) NOT NULL,
  PRIMARY KEY (`commissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commission
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsId` int(11) NOT NULL AUTO_INCREMENT,
  `shopId` int(11) NOT NULL,
  `goodsName` varchar(50) NOT NULL,
  `introduction` varchar(500) NOT NULL,
  `price` float(20,2) NOT NULL,
  `discount` float(20,2) DEFAULT NULL,
  `inventory` int(11) NOT NULL,
  `photoGroup` varchar(50) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `views` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`goodsId`),
  KEY `FK_Reference_3` (`shopId`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`shopId`) REFERENCES `shop` (`shopId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '1', '123', '123', '12.00', '123.00', '12', '12', null, null, '2016-12-11 16:21:40', '12', '1');
INSERT INTO `goods` VALUES ('2', '1', '23', '12', '1.00', '1.00', '12', '1', null, null, '2016-12-07 16:28:35', '1', '1');
INSERT INTO `goods` VALUES ('3', '2', 'aw', 'qwe', '1.00', '0.00', '2', 'Goodnull1481532643809', '0', 'unkown', '2016-12-12 16:50:44', '0', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ordersId` int(11) NOT NULL AUTO_INCREMENT,
  `orderNumber` varchar(100) NOT NULL,
  `userId` int(11) NOT NULL,
  `singleGoodId` int(11) DEFAULT NULL,
  `goodsId` int(11) NOT NULL,
  `addressId` int(11) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `goodsName` varchar(100) DEFAULT NULL,
  `goodsDescribe` varchar(200) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `paidTime` datetime DEFAULT NULL,
  `price` float(20,2) NOT NULL,
  `commission` float(20,2) NOT NULL,
  `commissionRate` float(20,2) NOT NULL,
  `comment` varchar(300) DEFAULT NULL,
  `commentTime` datetime DEFAULT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`ordersId`),
  KEY `FK_Reference_16` (`addressId`),
  KEY `FK_Reference_6` (`userId`),
  KEY `FK_Reference_7` (`goodsId`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`addressId`) REFERENCES `address` (`addressId`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`goodsId`) REFERENCES `goods` (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for owner
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `ownerId` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `userImage` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `phone` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `identityId` varchar(50) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `balance` float(20,2) NOT NULL,
  `confirm` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`ownerId`),
  KEY `FK_Reference_10` (`roleId`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of owner
-- ----------------------------
INSERT INTO `owner` VALUES ('1', '2', 'we', 'we', 'w', '4321', '2', '12', '12', '12', '12', '1.00', '1', '3');

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `photoId` int(11) NOT NULL AUTO_INCREMENT,
  `photoGroup` varchar(50) NOT NULL,
  `photoName` varchar(100) DEFAULT NULL,
  `address` varchar(200) NOT NULL,
  PRIMARY KEY (`photoId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES ('1', '12', '1', '1');
INSERT INTO `photo` VALUES ('2', '12', '2', '2');
INSERT INTO `photo` VALUES ('3', '13', '3', '3');
INSERT INTO `photo` VALUES ('4', '11481454062714', '', '/resources/images/owner/0edb3847-e8ac-4ad0-8a25-1289df69e7ac.png');
INSERT INTO `photo` VALUES ('5', 'Goodnull1481532643809', '', '/resources/images/owner/f17a0815-3d57-437c-bca7-77b7ba0c01c8.png');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `describes` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '游客');
INSERT INTO `role` VALUES ('2', '用户');
INSERT INTO `role` VALUES ('3', '商家');
INSERT INTO `role` VALUES ('4', '管理员');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `shopId` int(11) NOT NULL AUTO_INCREMENT,
  `ownerId` int(11) DEFAULT NULL,
  `shopName` varchar(50) NOT NULL,
  `introduction` varchar(1000) DEFAULT NULL,
  `photoGroup` varchar(100) NOT NULL,
  `views` int(11) NOT NULL,
  `logo` varchar(100) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`shopId`),
  KEY `FK_Reference_9` (`ownerId`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`ownerId`) REFERENCES `owner` (`ownerId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '1', '12', '121', '12', '1', '1', '2016-12-11 19:21:18', '0');
INSERT INTO `shop` VALUES ('2', '1', '123', 'dad ', '11481454062714', '0', '/resources/images/owner/2a978d10-12d3-4540-9008-4c733011723a.jpg', '2016-12-11 19:01:03', '3');

-- ----------------------------
-- Table structure for singlegood
-- ----------------------------
DROP TABLE IF EXISTS `singlegood`;
CREATE TABLE `singlegood` (
  `singleGoodId` int(11) NOT NULL AUTO_INCREMENT,
  `goodsId` int(11) DEFAULT NULL,
  `typeDescribe` varchar(50) DEFAULT NULL,
  `inventory` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`singleGoodId`),
  KEY `FK_Reference_4` (`goodsId`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`goodsId`) REFERENCES `goods` (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of singlegood
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `userImage` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `phone` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `balance` float(20,2) NOT NULL,
  `confirm` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `FK_Reference_1` (`roleId`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16', '2', '/resources/images/a.png', 'hoho', '', '123456', '791518746@qq.com', '0.00', '', '2');
SET FOREIGN_KEY_CHECKS=1;
