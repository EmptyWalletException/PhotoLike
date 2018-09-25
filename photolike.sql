/*
Navicat MySQL Data Transfer

Source Server         : 47.98.240.27阿里云
Source Server Version : 50641
Source Host           : 47.98.240.27:3306
Source Database       : photolike

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2018-09-25 16:42:03
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
INSERT INTO `category` VALUES ('13', '旅游景点', '23');
INSERT INTO `category` VALUES ('21', '路与远方', '21');
INSERT INTO `category` VALUES ('23', '自然奇景', '25');
INSERT INTO `category` VALUES ('36', '机械', '3');
INSERT INTO `category` VALUES ('37', '动物', '3');

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
) ENGINE=MyISAM AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

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
INSERT INTO `comment` VALUES ('110', '测试子评论', '2018-07-20 20:35:02', '109', '2', '0');
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
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

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
INSERT INTO `comment_est` VALUES ('53', '113', '0', '0', '96');
INSERT INTO `comment_est` VALUES ('54', '114', '0', '0', '96');

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
) ENGINE=MyISAM AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of essay
-- ----------------------------
INSERT INTO `essay` VALUES ('55', '0', '当22支队伍开始第105届环法自行车赛时，所有人的目光都聚焦在了法国。这项享有盛誉的自行车赛将带参赛者穿过2000多英里的乡村。比赛全程包括21个阶段，历时22天。参赛者将爬越阿尔卑斯山脉和比利牛斯山脉，并在数英里的鹅卵石、土路、乡间小路和高速公路上骑行，最终到达终点巴黎香榭丽舍大街。\n            ', '2018-08-20 19:44:33', 'http://pdr28szno.bkt.clouddn.com/1726620180820191525.jpg', '2016年环法自行车赛中骑在绿树成荫的道路上的选手', '1', '1', null);
INSERT INTO `essay` VALUES ('56', '0', '\n            当你看到这张航行在佩诺拉海峡的欧罗巴帆船的照片时，有没有觉得寒气逼人？这艘三桅帆船看起来很像极地探险家欧内斯特·沙克尔顿在1915年远征南极时所失踪的那艘“坚忍号”航船。但是欧罗巴帆船是一艘改装过的现代化轻舟，现在它载着付费乘客在世界各地航行，这艘船每年都要前往南极洲，圆了很多人的终极旅行梦。', '2018-08-20 19:44:35', 'http://pdr28szno.bkt.clouddn.com/4599420180820194110.jpg', '航行在佩诺拉海峡的欧罗巴帆船', '1', '1', null);
INSERT INTO `essay` VALUES ('57', '0', '\n            讲真，最好的庆祝热气球日的方式就是乘坐热气球，但是，如果你今天不能乘坐热气球，那么应该知道热气球的发明至少可以追溯到3世纪的中国，当时的纸灯笼被用来传递军事信号。后来在18世纪的法国，造纸的孟格菲兄弟发明了无人驾驶热气球。经过多次试验和改良，两兄弟终于让热气球顺利的载人升空，这也是你今天所见到的热气球的模样。', '2018-08-20 19:44:38', 'http://pdr28szno.bkt.clouddn.com/7888420180820194154.jpg', '奥本上方的热气球，美国华盛顿州', '1', '1', null);
INSERT INTO `essay` VALUES ('58', '0', '\n            1933年的今天，世界上第一家汽车电影院开业，它位于新泽西州的彭索金镇。而今天我们的照片展示的是位于沃尔镇的Fly-in Drive-in剧场，顾名思义，这个剧场欢迎飞机和汽车。这样的剧场在当时是观看电影的热门场所，而且有一种增长的趋势。而如今汽车电影院又再度成为年轻人观影的新鲜方式。', '2018-08-20 19:44:30', 'http://pdr28szno.bkt.clouddn.com/5219620180820194238.jpg', '抵达沃尔镇Fly-in Drive-in剧场的观众', '1', '1', null);
INSERT INTO `essay` VALUES ('59', '0', '\n            直到1883年的这一天布鲁克林大桥开通之前，从布鲁克林到曼哈顿的唯一途径就是乘船。在建造的时候，布鲁克林大桥是当时世界上最长的悬索桥。它最初被称为东河大桥或者纽约与布鲁克林大桥，但最终它的名字被确定为布鲁克林大桥。布鲁克林大桥启用后，它已成为纽约天际线不可或缺的一部分。', '2018-08-20 19:44:27', 'http://pdr28szno.bkt.clouddn.com/2593120180820194318.jpg', '1883年在建设中的布鲁克林大桥', '1', '1', null);
INSERT INTO `essay` VALUES ('60', '0', '\n            如果你有挪威血统，那么5月17日是个值得庆祝的日子。1814年的今天，挪威宪法被通过，并宣布挪威为独立王国。图中这个位于挪威特隆赫姆的历史地区将是一个庆祝的理想场所。特隆赫姆是挪威第三大城市，由挪威国王奥拉夫一世在997年创立。如今，它五颜六色的码头和水道吸引着来自世界各地的游客。', '2018-08-20 19:44:24', 'http://pdr28szno.bkt.clouddn.com/7667520180820194354.jpg', '历史悠久的捕鱼仓库，挪威特隆赫姆', '1', '1', null);
INSERT INTO `essay` VALUES ('61', '0', '\n            不知道是不是身边最先找到人生伴侣的大学同学了?借此网站公测的机会,送一首诗祝贺两位佳人和和美美,白头偕老:佳偶天成拜玉堂，争看娇女配仙郎.尊前合成调鹦鹉，台上吹箫引凤凰.华月团圆除宝扇，香云袅娜斗新妆.因风传语张京兆，日画春山几许长!\n', '2018-08-20 20:23:37', 'http://pdr28szno.bkt.clouddn.com/3834420180820202306.jpg', '恭喜大学同学喜登婚姻的殿堂!', '1', '1', null);
INSERT INTO `essay` VALUES ('62', '0', '\n            今天是瑞士的国庆日，瑞士人可以在这一天享受一天的假期。这个国家第一次独立是在1891年，但是直到1994年的这一天才被宣布为法定假日。也许瑞士人的这种耐心只有在穿越兰达镇附近的查尔斯库纳步行吊桥时才能得以体现，这座吊桥是目前世界上最长的行人悬索桥。何不趁着今天，来挑战一下自己？', '2018-08-20 20:26:19', 'http://pdr28szno.bkt.clouddn.com/6745220180820202544.jpg', '兰达镇附近的查尔斯库纳步行吊桥', '1', '1', null);
INSERT INTO `essay` VALUES ('63', '0', '\n            反复无常的天气是否让你更深刻的感受到秋天的来临，前一秒晴空万里下一秒电闪雷鸣的景象也成为家常便饭，就像这壁纸中暴风雨下的薰衣草和向日葵花田，远处的闪电让花田有着更加生动鲜明的对比，那片花海是否也是你心中的花田呢？来吧，让我们一起去普罗旺斯寻找心中的那片花海！', '2018-09-05 14:37:16', 'http://pdr28szno.bkt.clouddn.com/4356620180905143533.jpg', '暴风雨下的薰衣草和向日葵花田', '1', '1', null);
INSERT INTO `essay` VALUES ('64', '1', '\n            来皇后区的法拉盛草原可乐娜公园观看美国网球公开赛吧。当它于1881年作为美国全国锦标赛开始时，它是世界上最早的网球锦标赛之一，而且仅限于业余选手参加。而如今美网已经成为全世界最负盛名的网球锦标赛。在前往网球中心的路上，你可能会经过壁纸上的地球仪。这个140英尺高的不锈钢球体是为1964年纽约世博会而建造的。建造喷泉的目的是为了遮住结构的底部，让它看起来像一个网球！', '2018-09-05 14:37:13', 'http://pdr28szno.bkt.clouddn.com/2435320180905143618.jpg', '法拉盛草原可乐娜公园内的地球仪', '1', '1', null);
INSERT INTO `essay` VALUES ('65', '0', '\n            芒通靠近意大利、摩纳哥公国以及尼斯伯爵领地，宜人的亚热带微气候令这里几乎感受不到冬季。因此，人们一年四季在芒通均可享受最棒的海水以及附近阳光明媚的山脉。芒通不仅是花园的天堂，同时还是一个展示各种建筑的无与伦比的橱窗，这些建筑造就了这片神秘的蔚蓝海岸地区，每个人在其中都能沿着名人的足迹漫步徜徉，不计其数的名人都曾来到这个仙境般的环境重新寻觅真实。', '2018-09-08 07:42:55', 'http://pdr28szno.bkt.clouddn.com/2258520180905143937.jpg', '没有冬季的法国芒通', '1', '1', null);
INSERT INTO `essay` VALUES ('66', '0', '\n            都柏林三一学院图书馆是爱尔兰最古老也是最负盛名的图书馆，三一学院图书馆从成立之初就是为了打破原有的修道院的传统。图书馆最著名的就是那长达65米的图书长廊了，古朴的拱形圆顶和陈旧华丽的木头让整个图书馆充满神秘色彩。踏进图书馆的那一刻宁静，只想让人把灵魂安放于此。', '2018-09-08 07:42:52', 'http://pdr28szno.bkt.clouddn.com/5192020180908074141.jpg', '都柏林三一学院图书馆', '1', '1', null);
INSERT INTO `essay` VALUES ('67', '0', '\n            这里是位于加拿大安大略省的多伦多市，在这样一个充满活力的城市坐落着一座非同寻常的博物馆，那就是你在壁纸中看到的皇家安大略博物馆，一座被称为“水晶宫殿”的建筑。由于多伦多正在举办国际电影节，因此今天的博物馆可能不会非常繁忙。那么何不趁此机会，去一睹博物馆的风采？', '2018-09-09 07:22:21', 'http://pdr28szno.bkt.clouddn.com/1180720180909072152.jpg', '皇家安大略博物馆', '1', '1', null);

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
) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of event
-- ----------------------------
INSERT INTO `event` VALUES ('37', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动标题</h1><p>测试插入图片:</p><p><img src=\"/upload/event/1/4161320180813193017.jpg\" title=\"/upload/event/1/4161320180813193017.jpg\" alt=\"/upload/event/1/4161320180813193017.jpg\"/><img src=\"/upload/event/1/3192620180813193008.jpg\" title=\"/upload/event/1/3192620180813193008.jpg\" alt=\"/upload/event/1/3192620180813193008.jpg\"/></p>', '上海市', '50', '测试活动申请', '自助游', '2018-08-13 08:00:00', '3', '/upload/event/1/2589320180813193025.jpg', '4', null);
INSERT INTO `event` VALUES ('38', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动标题</h1><p>测试插入图片2:</p><p><img src=\"/upload/event/1/2582920180813193128.jpg\" title=\"/upload/event/1/2582920180813193128.jpg\"/></p><p><img src=\"/upload/event/1/9502020180813193128.jpg\" title=\"/upload/event/1/9502020180813193128.jpg\"/></p><p><br/></p>', '北京市', '50', '测试活动申请2', '自助游', '2018-08-13 08:00:00', '2', '/upload/event/1/7681020180813193154.jpg', '4', null);
INSERT INTO `event` VALUES ('39', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动标题</h1><p>测试插入图片2:</p><p><img src=\"/upload/event/1/2582920180813193128.jpg\" title=\"/upload/event/1/2582920180813193128.jpg\"/></p><p><img src=\"/upload/event/1/9502020180813193128.jpg\" title=\"/upload/event/1/9502020180813193128.jpg\"/></p><p><br/></p>', '北京市', '50', '测试活动申请3', '旅游', '2018-08-13 08:00:00', '2', '/upload/event/1/9573420180813193245.jpg', '4', null);
INSERT INTO `event` VALUES ('40', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动标题</h1><p>测试插入图片2:</p><p><img src=\"/upload/event/1/2582920180813193128.jpg\" title=\"/upload/event/1/2582920180813193128.jpg\"/></p><p><img src=\"/upload/event/1/9502020180813193128.jpg\" title=\"/upload/event/1/9502020180813193128.jpg\"/></p><p><br/></p>', '北京市', '50', '测试活动申请4', '旅游', '2018-08-13 08:00:00', '2', '/upload/event/1/3521020180813193259.jpg', '4', null);
INSERT INTO `event` VALUES ('41', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动标题</h1><p>测试插入图片2:</p><p><img src=\"/upload/event/1/2582920180813193128.jpg\" title=\"/upload/event/1/2582920180813193128.jpg\"/></p><p><img src=\"/upload/event/1/9502020180813193128.jpg\" title=\"/upload/event/1/9502020180813193128.jpg\"/></p><p><br/></p>', '北京市', '50', '测试活动申请5', '旅游', '2018-08-13 08:00:00', '2', '/upload/event/1/2600420180813193311.jpg', '4', null);
INSERT INTO `event` VALUES ('42', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动标题</h1><p>测试插入图片2:</p><p><img src=\"/upload/event/1/2582920180813193128.jpg\" title=\"/upload/event/1/2582920180813193128.jpg\"/></p><p><img src=\"/upload/event/1/9502020180813193128.jpg\" title=\"/upload/event/1/9502020180813193128.jpg\"/></p><p><br/></p>', '北京市', '50', '测试活动申请6', '旅游', '2018-08-13 08:00:00', '2', '/upload/event/1/3550920180813193322.jpg', '4', null);
INSERT INTO `event` VALUES ('43', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试活动标题</h1><p>测试七牛云图片上传：<br/></p><p><img src=\"/upload/event/1/6183320180905154347.jpg\" title=\"/upload/event/1/6183320180905154347.jpg\" alt=\"/upload/event/1/6183320180905154347.jpg\"/></p>', '北京市', '50元', '测试活动申请7', '旅游', '2018-09-02 08:00:00', '2', 'http://pdr28szno.bkt.clouddn.com/9969520180905154414.jpg', '4', null);
INSERT INTO `event` VALUES ('44', '<h1 style=\"margin: 0px 0px 20px; padding: 0px 4px 0px 0px; font-stretch: normal; font-size: 32px; line-height: 1.5; font-family: Verdana, 微软雅黑, &quot;Microsoft YaHei&quot;, Helvetica, Arial; color: rgb(45, 45, 45); white-space: normal; border-bottom: 2px solid rgb(204, 204, 204); text-align: center;\">测试活动标题</h1><p><span style=\"color: rgb(45, 45, 45); font-family: Verdana, 微软雅黑, &quot;Microsoft YaHei&quot;, Helvetica, Arial; font-size: 13px;\">测试插入图片2:</span><img src=\"/upload/event/1/4618020180905154606.jpg\" title=\"/upload/event/1/4618020180905154606.jpg\" alt=\"/upload/event/1/4618020180905154606.jpg\"/></p>', '北京市', '50', '测试活动申请8', '旅游', '2018-09-02 08:00:00', '2', 'http://pdr28szno.bkt.clouddn.com/4062520180905154725.jpg', '4', null);
INSERT INTO `event` VALUES ('45', '<p>活动标题</p><p>活动内容：</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/5242220180905162915.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/5242220180905162915.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/5242220180905162915.jpg\"/></p><p><br/></p>', '张家界国家森林公园', '500', '张家界三日游', '组团旅游', '2018-08-27 08:00:00', '5', 'http://pdr28szno.bkt.clouddn.com/2593920180905163014.jpg', '1', null);
INSERT INTO `event` VALUES ('46', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">多景点组团五日游</h1><p>景点以山水为主，具体计划暂定：<br/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/6963020180905171837.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/6963020180905171837.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/6963020180905171837.jpg\"/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/9168620180905171902.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/9168620180905171902.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/9168620180905171902.jpg\"/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/1350120180905172001.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/1350120180905172001.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/1350120180905172001.jpg\"/></p>', '多地点', '500', '山水之间', '组团旅游', '2018-09-03 08:00:00', '6', 'http://pdr28szno.bkt.clouddn.com/5599720180905172011.jpg', '1', null);
INSERT INTO `event` VALUES ('47', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">上海三日游</h1><p>在上海市范围内的景点游览，具体计划暂定：<br/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/1233420180905172204.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/1233420180905172204.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/1233420180905172204.jpg\"/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/9393020180905172249.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/9393020180905172249.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/9393020180905172249.jpg\"/></p><p><br/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/7053820180905172337.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/7053820180905172337.jpg\"/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/9977720180905172337.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/9977720180905172337.jpg\"/></p><p></p>', '上海市', '100', '上海之旅', '自驾游', '2018-09-04 08:00:00', '3', 'http://pdr28szno.bkt.clouddn.com/3491420180905172551.jpg', '1', null);
INSERT INTO `event` VALUES ('48', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试标题</h1><p>测试七牛云图片上传：<img src=\"http://pdr28szno.bkt.clouddn.com/9145520180905172842.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/9145520180905172842.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/9145520180905172842.jpg\"/></p>', '上海市', '50', '海滩聚会', '聚会', '2018-09-03 08:00:00', '3', 'http://pdr28szno.bkt.clouddn.com/8333220180905172904.jpg', '1', null);
INSERT INTO `event` VALUES ('49', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试标题</h1><p>测试七牛云图片上传：<img src=\"http://pdr28szno.bkt.clouddn.com/7614620180905172918.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/7614620180905172918.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/7614620180905172918.jpg\"/></p>', '西藏', '100', '挑战者之路', '锻炼，组团旅游', '2018-09-03 08:00:00', '12', 'http://pdr28szno.bkt.clouddn.com/5430020180905173047.jpg', '1', null);
INSERT INTO `event` VALUES ('50', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试标题</h1><p>测试七牛云图片上传：<img src=\"http://pdr28szno.bkt.clouddn.com/8169420180905173146.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/8169420180905173146.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/8169420180905173146.jpg\"/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/1656720180905173211.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/1656720180905173211.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/1656720180905173211.jpg\"/></p>', '云南', '100', '探索之旅', '自助游', '2018-09-03 08:00:00', '15', 'http://pdr28szno.bkt.clouddn.com/8175620180905173221.jpg', '1', null);

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
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of story
-- ----------------------------
INSERT INTO `story` VALUES ('35', '342', '2', '2018-07-01 16:45:48', '测试故事投稿富文本编辑器', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试故事投稿2</h1><p><span style=\"text-decoration: underline;\"><em>对方的身份为的方式福娃服务恶法地方我额服务<img src=\"http://img.baidu.com/hi/jx2/j_0013.gif\"/><img src=\"/upload/story/1/5218820180701164535.jpg\" title=\"/upload/story/1/5218820180701164535.jpg\" alt=\"/upload/story/1/5218820180701164535.jpg\"/><img src=\"/upload/story/1/2427720180701164545.jpg\" title=\"/upload/story/1/2427720180701164545.jpg\" alt=\"/upload/story/1/2427720180701164545.jpg\"/></em></span></p><h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\"><br/></h1>', '测试故事投稿2', '/upload/story/1/5948620180701164547.jpg', '2', null);
INSERT INTO `story` VALUES ('37', '5', '0', '2018-07-21 21:38:50', '追随足球的脚步永不停息!', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">追随足球的脚步永不停息</h1><p>&nbsp;&nbsp;&nbsp;&nbsp;历时一个月的足球狂欢终于要在今天完美落幕，你是否也在每个寂静的深夜跟一堆球友在酒吧看球发出阵阵欢呼？虽然世界杯将在今天结束，但人们对足球的热爱却从未减少，你看那些在海边踢足球的孩子们，他们无忧无虑的身影是否也让你想起儿时的自己？希望此时的你也能不忘初心，勇敢前行！</p><p>&nbsp; &nbsp; 2002年韩日世界杯是第17届世界杯足球赛，比赛于2002年5月31日至6月30日在韩国境内10座城市中的10座球场和日本境内10座城市中的10座球场举行。本届世界杯是首次在亚洲举行的世界杯，也是首次由两个国家共同举办的世界杯。中国队首次进军世界杯；最终巴西队夺得冠军，成就五冠王伟业。<br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;<img src=\"/upload/story/1/1301920180721212931.jpg\" title=\"/upload/story/1/1301920180721212931.jpg\" alt=\"/upload/story/1/1301920180721212931.jpg\"/></p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;2006年德国世界杯是第18届世界杯足球赛，比赛于2006年6月9日至7月9日在德国境内的12座球场举行。这是继1974年后世界杯第二次在德国举行，也是继1998年后世界杯再次在欧洲举行。世界杯决赛在柏林奥林匹克球场举行，最终意大利通过点球大战5比3击败法国夺冠！</p><p><img src=\"/upload/story/1/2284620180721213229.jpg\" title=\"/upload/story/1/2284620180721213229.jpg\" alt=\"/upload/story/1/2284620180721213229.jpg\"/></p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;2010年南非世界杯是第19届世界杯足球赛，比赛于2010年6月11日至7月11日在南非的9个城市的10座球场举行，这是世界杯足球赛首次在非洲地区举行。最终西班牙国家队获得了他们历史上的首个世界杯冠军头衔，这也是欧洲球队首次在欧洲之外的国家举办的世界杯上夺冠。</p><p><img src=\"/upload/story/1/9378320180721213343.jpg\" title=\"/upload/story/1/9378320180721213343.jpg\" alt=\"/upload/story/1/9378320180721213343.jpg\"/></p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;2014年巴西世界杯是第20届世界杯足球赛，比赛于2014年6月12日至7月13日在南美洲国家巴西境内12座城市中的12座球场内举行。期间总共在巴西境内举办共计64场比赛角逐出冠军。这也是首届运用门线技术的世界杯。决赛场上，德国国家男子足球队加时1比0战胜阿根廷夺得冠军。</p><p><img src=\"/upload/story/1/4774020180721213438.jpg\" title=\"/upload/story/1/4774020180721213438.jpg\" alt=\"/upload/story/1/4774020180721213438.jpg\"/></p><p>&nbsp;<br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 18px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">扎比瓦卡:</span></p><p><span style=\"font-size: 18px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=\"/upload/story/1/5230920180721213657.jpg\" title=\"/upload/story/1/5230920180721213657.jpg\" alt=\"/upload/story/1/5230920180721213657.jpg\"/></span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;扎比瓦卡是2018年俄罗斯世界杯足球赛吉祥物，这个吉祥物以西伯利亚平原狼为蓝本。扎比瓦卡，俄语意为“进球者”。吉祥物的评选首先参考了俄罗斯儿童的意见，并由大学生参与设计，共有超过100万人参与了投票。于是这只可爱的平原狼就成为这界世界杯为人熟知的宠儿。</p><p><span style=\"font-size: 18px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span></p><p><span style=\"font-size: 18px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;&nbsp;&nbsp;&nbsp;Telstar 18:</span></p><p><span style=\"font-size: 18px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;2017年11月9日，国际足联与阿迪达斯共同发布了本届赛事官方指定比赛用球“Telstar 18”。比赛用球保持了传统足球外观，球体采用经典黑白两色，文字则使用了金色，同时比赛用球采用了现代化的工艺和科技，这是首次在世界杯用球中植入NFC芯片，目的是让球迷可以通过智能手机连接比赛用球;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;<img src=\"/upload/story/1/3698520180721213827.jpg\" title=\"/upload/story/1/3698520180721213827.jpg\" alt=\"/upload/story/1/3698520180721213827.jpg\"/></p>', '历时一个月的足球狂欢终于要在今天完美落幕!', '/upload/story/1/5081520180721213850.jpg', '2', null);
INSERT INTO `story` VALUES ('40', '0', '0', '2018-08-20 20:01:59', '占个位置', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试标题</h1><p>测试七牛云图片上传<br/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/8529720180820200140.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/8529720180820200140.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/8529720180820200140.jpg\"/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/3601020180820200122.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/3601020180820200122.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/3601020180820200122.jpg\"/></p>', '测试百度富文本编辑器与七牛云', 'http://pdr28szno.bkt.clouddn.com/3194920180820200152.jpg', '1', null);
INSERT INTO `story` VALUES ('41', '0', '0', '2018-08-20 20:04:43', '测试稿件管理', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">测试标题</h1><p>测试七牛云图片上传</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/8305520180820200416.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/8305520180820200416.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/8305520180820200416.jpg\"/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/9703620180820200403.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/9703620180820200403.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/9703620180820200403.jpg\"/></p><p><img src=\"http://pdr28szno.bkt.clouddn.com/5216720180820200336.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/5216720180820200336.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/5216720180820200336.jpg\"/></p><p></p><p><br/></p>', '测试稿件七牛云图片的回显', 'http://pdr28szno.bkt.clouddn.com/9445220180820200436.jpg', '1', null);
INSERT INTO `story` VALUES ('42', '0', '0', '2018-09-05 14:57:25', '极致的浪漫享受', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">极致的浪漫享受</h1><p>&nbsp;&nbsp;&nbsp;&nbsp;反复无常的天气是否让你更深刻的感受到秋天的来临，前一秒晴空万里下一秒电闪雷鸣的景象也成为家常便饭，就像这壁纸中暴风雨下的薰衣草和向日葵花田，远处的闪电让花田有着更加生动鲜明的对比，那片花海是否也是你心中的花田呢？来吧，让我们一起去普罗旺斯寻找心中的那片花海！ &nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;</p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;<br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;尼斯老城最初由希腊人建成，曾是罗马帝国的殖民地，于1860年才脱离意大利统治。因此尼斯的老城区到处都是充满意大利风格的古老建筑，让人感觉更像是身在意大利而不是在法国，建筑物颜色鲜艳明亮，小巷随小山丘高低曲折，那些藏在小巷中的特色小店，百年餐厅和酒吧总是给游人带来无限惊喜。</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/1621220180905145452.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/1621220180905145452.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/1621220180905145452.jpg\"/></p><p><br/></p><p><br/></p><p>蔚蓝海岸是法国东南部的一片地区，又名里维埃拉，西起土伦，东到芒通，海岸线总长度约115公里。这里是典型的地中海式气候，夏季阳光明媚，冬季温暖多雨，既有山，又有海，非常适宜休闲度假。现在这里是法国除巴黎以外最受欢迎的旅游胜地，每年接待几百万来自世界各地的游客。<img src=\"http://pdr28szno.bkt.clouddn.com/3244920180905145428.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/3244920180905145428.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/3244920180905145428.jpg\"/></p><p><br/></p><p><br/></p><p>索村被称为“薰衣草之都”，是欧洲最大的薰衣草产地，索村只有一千多的人口，整个小镇被薰衣草的花海所包围着。这座小城保留了一些中世纪小巷，城内还有一些精美的老橱窗，每周三的早上都会有集市，这一传统可追溯至1515年。由于海拔较高，花季相对较晚，一般8月还能看到成片紫色的薰衣草。</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/4484120180905145509.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/4484120180905145509.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/4484120180905145509.jpg\"/></p><p><br/></p><p><br/></p><p>隆尚宫建于拿破仑三世统治时期的1862年，实际上是一座水塔，整体风格融巴洛克、罗马及东方建筑于一体。沿着著名的卡奴比埃尔大街前行，就可以看到这座宏伟的宫殿。隆尚宫的基本格局为半圆形，中间是群雕和喷泉，两边各延伸出去一段回廊，如今，回廊的尽头各设有一座博物馆，分别是美术博物馆和马赛历史博物馆。</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/3688720180905145543.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/3688720180905145543.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/3688720180905145543.jpg\"/></p><p><br/></p><p><br/></p><p></p><p><p><p>奥朗日古罗马剧场——现场感与年代感并存的古老剧院</p></p></p><p>奥朗日古罗马剧场因为保存完整而世界闻名，剧场残留的大理石圆柱令人遥想当年罗马时代的繁华绚烂。这里原本是可以容纳近万人的雄伟剧场，可保存到现在的只有宽达百米的外台、背景装饰壁、舞台遗迹等。如今会有表演团体在这里举办民族服饰节庆、大型展会、歌舞表演等，非常有现场感！</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/2140520180905145629.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/2140520180905145629.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/2140520180905145629.jpg\"/></p><p><br/></p><p><br/></p><p><a href=\"http://cn.bing.com/Search?q=%e5%8d%a1%e6%9c%97%e6%a0%bc%e5%b3%a1%e6%b9%be%e5%9c%b0%e5%8c%ba&mkt=zh-cn&FORM=BNLH\" style=\"font-family: &quot;Microsoft YaHei&quot;, &quot;Segoe UI&quot;, Segoe, Tahoma, Arial, Verdana, sans-serif; font-size: 13px; color: rgb(255, 255, 255); letter-spacing: 0.8px; white-space: normal; background-color: rgba(0, 0, 0, 0.8);\"><p></p></a><p>卡朗格峡湾地区——小巧秀美的地中海港湾群。</p></p><p>对于户外爱好者来说，去到马赛一定要去徒步卡郞格峡湾地区。这是一处地中海风格的天然岩石峡湾群，绵延近20公里。想要欣赏美丽的峡湾景色，既可以选择乘坐游船游览，这里碧蓝的水一定会让你有跳下去游泳的冲动；也可以选择在山上徒步，居高临下的俯瞰峡湾，这也是游览峡湾的最佳方式。</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/2119620180905145655.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/2119620180905145655.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/2119620180905145655.jpg\"/></p><p><br/></p>', '薰衣草才不是普罗旺斯的全部呢！', 'http://pdr28szno.bkt.clouddn.com/7080220180905145717.jpg', '1', null);
INSERT INTO `story` VALUES ('43', '0', '0', '2018-09-05 15:14:00', '就要不一样的小众运动', '1', '<h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;\">不为人知的优雅游戏</h1><p>如果一项运动能持续8个世纪，那一定有它存在的理由，它就是不那么广为人知的草地滚球运动。1830年割草机的发明，让这项运动有了绝佳的进行场地，草地滚球运动也随之普及。你所看到的草地上的这些球是在游戏中使用的，参赛者的目的是将葡萄柚大小的球滚向一个叫做“杰克”的小球，并将分数奖给最接近的参赛者。有没有觉得这项运动高智商大运动量，聪明的你快跟我们一起玩吧！<img src=\"http://pdr28szno.bkt.clouddn.com/7426420180905151330.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/7426420180905151330.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/7426420180905151330.jpg\"/></p><p><br/></p><p><br/></p><p>1：曲棍球：</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/5027020180905151105.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/5027020180905151105.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/5027020180905151105.jpg\"/></p><p>曲棍球又名草地曲棍球，是奥运会项目中历史最为悠久和光辉的项目之一。曲棍球这一名称起源于法语，意思是牧羊人的棍杖。作为世界上历史最悠久的体育项目之一，曲棍球的出现要比最初的奥林匹克运动会早1200年或者更多。曲棍球的技术掌握也是一项挑战，厌倦普通足球的你，何不来试试这个？</p><p><br/></p><p>2：手球：</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/4604620180905151131.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/4604620180905151131.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/4604620180905151131.jpg\"/></p><p>手球是一种起源于德国的球类运动。基本上，手球好似足球加橄榄球的混合物。同时，手球的一些规则是由篮球的规则转变发展而来的。手球比赛中，进攻队员之间的传接球花样繁多，多样、准确的射门动作更是令人赏心悦目。各种各样的鱼跃、倒地和滚翻射门技术，在手球比赛中屡见不鲜。</p><p><br/></p><p>3：冰球：</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/2773320180905151154.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/2773320180905151154.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/2773320180905151154.jpg\"/></p><p>冰球也称为“冰上曲棍球”，它是多变的滑冰技艺和敏捷娴熟的曲棍球技艺的结合，是对抗性较强的集体冰上运动项目之一，也是冬季奥运会正式比赛项目。运动员穿着冰鞋，手拿冰杆滑行拼抢击球。球一般用硬橡胶制成，运动员用冰杆将球击入对方球门，以多者为胜。</p><p><br/></p><p>4：垒球：</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/6829520180905151210.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/6829520180905151210.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/6829520180905151210.jpg\"/></p><p>垒球由棒球发展而来，规则也相似，技术难度、运动剧烈程度低于棒球。垒球诞生于19世纪80年代的美国芝加哥，现在全世界有2000万人进行这项体育运动。垒球于1996年在奥林匹克运动会上成为正式项目。其实垒球运动的诞生完全是处于一种需要，由于恶劣的天气和拥挤的城市影响，棒球运动转移到室内，就形成了垒球运动。</p><p><br/></p><p>5：极限飞盘：</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/1055020180905151230.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/1055020180905151230.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/1055020180905151230.jpg\"/></p><p>极限飞盘运动时会穿足球鞋，一般会占用半个足球场地，因为没有身体接触，所以男女都能同时参加。运动的时候，气氛非常激烈，为了赢得比赛飞盘运动员通常需要具备良好的速度、判断力和技巧。当自己飞出一手漂亮的飞盘，在空中划过一道漂亮的弧线时，就觉得特别满足！</p><p><br/></p><p>6：棒球：</p><p><img src=\"http://pdr28szno.bkt.clouddn.com/1725020180905151247.jpg\" title=\"http://pdr28szno.bkt.clouddn.com/1725020180905151247.jpg\" alt=\"http://pdr28szno.bkt.clouddn.com/1725020180905151247.jpg\"/></p>在棒球中最令人期待的当然是完美的全垒打了，最伟大的打者通常很会打全垒打。棒球进攻队伍的球员必须依序经过四个垒包才能得分，在其它情况下，打者上垒成为跑者之后，必须藉由其他队友的帮助推进才能得分。但是若击出全垒打，计分板上分数便会噌噌上涨，因此全垒打一向是棒球运动中最为人津津乐道的一环！<p><br/></p>', '厌倦了健身房？来跟我们动起来吧！', 'http://pdr28szno.bkt.clouddn.com/8679520180905151351.jpg', '1', null);

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
INSERT INTO `topic` VALUES ('100', '0', '0', '\n            在城市与大海交接的港口,如画的风景正等待着人们欣赏.', '2018-08-20 19:50:11', '城市边缘的港口', '1', 'http://pdr28szno.bkt.clouddn.com/6109920180820195010.jpg', 'http://pdr28szno.bkt.clouddn.com/5142220180820195017.jpg,http://pdr28szno.bkt.clouddn.com/2983320180820195024.jpg,http://pdr28szno.bkt.clouddn.com/7578720180820195030.jpg,http://pdr28szno.bkt.clouddn.com/2824920180820195038.jpg,http://pdr28szno.bkt.clouddn.com/7373820180820195045.jpg,http://pdr28szno.bkt.clouddn.com/9666520180820195053.jpg', '4', '/upload/topic/1/100/.zip', '1', null);
INSERT INTO `topic` VALUES ('101', '0', '0', '\n            设计师和工程师可不仅仅满足于实用性.', '2018-08-20 19:54:03', '桥的艺术', '1', 'http://pdr28szno.bkt.clouddn.com/7366720180820195403.jpg', 'http://pdr28szno.bkt.clouddn.com/3210820180820195409.jpg,http://pdr28szno.bkt.clouddn.com/3781920180820195416.jpg,http://pdr28szno.bkt.clouddn.com/2553620180820195424.jpg,http://pdr28szno.bkt.clouddn.com/7915720180820195432.jpg,http://pdr28szno.bkt.clouddn.com/6587720180820195439.jpg', '5', '/upload/topic/1/101/.zip', '1', null);
INSERT INTO `topic` VALUES ('102', '0', '0', '\n            有些路,在踏上它之前就被迷住了.', '2018-08-20 19:57:44', '路与远方', '1', 'http://pdr28szno.bkt.clouddn.com/5469720180820195743.jpg', 'http://pdr28szno.bkt.clouddn.com/9166420180820195752.jpg,http://pdr28szno.bkt.clouddn.com/2259220180820195801.jpg,http://pdr28szno.bkt.clouddn.com/2359720180820195811.jpg,http://pdr28szno.bkt.clouddn.com/6914620180820195824.jpg,http://pdr28szno.bkt.clouddn.com/2847020180820195833.jpg,http://pdr28szno.bkt.clouddn.com/8608220180820195841.jpg,http://pdr28szno.bkt.clouddn.com/6613320180820195848.jpg,http://pdr28szno.bkt.clouddn.com/9805320180820195858.jpg', '21', '/upload/topic/1/102/.zip', '1', null);
INSERT INTO `topic` VALUES ('103', '0', '0', '\n            动物之间的亲情和友谊一直都是单纯和真诚的，它们可不像人类一样会演戏。', '2018-09-05 14:21:23', '治愈系动物壁纸', '1', 'http://pdr28szno.bkt.clouddn.com/7499120180905142123.jpg', 'http://pdr28szno.bkt.clouddn.com/2008620180905142131.jpg,http://pdr28szno.bkt.clouddn.com/5043220180905142138.jpg,http://pdr28szno.bkt.clouddn.com/1848120180905142145.jpg,http://pdr28szno.bkt.clouddn.com/5289720180905142153.jpg,http://pdr28szno.bkt.clouddn.com/7163620180905142200.jpg,http://pdr28szno.bkt.clouddn.com/2831120180905142205.jpg,http://pdr28szno.bkt.clouddn.com/7673820180905142213.jpg,http://pdr28szno.bkt.clouddn.com/9181120180905142218.jpg,http://pdr28szno.bkt.clouddn.com/8857420180905142224.jpg,http://pdr28szno.bkt.clouddn.com/9550420180905142232.jpg,http://pdr28szno.bkt.clouddn.com/1098720180905142239.jpg,http://pdr28szno.bkt.clouddn.com/5939720180905142243.jpg,http://pdr28szno.bkt.clouddn.com/6299920180905142251.jpg,http://pdr28szno.bkt.clouddn.com/3596920180905142259.jpg', '37', '/upload/topic/1/103/.zip', '1', null);
INSERT INTO `topic` VALUES ('104', '0', '0', '\n            说好的建国后动物不能成精啊？', '2018-09-05 14:27:17', '充满灵性的动物系列', '1', 'http://pdr28szno.bkt.clouddn.com/9982620180905142716.jpg', 'http://pdr28szno.bkt.clouddn.com/1009320180905142725.jpg,http://pdr28szno.bkt.clouddn.com/6182220180905142733.jpg,http://pdr28szno.bkt.clouddn.com/6063920180905142740.jpg,http://pdr28szno.bkt.clouddn.com/9114220180905142748.jpg,http://pdr28szno.bkt.clouddn.com/6347320180905142751.jpg,http://pdr28szno.bkt.clouddn.com/4614020180905142759.jpg,http://pdr28szno.bkt.clouddn.com/3333320180905142801.jpg,http://pdr28szno.bkt.clouddn.com/6250620180905142809.jpg', '37', '/upload/topic/1/104/.zip', '1', null);
INSERT INTO `topic` VALUES ('105', '0', '0', '\n           每一张此系列壁纸的背后，都藏着一名差点饿死在野外的摄影师。', '2018-09-05 14:32:46', '镜头下的精彩瞬间', '1', 'http://pdr28szno.bkt.clouddn.com/3353220180905143246.jpg', 'http://pdr28szno.bkt.clouddn.com/5313120180905143253.jpg,http://pdr28szno.bkt.clouddn.com/4818320180905143300.jpg,http://pdr28szno.bkt.clouddn.com/4368320180905143309.jpg,http://pdr28szno.bkt.clouddn.com/4734420180905143316.jpg,http://pdr28szno.bkt.clouddn.com/9617320180905143323.jpg,http://pdr28szno.bkt.clouddn.com/3243920180905143330.jpg,http://pdr28szno.bkt.clouddn.com/2514720180905143335.jpg,http://pdr28szno.bkt.clouddn.com/6292420180905143342.jpg,http://pdr28szno.bkt.clouddn.com/6976620180905143350.jpg,http://pdr28szno.bkt.clouddn.com/1495820180905143357.jpg,http://pdr28szno.bkt.clouddn.com/3780620180905143406.jpg,http://pdr28szno.bkt.clouddn.com/3414220180905143412.jpg,http://pdr28szno.bkt.clouddn.com/7267520180905143420.jpg', '37', '/upload/topic/1/105/.zip', '1', null);
INSERT INTO `topic` VALUES ('106', '0', '0', '\n            东西方节日精彩瞬间。', '2018-09-05 17:43:14', '节日的气氛', '1', 'http://pdr28szno.bkt.clouddn.com/7472820180905174313.jpg', 'http://pdr28szno.bkt.clouddn.com/8821120180905174321.jpg,http://pdr28szno.bkt.clouddn.com/4396920180905174330.jpg,http://pdr28szno.bkt.clouddn.com/1764520180905174340.jpg,http://pdr28szno.bkt.clouddn.com/9735720180905174349.jpg,http://pdr28szno.bkt.clouddn.com/3301720180905174359.jpg,http://pdr28szno.bkt.clouddn.com/7645720180905174409.jpg,http://pdr28szno.bkt.clouddn.com/3568520180905174416.jpg,http://pdr28szno.bkt.clouddn.com/9623620180905174425.jpg,http://pdr28szno.bkt.clouddn.com/5451720180905174435.jpg,http://pdr28szno.bkt.clouddn.com/4964120180905174442.jpg', '6', '/upload/topic/1/106/.zip', '1', null);
INSERT INTO `topic` VALUES ('107', '0', '0', '\n            咸鱼不光是用来吃的，还能挂起来欣赏！', '2018-09-05 17:49:11', '房子？不，是艺术！', '1', 'http://pdr28szno.bkt.clouddn.com/8807820180905174911.jpg', 'http://pdr28szno.bkt.clouddn.com/2467820180905174922.jpg,http://pdr28szno.bkt.clouddn.com/5817420180905174934.jpg,http://pdr28szno.bkt.clouddn.com/4802320180905174941.jpg,http://pdr28szno.bkt.clouddn.com/9221420180905174949.jpg,http://pdr28szno.bkt.clouddn.com/9211020180905174955.jpg,http://pdr28szno.bkt.clouddn.com/1139020180905175003.jpg,http://pdr28szno.bkt.clouddn.com/2818920180905175010.jpg,http://pdr28szno.bkt.clouddn.com/1481320180905175016.jpg,http://pdr28szno.bkt.clouddn.com/3239820180905175023.jpg,http://pdr28szno.bkt.clouddn.com/4086820180905175031.jpg,http://pdr28szno.bkt.clouddn.com/4779520180905175039.jpg,http://pdr28szno.bkt.clouddn.com/6806320180905175047.jpg,http://pdr28szno.bkt.clouddn.com/1776420180905175055.jpg', '5', '/upload/topic/1/107/.zip', '1', null);

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
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'testuser', '1', 'http://pdr28szno.bkt.clouddn.com/7433920180820193855.png', '2018-06-23 20:04:54', '管理员', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '测试修改签名', '2');
INSERT INTO `user` VALUES ('2', 'testuser2', '2', '/upload/user.jpg', '2018-06-23 20:04:54', '测试用户2', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '测试签名2', '3');
INSERT INTO `user` VALUES ('3', 'testuser3', '1', '/upload/user.jpg', '2018-06-23 20:04:54', '测试用户3', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '测试签名3', '1');
INSERT INTO `user` VALUES ('32', 'testuser123', '2', '/upload/user.jpg', '2018-07-05 19:40:25', '测试注册功能', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '3');
INSERT INTO `user` VALUES ('33', 'testuser1234', '1', '/upload/user.jpg', '2018-07-05 19:43:59', '测试注册功能', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '4');
INSERT INTO `user` VALUES ('34', 'testuser1111', '1', '/upload/user.jpg', '2018-07-05 19:55:18', '测试注册功能', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '2');
INSERT INTO `user` VALUES ('35', '222222', '1', '/img/test/userUserHead.jpg', '2018-07-27 19:46:55', '管理员2', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '1');
INSERT INTO `user` VALUES ('36', 'admin', '1', '/img/test/userUserHead.jpg', '2018-08-31 12:49:42', 'admin', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '1');
INSERT INTO `user` VALUES ('37', '1538388150', '1', '/img/test/userUserHead.jpg', '2018-09-05 21:49:15', 'fsf', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '16');
INSERT INTO `user` VALUES ('38', 'aavva', '1', '/img/test/userUserHead.jpg', '2018-09-13 14:05:35', 'aavva', '$2a$10$7fg1JgdaBBW/GEjZ.SzZbu142CJtx..ti5Re1f7zSAmxAH7TPNnh2', '这个人很懒,没有设置签名...', '2');

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
) ENGINE=MyISAM AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

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
INSERT INTO `user_favorite` VALUES ('137', '64', '0', '0', '1');
