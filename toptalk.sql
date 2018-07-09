/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : toptalk

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-07-09 21:56:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '分类1', '11');
INSERT INTO `category` VALUES ('2', '分类2', '12');
INSERT INTO `category` VALUES ('3', '分类3', '13');
INSERT INTO `category` VALUES ('4', '分类4', '14');
INSERT INTO `category` VALUES ('5', '分类5', '15');
INSERT INTO `category` VALUES ('6', '分类6', '16');
INSERT INTO `category` VALUES ('7', '分类7', '17');
INSERT INTO `category` VALUES ('8', '分类8', '18');
INSERT INTO `category` VALUES ('9', '分类9', '19');
INSERT INTO `category` VALUES ('10', '分类10', '20');
INSERT INTO `category` VALUES ('11', '分类11', '21');
INSERT INTO `category` VALUES ('12', '分类12', '22');
INSERT INTO `category` VALUES ('13', '分类13', '23');
INSERT INTO `category` VALUES ('14', '分类14', '24');
INSERT INTO `category` VALUES ('15', '分类15', '25');
INSERT INTO `category` VALUES ('16', '分类16', '26');
INSERT INTO `category` VALUES ('17', '分类17', '27');
INSERT INTO `category` VALUES ('18', '分类18', '28');
INSERT INTO `category` VALUES ('19', '分类19', '29');
INSERT INTO `category` VALUES ('20', '分类20', '30');
INSERT INTO `category` VALUES ('21', '分类21', '31');
INSERT INTO `category` VALUES ('22', '分类22', '32');
INSERT INTO `category` VALUES ('23', '分类23', '33');
INSERT INTO `category` VALUES ('24', '分类24', '34');
INSERT INTO `category` VALUES ('25', '分类25', '35');
INSERT INTO `category` VALUES ('26', '分类26', '36');
INSERT INTO `category` VALUES ('27', '分类27', '37');
INSERT INTO `category` VALUES ('28', '分类28', '38');
INSERT INTO `category` VALUES ('29', '分类29', '39');

-- ----------------------------
-- Table structure for `category_topic`
-- ----------------------------
DROP TABLE IF EXISTS `category_topic`;
CREATE TABLE `category_topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_topic
-- ----------------------------
INSERT INTO `category_topic` VALUES ('1', '1', '1');
INSERT INTO `category_topic` VALUES ('2', '2', '2');
INSERT INTO `category_topic` VALUES ('3', '3', '3');
INSERT INTO `category_topic` VALUES ('4', '4', '4');
INSERT INTO `category_topic` VALUES ('5', '5', '5');
INSERT INTO `category_topic` VALUES ('6', '6', '6');
INSERT INTO `category_topic` VALUES ('7', '7', '7');
INSERT INTO `category_topic` VALUES ('8', '8', '8');
INSERT INTO `category_topic` VALUES ('9', '9', '9');
INSERT INTO `category_topic` VALUES ('10', '10', '10');
INSERT INTO `category_topic` VALUES ('11', '11', '11');
INSERT INTO `category_topic` VALUES ('12', '12', '12');
INSERT INTO `category_topic` VALUES ('13', '13', '13');
INSERT INTO `category_topic` VALUES ('14', '14', '14');
INSERT INTO `category_topic` VALUES ('15', '15', '15');
INSERT INTO `category_topic` VALUES ('16', '16', '16');
INSERT INTO `category_topic` VALUES ('17', '17', '17');
INSERT INTO `category_topic` VALUES ('18', '18', '18');
INSERT INTO `category_topic` VALUES ('19', '19', '19');
INSERT INTO `category_topic` VALUES ('20', '20', '20');
INSERT INTO `category_topic` VALUES ('21', '21', '21');
INSERT INTO `category_topic` VALUES ('22', '22', '22');
INSERT INTO `category_topic` VALUES ('23', '23', '23');
INSERT INTO `category_topic` VALUES ('24', '24', '24');
INSERT INTO `category_topic` VALUES ('25', '25', '25');
INSERT INTO `category_topic` VALUES ('26', '26', '26');
INSERT INTO `category_topic` VALUES ('27', '27', '27');
INSERT INTO `category_topic` VALUES ('28', '28', '28');
INSERT INTO `category_topic` VALUES ('29', '29', '29');
INSERT INTO `category_topic` VALUES ('30', '29', '30');
INSERT INTO `category_topic` VALUES ('31', '29', '31');
INSERT INTO `category_topic` VALUES ('32', '29', '32');
INSERT INTO `category_topic` VALUES ('33', '29', '33');
INSERT INTO `category_topic` VALUES ('34', '29', '34');
INSERT INTO `category_topic` VALUES ('35', '29', '35');
INSERT INTO `category_topic` VALUES ('36', '29', '36');
INSERT INTO `category_topic` VALUES ('37', '29', '37');
INSERT INTO `category_topic` VALUES ('38', '29', '38');
INSERT INTO `category_topic` VALUES ('39', '29', '39');
INSERT INTO `category_topic` VALUES ('40', '29', '40');
INSERT INTO `category_topic` VALUES ('41', '29', '41');
INSERT INTO `category_topic` VALUES ('42', '29', '42');
INSERT INTO `category_topic` VALUES ('43', '29', '43');
INSERT INTO `category_topic` VALUES ('44', '29', '44');
INSERT INTO `category_topic` VALUES ('45', '29', '45');
INSERT INTO `category_topic` VALUES ('46', '29', '46');
INSERT INTO `category_topic` VALUES ('47', '29', '47');
INSERT INTO `category_topic` VALUES ('48', '29', '48');
INSERT INTO `category_topic` VALUES ('49', '29', '49');
INSERT INTO `category_topic` VALUES ('50', '29', '50');
INSERT INTO `category_topic` VALUES ('51', '29', '51');
INSERT INTO `category_topic` VALUES ('52', '29', '52');
INSERT INTO `category_topic` VALUES ('53', '29', '53');
INSERT INTO `category_topic` VALUES ('54', '29', '54');
INSERT INTO `category_topic` VALUES ('55', '29', '55');
INSERT INTO `category_topic` VALUES ('56', '29', '56');
INSERT INTO `category_topic` VALUES ('57', '29', '57');
INSERT INTO `category_topic` VALUES ('58', '29', '58');
INSERT INTO `category_topic` VALUES ('59', '29', '59');

-- ----------------------------
-- Table structure for `city`
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '城市1', '1');
INSERT INTO `city` VALUES ('2', '城市2', '2');
INSERT INTO `city` VALUES ('3', '城市3', '3');
INSERT INTO `city` VALUES ('4', '城市4', '4');
INSERT INTO `city` VALUES ('5', '城市5', '5');
INSERT INTO `city` VALUES ('6', '城市6', '6');
INSERT INTO `city` VALUES ('7', '城市7', '7');
INSERT INTO `city` VALUES ('8', '城市8', '8');
INSERT INTO `city` VALUES ('9', '城市9', '9');
INSERT INTO `city` VALUES ('10', '城市10', '10');
INSERT INTO `city` VALUES ('11', '城市11', '11');
INSERT INTO `city` VALUES ('12', '城市12', '12');
INSERT INTO `city` VALUES ('13', '城市13', '13');
INSERT INTO `city` VALUES ('14', '城市14', '14');
INSERT INTO `city` VALUES ('15', '城市15', '15');
INSERT INTO `city` VALUES ('16', '城市16', '16');
INSERT INTO `city` VALUES ('17', '城市17', '17');
INSERT INTO `city` VALUES ('18', '城市18', '18');
INSERT INTO `city` VALUES ('19', '城市19', '19');
INSERT INTO `city` VALUES ('20', '城市20', '20');
INSERT INTO `city` VALUES ('21', '城市21', '21');
INSERT INTO `city` VALUES ('22', '城市22', '22');
INSERT INTO `city` VALUES ('23', '城市23', '23');
INSERT INTO `city` VALUES ('24', '城市24', '24');
INSERT INTO `city` VALUES ('25', '城市25', '25');
INSERT INTO `city` VALUES ('26', '城市26', '26');
INSERT INTO `city` VALUES ('27', '城市27', '27');
INSERT INTO `city` VALUES ('28', '城市28', '28');
INSERT INTO `city` VALUES ('29', '城市29', '29');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `creat_time` datetime DEFAULT NULL,
  `supcomment_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjwn7a4qe3io39cr7n1ek8uxqw` (`supcomment_id`),
  KEY `FKh1gtv412u19wcbx22177xbkjp` (`author_id`)
) ENGINE=MyISAM AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '测试评论正文', '2018-06-23 20:04:54', '0', '1');
INSERT INTO `comment` VALUES ('2', '测试评论正文', '2018-06-23 20:04:54', '0', '2');
INSERT INTO `comment` VALUES ('3', '测试评论正文', '2018-06-23 20:04:54', '0', '3');
INSERT INTO `comment` VALUES ('4', '测试评论正文', '2018-06-23 20:04:54', '0', '4');
INSERT INTO `comment` VALUES ('5', '测试评论正文', '2018-06-23 20:04:54', '0', '5');
INSERT INTO `comment` VALUES ('6', '测试评论正文', '2018-06-23 20:04:54', '0', '6');
INSERT INTO `comment` VALUES ('7', '测试评论正文', '2018-06-23 20:04:54', '0', '7');
INSERT INTO `comment` VALUES ('8', '测试评论正文', '2018-06-23 20:04:54', '0', '8');
INSERT INTO `comment` VALUES ('9', '测试评论正文', '2018-06-23 20:04:54', '0', '9');
INSERT INTO `comment` VALUES ('10', '测试评论正文', '2018-06-23 20:04:54', '0', '10');
INSERT INTO `comment` VALUES ('11', '测试评论正文', '2018-06-23 20:04:54', '0', '11');
INSERT INTO `comment` VALUES ('12', '测试评论正文', '2018-06-23 20:04:54', '0', '12');
INSERT INTO `comment` VALUES ('13', '测试评论正文', '2018-06-23 20:04:55', '1', '13');
INSERT INTO `comment` VALUES ('14', '测试评论正文', '2018-06-23 20:04:55', '1', '14');
INSERT INTO `comment` VALUES ('15', '测试评论正文', '2018-06-23 20:04:55', '1', '15');
INSERT INTO `comment` VALUES ('16', '测试评论正文', '2018-06-23 20:04:55', '1', '16');
INSERT INTO `comment` VALUES ('17', '测试评论正文', '2018-06-23 20:04:55', '1', '17');
INSERT INTO `comment` VALUES ('18', '测试评论正文', '2018-06-23 20:04:55', '1', '18');
INSERT INTO `comment` VALUES ('19', '测试评论正文', '2018-06-23 20:04:55', '1', '19');
INSERT INTO `comment` VALUES ('20', '测试评论正文', '2018-06-23 20:04:55', '1', '20');
INSERT INTO `comment` VALUES ('21', '测试评论正文', '2018-06-23 20:04:55', '1', '21');
INSERT INTO `comment` VALUES ('22', '测试评论正文', '2018-06-23 20:04:55', '1', '22');
INSERT INTO `comment` VALUES ('23', '测试评论正文', '2018-06-23 20:04:55', '1', '23');
INSERT INTO `comment` VALUES ('24', '测试评论正文', '2018-06-23 20:04:55', '1', '24');
INSERT INTO `comment` VALUES ('25', '测试评论正文', '2018-06-23 20:04:55', '1', '25');
INSERT INTO `comment` VALUES ('26', '测试评论正文', '2018-06-23 20:04:55', '1', '26');
INSERT INTO `comment` VALUES ('27', '测试评论正文', '2018-06-23 20:04:55', '1', '27');
INSERT INTO `comment` VALUES ('28', '测试评论正文', '2018-06-23 20:04:55', '1', '28');
INSERT INTO `comment` VALUES ('29', '测试评论正文', '2018-06-23 20:04:55', '1', '29');
INSERT INTO `comment` VALUES ('30', '测试评论专辑', '2018-07-07 20:31:53', '0', '1');
INSERT INTO `comment` VALUES ('31', '测试评论专辑2', '2018-07-07 20:32:50', '0', '1');
INSERT INTO `comment` VALUES ('32', '测试评论计数增加', '2018-07-07 21:07:20', '0', '1');
INSERT INTO `comment` VALUES ('33', 'sssss', '2018-07-08 17:41:50', '30', '1');
INSERT INTO `comment` VALUES ('34', '测试子评论2', '2018-07-08 17:45:11', '30', '1');
INSERT INTO `comment` VALUES ('35', '测试子评论3', '2018-07-08 17:51:55', '30', '1');
INSERT INTO `comment` VALUES ('36', '测试评论', '2018-07-08 18:11:45', '0', '1');
INSERT INTO `comment` VALUES ('37', '测试评论', '2018-07-08 18:18:00', '0', '1');
INSERT INTO `comment` VALUES ('38', '测试评论故事', '2018-07-08 18:21:11', '0', '1');
INSERT INTO `comment` VALUES ('39', '测试子评论', '2018-07-08 18:22:18', '38', '1');
INSERT INTO `comment` VALUES ('40', '测试子评论发布4', '2018-07-08 19:42:41', '32', '1');
INSERT INTO `comment` VALUES ('41', '测试子评论', '2018-07-08 20:19:53', '32', '1');
INSERT INTO `comment` VALUES ('42', '测试发布子评论', '2018-07-09 18:14:23', '30', '1');
INSERT INTO `comment` VALUES ('43', '测试发布子评论', '2018-07-09 18:14:28', '30', '1');
INSERT INTO `comment` VALUES ('44', '测试发布子评论', '2018-07-09 18:14:32', '30', '1');
INSERT INTO `comment` VALUES ('45', '测试发布子评论7', '2018-07-09 18:14:47', '30', '1');
INSERT INTO `comment` VALUES ('46', '测试发布子评论8', '2018-07-09 18:14:53', '30', '1');
INSERT INTO `comment` VALUES ('47', '测试发布子评论9', '2018-07-09 18:15:00', '30', '1');
INSERT INTO `comment` VALUES ('48', '测试发布子评论10', '2018-07-09 18:15:08', '30', '1');
INSERT INTO `comment` VALUES ('49', '测试发布子评论11', '2018-07-09 18:15:45', '30', '1');
INSERT INTO `comment` VALUES ('50', '测试发布子评论12', '2018-07-09 18:16:51', '30', '1');
INSERT INTO `comment` VALUES ('51', '测试发布子评论13', '2018-07-09 18:21:52', '30', '1');
INSERT INTO `comment` VALUES ('52', '测试发布子评论14', '2018-07-09 21:16:12', '30', '1');
INSERT INTO `comment` VALUES ('53', '测试发布子评论15', '2018-07-09 21:16:19', '30', '1');
INSERT INTO `comment` VALUES ('54', '测试发布子评论16', '2018-07-09 21:16:25', '30', '1');
INSERT INTO `comment` VALUES ('55', '测试发布子评论17', '2018-07-09 21:16:32', '30', '1');
INSERT INTO `comment` VALUES ('56', '测试发布子评论18', '2018-07-09 21:16:38', '30', '1');
INSERT INTO `comment` VALUES ('57', '测试发布子评论19', '2018-07-09 21:16:44', '30', '1');
INSERT INTO `comment` VALUES ('58', '测试发布子评论20', '2018-07-09 21:16:51', '30', '1');
INSERT INTO `comment` VALUES ('59', '测试发布子评论21', '2018-07-09 21:16:56', '30', '1');
INSERT INTO `comment` VALUES ('60', '测试子评论翻页', '2018-07-09 21:34:15', '32', '1');
INSERT INTO `comment` VALUES ('61', '测试子评论翻页', '2018-07-09 21:34:20', '32', '1');
INSERT INTO `comment` VALUES ('62', '测试子评论翻页', '2018-07-09 21:34:25', '32', '1');
INSERT INTO `comment` VALUES ('63', '测试子评论翻页', '2018-07-09 21:34:29', '32', '1');
INSERT INTO `comment` VALUES ('64', '测试子评论翻页', '2018-07-09 21:34:33', '32', '1');
INSERT INTO `comment` VALUES ('65', '测试子评论翻页', '2018-07-09 21:34:37', '32', '1');
INSERT INTO `comment` VALUES ('66', '测试子评论翻页', '2018-07-09 21:34:41', '32', '1');
INSERT INTO `comment` VALUES ('67', '测试子评论翻页', '2018-07-09 21:34:45', '32', '1');
INSERT INTO `comment` VALUES ('68', '测试子评论翻页', '2018-07-09 21:34:50', '32', '1');
INSERT INTO `comment` VALUES ('69', '测试子评论翻页', '2018-07-09 21:34:55', '32', '1');
INSERT INTO `comment` VALUES ('70', '测试子评论翻页', '2018-07-09 21:34:59', '32', '1');
INSERT INTO `comment` VALUES ('71', '测试子评论翻页', '2018-07-09 21:35:03', '32', '1');
INSERT INTO `comment` VALUES ('72', '测试子评论翻页', '2018-07-09 21:35:06', '32', '1');
INSERT INTO `comment` VALUES ('73', '测试子评论翻页', '2018-07-09 21:35:10', '32', '1');
INSERT INTO `comment` VALUES ('74', '测试子评论翻页', '2018-07-09 21:35:13', '32', '1');
INSERT INTO `comment` VALUES ('75', '测试子评论翻页', '2018-07-09 21:35:17', '32', '1');
INSERT INTO `comment` VALUES ('76', '测试子评论翻页', '2018-07-09 21:35:20', '32', '1');
INSERT INTO `comment` VALUES ('77', '测试子评论翻页', '2018-07-09 21:35:23', '32', '1');
INSERT INTO `comment` VALUES ('78', '测试子评论翻页', '2018-07-09 21:35:27', '32', '1');
INSERT INTO `comment` VALUES ('79', '测试子评论翻页', '2018-07-09 21:35:30', '32', '1');
INSERT INTO `comment` VALUES ('80', '测试子评论翻页', '2018-07-09 21:35:34', '32', '1');
INSERT INTO `comment` VALUES ('81', '测试子评论翻页', '2018-07-09 21:35:37', '32', '1');
INSERT INTO `comment` VALUES ('82', '测试子评论翻页', '2018-07-09 21:35:40', '32', '1');
INSERT INTO `comment` VALUES ('83', '测试子评论', '2018-07-09 21:36:06', '31', '1');
INSERT INTO `comment` VALUES ('84', '测试子评论', '2018-07-09 21:44:39', '38', '1');
INSERT INTO `comment` VALUES ('85', '测试子评论', '2018-07-09 21:44:47', '38', '1');
INSERT INTO `comment` VALUES ('86', '测试子评论', '2018-07-09 21:44:50', '38', '1');
INSERT INTO `comment` VALUES ('87', '测试子评论翻页', '2018-07-09 21:44:58', '38', '1');
INSERT INTO `comment` VALUES ('88', '测试子评论翻页', '2018-07-09 21:45:02', '38', '1');
INSERT INTO `comment` VALUES ('89', '测试子评论翻页', '2018-07-09 21:45:05', '38', '1');
INSERT INTO `comment` VALUES ('90', '测试子评论翻页', '2018-07-09 21:45:08', '38', '1');
INSERT INTO `comment` VALUES ('91', '测试子评论翻页', '2018-07-09 21:45:11', '38', '1');
INSERT INTO `comment` VALUES ('92', '测试子评论翻页', '2018-07-09 21:45:14', '38', '1');
INSERT INTO `comment` VALUES ('93', '测试子评论翻页', '2018-07-09 21:45:18', '38', '1');
INSERT INTO `comment` VALUES ('94', '测试子评论翻页', '2018-07-09 21:45:21', '38', '1');
INSERT INTO `comment` VALUES ('95', '测试子评论翻页', '2018-07-09 21:45:24', '38', '1');
INSERT INTO `comment` VALUES ('96', '测试子评论翻页', '2018-07-09 21:45:27', '38', '1');
INSERT INTO `comment` VALUES ('97', '测试子评论翻页', '2018-07-09 21:45:31', '38', '1');
INSERT INTO `comment` VALUES ('98', '测试子评论翻页', '2018-07-09 21:45:34', '38', '1');
INSERT INTO `comment` VALUES ('99', '测试子评论翻页', '2018-07-09 21:45:45', '38', '1');
INSERT INTO `comment` VALUES ('100', '测试子评论翻页', '2018-07-09 21:45:49', '38', '1');
INSERT INTO `comment` VALUES ('101', '测试子评论翻页', '2018-07-09 21:45:59', '38', '1');
INSERT INTO `comment` VALUES ('102', '测试子评论翻页', '2018-07-09 21:46:03', '38', '1');
INSERT INTO `comment` VALUES ('103', '测试子评论翻页', '2018-07-09 21:46:06', '38', '1');
INSERT INTO `comment` VALUES ('104', '测试父评论', '2018-07-09 21:46:20', '0', '1');
INSERT INTO `comment` VALUES ('105', '测试父评论3', '2018-07-09 21:46:31', '0', '1');

-- ----------------------------
-- Table structure for `comment_est`
-- ----------------------------
DROP TABLE IF EXISTS `comment_est`;
CREATE TABLE `comment_est` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) DEFAULT NULL,
  `essay_id` bigint(20) DEFAULT NULL,
  `story_id` bigint(20) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment_est
-- ----------------------------
INSERT INTO `comment_est` VALUES ('1', '1', '0', '0', '1');
INSERT INTO `comment_est` VALUES ('2', '2', '0', '0', '2');
INSERT INTO `comment_est` VALUES ('3', '3', '0', '0', '3');
INSERT INTO `comment_est` VALUES ('4', '4', '0', '0', '4');
INSERT INTO `comment_est` VALUES ('5', '5', '0', '0', '5');
INSERT INTO `comment_est` VALUES ('6', '6', '0', '0', '6');
INSERT INTO `comment_est` VALUES ('7', '7', '0', '0', '7');
INSERT INTO `comment_est` VALUES ('8', '8', '0', '0', '8');
INSERT INTO `comment_est` VALUES ('9', '9', '0', '0', '9');
INSERT INTO `comment_est` VALUES ('10', '10', '0', '0', '10');
INSERT INTO `comment_est` VALUES ('11', '11', '0', '0', '11');
INSERT INTO `comment_est` VALUES ('12', '12', '0', '0', '12');
INSERT INTO `comment_est` VALUES ('13', '13', '0', '0', '13');
INSERT INTO `comment_est` VALUES ('14', '14', '0', '0', '14');
INSERT INTO `comment_est` VALUES ('15', '15', '0', '0', '15');
INSERT INTO `comment_est` VALUES ('16', '16', '0', '0', '16');
INSERT INTO `comment_est` VALUES ('17', '17', '0', '0', '17');
INSERT INTO `comment_est` VALUES ('18', '18', '0', '0', '18');
INSERT INTO `comment_est` VALUES ('19', '19', '0', '0', '19');
INSERT INTO `comment_est` VALUES ('20', '20', '0', '0', '20');
INSERT INTO `comment_est` VALUES ('21', '21', '0', '0', '21');
INSERT INTO `comment_est` VALUES ('22', '22', '0', '0', '22');
INSERT INTO `comment_est` VALUES ('23', '23', '0', '0', '23');
INSERT INTO `comment_est` VALUES ('24', '24', '0', '0', '24');
INSERT INTO `comment_est` VALUES ('25', '25', '0', '0', '25');
INSERT INTO `comment_est` VALUES ('26', '26', '0', '0', '26');
INSERT INTO `comment_est` VALUES ('27', '27', '0', '0', '27');
INSERT INTO `comment_est` VALUES ('28', '28', '0', '0', '28');
INSERT INTO `comment_est` VALUES ('29', '29', '0', '0', '29');
INSERT INTO `comment_est` VALUES ('30', '2', null, null, '1');
INSERT INTO `comment_est` VALUES ('31', '3', null, null, '1');
INSERT INTO `comment_est` VALUES ('32', '4', null, null, '1');
INSERT INTO `comment_est` VALUES ('33', '5', null, null, '1');
INSERT INTO `comment_est` VALUES ('34', '6', null, null, '1');
INSERT INTO `comment_est` VALUES ('35', '7', null, null, '1');
INSERT INTO `comment_est` VALUES ('36', '8', null, null, '1');
INSERT INTO `comment_est` VALUES ('37', '9', null, null, '1');
INSERT INTO `comment_est` VALUES ('38', '10', null, null, '1');
INSERT INTO `comment_est` VALUES ('39', '11', null, null, '1');
INSERT INTO `comment_est` VALUES ('40', '12', null, null, '1');
INSERT INTO `comment_est` VALUES ('41', '13', null, null, '1');
INSERT INTO `comment_est` VALUES ('42', '14', null, null, '1');
INSERT INTO `comment_est` VALUES ('43', '15', null, null, '1');
INSERT INTO `comment_est` VALUES ('44', '30', '0', '0', '84');
INSERT INTO `comment_est` VALUES ('45', '31', '0', '0', '84');
INSERT INTO `comment_est` VALUES ('46', '32', '0', '0', '84');
INSERT INTO `comment_est` VALUES ('47', '38', '0', '36', '0');
INSERT INTO `comment_est` VALUES ('48', '104', '0', '36', '0');
INSERT INTO `comment_est` VALUES ('49', '105', '0', '36', '0');

-- ----------------------------
-- Table structure for `essay`
-- ----------------------------
DROP TABLE IF EXISTS `essay`;
CREATE TABLE `essay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collect_number` bigint(20) DEFAULT NULL,
  `content` text,
  `creat_time` datetime DEFAULT NULL,
  `img_addr` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1h2hjd11hus4qmxy08l63tr2v` (`author_id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of essay
-- ----------------------------
INSERT INTO `essay` VALUES ('36', '0', '\n            测试随笔投稿1', '2018-07-01 16:42:36', '/upload/essay/1/9877320180701164236.jpg', '测试随笔投稿1', '1');
INSERT INTO `essay` VALUES ('37', '0', '\n            测试随笔投稿2', '2018-07-01 16:42:45', '/upload/essay/1/9385920180701164245.jpg', '测试随笔投稿2', '1');
INSERT INTO `essay` VALUES ('38', '0', '\n            测试随笔投稿3', '2018-07-01 16:42:59', '/upload/essay/1/3330520180701164258.jpg', '测试随笔投稿3', '1');
INSERT INTO `essay` VALUES ('39', '0', '\n            测试随笔投稿4', '2018-07-01 16:43:13', '/upload/essay/1/1783720180701164313.jpg', '测试随笔投稿4', '1');
INSERT INTO `essay` VALUES ('40', '0', '\n            测试随笔投稿5', '2018-07-01 16:43:29', '/upload/essay/1/1050220180701164328.jpg', '测试随笔投稿5', '1');

-- ----------------------------
-- Table structure for `event`
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `location` varchar(255) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `theme` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `city_id` bigint(20) NOT NULL,
  `cover_img_addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdlt0s1srth133hmddr12pjouk` (`city_id`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of event
-- ----------------------------
INSERT INTO `event` VALUES ('30', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动投稿正文</h1><p><img src=\"/upload/event/1/4670920180702191220.jpg\" title=\"/upload/event/1/4670920180702191220.jpg\" alt=\"/upload/event/1/4670920180702191220.jpg\"/><img src=\"/upload/event/1/3424720180702191157.jpg\" title=\"/upload/event/1/3424720180702191157.jpg\" alt=\"/upload/event/1/3424720180702191157.jpg\"/><img src=\"/upload/event/1/4103220180702191157.jpg\" title=\"/upload/event/1/4103220180702191157.jpg\" alt=\"/upload/event/1/4103220180702191157.jpg\"/></p>', 'xx市xx区xx街', '50元', '测试活动投稿', '测试', '2018-09-12 08:00:00', '1', '/upload/event/1/7560520180702192048.jpg');
INSERT INTO `event` VALUES ('31', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动投稿正文2</h1><p>19:36:39</p><p><img width=\"530\" height=\"340\" src=\"http://api.map.baidu.com/staticimage?center=116.404,39.915&zoom=10&width=530&height=340&markers=116.404,39.915\"/><img src=\"/upload/event/1/1016820180702193735.jpg\" title=\"/upload/event/1/1016820180702193735.jpg\" alt=\"/upload/event/1/1016820180702193735.jpg\"/></p>', 'xx市xx区xx街', '50元', '测试活动投稿2', '测试', '2018-09-12 08:00:00', '1', '/upload/event/1/4106020180702193744.jpg');
INSERT INTO `event` VALUES ('32', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\">测试活动申请2018-07-02</h1><p><img width=\"530\" height=\"340\" src=\"http://api.map.baidu.com/staticimage?center=104.062942,33.267644&zoom=11&width=530&height=340&markers=104.062942,33.267644\"/></p>', '九寨沟', '300元', '测试活动申请', '旅游', '2018-07-28 08:00:00', '1', '/upload/event/1/3842020180702204847.jpg');
INSERT INTO `event` VALUES ('33', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\"><span style=\"font-size: 16px; font-style: italic; font-weight: bold; color: rgb(51, 153, 204); line-height: 18px;\">测试活动申请2018-07-02</span></h1><p><img src=\"http://img.baidu.com/hi/jx2/j_0011.gif\"/></p>', '环城公园', '20元', '测试活动申请', '无限之旅', '2018-07-28 08:00:00', '1', '/upload/event/1/5510220180702205011.jpg');
INSERT INTO `event` VALUES ('34', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\"><span style=\"font-size: 16px; font-style: italic; font-weight: bold; color: rgb(51, 153, 204); line-height: 18px;\">测试活动申请2018-07-02</span></h1><p><img src=\"http://img.baidu.com/hi/jx2/j_0011.gif\"/><img width=\"530\" height=\"340\" src=\"http://api.map.baidu.com/staticimage?center=117.717395,39.009416&zoom=13&width=530&height=340&markers=117.717395,39.009416\"/></p>', '滨海', '20元', '自驾游', '旅游', '2018-07-26 08:00:00', '1', '/upload/event/1/4797820180702205108.jpg');
INSERT INTO `event` VALUES ('35', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\"><span style=\"font-size: 16px; font-style: italic; font-weight: bold; color: rgb(51, 153, 204); line-height: 18px;\">测试活动申请2018-07-02</span></h1><p><img src=\"http://img.baidu.com/hi/jx2/j_0011.gif\"/></p><p><img src=\"/upload/event/1/5591320180702205254.jpg\" title=\"/upload/event/1/5591320180702205254.jpg\"/></p><p><img src=\"/upload/event/1/8328520180702205254.jpg\" title=\"/upload/event/1/8328520180702205254.jpg\"/></p><p><img src=\"/upload/event/1/1175020180702205254.jpg\" title=\"/upload/event/1/1175020180702205254.jpg\"/></p><p><br/></p>', '集合点:湖南省湘潭市', '500元', '丝绸之路', '旅游', '2018-07-26 08:00:00', '1', '/upload/event/1/8817920180702205257.jpg');
INSERT INTO `event` VALUES ('36', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\">活动详情:</h1><p><img width=\"530\" height=\"340\" src=\"http://api.map.baidu.com/staticimage?center=100.016327,36.866641&zoom=9&width=530&height=340&markers=116.404,39.915\"/></p><p><img src=\"/upload/event/1/2720020180702210416.jpg\" title=\"/upload/event/1/2720020180702210416.jpg\"/></p><p><img src=\"/upload/event/1/3926820180702210416.jpg\" title=\"/upload/event/1/3926820180702210416.jpg\"/></p><p><img src=\"/upload/event/1/7954620180702210416.jpg\" title=\"/upload/event/1/7954620180702210416.jpg\"/></p><p><br/></p>', '青海湖', '100元', '青海湖自驾游', '自驾游', '2018-07-24 08:00:00', '29', '/upload/event/1/6414520180702210557.jpg');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `creat_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('2', '测试消息正文2', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('3', '测试消息正文3', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('4', '测试消息正文4', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('5', '测试消息正文5', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('6', '测试消息正文6', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('7', '测试消息正文7', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('8', '测试消息正文8', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('9', '测试消息正文9', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('10', '测试消息正文10', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('11', '测试消息正文11', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('12', '测试消息正文12', '2018-06-23 20:04:54');
INSERT INTO `message` VALUES ('13', '测试消息正文13', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('14', '测试消息正文14', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('15', '测试消息正文15', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('16', '测试消息正文16', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('17', '测试消息正文17', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('18', '测试消息正文18', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('19', '测试消息正文19', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('20', '测试消息正文20', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('21', '测试消息正文21', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('22', '测试消息正文22', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('23', '测试消息正文23', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('24', '测试消息正文24', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('25', '测试消息正文25', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('26', '测试消息正文26', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('27', '测试消息正文27', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('28', '测试消息正文28', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('29', '测试消息正文29', '2018-06-23 20:04:55');
INSERT INTO `message` VALUES ('30', '测试消息正文1', '2018-06-23 20:04:54');

-- ----------------------------
-- Table structure for `photo`
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `event_id` bigint(20) NOT NULL,
  `story_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdbadfit090kvl159gty4hl5so` (`event_id`),
  KEY `FKbir82lbmtue1hk2xygmh1c38w` (`story_id`),
  KEY `FKoe3yvp9wivuq1i1c47walu68i` (`topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for `role_user`
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('1', '1', '1');
INSERT INTO `role_user` VALUES ('2', '2', '1');

-- ----------------------------
-- Table structure for `story`
-- ----------------------------
DROP TABLE IF EXISTS `story`;
CREATE TABLE `story` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collect_number` bigint(20) DEFAULT NULL,
  `comment_number` bigint(20) DEFAULT NULL,
  `creat_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` bigint(20) NOT NULL,
  `content` text,
  `subscribe` text,
  `cover_img_addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfsmrtuhgkrna5psxspo06q56f` (`author_id`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of story
-- ----------------------------
INSERT INTO `story` VALUES ('34', '342', '2', '2018-07-01 16:44:23', '测试故事投稿1', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试故事投稿1</h1><p><img src=\"/upload/story/1/7107320180701164416.jpg\" title=\"/upload/story/1/7107320180701164416.jpg\"/></p><p><img src=\"/upload/story/1/1697220180701164416.jpg\" title=\"/upload/story/1/1697220180701164416.jpg\"/></p><p><img src=\"/upload/story/1/8201420180701164416.jpg\" title=\"/upload/story/1/8201420180701164416.jpg\"/></p><p><img src=\"/upload/story/1/4734220180701164417.jpg\" title=\"/upload/story/1/4734220180701164417.jpg\"/></p><h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\"><br/></h1>', '测试故事投稿1', '/upload/story/1/8988620180701164422.jpg');
INSERT INTO `story` VALUES ('35', '342', '2', '2018-07-01 16:45:48', '测试故事投稿富文本编辑器', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试故事投稿2</h1><p><span style=\"text-decoration: underline;\"><em>对方的身份为的方式福娃服务恶法地方我额服务<img src=\"http://img.baidu.com/hi/jx2/j_0013.gif\"/><img src=\"/upload/story/1/5218820180701164535.jpg\" title=\"/upload/story/1/5218820180701164535.jpg\" alt=\"/upload/story/1/5218820180701164535.jpg\"/><img src=\"/upload/story/1/2427720180701164545.jpg\" title=\"/upload/story/1/2427720180701164545.jpg\" alt=\"/upload/story/1/2427720180701164545.jpg\"/></em></span></p><h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\"><br/></h1>', '测试故事投稿2', '/upload/story/1/5948620180701164547.jpg');
INSERT INTO `story` VALUES ('36', '342', '24', '2018-07-01 17:33:50', '测试故事投稿1080p图片', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">图片源文件为1080p,只在显示时缩放成合适大小</h1><p>直接右击另存为可查看效果;</p><p><img src=\"/upload/story/1/6573120180701173341.jpg\" title=\"/upload/story/1/6573120180701173341.jpg\"/></p><p><img src=\"/upload/story/1/3980920180701173341.jpg\" title=\"/upload/story/1/3980920180701173341.jpg\"/></p><p><img src=\"/upload/story/1/7199320180701173341.jpg\" title=\"/upload/story/1/7199320180701173341.jpg\"/></p><p><img src=\"/upload/story/1/6532020180701173341.jpg\" title=\"/upload/story/1/6532020180701173341.jpg\"/></p><p><img src=\"/upload/story/1/4025420180701173341.jpg\" title=\"/upload/story/1/4025420180701173341.jpg\"/></p><p><br/></p>', '图片源文件为1080p,只在显示时缩放成合适大小;', '/upload/story/1/7297620180701173350.jpg');

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collect_number` bigint(20) DEFAULT NULL,
  `comment_number` bigint(20) DEFAULT NULL,
  `content` text,
  `creat_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` bigint(20) NOT NULL,
  `cover_img_addr` varchar(255) DEFAULT NULL,
  `content_imgs_addr` text,
  `category_id` bigint(20) NOT NULL,
  `zip_addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK91351a48ok1rkldd36rmpb34g` (`author_id`),
  KEY `FK8n7r9utm8sjpdfstb4wcqd7qj` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('79', '342', '134', '\n            测试上传和打包下载专辑图片', '2018-07-01 16:36:32', '测试上传和打包下载专辑图片', '1', '/upload/topic/1/3426920180701163630.jpg', '/upload/topic/1/4114320180701163630.jpg,/upload/topic/1/4640020180701163630.jpg,/upload/topic/1/8393320180701163631.jpg,/upload/topic/1/2542220180701163631.jpg,/upload/topic/1/8760020180701163632.jpg,/upload/topic/1/9686320180701163632.jpg', '29', '/upload/topic/1/.zip');
INSERT INTO `topic` VALUES ('78', '342', '134', '\n            测试zip打包功能1', '2018-07-01 16:34:28', '测试zip打包功能1', '1', '/upload/topic/1/6816820180701163426.jpg', '/upload/topic/1/2616720180701163427.jpg,/upload/topic/1/8258520180701163427.jpg,/upload/topic/1/4755120180701163427.jpg,/upload/topic/1/5324220180701163427.jpg', '29', '/upload/topic/1/.zip');
INSERT INTO `topic` VALUES ('80', '342', '134', '\n            测试上传和打包下载专辑图片2', '2018-07-01 16:37:59', '测试上传和打包下载专辑图片2', '1', '/upload/topic/1/4327420180701163758.jpg', '/upload/topic/1/6691920180701163758.jpg,/upload/topic/1/3593520180701163759.jpg,/upload/topic/1/7316920180701163759.jpg,/upload/topic/1/1486820180701163759.jpg,/upload/topic/1/5192520180701163759.jpg', '29', '/upload/topic/1/.zip');
INSERT INTO `topic` VALUES ('81', '342', '134', '\n            测试上传和打包下载专辑图片2', '2018-07-01 16:38:37', '测试上传和打包下载专辑图片3', '1', '/upload/topic/1/7407920180701163836.jpg', '/upload/topic/1/3496020180701163837.jpg,/upload/topic/1/3068420180701163837.jpg,/upload/topic/1/8620820180701163837.jpg,/upload/topic/1/1330820180701163837.jpg', '29', '/upload/topic/1/.zip');
INSERT INTO `topic` VALUES ('82', '342', '134', '\n         测试专辑绑定分类正文', '2018-07-01 16:39:30', '测试专辑绑定分类', '1', '/upload/topic/1/1174720180701163929.jpg', '/upload/topic/1/9612520180701163929.jpg,/upload/topic/1/9056220180701163929.jpg,/upload/topic/1/9517420180701163929.jpg', '1', '/upload/topic/1/.zip');
INSERT INTO `topic` VALUES ('83', '342', '2', '\n    投稿绑定分类测试', '2018-07-01 16:40:52', '投稿绑定分类测试', '1', '/upload/topic/1/6208620180701164051.jpg', '/upload/topic/1/3867220180701164052.jpg,/upload/topic/1/8292420180701164052.jpg,/upload/topic/1/9486420180701164052.jpg,/upload/topic/1/3953920180701164052.jpg', '28', '/upload/topic/1/.zip');
INSERT INTO `topic` VALUES ('84', '342', '50', '\n    投稿绑定分类测试', '2018-07-01 16:41:16', '投稿绑定分类测试', '1', '/upload/topic/1/8981820180701164115.jpg', '/upload/topic/1/2442320180701164115.jpg,/upload/topic/1/3360020180701164115.jpg,/upload/topic/1/8443920180701164116.jpg,/upload/topic/1/7935320180701164116.jpg', '28', '/upload/topic/1/.zip');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(225) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `img_addr` varchar(255) DEFAULT NULL,
  `join_time` datetime DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK29eqyw0gxw5r4f1ommy11nd9i` (`city_id`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'testuser', '1', '/upload/user/1/7254520180707191444.png', '2018-06-23 20:04:54', '测试修改昵称12', 'password', '测试修改签名', '2');
INSERT INTO `user` VALUES ('2', 'testuser2', '1', '/upload/user.jpg', '2018-06-23 20:04:54', '测试用户2', 'password2', '测试签名2', '1');
INSERT INTO `user` VALUES ('3', 'testuser3', '1', '/upload/user.jpg', '2018-06-23 20:04:54', '测试用户3', 'password3', '测试签名3', '1');
INSERT INTO `user` VALUES ('32', 'testuser123', '2', '/upload/user.jpg', '2018-07-05 19:40:25', '测试注册功能', 'password', '这个人很懒,没有设置签名...', '3');
INSERT INTO `user` VALUES ('33', 'testuser1234', '1', '/upload/user.jpg', '2018-07-05 19:43:59', '测试注册功能', 'password', '这个人很懒,没有设置签名...', '4');
INSERT INTO `user` VALUES ('34', 'testuser1111', '1', '/upload/user.jpg', '2018-07-05 19:55:18', '测试注册功能', 'password', '这个人很懒,没有设置签名...', '2');

-- ----------------------------
-- Table structure for `user_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `user_favorite`;
CREATE TABLE `user_favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `essay_id` bigint(20) DEFAULT NULL,
  `story_id` bigint(20) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_favorite
-- ----------------------------
INSERT INTO `user_favorite` VALUES ('2', '0', '0', '2', '2');
INSERT INTO `user_favorite` VALUES ('3', '0', '0', '3', '3');
INSERT INTO `user_favorite` VALUES ('4', '0', '0', '4', '4');
INSERT INTO `user_favorite` VALUES ('5', '0', '0', '5', '5');
INSERT INTO `user_favorite` VALUES ('6', '0', '0', '6', '6');
INSERT INTO `user_favorite` VALUES ('7', '0', '0', '7', '7');
INSERT INTO `user_favorite` VALUES ('8', '0', '0', '8', '8');
INSERT INTO `user_favorite` VALUES ('9', '0', '0', '9', '9');
INSERT INTO `user_favorite` VALUES ('10', '0', '0', '10', '10');
INSERT INTO `user_favorite` VALUES ('11', '0', '0', '11', '11');
INSERT INTO `user_favorite` VALUES ('12', '0', '0', '12', '12');
INSERT INTO `user_favorite` VALUES ('13', '0', '0', '13', '13');
INSERT INTO `user_favorite` VALUES ('14', '0', '0', '14', '14');
INSERT INTO `user_favorite` VALUES ('15', '0', '0', '15', '15');
INSERT INTO `user_favorite` VALUES ('16', '0', '0', '16', '16');
INSERT INTO `user_favorite` VALUES ('17', '0', '0', '17', '17');
INSERT INTO `user_favorite` VALUES ('18', '0', '0', '18', '18');
INSERT INTO `user_favorite` VALUES ('19', '0', '0', '19', '19');
INSERT INTO `user_favorite` VALUES ('20', '0', '0', '20', '20');
INSERT INTO `user_favorite` VALUES ('21', '0', '0', '21', '21');
INSERT INTO `user_favorite` VALUES ('22', '0', '0', '22', '22');
INSERT INTO `user_favorite` VALUES ('23', '0', '0', '23', '23');
INSERT INTO `user_favorite` VALUES ('24', '0', '0', '24', '24');
INSERT INTO `user_favorite` VALUES ('25', '0', '0', '25', '25');
INSERT INTO `user_favorite` VALUES ('26', '0', '0', '26', '26');
INSERT INTO `user_favorite` VALUES ('27', '0', '0', '27', '27');
INSERT INTO `user_favorite` VALUES ('28', '0', '0', '28', '28');
INSERT INTO `user_favorite` VALUES ('29', '0', '0', '0', '29');
INSERT INTO `user_favorite` VALUES ('30', '0', '1', '0', '1');
INSERT INTO `user_favorite` VALUES ('31', '0', '2', '0', '1');
INSERT INTO `user_favorite` VALUES ('32', '0', '3', '0', '1');
INSERT INTO `user_favorite` VALUES ('33', '0', '4', '0', '1');
INSERT INTO `user_favorite` VALUES ('34', '0', '5', '0', '1');
INSERT INTO `user_favorite` VALUES ('35', '0', '6', '0', '1');
INSERT INTO `user_favorite` VALUES ('36', '0', '7', '0', '1');
INSERT INTO `user_favorite` VALUES ('37', '0', '8', '0', '1');
INSERT INTO `user_favorite` VALUES ('38', '0', '9', '0', '1');
INSERT INTO `user_favorite` VALUES ('39', '0', '10', '0', '1');
INSERT INTO `user_favorite` VALUES ('40', null, null, '5', '1');
INSERT INTO `user_favorite` VALUES ('41', null, null, '4', '1');
INSERT INTO `user_favorite` VALUES ('42', null, null, '3', '1');
INSERT INTO `user_favorite` VALUES ('43', null, null, '2', '1');
INSERT INTO `user_favorite` VALUES ('44', null, null, '1', '1');
INSERT INTO `user_favorite` VALUES ('45', null, null, '10', '1');
INSERT INTO `user_favorite` VALUES ('46', null, null, '9', '1');
INSERT INTO `user_favorite` VALUES ('47', null, null, '8', '1');
INSERT INTO `user_favorite` VALUES ('48', null, null, '7', '1');
INSERT INTO `user_favorite` VALUES ('49', '0', null, '6', '1');
INSERT INTO `user_favorite` VALUES ('50', '10', null, null, '1');
INSERT INTO `user_favorite` VALUES ('51', '9', null, null, '1');
INSERT INTO `user_favorite` VALUES ('52', '8', null, null, '1');
INSERT INTO `user_favorite` VALUES ('53', '7', null, null, '1');
INSERT INTO `user_favorite` VALUES ('54', '6', null, null, '1');
INSERT INTO `user_favorite` VALUES ('55', '5', null, null, '1');
INSERT INTO `user_favorite` VALUES ('56', '4', null, null, '1');
INSERT INTO `user_favorite` VALUES ('57', '3', null, null, '1');
INSERT INTO `user_favorite` VALUES ('58', '2', null, null, '1');
INSERT INTO `user_favorite` VALUES ('59', '1', null, null, '1');
INSERT INTO `user_favorite` VALUES ('60', '40', null, null, '1');
INSERT INTO `user_favorite` VALUES ('61', '39', null, null, '1');
INSERT INTO `user_favorite` VALUES ('62', '38', null, null, '1');
INSERT INTO `user_favorite` VALUES ('63', '37', null, null, '1');
INSERT INTO `user_favorite` VALUES ('64', '36', null, null, '1');
INSERT INTO `user_favorite` VALUES ('65', null, null, '84', '1');
INSERT INTO `user_favorite` VALUES ('66', null, null, '83', '1');
INSERT INTO `user_favorite` VALUES ('67', null, null, '82', '1');
INSERT INTO `user_favorite` VALUES ('68', null, null, '81', '1');
INSERT INTO `user_favorite` VALUES ('69', null, null, '80', '1');
INSERT INTO `user_favorite` VALUES ('70', null, null, '79', '1');
INSERT INTO `user_favorite` VALUES ('71', null, null, null, '1');
INSERT INTO `user_favorite` VALUES ('72', null, null, null, '1');
INSERT INTO `user_favorite` VALUES ('73', null, null, null, '1');
INSERT INTO `user_favorite` VALUES ('74', null, null, null, '1');
INSERT INTO `user_favorite` VALUES ('75', null, '36', null, '1');
INSERT INTO `user_favorite` VALUES ('76', null, '35', null, '1');
INSERT INTO `user_favorite` VALUES ('77', null, '34', null, '1');
