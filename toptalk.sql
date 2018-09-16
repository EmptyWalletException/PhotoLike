/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : toptalk

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-09-02 11:03:56
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
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '备用分类', '1');
INSERT INTO `category` VALUES ('2', '体育运动', '2');
INSERT INTO `category` VALUES ('3', '科技', '3');
INSERT INTO `category` VALUES ('4', '城市风光', '4');
INSERT INTO `category` VALUES ('5', '建筑', '5');
INSERT INTO `category` VALUES ('6', '节日', '6');
INSERT INTO `category` VALUES ('7', '艺术', '7');
INSERT INTO `category` VALUES ('8', '历史', '8');
INSERT INTO `category` VALUES ('9', '创意', '9');
INSERT INTO `category` VALUES ('10', '山脉', '20');
INSERT INTO `category` VALUES ('11', '湖泊', '21');
INSERT INTO `category` VALUES ('12', '诗意田园', '22');
INSERT INTO `category` VALUES ('13', '旅游景点', '23');
INSERT INTO `category` VALUES ('14', '浩瀚宇宙', '24');
INSERT INTO `category` VALUES ('31', '飞禽', '23');
INSERT INTO `category` VALUES ('32', '走兽', '24');
INSERT INTO `category` VALUES ('20', '海滩', '20');
INSERT INTO `category` VALUES ('21', '路与远方', '21');
INSERT INTO `category` VALUES ('22', '桃园仙境', '22');
INSERT INTO `category` VALUES ('23', '自然奇景', '25');
INSERT INTO `category` VALUES ('33', '水系生物', '23');
INSERT INTO `category` VALUES ('34', '影视', '2');
INSERT INTO `category` VALUES ('35', '人物', '3');
INSERT INTO `category` VALUES ('36', '机械', '3');
INSERT INTO `category` VALUES ('37', 'Cosplay', '3');
INSERT INTO `category` VALUES ('38', '游戏', '4');

-- ----------------------------
-- Table structure for `city`
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '备用城市', '1');
INSERT INTO `city` VALUES ('2', '北京', '2');
INSERT INTO `city` VALUES ('3', '上海', '3');
INSERT INTO `city` VALUES ('4', '深圳', '4');
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
INSERT INTO `city` VALUES ('31', '重庆', '30');
INSERT INTO `city` VALUES ('30', '测试新增城市', '30');

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
  `praise_number` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FKjwn7a4qe3io39cr7n1ek8uxqw` (`supcomment_id`),
  KEY `FKh1gtv412u19wcbx22177xbkjp` (`author_id`)
) ENGINE=MyISAM AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '测试评论正文', '2018-06-23 20:04:54', '0', '1', '0');
INSERT INTO `comment` VALUES ('2', '测试评论正文', '2018-06-23 20:04:54', '0', '2', '0');
INSERT INTO `comment` VALUES ('3', '测试评论正文', '2018-06-23 20:04:54', '0', '3', '0');
INSERT INTO `comment` VALUES ('4', '测试评论正文', '2018-06-23 20:04:54', '0', '4', '0');
INSERT INTO `comment` VALUES ('5', '测试评论正文', '2018-06-23 20:04:54', '0', '5', '0');
INSERT INTO `comment` VALUES ('6', '测试评论正文', '2018-06-23 20:04:54', '0', '6', '0');
INSERT INTO `comment` VALUES ('7', '测试评论正文', '2018-06-23 20:04:54', '0', '7', '0');
INSERT INTO `comment` VALUES ('8', '测试评论正文', '2018-06-23 20:04:54', '0', '8', '0');
INSERT INTO `comment` VALUES ('9', '测试评论正文', '2018-06-23 20:04:54', '0', '9', '0');
INSERT INTO `comment` VALUES ('10', '测试评论正文', '2018-06-23 20:04:54', '0', '10', '0');
INSERT INTO `comment` VALUES ('11', '测试评论正文', '2018-06-23 20:04:54', '0', '11', '0');
INSERT INTO `comment` VALUES ('12', '测试评论正文', '2018-06-23 20:04:54', '0', '12', '0');
INSERT INTO `comment` VALUES ('13', '测试评论正文', '2018-06-23 20:04:55', '1', '13', '0');
INSERT INTO `comment` VALUES ('14', '测试评论正文', '2018-06-23 20:04:55', '1', '14', '0');
INSERT INTO `comment` VALUES ('15', '测试评论正文', '2018-06-23 20:04:55', '1', '15', '0');
INSERT INTO `comment` VALUES ('16', '测试评论正文', '2018-06-23 20:04:55', '1', '16', '0');
INSERT INTO `comment` VALUES ('17', '测试评论正文', '2018-06-23 20:04:55', '1', '17', '0');
INSERT INTO `comment` VALUES ('18', '测试评论正文', '2018-06-23 20:04:55', '1', '18', '0');
INSERT INTO `comment` VALUES ('19', '测试评论正文', '2018-06-23 20:04:55', '1', '19', '0');
INSERT INTO `comment` VALUES ('20', '测试评论正文', '2018-06-23 20:04:55', '1', '20', '0');
INSERT INTO `comment` VALUES ('21', '测试评论正文', '2018-06-23 20:04:55', '1', '21', '0');
INSERT INTO `comment` VALUES ('22', '测试评论正文', '2018-06-23 20:04:55', '1', '22', '0');
INSERT INTO `comment` VALUES ('23', '测试评论正文', '2018-06-23 20:04:55', '1', '23', '0');
INSERT INTO `comment` VALUES ('24', '测试评论正文', '2018-06-23 20:04:55', '1', '24', '0');
INSERT INTO `comment` VALUES ('25', '测试评论正文', '2018-06-23 20:04:55', '1', '25', '0');
INSERT INTO `comment` VALUES ('26', '测试评论正文', '2018-06-23 20:04:55', '1', '26', '0');
INSERT INTO `comment` VALUES ('27', '测试评论正文', '2018-06-23 20:04:55', '1', '27', '0');
INSERT INTO `comment` VALUES ('28', '测试评论正文', '2018-06-23 20:04:55', '1', '28', '0');
INSERT INTO `comment` VALUES ('29', '测试评论正文', '2018-06-23 20:04:55', '1', '29', '0');
INSERT INTO `comment` VALUES ('30', '测试评论专辑', '2018-07-07 20:31:53', '0', '1', '0');
INSERT INTO `comment` VALUES ('31', '测试评论专辑2', '2018-07-07 20:32:50', '0', '1', '0');
INSERT INTO `comment` VALUES ('32', '测试评论计数增加', '2018-07-07 21:07:20', '0', '1', '0');
INSERT INTO `comment` VALUES ('33', 'sssss', '2018-07-08 17:41:50', '30', '1', '0');
INSERT INTO `comment` VALUES ('34', '测试子评论2', '2018-07-08 17:45:11', '30', '1', '0');
INSERT INTO `comment` VALUES ('35', '测试子评论3', '2018-07-08 17:51:55', '30', '1', '0');
INSERT INTO `comment` VALUES ('36', '测试评论', '2018-07-08 18:11:45', '0', '1', '0');
INSERT INTO `comment` VALUES ('37', '测试评论', '2018-07-08 18:18:00', '0', '1', '0');
INSERT INTO `comment` VALUES ('38', '测试评论故事', '2018-07-08 18:21:11', '0', '1', '0');
INSERT INTO `comment` VALUES ('39', '测试子评论', '2018-07-08 18:22:18', '38', '1', '0');
INSERT INTO `comment` VALUES ('40', '测试子评论发布4', '2018-07-08 19:42:41', '32', '1', '0');
INSERT INTO `comment` VALUES ('41', '测试子评论', '2018-07-08 20:19:53', '32', '1', '0');
INSERT INTO `comment` VALUES ('42', '测试发布子评论', '2018-07-09 18:14:23', '30', '1', '0');
INSERT INTO `comment` VALUES ('43', '测试发布子评论', '2018-07-09 18:14:28', '30', '1', '0');
INSERT INTO `comment` VALUES ('44', '测试发布子评论', '2018-07-09 18:14:32', '30', '1', '0');
INSERT INTO `comment` VALUES ('45', '测试发布子评论7', '2018-07-09 18:14:47', '30', '1', '0');
INSERT INTO `comment` VALUES ('46', '测试发布子评论8', '2018-07-09 18:14:53', '30', '1', '0');
INSERT INTO `comment` VALUES ('47', '测试发布子评论9', '2018-07-09 18:15:00', '30', '1', '0');
INSERT INTO `comment` VALUES ('48', '测试发布子评论10', '2018-07-09 18:15:08', '30', '1', '0');
INSERT INTO `comment` VALUES ('49', '测试发布子评论11', '2018-07-09 18:15:45', '30', '1', '0');
INSERT INTO `comment` VALUES ('50', '测试发布子评论12', '2018-07-09 18:16:51', '30', '1', '0');
INSERT INTO `comment` VALUES ('51', '测试发布子评论13', '2018-07-09 18:21:52', '30', '1', '0');
INSERT INTO `comment` VALUES ('52', '测试发布子评论14', '2018-07-09 21:16:12', '30', '1', '0');
INSERT INTO `comment` VALUES ('53', '测试发布子评论15', '2018-07-09 21:16:19', '30', '1', '0');
INSERT INTO `comment` VALUES ('54', '测试发布子评论16', '2018-07-09 21:16:25', '30', '1', '0');
INSERT INTO `comment` VALUES ('55', '测试发布子评论17', '2018-07-09 21:16:32', '30', '1', '0');
INSERT INTO `comment` VALUES ('56', '测试发布子评论18', '2018-07-09 21:16:38', '30', '1', '0');
INSERT INTO `comment` VALUES ('57', '测试发布子评论19', '2018-07-09 21:16:44', '30', '1', '0');
INSERT INTO `comment` VALUES ('58', '测试发布子评论20', '2018-07-09 21:16:51', '30', '1', '0');
INSERT INTO `comment` VALUES ('59', '测试发布子评论21', '2018-07-09 21:16:56', '30', '1', '0');
INSERT INTO `comment` VALUES ('60', '测试子评论翻页', '2018-07-09 21:34:15', '32', '1', '0');
INSERT INTO `comment` VALUES ('61', '测试子评论翻页', '2018-07-09 21:34:20', '32', '1', '0');
INSERT INTO `comment` VALUES ('62', '测试子评论翻页', '2018-07-09 21:34:25', '32', '1', '0');
INSERT INTO `comment` VALUES ('63', '测试子评论翻页', '2018-07-09 21:34:29', '32', '1', '0');
INSERT INTO `comment` VALUES ('64', '测试子评论翻页', '2018-07-09 21:34:33', '32', '1', '0');
INSERT INTO `comment` VALUES ('65', '测试子评论翻页', '2018-07-09 21:34:37', '32', '1', '0');
INSERT INTO `comment` VALUES ('66', '测试子评论翻页', '2018-07-09 21:34:41', '32', '1', '0');
INSERT INTO `comment` VALUES ('67', '测试子评论翻页', '2018-07-09 21:34:45', '32', '1', '0');
INSERT INTO `comment` VALUES ('68', '测试子评论翻页', '2018-07-09 21:34:50', '32', '1', '0');
INSERT INTO `comment` VALUES ('69', '测试子评论翻页', '2018-07-09 21:34:55', '32', '1', '0');
INSERT INTO `comment` VALUES ('70', '测试子评论翻页', '2018-07-09 21:34:59', '32', '1', '0');
INSERT INTO `comment` VALUES ('71', '测试子评论翻页', '2018-07-09 21:35:03', '32', '1', '0');
INSERT INTO `comment` VALUES ('72', '测试子评论翻页', '2018-07-09 21:35:06', '32', '1', '0');
INSERT INTO `comment` VALUES ('73', '测试子评论翻页', '2018-07-09 21:35:10', '32', '1', '0');
INSERT INTO `comment` VALUES ('74', '测试子评论翻页', '2018-07-09 21:35:13', '32', '1', '0');
INSERT INTO `comment` VALUES ('75', '测试子评论翻页', '2018-07-09 21:35:17', '32', '1', '0');
INSERT INTO `comment` VALUES ('76', '测试子评论翻页', '2018-07-09 21:35:20', '32', '1', '0');
INSERT INTO `comment` VALUES ('77', '测试子评论翻页', '2018-07-09 21:35:23', '32', '1', '0');
INSERT INTO `comment` VALUES ('78', '测试子评论翻页', '2018-07-09 21:35:27', '32', '1', '0');
INSERT INTO `comment` VALUES ('79', '测试子评论翻页', '2018-07-09 21:35:30', '32', '1', '0');
INSERT INTO `comment` VALUES ('80', '测试子评论翻页', '2018-07-09 21:35:34', '32', '1', '0');
INSERT INTO `comment` VALUES ('81', '测试子评论翻页', '2018-07-09 21:35:37', '32', '1', '0');
INSERT INTO `comment` VALUES ('82', '测试子评论翻页', '2018-07-09 21:35:40', '32', '1', '0');
INSERT INTO `comment` VALUES ('83', '测试子评论', '2018-07-09 21:36:06', '31', '1', '0');
INSERT INTO `comment` VALUES ('84', '测试子评论', '2018-07-09 21:44:39', '38', '1', '0');
INSERT INTO `comment` VALUES ('85', '测试子评论', '2018-07-09 21:44:47', '38', '1', '0');
INSERT INTO `comment` VALUES ('86', '测试子评论', '2018-07-09 21:44:50', '38', '1', '0');
INSERT INTO `comment` VALUES ('87', '测试子评论翻页', '2018-07-09 21:44:58', '38', '1', '0');
INSERT INTO `comment` VALUES ('88', '测试子评论翻页', '2018-07-09 21:45:02', '38', '1', '0');
INSERT INTO `comment` VALUES ('89', '测试子评论翻页', '2018-07-09 21:45:05', '38', '1', '0');
INSERT INTO `comment` VALUES ('90', '测试子评论翻页', '2018-07-09 21:45:08', '38', '1', '0');
INSERT INTO `comment` VALUES ('91', '测试子评论翻页', '2018-07-09 21:45:11', '38', '1', '0');
INSERT INTO `comment` VALUES ('92', '测试子评论翻页', '2018-07-09 21:45:14', '38', '1', '0');
INSERT INTO `comment` VALUES ('93', '测试子评论翻页', '2018-07-09 21:45:18', '38', '1', '1');
INSERT INTO `comment` VALUES ('94', '测试子评论翻页', '2018-07-09 21:45:21', '38', '1', '0');
INSERT INTO `comment` VALUES ('95', '测试子评论翻页', '2018-07-09 21:45:24', '38', '1', '0');
INSERT INTO `comment` VALUES ('96', '测试子评论翻页', '2018-07-09 21:45:27', '38', '1', '0');
INSERT INTO `comment` VALUES ('97', '测试子评论翻页', '2018-07-09 21:45:31', '38', '1', '0');
INSERT INTO `comment` VALUES ('98', '测试子评论翻页', '2018-07-09 21:45:34', '38', '1', '0');
INSERT INTO `comment` VALUES ('99', '测试子评论翻页', '2018-07-09 21:45:45', '38', '1', '0');
INSERT INTO `comment` VALUES ('100', '测试子评论翻页', '2018-07-09 21:45:49', '38', '1', '0');
INSERT INTO `comment` VALUES ('101', '测试子评论翻页', '2018-07-09 21:45:59', '38', '1', '0');
INSERT INTO `comment` VALUES ('102', '测试子评论翻页', '2018-07-09 21:46:03', '38', '1', '0');
INSERT INTO `comment` VALUES ('103', '测试子评论翻页', '2018-07-09 21:46:06', '38', '1', '1');
INSERT INTO `comment` VALUES ('104', '测试父评论', '2018-07-09 21:46:20', '0', '1', '1');
INSERT INTO `comment` VALUES ('105', '测试父评论3', '2018-07-09 21:46:31', '0', '1', '1');
INSERT INTO `comment` VALUES ('109', '测试评论', '2018-07-20 20:34:51', '0', '2', '1');
INSERT INTO `comment` VALUES ('110', '测试子评论', '2018-07-20 20:35:02', '109', '2', '0');
INSERT INTO `comment` VALUES ('111', '测试评论数量增加', '2018-08-11 18:10:43', '0', '1', '1');
INSERT INTO `comment` VALUES ('112', '测试评论数量增加', '2018-08-11 18:11:01', '111', '1', '0');

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
) ENGINE=MyISAM AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

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
INSERT INTO `comment_est` VALUES ('51', '109', '0', '0', '92');
INSERT INTO `comment_est` VALUES ('52', '111', '0', '0', '96');

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
  `status` int(2) DEFAULT '0',
  `info` varchar(255) DEFAULT '暂时还没有提示信息!',
  PRIMARY KEY (`id`),
  KEY `FK1h2hjd11hus4qmxy08l63tr2v` (`author_id`)
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of essay
-- ----------------------------
INSERT INTO `essay` VALUES ('37', '1', '\n            测试随笔投稿2', '2018-07-16 19:27:42', '/upload/essay/1/9385920180701164245.jpg', '测试随笔投稿2', '1', '1', null);
INSERT INTO `essay` VALUES ('38', '0', '\n            测试随笔投稿3', '2018-07-14 21:07:01', '/upload/essay/1/3330520180701164258.jpg', '测试随笔投稿3', '1', '4', null);
INSERT INTO `essay` VALUES ('39', '0', '\n            测试随笔投稿4', '2018-07-16 19:52:04', '/upload/essay/1/1783720180701164313.jpg', '测试随笔投稿4', '1', '1', null);
INSERT INTO `essay` VALUES ('40', '0', '\n            测试随笔投稿5', '2018-07-14 21:07:07', '/upload/essay/1/1050220180701164328.jpg', '测试随笔投稿5', '1', '1', null);
INSERT INTO `essay` VALUES ('41', '0', '    讲真，最好的庆祝热气球日的方式就是乘坐热气球，但是，如果你今天不能乘坐热气球，那么应该知道热气球的发明至少可以追溯到3世纪的中国，当时的纸灯笼被用来传递军事信号。后来在18世纪的法国，造纸的孟格菲兄弟发明了无人驾驶热气球。经过多次试验和改良，两兄弟终于让热气球顺利的载人升空，这也是你今天所见到的热气球的模样。\n            ', '2018-07-21 20:15:15', '/upload/essay/1/8602920180721201459.jpg', '奥本上方的热气球', '1', '1', null);
INSERT INTO `essay` VALUES ('42', '0', '1933年的今天，世界上第一家汽车电影院开业，它位于新泽西州的彭索金镇。而今天我们的照片展示的是位于沃尔镇的Fly-in Drive-in剧场，顾名思义，这个剧场欢迎飞机和汽车。这样的剧场在当时是观看电影的热门场所，而且有一种增长的趋势。而如今汽车电影院又再度成为年轻人观影的新鲜方式。', '2018-07-21 20:19:03', '/upload/essay/1/3774620180721201721.jpg', '抵达沃尔镇Fly-in Drive-in剧场的观众', '1', '1', null);
INSERT INTO `essay` VALUES ('43', '0', '直到1883年的这一天布鲁克林大桥开通之前，从布鲁克林到曼哈顿的唯一途径就是乘船。在建造的时候，布鲁克林大桥是当时世界上最长的悬索桥。它最初被称为东河大桥或者纽约与布鲁克林大桥，但最终它的名字被确定为布鲁克林大桥。布鲁克林大桥启用后，它已成为纽约天际线不可或缺的一部分.', '2018-07-21 20:19:05', '/upload/essay/1/9495820180721201754.jpg', '1883年在建设中的布鲁克林大桥', '1', '1', null);
INSERT INTO `essay` VALUES ('44', '0', '‘Kolonihavehus, 2010’是布鲁克林大桥公园的一幅公共艺术作品，位于东河的布鲁克林一侧。这幅作品由艺术家汤姆·弗鲁因利用在当地打捞的废弃有机玻璃制成，灵感来自于哥本哈根。布鲁克林大桥公园占地85英亩，设计师将这里由工业厂区转变为休闲天堂，站在码头上，你可以看见生机勃勃的船运与曼哈顿的天际线.', '2018-07-21 20:19:08', '/upload/essay/1/6431220180721201849.jpg', '布鲁克林大桥公园展出的汤姆·弗鲁因的‘Kolonihavehus, 2010\'作品', '1', '1', null);
INSERT INTO `essay` VALUES ('45', '0', '北京大学创办于1898年，初名京师大学堂，是近代中国第一所国立综合性大学，其建立标志着中华民族现代高等教育事业的伟大起步。百年风云际会之中，北京大学为民族振兴、国家发展、社会和文明的进步做出了卓越的贡献，在中国走向现代化的进程中起到了重要的先锋作用，同时更是当代中国高等教育的引领者与开拓者。', '2018-07-21 20:20:30', '/upload/essay/1/3815420180721202022.jpg', '北京大学日落时的鸟瞰图', '1', '1', null);
INSERT INTO `essay` VALUES ('46', '1', '每周都会有数以百计的游客前往海勒卡拉国家公园的哈莱亚卡拉火山，他们来到火山顶端，在高出海平面10000英尺的地方看日出。传说这个受保护的地方是半人半神茂伊的地盘，他将太阳束缚在这里，以延长白天的时间。如今这里成为休眠火山公园，目前这里的火山非常稳定，游客们可以放心前往。', '2018-07-21 20:23:18', '/upload/essay/1/8061520180721202306.jpg', '海勒卡拉国家公园的哈莱亚卡拉火山顶端的游客', '1', '1', null);
INSERT INTO `essay` VALUES ('47', '2', '张家界国家森林公园建立于1982年，占地近12,000英亩，是武陵源风景名胜区的一部分。公园保存着张家界最原始的生态环境，那里的动物种群为公园增添了几分神秘，在这种不寻常的生态系统中，有着来无影去无踪的云豹，它们经常神出鬼没，如果你有幸看到它，可以说是非常幸运了！', '2018-07-21 20:25:47', '/upload/essay/1/5384220180721202402.jpg', '张家界国家森林公园', '1', '1', null);
INSERT INTO `essay` VALUES ('48', '0', '犹他州的大盐湖沿岸至少有27个私人保护区。而肖尔兰兹自然保护区由大自然保护协会所拥有，它坐落在一个沼泽地里，这个保护区是太平洋鸟类迁徙的必经之路，也是迁徙鸟类的四大迁徙路线之一。自然保护区保护着这些迁徙路线不被人类破坏，努力保持着它最最原始的生态。', '2018-07-21 20:25:45', '/upload/essay/1/7913820180721202441.jpg', '大盐湖肖尔兰兹自然保护区', '1', '1', null);
INSERT INTO `essay` VALUES ('49', '0', '这座位于台湾东海岸的独特的人行天桥一直连接到三仙台，三仙台是一个无人居住的小岛，以其独特多样的岩层和海景而闻名。相传“八仙”中的李铁拐、吕洞宾、何仙姑曾于岛上停憩，在山上留下三仙足印，故名三仙台。这座桥始建于1987年，其设计类似于中国民间传说中的龙，十分壮观！', '2018-07-21 20:25:50', '/upload/essay/1/1127620180721202536.jpg', '三仙台龙桥', '1', '1', null);
INSERT INTO `essay` VALUES ('50', '0', '\n            纽约州并不是只有摩天大楼和熙熙攘攘的人群，其实它还拥有多种多样的地理环境，包括1788年7月26日纽约州正式加入联邦时的一些原始地区。为了纪念纽约州的成立之日，我们来到了罗切斯特附近的一个荒野——莱奇沃思州立公园。这里被誉为“东方大峡谷”，在此您可以感受到三大瀑布怒吼的震撼感觉。', '2018-07-26 08:14:01', '/upload/essay/1/6702420180726081401.jpg', '纽约州的极致自然景观', '1', '0', null);
INSERT INTO `essay` VALUES ('51', '0', '\n            测试七牛云', '2018-08-20 17:49:48', 'pdr28szno.bkt.clouddn.com/5537820180820174947.jpg', '测试七牛云', '1', '0', null);
INSERT INTO `essay` VALUES ('52', '0', '\n            测试七牛云', '2018-08-20 17:53:41', 'http://pdr28szno.bkt.clouddn.com/9179620180820175340.jpg', '测试七牛云', '1', '0', null);
INSERT INTO `essay` VALUES ('53', '0', '\n            测试七牛云', '2018-08-20 18:54:12', 'http://pdr28szno.bkt.clouddn.com/2834820180820185412.jpg', '测试七牛云', '1', '0', null);

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
  `status` int(2) DEFAULT '0',
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdlt0s1srth133hmddr12pjouk` (`city_id`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of event
-- ----------------------------
INSERT INTO `event` VALUES ('30', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动投稿正文</h1><p><img src=\"/upload/event/1/4670920180702191220.jpg\" title=\"/upload/event/1/4670920180702191220.jpg\" alt=\"/upload/event/1/4670920180702191220.jpg\"/><img src=\"/upload/event/1/3424720180702191157.jpg\" title=\"/upload/event/1/3424720180702191157.jpg\" alt=\"/upload/event/1/3424720180702191157.jpg\"/><img src=\"/upload/event/1/4103220180702191157.jpg\" title=\"/upload/event/1/4103220180702191157.jpg\" alt=\"/upload/event/1/4103220180702191157.jpg\"/></p>', 'xx市xx区xx街', '50元', '测试活动投稿', '测试', '2018-09-12 08:00:00', '1', '/upload/event/1/7560520180702192048.jpg', '1', null);
INSERT INTO `event` VALUES ('31', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动投稿正文2</h1><p>19:36:39</p><p><img width=\"530\" height=\"340\" src=\"http://api.map.baidu.com/staticimage?center=116.404,39.915&zoom=10&width=530&height=340&markers=116.404,39.915\"/><img src=\"/upload/event/1/1016820180702193735.jpg\" title=\"/upload/event/1/1016820180702193735.jpg\" alt=\"/upload/event/1/1016820180702193735.jpg\"/></p>', 'xx市xx区xx街', '50元', '测试活动投稿2', '测试', '2018-09-12 08:00:00', '1', '/upload/event/1/4106020180702193744.jpg', '1', null);
INSERT INTO `event` VALUES ('32', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\">测试活动申请2018-07-02</h1><p><img width=\"530\" height=\"340\" src=\"http://api.map.baidu.com/staticimage?center=104.062942,33.267644&zoom=11&width=530&height=340&markers=104.062942,33.267644\"/></p>', '九寨沟', '300元', '测试活动申请', '旅游', '2018-07-28 08:00:00', '1', '/upload/event/1/3842020180702204847.jpg', '1', null);
INSERT INTO `event` VALUES ('33', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\"><span style=\"font-size: 16px; font-style: italic; font-weight: bold; color: rgb(51, 153, 204); line-height: 18px;\">测试活动申请2018-07-02</span></h1><p><img src=\"http://img.baidu.com/hi/jx2/j_0011.gif\"/></p>', '环城公园', '20元', '测试活动申请', '无限之旅', '2018-07-28 08:00:00', '1', '/upload/event/1/5510220180702205011.jpg', '1', null);
INSERT INTO `event` VALUES ('34', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\"><span style=\"font-size: 16px; font-style: italic; font-weight: bold; color: rgb(51, 153, 204); line-height: 18px;\">测试活动申请2018-07-02</span></h1><p><img src=\"http://img.baidu.com/hi/jx2/j_0011.gif\"/><img width=\"530\" height=\"340\" src=\"http://api.map.baidu.com/staticimage?center=117.717395,39.009416&zoom=13&width=530&height=340&markers=117.717395,39.009416\"/></p>', '滨海', '20元', '自驾游', '旅游', '2018-07-26 08:00:00', '1', '/upload/event/1/4797820180702205108.jpg', '1', null);
INSERT INTO `event` VALUES ('35', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\"><span style=\"font-size: 16px; font-style: italic; font-weight: bold; color: rgb(51, 153, 204); line-height: 18px;\">测试活动申请2018-07-02</span></h1><p><img src=\"http://img.baidu.com/hi/jx2/j_0011.gif\"/></p><p><img src=\"/upload/event/1/5591320180702205254.jpg\" title=\"/upload/event/1/5591320180702205254.jpg\"/></p><p><img src=\"/upload/event/1/8328520180702205254.jpg\" title=\"/upload/event/1/8328520180702205254.jpg\"/></p><p><img src=\"/upload/event/1/1175020180702205254.jpg\" title=\"/upload/event/1/1175020180702205254.jpg\"/></p><p><br/></p>', '集合点:湖南省湘潭市', '500元', '丝绸之路', '旅游', '2018-07-26 08:00:00', '1', '/upload/event/1/8817920180702205257.jpg', '1', null);
INSERT INTO `event` VALUES ('36', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\">活动详情:</h1><p><img width=\"530\" height=\"340\" src=\"http://api.map.baidu.com/staticimage?center=100.016327,36.866641&zoom=9&width=530&height=340&markers=116.404,39.915\"/></p><p><img src=\"/upload/event/1/2720020180702210416.jpg\" title=\"/upload/event/1/2720020180702210416.jpg\"/></p><p><img src=\"/upload/event/1/3926820180702210416.jpg\" title=\"/upload/event/1/3926820180702210416.jpg\"/></p><p><img src=\"/upload/event/1/7954620180702210416.jpg\" title=\"/upload/event/1/7954620180702210416.jpg\"/></p><p><br/></p>', '青海湖', '100元', '青海湖自驾游', '自驾游', '2018-07-24 08:00:00', '1', '/upload/event/1/6414520180702210557.jpg', '1', null);

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
-- Table structure for `praise`
-- ----------------------------
DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) DEFAULT '0',
  `essay_id` bigint(20) DEFAULT '0',
  `event_id` bigint(20) DEFAULT '0',
  `story_id` bigint(20) DEFAULT '0',
  `topic_id` bigint(20) DEFAULT '0',
  `user_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of praise
-- ----------------------------
INSERT INTO `praise` VALUES ('9', '104', '0', '0', '0', '0', '1');
INSERT INTO `praise` VALUES ('5', '107', '0', '0', '0', '0', '1');
INSERT INTO `praise` VALUES ('4', '106', '0', '0', '0', '0', '1');
INSERT INTO `praise` VALUES ('6', '105', '0', '0', '0', '0', '1');
INSERT INTO `praise` VALUES ('7', '103', '0', '0', '0', '0', '1');
INSERT INTO `praise` VALUES ('8', '93', '0', '0', '0', '0', '1');
INSERT INTO `praise` VALUES ('10', '109', '0', '0', '0', '0', '1');
INSERT INTO `praise` VALUES ('11', '111', '0', '0', '0', '0', '1');

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
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('1', '1', '1');
INSERT INTO `role_user` VALUES ('2', '2', '1');
INSERT INTO `role_user` VALUES ('3', '2', '2');

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
  `status` int(2) DEFAULT '0',
  `info` varchar(255) DEFAULT '暂时还没有提示信息!',
  PRIMARY KEY (`id`),
  KEY `FKfsmrtuhgkrna5psxspo06q56f` (`author_id`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of story
-- ----------------------------
INSERT INTO `story` VALUES ('35', '342', '2', '2018-07-01 16:45:48', '测试故事投稿富文本编辑器', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试故事投稿2</h1><p><span style=\"text-decoration: underline;\"><em>对方的身份为的方式福娃服务恶法地方我额服务<img src=\"http://img.baidu.com/hi/jx2/j_0013.gif\"/><img src=\"/upload/story/1/5218820180701164535.jpg\" title=\"/upload/story/1/5218820180701164535.jpg\" alt=\"/upload/story/1/5218820180701164535.jpg\"/><img src=\"/upload/story/1/2427720180701164545.jpg\" title=\"/upload/story/1/2427720180701164545.jpg\" alt=\"/upload/story/1/2427720180701164545.jpg\"/></em></span></p><h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\"><br/></h1>', '测试故事投稿2', '/upload/story/1/5948620180701164547.jpg', '1', null);
INSERT INTO `story` VALUES ('36', '343', '24', '2018-07-01 17:33:50', '测试故事投稿1080p图片', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">图片源文件为1080p,只在显示时缩放成合适大小</h1><p>直接右击另存为可查看效果;</p><p><img src=\"/upload/story/1/6573120180701173341.jpg\" title=\"/upload/story/1/6573120180701173341.jpg\"/></p><p><img src=\"/upload/story/1/3980920180701173341.jpg\" title=\"/upload/story/1/3980920180701173341.jpg\"/></p><p><img src=\"/upload/story/1/7199320180701173341.jpg\" title=\"/upload/story/1/7199320180701173341.jpg\"/></p><p><img src=\"/upload/story/1/6532020180701173341.jpg\" title=\"/upload/story/1/6532020180701173341.jpg\"/></p><p><img src=\"/upload/story/1/4025420180701173341.jpg\" title=\"/upload/story/1/4025420180701173341.jpg\"/></p><p><br/></p>', '图片源文件为1080p,只在显示时缩放成合适大小;', '/upload/story/1/7297620180701173350.jpg', '1', null);
INSERT INTO `story` VALUES ('37', '5', '0', '2018-07-21 21:38:50', '追随足球的脚步永不停息!', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">追随足球的脚步永不停息</h1><p>&nbsp;&nbsp;&nbsp;&nbsp;历时一个月的足球狂欢终于要在今天完美落幕，你是否也在每个寂静的深夜跟一堆球友在酒吧看球发出阵阵欢呼？虽然世界杯将在今天结束，但人们对足球的热爱却从未减少，你看那些在海边踢足球的孩子们，他们无忧无虑的身影是否也让你想起儿时的自己？希望此时的你也能不忘初心，勇敢前行！</p><p>&nbsp; &nbsp; 2002年韩日世界杯是第17届世界杯足球赛，比赛于2002年5月31日至6月30日在韩国境内10座城市中的10座球场和日本境内10座城市中的10座球场举行。本届世界杯是首次在亚洲举行的世界杯，也是首次由两个国家共同举办的世界杯。中国队首次进军世界杯；最终巴西队夺得冠军，成就五冠王伟业。<br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;<img src=\"/upload/story/1/1301920180721212931.jpg\" title=\"/upload/story/1/1301920180721212931.jpg\" alt=\"/upload/story/1/1301920180721212931.jpg\"/></p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;2006年德国世界杯是第18届世界杯足球赛，比赛于2006年6月9日至7月9日在德国境内的12座球场举行。这是继1974年后世界杯第二次在德国举行，也是继1998年后世界杯再次在欧洲举行。世界杯决赛在柏林奥林匹克球场举行，最终意大利通过点球大战5比3击败法国夺冠！</p><p><img src=\"/upload/story/1/2284620180721213229.jpg\" title=\"/upload/story/1/2284620180721213229.jpg\" alt=\"/upload/story/1/2284620180721213229.jpg\"/></p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;2010年南非世界杯是第19届世界杯足球赛，比赛于2010年6月11日至7月11日在南非的9个城市的10座球场举行，这是世界杯足球赛首次在非洲地区举行。最终西班牙国家队获得了他们历史上的首个世界杯冠军头衔，这也是欧洲球队首次在欧洲之外的国家举办的世界杯上夺冠。</p><p><img src=\"/upload/story/1/9378320180721213343.jpg\" title=\"/upload/story/1/9378320180721213343.jpg\" alt=\"/upload/story/1/9378320180721213343.jpg\"/></p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;2014年巴西世界杯是第20届世界杯足球赛，比赛于2014年6月12日至7月13日在南美洲国家巴西境内12座城市中的12座球场内举行。期间总共在巴西境内举办共计64场比赛角逐出冠军。这也是首届运用门线技术的世界杯。决赛场上，德国国家男子足球队加时1比0战胜阿根廷夺得冠军。</p><p><img src=\"/upload/story/1/4774020180721213438.jpg\" title=\"/upload/story/1/4774020180721213438.jpg\" alt=\"/upload/story/1/4774020180721213438.jpg\"/></p><p>&nbsp;<br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 18px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">扎比瓦卡:</span></p><p><span style=\"font-size: 18px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=\"/upload/story/1/5230920180721213657.jpg\" title=\"/upload/story/1/5230920180721213657.jpg\" alt=\"/upload/story/1/5230920180721213657.jpg\"/></span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;扎比瓦卡是2018年俄罗斯世界杯足球赛吉祥物，这个吉祥物以西伯利亚平原狼为蓝本。扎比瓦卡，俄语意为“进球者”。吉祥物的评选首先参考了俄罗斯儿童的意见，并由大学生参与设计，共有超过100万人参与了投票。于是这只可爱的平原狼就成为这界世界杯为人熟知的宠儿。</p><p><span style=\"font-size: 18px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span></p><p><span style=\"font-size: 18px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;&nbsp;&nbsp;&nbsp;Telstar 18:</span></p><p><span style=\"font-size: 18px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;2017年11月9日，国际足联与阿迪达斯共同发布了本届赛事官方指定比赛用球“Telstar 18”。比赛用球保持了传统足球外观，球体采用经典黑白两色，文字则使用了金色，同时比赛用球采用了现代化的工艺和科技，这是首次在世界杯用球中植入NFC芯片，目的是让球迷可以通过智能手机连接比赛用球;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;<img src=\"/upload/story/1/3698520180721213827.jpg\" title=\"/upload/story/1/3698520180721213827.jpg\" alt=\"/upload/story/1/3698520180721213827.jpg\"/></p>', '历时一个月的足球狂欢终于要在今天完美落幕!', '/upload/story/1/5081520180721213850.jpg', '1', null);
INSERT INTO `story` VALUES ('38', '0', '0', '2018-08-20 18:55:01', '测试七牛云', '1', '<p>测试七牛云<img src=\"http://pdr28szno.bkt.clouddn.com/5794920180820185444.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/5794920180820185444.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/5794920180820185444.jpg\"/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/3072120180820185457.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/3072120180820185457.jpg\"/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/7389720180820185457.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/7389720180820185457.jpg\"/></p><p><br/></p>', '测试七牛云', 'http://pdr28szno.bkt.clouddn.com/6363320180820185501.jpg', '0', null);

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
  `status` int(2) unsigned DEFAULT '0',
  `info` varchar(255) DEFAULT '暂时还没有提示信息!',
  PRIMARY KEY (`id`),
  KEY `FK91351a48ok1rkldd36rmpb34g` (`author_id`),
  KEY `FK8n7r9utm8sjpdfstb4wcqd7qj` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('81', '342', '134', '\n            测试上传和打包下载专辑图片2', '2018-07-01 16:38:37', '测试上传和打包下载专辑图片3', '1', '/upload/topic/1/7407920180701163836.jpg', '/upload/topic/1/3496020180701163837.jpg,/upload/topic/1/3068420180701163837.jpg,/upload/topic/1/8620820180701163837.jpg,/upload/topic/1/1330820180701163837.jpg', '1', '/upload/topic/1/.zip', '3', '稿件重复,请勿重复投稿!');
INSERT INTO `topic` VALUES ('82', '343', '134', '\n         测试专辑绑定分类正文', '2018-07-01 16:39:30', '测试专辑绑定分类', '1', '/upload/topic/1/1174720180701163929.jpg', '/upload/topic/1/9612520180701163929.jpg,/upload/topic/1/9056220180701163929.jpg,/upload/topic/1/9517420180701163929.jpg', '1', '/upload/topic/1/.zip', '0', ' 内容与其它稿件重复; ');
INSERT INTO `topic` VALUES ('83', '342', '2', '\n    投稿绑定分类测试', '2018-07-01 16:40:52', '投稿绑定分类测试', '1', '/upload/topic/1/6208620180701164051.jpg', '/upload/topic/1/3867220180701164052.jpg,/upload/topic/1/8292420180701164052.jpg,/upload/topic/1/9486420180701164052.jpg,/upload/topic/1/3953920180701164052.jpg', '1', '/upload/topic/1/.zip', '1', null);
INSERT INTO `topic` VALUES ('91', '6', '0', '\n            测试新的投稿逻辑测试新的投稿逻辑', '2018-07-09 22:26:27', '测试新的投稿逻辑', '1', '/upload/topic/1/91/3302820180709222627.jpg', '/upload/topic/1/91/4003220180709222633.jpg,/upload/topic/1/91/9845920180709222638.jpg,/upload/topic/1/91/9368020180709222643.jpg,/upload/topic/1/91/6618720180709222651.jpg,/upload/topic/1/91/2997120180709222658.jpg', '1', '/upload/topic/1/91/.zip', '2', null);
INSERT INTO `topic` VALUES ('92', '3', '2', '\n            测试测试', '2018-07-09 22:31:47', '测试新的投稿逻辑2', '1', '/upload/topic/1/92/9707320180709223147.jpg', '/upload/topic/1/92/2632520180709223203.jpg,/upload/topic/1/92/2850820180709223209.jpg,/upload/topic/1/92/5579920180709223209.jpg,/upload/topic/1/92/8256120180709223210.jpg,/upload/topic/1/92/9765020180709223210.jpg,/upload/topic/1/92/9141320180709223210.jpg', '4', '/upload/topic/1/92/.zip', '1', null);
INSERT INTO `topic` VALUES ('93', '0', '0', '\n            精选出来的田园风光1080P壁纸.', '2018-07-21 19:32:21', '田野风景壁纸', '1', '/upload/topic/1/93/1872620180721193221.jpg', '/upload/topic/1/93/5073320180721193221.jpg,/upload/topic/1/93/2206820180721193222.jpg,/upload/topic/1/93/3264820180721193222.jpg,/upload/topic/1/93/5430020180721193222.jpg,/upload/topic/1/93/3606220180721193222.jpg,/upload/topic/1/93/5869020180721193223.jpg,/upload/topic/1/93/8468320180721193223.jpg,/upload/topic/1/93/2055420180721193223.jpg,/upload/topic/1/93/3902620180721193223.jpg', '12', '/upload/topic/1/93/.zip', '1', null);
INSERT INTO `topic` VALUES ('94', '0', '0', '\n            Long may the sunshine!', '2018-07-21 19:56:51', '光与影', '1', '/upload/topic/1/94/6532220180721195651.jpg', '/upload/topic/1/94/9815420180721195651.jpg,/upload/topic/1/94/7217120180721195652.jpg,/upload/topic/1/94/3256520180721195652.jpg,/upload/topic/1/94/3138720180721195652.jpg,/upload/topic/1/94/2542420180721195652.jpg,/upload/topic/1/94/1000220180721195652.jpg,/upload/topic/1/94/8715220180721195653.jpg,/upload/topic/1/94/1460820180721195653.jpg', '23', '/upload/topic/1/94/.zip', '1', null);
INSERT INTO `topic` VALUES ('95', '1', '0', '\n            山峦与湖泊风景壁纸', '2018-07-21 20:09:18', '依山傍水', '1', '/upload/topic/1/95/4417520180721200918.jpg', '/upload/topic/1/95/1442520180721200918.jpg,/upload/topic/1/95/3215120180721200918.jpg,/upload/topic/1/95/8137320180721200919.jpg,/upload/topic/1/95/7813020180721200919.jpg,/upload/topic/1/95/6541220180721200919.jpg,/upload/topic/1/95/8187620180721200919.jpg,/upload/topic/1/95/1762520180721200919.jpg,/upload/topic/1/95/2626220180721200920.jpg,/upload/topic/1/95/7643820180721200920.jpg,/upload/topic/1/95/2891020180721200920.jpg,/upload/topic/1/95/1429820180721200920.jpg,/upload/topic/1/95/5854720180721200921.jpg,/upload/topic/1/95/2578320180721200921.jpg,/upload/topic/1/95/5962820180721200921.jpg', '11', '/upload/topic/1/95/.zip', '1', null);
INSERT INTO `topic` VALUES ('96', '1', '2', '\n            俯瞰城市风光', '2018-07-21 20:12:31', '城市风景', '1', '/upload/topic/1/96/2490320180721201231.jpg', '/upload/topic/1/96/7462520180721201232.jpg,/upload/topic/1/96/6682820180721201232.jpg,/upload/topic/1/96/4351820180721201232.jpg,/upload/topic/1/96/2896220180721201232.jpg,/upload/topic/1/96/5415020180721201233.jpg,/upload/topic/1/96/4504720180721201233.jpg,/upload/topic/1/96/7392420180721201233.jpg,/upload/topic/1/96/6611520180721201233.jpg,/upload/topic/1/96/6281020180721201233.jpg,/upload/topic/1/96/1356920180721201233.jpg,/upload/topic/1/96/7216120180721201234.jpg,/upload/topic/1/96/5786520180721201234.jpg,/upload/topic/1/96/6248020180721201234.jpg,/upload/topic/1/96/5466720180721201234.jpg', '4', '/upload/topic/1/96/.zip', '1', null);
INSERT INTO `topic` VALUES ('97', '0', '0', '\n            测试七牛云', '2018-08-20 17:54:33', '测试七牛云', '1', null, null, '23', null, '0', null);
INSERT INTO `topic` VALUES ('98', '0', '0', '\n            测试七牛云', '2018-08-20 18:01:28', '测试七牛云', '1', null, null, '23', null, '0', null);
INSERT INTO `topic` VALUES ('99', '0', '0', '\n            测试七牛云', '2018-08-20 18:03:28', '测试七牛云', '1', null, null, '23', null, '0', null);
INSERT INTO `topic` VALUES ('100', '0', '0', '\n            测试七牛云', '2018-08-20 18:04:23', '测试七牛云', '1', null, null, '23', null, '0', null);
INSERT INTO `topic` VALUES ('101', '0', '0', '\n            测试七牛云', '2018-08-20 18:08:29', '测试七牛云', '1', null, null, '23', null, '0', null);
INSERT INTO `topic` VALUES ('102', '0', '0', '\n            测试七牛云', '2018-08-20 18:13:44', '测试七牛云', '1', null, null, '23', null, '0', null);
INSERT INTO `topic` VALUES ('103', '0', '0', '\n            测试七牛云', '2018-08-20 18:17:08', '测试七牛云', '1', null, null, '23', null, '0', null);
INSERT INTO `topic` VALUES ('104', '0', '0', '\n            测试七牛云', '2018-08-20 18:19:48', '测试七牛云', '1', null, null, '23', null, '0', null);
INSERT INTO `topic` VALUES ('105', '0', '0', '\n            测试七牛云', '2018-08-20 18:22:16', '测试七牛云', '1', 'http://pdr28szno.bkt.clouddn.com/7009420180820182216.jpg', 'http://pdr28szno.bkt.clouddn.com/2884520180820182217.jpg,http://pdr28szno.bkt.clouddn.com/3079520180820182218.jpg,http://pdr28szno.bkt.clouddn.com/8980920180820182218.jpg,htt', '23', '/upload/topic/1/105/.zip', '0', null);
INSERT INTO `topic` VALUES ('106', '0', '0', '\n         测试', '2018-08-20 18:32:06', '测试七牛云', '1', 'http://pdr28szno.bkt.clouddn.com/5824420180820183206.jpg', 'http://pdr28szno.bkt.clouddn.com/6591720180820183206.jpg,http://pdr28szno.bkt.clouddn.com/2859120180820183207.jpg,http://pdr28szno.bkt.clouddn.com/1271120180820183207.jpg,htt', '23', '/upload/topic/1/106/.zip', '0', null);
INSERT INTO `topic` VALUES ('107', '0', '0', '\n            测试七牛云', '2018-08-20 18:53:16', '测试七牛云', '1', 'http://pdr28szno.bkt.clouddn.com/4775320180820185316.jpg', 'http://pdr28szno.bkt.clouddn.com/6029320180820185317.jpg,http://pdr28szno.bkt.clouddn.com/6742420180820185318.jpg,http://pdr28szno.bkt.clouddn.com/7506820180820185319.jpg,http://pdr28szno.bkt.clouddn.com/1539820180820185319.jpg', '23', '/upload/topic/1/107/.zip', '0', null);

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
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'testuser', '1', 'http://pdr28szno.bkt.clouddn.com/9809320180820185542.png', '2018-06-23 20:04:54', '管理员', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '测试修改签名', '2');
INSERT INTO `user` VALUES ('2', 'testuser2', '2', '/upload/user.jpg', '2018-06-23 20:04:54', '测试用户2', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '测试签名2', '3');
INSERT INTO `user` VALUES ('3', 'testuser3', '1', '/upload/user.jpg', '2018-06-23 20:04:54', '测试用户3', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '测试签名3', '1');
INSERT INTO `user` VALUES ('32', 'testuser123', '2', '/upload/user.jpg', '2018-07-05 19:40:25', '测试注册功能', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '3');
INSERT INTO `user` VALUES ('33', 'testuser1234', '1', '/upload/user.jpg', '2018-07-05 19:43:59', '测试注册功能', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '4');
INSERT INTO `user` VALUES ('34', 'testuser1111', '1', '/upload/user.jpg', '2018-07-05 19:55:18', '测试注册功能', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '2');
INSERT INTO `user` VALUES ('35', '222222', '1', '/img/test/userUserHead.jpg', '2018-07-27 19:46:55', '管理员2', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '1');

-- ----------------------------
-- Table structure for `user_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `user_favorite`;
CREATE TABLE `user_favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `essay_id` bigint(20) DEFAULT '0',
  `story_id` bigint(20) DEFAULT '0',
  `topic_id` bigint(20) DEFAULT '0',
  `user_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_favorite
-- ----------------------------
INSERT INTO `user_favorite` VALUES ('114', '46', '0', '0', '2');
INSERT INTO `user_favorite` VALUES ('129', '0', '35', '0', '1');
INSERT INTO `user_favorite` VALUES ('103', '0', '0', '82', '1');
INSERT INTO `user_favorite` VALUES ('104', '0', '0', '79', '1');
INSERT INTO `user_favorite` VALUES ('113', '49', '0', '0', '2');
INSERT INTO `user_favorite` VALUES ('123', '37', '0', '0', '1');
INSERT INTO `user_favorite` VALUES ('135', '0', '0', '95', '1');
INSERT INTO `user_favorite` VALUES ('111', '0', '34', '0', '1');
INSERT INTO `user_favorite` VALUES ('126', '0', '0', '96', '1');
INSERT INTO `user_favorite` VALUES ('128', '47', '0', '0', '1');
