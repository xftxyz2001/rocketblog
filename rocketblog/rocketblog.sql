/*
 Navicat Premium Data Transfer

 Source Server         : ali_mysql
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : 8.130.81.23:3306
 Source Schema         : rocketblog

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 03/04/2023 14:09:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `blog_id` bigint NOT NULL AUTO_INCREMENT,
  `blog_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `blog_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userid` bigint NULL DEFAULT NULL,
  `blog_status` int NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`blog_id`) USING BTREE,
  INDEX `fk_user`(`userid` ASC) USING BTREE,
  CONSTRAINT `fk_user` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
-- 来自有道翻译
INSERT INTO `t_blog` VALUES (101, '角色：相沢祐一', '作为Key的初代主人公，他是最不会察言观色的男人。受害者主要有5人，但预计还会增加。虽然基本奉行息事宁人的原则，但也会因为不齿的言行引起麻烦。也是主人公们的领头人。', 101, 1, '2021-12-30 00:00:00', '2021-12-30 00:00:00', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123802/%E7%9B%B8%E6%B2%A2%E7%A5%90%E4%B8%80-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (102, '角色：月宮あゆ', '从下雪的城市来的烧鲷鱼的惯犯。据说通缉令已经下发到全国的鲷鱼烧店。口头禅是“呜呜”。', 102, 1, '2021-12-30 00:00:01', '2021-12-30 00:00:01', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123812/%E6%9C%88%E5%AE%AE%E3%81%82%E3%82%86-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (103, '角色：水瀬名雪', '相泽祐一的寄人篱下的少女。因为没有忘记小时候被人等的怨恨，在时隔7年的重逢时，在雪中放置了2个小时。当然，母亲做的危险果酱的存在，他也没有告诉过她。', 103, 1, '2021-12-30 00:00:02', '2021-12-30 00:00:02', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123819/%E6%B0%B4%E7%80%AC%E5%90%8D%E9%9B%AA-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (104, '角色：川澄 舞', '自称“讨伐魔物者”在夜晚的学校徘徊的少女。这是非法侵入。因为相泽祐一的诅咒，只能说“蜂蜜熊”和“嘭嘭狸”。喂了牛肉盖饭就老实了。', 104, 1, '2021-12-30 00:00:03', '2021-12-30 00:00:03', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123825/%E5%B7%9D%E6%BE%84-%E8%88%9E-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (105, '角色：美坂 栞', '下一个生日就要死了，开始倒计时的少女。姐姐也曾回避过她，但现在已经表面上和解了。喜欢的东西是冰淇淋。不管多冷也不停止吃东西。不…也许停不下来。', 105, 1, '2021-12-30 00:00:04', '2021-12-30 00:00:04', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123830/%E7%BE%8E%E5%9D%82-%E6%A0%9E.png');
INSERT INTO `t_blog` VALUES (106, '角色：沢渡真琴', '相泽祐一最大的受害者，也是对他最深情的少女。在水濑家有着作为“关东煮种子”被带来的过去。不管心情多么不好，只要给他看肉包子就能解决。受漫画的影响结婚愿望强烈。', 106, 1, '2021-12-30 00:00:05', '2021-12-30 00:00:05', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123834/%E6%B2%A2%E6%B8%A1%E7%9C%9F%E7%90%B4.png');
INSERT INTO `t_blog` VALUES (107, '角色：国崎往人', '居无定所自称是街头艺人的无业游民。虽然已经成人了，但是因为怀念什么出现在学园。经常因为缺钱而晕倒。特技是利用法术操纵人偶，以及变身成乌鸦。', 107, 1, '2021-12-30 00:00:06', '2021-12-30 00:00:06', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123839/%E5%9B%BD%E5%B4%8E%E5%BE%80%E4%BA%BA-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (108, '角色：神尾観鈴', '来自海边小镇的元祖少女。好像在努力交朋友，但是没有成果。休息日在海之家“神尾”帮母亲的忙，是个好孩子。但是，一旦露出破绽，就想冲过终点，是个让人头疼的孩子。', 108, 1, '2021-12-30 00:00:07', '2021-12-30 00:00:07', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123846/%E7%A5%9E%E5%B0%BE%E8%A6%B3%E9%88%B4-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (109, '角色：霧島佳乃', '戴在手腕上的黄色大头巾是她的标志。她相信姐姐说的“只要不摘掉头巾，长大成人就会使用魔法”。因为公开说了那个，周围的人也会用稍微温暖的眼神看。', 109, 1, '2021-12-30 00:00:08', '2021-12-30 00:00:08', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123853/%E9%9C%A7%E5%B3%B6%E4%BD%B3%E4%B9%83-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (110, '角色：遠野美凪', '送米券的任性身材。温文尔雅的说话，会让听者沉睡…不，让心情平静下来。尽管她成绩优秀，容貌端庄，但因为总是和比自己小的满露在一起，实际上缺乏同龄的朋友。', 110, 1, '2021-12-30 00:00:09', '2021-12-30 00:00:09', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123859/%E9%81%A0%E9%87%8E%E7%BE%8E%E5%87%AA.png');
INSERT INTO `t_blog` VALUES (111, '角色：岡崎朋也', '虽然被称为不良少年，却能窥见诚实的素颜，凭借这种反差俘获了众多女主人公的心。通称哈莱姆国王。他的出息有时甚至会波及他的好友春原阳平。他本人比任何主人公都专一，还结过婚。', 111, 1, '2021-12-30 00:00:10', '2021-12-30 00:00:10', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24175506/f0c4a6644a2e84507eb1814e9cbf9248.png');
INSERT INTO `t_blog` VALUES (112, '角色：古河 渚', '戏剧部所属的独唱女演员。第一次登台，第一次主演也是独唱。虽然觉得太过无理取闹，但绝对不会说出口。最近，我很在意团子大家族的馅儿是豆沙馅还是豆沙馅。有时会在学校楼下的坡道上突然大叫。', 112, 1, '2021-12-30 00:00:11', '2021-12-30 00:00:11', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123914/%E5%8F%A4%E6%B2%B3-%E6%B8%9A-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (113, '角色：藤林 杏', '拥有无与伦比的投掷能力的Key初代双胞胎角色的姐姐。幸运地点是体育仓库。板着脸。为了确认对方的真意，剪掉头发代替双胞胎的妹妹，有策略家的一面。', 113, 1, '2021-12-30 00:00:12', '2021-12-30 00:00:12', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123923/%E8%97%A4%E6%9E%97-%E6%A4%8B-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (114, '角色：藤林 椋', '进行猜中率100%的占卜的Key初代双胞胎角色的妹妹。她暗自炫耀自己比姐姐有胸。将来的梦想是当护士。即使不被主人公攻略也能靠自己的力量抓住新的幸福的坚强的少女。不是轻佻。', 114, 1, '2021-12-30 00:00:13', '2021-12-30 00:00:13', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123939/%E6%98%A5%E5%8E%9F%E9%99%BD%E5%B9%B3-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (115, '角色：坂上智代', '为了保护樱花行道树，借助各种各样的人的力量成为了学生会长的少女。由于他的声望，在学校的创立者祭上，他对春原阳平创下了连续踢64hit的记录。能引出冈崎朋也的变态性的才能的持有者。', 115, 1, '2021-12-30 00:00:14', '2021-12-30 00:00:14', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123929/%E5%9D%82%E4%B8%8A%E6%99%BA%E4%BB%A3.png');
INSERT INTO `t_blog` VALUES (116, '角色：伊吹風子', '无差别地分发木雕人手的少女。如果不被误认为是星星的话，就会觉得不够。由于冈崎朋也的巧妙调教，变得可以用鼻子喝果汁了。即使长大成人，言行也不会改变。', 116, 1, '2021-12-30 00:00:15', '2021-12-30 00:00:15', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123935/%E4%BC%8A%E5%90%B9%E9%A2%A8%E5%AD%90.png');
INSERT INTO `t_blog` VALUES (117, '角色：一ノ瀬ことみ', '擅长小提琴外扩攻击的天灾……天才少女。朋友们为了加强个性，向我传授了吐槽和搞笑的方法，但基本上都没有成功。虽然觉得长年累月送来的熊玩偶的味道有些奇怪，但在大家面前，还是把话咽了回去。', 117, 1, '2021-12-30 00:00:16', '2021-12-30 00:00:16', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123932/%E4%B8%80%E3%83%8E%E7%80%AC%E3%81%93%E3%81%A8%E3%81%BF.png');
INSERT INTO `t_blog` VALUES (118, '角色：春原陽平', '冈崎朋也的(本人)好友。原足球部所属的回家部。如果放下自尊心，或许就能得到藤林杏。虽然平时很散漫，但骨子里很诚实……但还是很邋遢。还有座便器套。', 118, 1, '2021-12-30 00:00:17', '2021-12-30 00:00:17', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123942/%E6%98%A5%E5%8E%9F%E9%99%BD%E5%B9%B3.png');
INSERT INTO `t_blog` VALUES (119, '角色：音無結弦', '遇到的危险人物，让我把心刺进脑袋里。也许是这种鲁莽被认可为勇气，突然死去的世界战线的干部。在战线内构筑“好人”的位置。看起来是冷静又通情达理的角色，只是不擅长交际而已。', 119, 1, '2021-12-30 00:00:18', '2021-12-30 00:00:18', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101539/83a6288beb333dd9e698774a5f3c201b.png');
INSERT INTO `t_blog` VALUES (120, '角色：仲村ゆり', '由于太过憎恨神而结成的“死去的世界战线”的首领。被公认为钻牛角尖，最后的天真。凡事都有从形式切入的习惯。主要物品是帽子和眼镜。她特别喜欢用手肘撑在桌子上遮住嘴的姿势。', 120, 1, '2021-12-30 00:00:19', '2021-12-30 00:00:19', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101712/e92168f0b30574ebd39be004ed9f6532.png');
INSERT INTO `t_blog` VALUES (121, '角色：天使', '吃麻婆的时候是无害的。保护技能应该是自卫的手段，但其杀伤力之高让人感到黑暗。另外，各技能名称中都渗透着无法抑制的帕托斯。我认为生羽毛的建议是天启。', 121, 1, '2021-12-30 00:00:20', '2021-12-30 00:00:20', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101833/57de04aa59fc17c0480c6a7444972245.png');
INSERT INTO `t_blog` VALUES (122, '角色：日向秀樹', '是呀。原本是百合的得力助手，但因音无的登场而被剥夺了地位。但是对音无的距离的快速接近和照顾的好，被怀疑怀有不寻常的感情。本人对此予以否认。', 122, 1, '2021-12-30 00:00:21', '2021-12-30 00:00:21', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101952/aff2524e0d3925c35e42027b65652587.png');
INSERT INTO `t_blog` VALUES (123, '角色：直井文人', '忠犬不愿离开音无身边。虽然连视线都不一致，但现在也变成了一种快乐。曾经成为死后的学园的左右神，不过是遥远的记忆。一有机会就缩小与音无的物理距离。催眠术很方便。', 123, 1, '2021-12-30 00:00:22', '2021-12-30 00:00:22', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13102120/1eb3b16bd7c0d27d6ec8c0ffbbba1000.png');
INSERT INTO `t_blog` VALUES (124, '角色：ユイ', '对句尾的“哟哟”反应过度。他无法掩饰对向阳的思念，选择了攻击这一恶毒手段。一个人的时候会脸红后悔的类型。', 124, 1, '2021-12-30 00:00:23', '2021-12-30 00:00:23', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13102227/329df80c7aa719861f18c2c7a6ab861c.png');

-- ----------------------------
-- Table structure for t_bookmark
-- ----------------------------
DROP TABLE IF EXISTS `t_bookmark`;
CREATE TABLE `t_bookmark`  (
  `bookmark_id` bigint NOT NULL AUTO_INCREMENT,
  `userid` bigint NOT NULL,
  `blog_id` bigint NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`bookmark_id`) USING BTREE,
  INDEX `fk_blog3`(`blog_id` ASC) USING BTREE,
  INDEX `fk_user4`(`userid` ASC) USING BTREE,
  CONSTRAINT `fk_blog3` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`blog_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user4` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_bookmark
-- ----------------------------
INSERT INTO `t_bookmark` VALUES (1, 102, 101, '2021-12-31 00:02:01');
INSERT INTO `t_bookmark` VALUES (2, 103, 101, '2021-12-31 00:02:02');
INSERT INTO `t_bookmark` VALUES (3, 104, 101, '2021-12-31 00:02:03');
INSERT INTO `t_bookmark` VALUES (4, 105, 101, '2021-12-31 00:02:04');
INSERT INTO `t_bookmark` VALUES (5, 106, 101, '2021-12-31 00:02:05');
INSERT INTO `t_bookmark` VALUES (6, 108, 101, '2021-12-31 00:02:06');
INSERT INTO `t_bookmark` VALUES (7, 109, 101, '2021-12-31 00:02:07');
INSERT INTO `t_bookmark` VALUES (8, 110, 101, '2021-12-31 00:02:08');
INSERT INTO `t_bookmark` VALUES (9, 112, 101, '2021-12-31 00:02:09');
INSERT INTO `t_bookmark` VALUES (10, 113, 101, '2021-12-31 00:02:10');
INSERT INTO `t_bookmark` VALUES (11, 114, 101, '2021-12-31 00:02:11');
INSERT INTO `t_bookmark` VALUES (12, 115, 101, '2021-12-31 00:02:12');
INSERT INTO `t_bookmark` VALUES (13, 116, 101, '2021-12-31 00:02:13');
INSERT INTO `t_bookmark` VALUES (14, 117, 101, '2021-12-31 00:02:14');
INSERT INTO `t_bookmark` VALUES (15, 120, 101, '2021-12-31 00:02:15');
INSERT INTO `t_bookmark` VALUES (16, 121, 101, '2021-12-31 00:02:16');
INSERT INTO `t_bookmark` VALUES (17, 124, 101, '2021-12-31 00:02:17');
INSERT INTO `t_bookmark` VALUES (18, 102, 107, '2021-12-31 00:02:18');
INSERT INTO `t_bookmark` VALUES (19, 103, 107, '2021-12-31 00:02:19');
INSERT INTO `t_bookmark` VALUES (20, 104, 107, '2021-12-31 00:02:20');
INSERT INTO `t_bookmark` VALUES (21, 105, 107, '2021-12-31 00:02:21');
INSERT INTO `t_bookmark` VALUES (22, 106, 107, '2021-12-31 00:02:22');
INSERT INTO `t_bookmark` VALUES (23, 108, 107, '2021-12-31 00:02:23');
INSERT INTO `t_bookmark` VALUES (24, 109, 107, '2021-12-31 00:02:24');
INSERT INTO `t_bookmark` VALUES (25, 110, 107, '2021-12-31 00:02:25');
INSERT INTO `t_bookmark` VALUES (26, 112, 107, '2021-12-31 00:02:26');
INSERT INTO `t_bookmark` VALUES (27, 113, 107, '2021-12-31 00:02:27');
INSERT INTO `t_bookmark` VALUES (28, 114, 107, '2021-12-31 00:02:28');
INSERT INTO `t_bookmark` VALUES (29, 115, 107, '2021-12-31 00:02:29');
INSERT INTO `t_bookmark` VALUES (30, 116, 107, '2021-12-31 00:02:30');
INSERT INTO `t_bookmark` VALUES (31, 117, 107, '2021-12-31 00:02:31');
INSERT INTO `t_bookmark` VALUES (32, 120, 107, '2021-12-31 00:02:32');
INSERT INTO `t_bookmark` VALUES (33, 121, 107, '2021-12-31 00:02:33');
INSERT INTO `t_bookmark` VALUES (34, 124, 107, '2021-12-31 00:02:34');
INSERT INTO `t_bookmark` VALUES (35, 102, 111, '2021-12-31 00:02:35');
INSERT INTO `t_bookmark` VALUES (36, 103, 111, '2021-12-31 00:02:36');
INSERT INTO `t_bookmark` VALUES (37, 104, 111, '2021-12-31 00:02:37');
INSERT INTO `t_bookmark` VALUES (38, 105, 111, '2021-12-31 00:02:38');
INSERT INTO `t_bookmark` VALUES (39, 106, 111, '2021-12-31 00:02:39');
INSERT INTO `t_bookmark` VALUES (40, 108, 111, '2021-12-31 00:02:40');
INSERT INTO `t_bookmark` VALUES (41, 109, 111, '2021-12-31 00:02:41');
INSERT INTO `t_bookmark` VALUES (42, 110, 111, '2021-12-31 00:02:42');
INSERT INTO `t_bookmark` VALUES (43, 112, 111, '2021-12-31 00:02:43');
INSERT INTO `t_bookmark` VALUES (44, 113, 111, '2021-12-31 00:02:44');
INSERT INTO `t_bookmark` VALUES (45, 114, 111, '2021-12-31 00:02:45');
INSERT INTO `t_bookmark` VALUES (46, 115, 111, '2021-12-31 00:02:46');
INSERT INTO `t_bookmark` VALUES (47, 116, 111, '2021-12-31 00:02:47');
INSERT INTO `t_bookmark` VALUES (48, 117, 111, '2021-12-31 00:02:48');
INSERT INTO `t_bookmark` VALUES (49, 120, 111, '2021-12-31 00:02:49');
INSERT INTO `t_bookmark` VALUES (50, 121, 111, '2021-12-31 00:02:50');
INSERT INTO `t_bookmark` VALUES (51, 124, 111, '2021-12-31 00:02:51');
INSERT INTO `t_bookmark` VALUES (52, 102, 118, '2021-12-31 00:02:52');
INSERT INTO `t_bookmark` VALUES (53, 103, 118, '2021-12-31 00:02:53');
INSERT INTO `t_bookmark` VALUES (54, 104, 118, '2021-12-31 00:02:54');
INSERT INTO `t_bookmark` VALUES (55, 105, 118, '2021-12-31 00:02:55');
INSERT INTO `t_bookmark` VALUES (56, 106, 118, '2021-12-31 00:02:56');
INSERT INTO `t_bookmark` VALUES (57, 108, 118, '2021-12-31 00:02:57');
INSERT INTO `t_bookmark` VALUES (58, 109, 118, '2021-12-31 00:02:58');
INSERT INTO `t_bookmark` VALUES (59, 110, 118, '2021-12-31 00:02:59');
INSERT INTO `t_bookmark` VALUES (60, 112, 118, '2021-12-31 00:03:00');
INSERT INTO `t_bookmark` VALUES (61, 113, 118, '2021-12-31 00:03:01');
INSERT INTO `t_bookmark` VALUES (62, 114, 118, '2021-12-31 00:03:02');
INSERT INTO `t_bookmark` VALUES (63, 115, 118, '2021-12-31 00:03:03');
INSERT INTO `t_bookmark` VALUES (64, 116, 118, '2021-12-31 00:03:04');
INSERT INTO `t_bookmark` VALUES (65, 117, 118, '2021-12-31 00:03:05');
INSERT INTO `t_bookmark` VALUES (66, 120, 118, '2021-12-31 00:03:06');
INSERT INTO `t_bookmark` VALUES (67, 121, 118, '2021-12-31 00:03:07');
INSERT INTO `t_bookmark` VALUES (68, 124, 118, '2021-12-31 00:03:08');
INSERT INTO `t_bookmark` VALUES (69, 102, 119, '2021-12-31 00:03:09');
INSERT INTO `t_bookmark` VALUES (70, 103, 119, '2021-12-31 00:03:10');
INSERT INTO `t_bookmark` VALUES (71, 104, 119, '2021-12-31 00:03:11');
INSERT INTO `t_bookmark` VALUES (72, 105, 119, '2021-12-31 00:03:12');
INSERT INTO `t_bookmark` VALUES (73, 106, 119, '2021-12-31 00:03:13');
INSERT INTO `t_bookmark` VALUES (74, 108, 119, '2021-12-31 00:03:14');
INSERT INTO `t_bookmark` VALUES (75, 109, 119, '2021-12-31 00:03:15');
INSERT INTO `t_bookmark` VALUES (76, 110, 119, '2021-12-31 00:03:16');
INSERT INTO `t_bookmark` VALUES (77, 112, 119, '2021-12-31 00:03:17');
INSERT INTO `t_bookmark` VALUES (78, 113, 119, '2021-12-31 00:03:18');
INSERT INTO `t_bookmark` VALUES (79, 114, 119, '2021-12-31 00:03:19');
INSERT INTO `t_bookmark` VALUES (80, 115, 119, '2021-12-31 00:03:20');
INSERT INTO `t_bookmark` VALUES (81, 116, 119, '2021-12-31 00:03:21');
INSERT INTO `t_bookmark` VALUES (82, 117, 119, '2021-12-31 00:03:22');
INSERT INTO `t_bookmark` VALUES (83, 120, 119, '2021-12-31 00:03:23');
INSERT INTO `t_bookmark` VALUES (84, 121, 119, '2021-12-31 00:03:24');
INSERT INTO `t_bookmark` VALUES (85, 124, 119, '2021-12-31 00:03:25');
INSERT INTO `t_bookmark` VALUES (86, 102, 122, '2021-12-31 00:03:26');
INSERT INTO `t_bookmark` VALUES (87, 103, 122, '2021-12-31 00:03:27');
INSERT INTO `t_bookmark` VALUES (88, 104, 122, '2021-12-31 00:03:28');
INSERT INTO `t_bookmark` VALUES (89, 105, 122, '2021-12-31 00:03:29');
INSERT INTO `t_bookmark` VALUES (90, 106, 122, '2021-12-31 00:03:30');
INSERT INTO `t_bookmark` VALUES (91, 108, 122, '2021-12-31 00:03:31');
INSERT INTO `t_bookmark` VALUES (92, 109, 122, '2021-12-31 00:03:32');
INSERT INTO `t_bookmark` VALUES (93, 110, 122, '2021-12-31 00:03:33');
INSERT INTO `t_bookmark` VALUES (94, 112, 122, '2021-12-31 00:03:34');
INSERT INTO `t_bookmark` VALUES (95, 113, 122, '2021-12-31 00:03:35');
INSERT INTO `t_bookmark` VALUES (96, 114, 122, '2021-12-31 00:03:36');
INSERT INTO `t_bookmark` VALUES (97, 115, 122, '2021-12-31 00:03:37');
INSERT INTO `t_bookmark` VALUES (98, 116, 122, '2021-12-31 00:03:38');
INSERT INTO `t_bookmark` VALUES (99, 117, 122, '2021-12-31 00:03:39');
INSERT INTO `t_bookmark` VALUES (100, 120, 122, '2021-12-31 00:03:40');
INSERT INTO `t_bookmark` VALUES (101, 121, 122, '2021-12-31 00:03:41');
INSERT INTO `t_bookmark` VALUES (102, 124, 122, '2021-12-31 00:03:42');
INSERT INTO `t_bookmark` VALUES (103, 102, 123, '2021-12-31 00:03:43');
INSERT INTO `t_bookmark` VALUES (104, 103, 123, '2021-12-31 00:03:44');
INSERT INTO `t_bookmark` VALUES (105, 104, 123, '2021-12-31 00:03:45');
INSERT INTO `t_bookmark` VALUES (106, 105, 123, '2021-12-31 00:03:46');
INSERT INTO `t_bookmark` VALUES (107, 106, 123, '2021-12-31 00:03:47');
INSERT INTO `t_bookmark` VALUES (108, 108, 123, '2021-12-31 00:03:48');
INSERT INTO `t_bookmark` VALUES (109, 109, 123, '2021-12-31 00:03:49');
INSERT INTO `t_bookmark` VALUES (110, 110, 123, '2021-12-31 00:03:50');
INSERT INTO `t_bookmark` VALUES (111, 112, 123, '2021-12-31 00:03:51');
INSERT INTO `t_bookmark` VALUES (112, 113, 123, '2021-12-31 00:03:52');
INSERT INTO `t_bookmark` VALUES (113, 114, 123, '2021-12-31 00:03:53');
INSERT INTO `t_bookmark` VALUES (114, 115, 123, '2021-12-31 00:03:54');
INSERT INTO `t_bookmark` VALUES (115, 116, 123, '2021-12-31 00:03:55');
INSERT INTO `t_bookmark` VALUES (116, 117, 123, '2021-12-31 00:03:56');
INSERT INTO `t_bookmark` VALUES (117, 120, 123, '2021-12-31 00:03:57');
INSERT INTO `t_bookmark` VALUES (118, 121, 123, '2021-12-31 00:03:58');
INSERT INTO `t_bookmark` VALUES (119, 124, 123, '2021-12-31 00:03:59');

-- ----------------------------
-- Table structure for t_chat
-- ----------------------------
DROP TABLE IF EXISTS `t_chat`;
CREATE TABLE `t_chat`  (
  `chat_id` bigint NOT NULL AUTO_INCREMENT,
  `userid_from` bigint NOT NULL,
  `userid_to` bigint NOT NULL,
  `message_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `read` int NOT NULL,
  PRIMARY KEY (`chat_id`) USING BTREE,
  INDEX `fk_from`(`userid_from` ASC) USING BTREE,
  INDEX `fk_to`(`userid_to` ASC) USING BTREE,
  CONSTRAINT `fk_from` FOREIGN KEY (`userid_from`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_to` FOREIGN KEY (`userid_to`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_chat
-- ----------------------------
INSERT INTO `t_chat` VALUES (1, 100, 101, '相沢祐一，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (2, 100, 102, '月宮あゆ，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (3, 100, 103, '水瀬名雪，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (4, 100, 104, '川澄 舞，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (5, 100, 105, '美坂 栞，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (6, 100, 106, '沢渡真琴，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (7, 100, 107, '国崎往人，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (8, 100, 108, '神尾観鈴，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (9, 100, 109, '霧島佳乃，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (10, 100, 110, '遠野美凪，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (11, 100, 111, '岡崎朋也，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (12, 100, 112, '古河 渚，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (13, 100, 113, '藤林 杏，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (14, 100, 114, '藤林 椋，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (15, 100, 115, '坂上智代，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (16, 100, 116, '伊吹風子，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (17, 100, 117, '一ノ瀬ことみ，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (18, 100, 118, '春原陽平，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (19, 100, 119, '音無結弦，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (20, 100, 120, '仲村ゆり，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (21, 100, 121, '天使，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (22, 100, 122, '日向秀樹，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (23, 100, 123, '直井文人，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);
INSERT INTO `t_chat` VALUES (24, 100, 124, 'ユイ，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00', 0);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `comment_id` bigint NOT NULL AUTO_INCREMENT,
  `comment_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `blog_id` bigint NOT NULL,
  `createtime` datetime NOT NULL,
  `userid` bigint NOT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `fk_blog`(`blog_id` ASC) USING BTREE,
  INDEX `fk_user2`(`userid` ASC) USING BTREE,
  CONSTRAINT `fk_blog` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`blog_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user2` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 213 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, '初次见面，我是来自AIR的国崎往人，请多关照。', 101, '2021-12-31 00:04:02', 107);
INSERT INTO `t_comment` VALUES (2, '初次见面，我是来自AIR的神尾観鈴，请多关照。', 101, '2021-12-31 00:04:03', 108);
INSERT INTO `t_comment` VALUES (3, '初次见面，我是来自AIR的霧島佳乃，请多关照。', 101, '2021-12-31 00:04:04', 109);
INSERT INTO `t_comment` VALUES (4, '初次见面，我是来自AIR的遠野美凪，请多关照。', 101, '2021-12-31 00:04:05', 110);
INSERT INTO `t_comment` VALUES (5, '初次见面，我是来自AIR的国崎往人，请多关照。', 102, '2021-12-31 00:04:06', 107);
INSERT INTO `t_comment` VALUES (6, '初次见面，我是来自AIR的神尾観鈴，请多关照。', 102, '2021-12-31 00:04:07', 108);
INSERT INTO `t_comment` VALUES (7, '初次见面，我是来自AIR的霧島佳乃，请多关照。', 102, '2021-12-31 00:04:08', 109);
INSERT INTO `t_comment` VALUES (8, '初次见面，我是来自AIR的遠野美凪，请多关照。', 102, '2021-12-31 00:04:09', 110);
INSERT INTO `t_comment` VALUES (9, '初次见面，我是来自AIR的国崎往人，请多关照。', 103, '2021-12-31 00:04:10', 107);
INSERT INTO `t_comment` VALUES (10, '初次见面，我是来自AIR的神尾観鈴，请多关照。', 103, '2021-12-31 00:04:11', 108);
INSERT INTO `t_comment` VALUES (11, '初次见面，我是来自AIR的霧島佳乃，请多关照。', 103, '2021-12-31 00:04:12', 109);
INSERT INTO `t_comment` VALUES (12, '初次见面，我是来自AIR的遠野美凪，请多关照。', 103, '2021-12-31 00:04:13', 110);
INSERT INTO `t_comment` VALUES (13, '初次见面，我是来自AIR的国崎往人，请多关照。', 104, '2021-12-31 00:04:14', 107);
INSERT INTO `t_comment` VALUES (14, '初次见面，我是来自AIR的神尾観鈴，请多关照。', 104, '2021-12-31 00:04:15', 108);
INSERT INTO `t_comment` VALUES (15, '初次见面，我是来自AIR的霧島佳乃，请多关照。', 104, '2021-12-31 00:04:16', 109);
INSERT INTO `t_comment` VALUES (16, '初次见面，我是来自AIR的遠野美凪，请多关照。', 104, '2021-12-31 00:04:17', 110);
INSERT INTO `t_comment` VALUES (17, '初次见面，我是来自AIR的国崎往人，请多关照。', 105, '2021-12-31 00:04:18', 107);
INSERT INTO `t_comment` VALUES (18, '初次见面，我是来自AIR的神尾観鈴，请多关照。', 105, '2021-12-31 00:04:19', 108);
INSERT INTO `t_comment` VALUES (19, '初次见面，我是来自AIR的霧島佳乃，请多关照。', 105, '2021-12-31 00:04:20', 109);
INSERT INTO `t_comment` VALUES (20, '初次见面，我是来自AIR的遠野美凪，请多关照。', 105, '2021-12-31 00:04:21', 110);
INSERT INTO `t_comment` VALUES (21, '初次见面，我是来自AIR的国崎往人，请多关照。', 106, '2021-12-31 00:04:22', 107);
INSERT INTO `t_comment` VALUES (22, '初次见面，我是来自AIR的神尾観鈴，请多关照。', 106, '2021-12-31 00:04:23', 108);
INSERT INTO `t_comment` VALUES (23, '初次见面，我是来自AIR的霧島佳乃，请多关照。', 106, '2021-12-31 00:04:24', 109);
INSERT INTO `t_comment` VALUES (24, '初次见面，我是来自AIR的遠野美凪，请多关照。', 106, '2021-12-31 00:04:25', 110);
INSERT INTO `t_comment` VALUES (25, '初次见面，我是来自CLANNAD的岡崎朋也，请多关照。', 101, '2021-12-31 00:04:26', 111);
INSERT INTO `t_comment` VALUES (26, '初次见面，我是来自CLANNAD的古河 渚，请多关照。', 101, '2021-12-31 00:04:27', 112);
INSERT INTO `t_comment` VALUES (27, '初次见面，我是来自CLANNAD的藤林 杏，请多关照。', 101, '2021-12-31 00:04:28', 113);
INSERT INTO `t_comment` VALUES (28, '初次见面，我是来自CLANNAD的藤林 椋，请多关照。', 101, '2021-12-31 00:04:29', 114);
INSERT INTO `t_comment` VALUES (29, '初次见面，我是来自CLANNAD的坂上智代，请多关照。', 101, '2021-12-31 00:04:30', 115);
INSERT INTO `t_comment` VALUES (30, '初次见面，我是来自CLANNAD的伊吹風子，请多关照。', 101, '2021-12-31 00:04:31', 116);
INSERT INTO `t_comment` VALUES (31, '初次见面，我是来自CLANNAD的一ノ瀬ことみ，请多关照。', 101, '2021-12-31 00:04:32', 117);
INSERT INTO `t_comment` VALUES (32, '初次见面，我是来自CLANNAD的春原陽平，请多关照。', 101, '2021-12-31 00:04:33', 118);
INSERT INTO `t_comment` VALUES (33, '初次见面，我是来自CLANNAD的岡崎朋也，请多关照。', 102, '2021-12-31 00:04:34', 111);
INSERT INTO `t_comment` VALUES (34, '初次见面，我是来自CLANNAD的古河 渚，请多关照。', 102, '2021-12-31 00:04:35', 112);
INSERT INTO `t_comment` VALUES (35, '初次见面，我是来自CLANNAD的藤林 杏，请多关照。', 102, '2021-12-31 00:04:36', 113);
INSERT INTO `t_comment` VALUES (36, '初次见面，我是来自CLANNAD的藤林 椋，请多关照。', 102, '2021-12-31 00:04:37', 114);
INSERT INTO `t_comment` VALUES (37, '初次见面，我是来自CLANNAD的坂上智代，请多关照。', 102, '2021-12-31 00:04:38', 115);
INSERT INTO `t_comment` VALUES (38, '初次见面，我是来自CLANNAD的伊吹風子，请多关照。', 102, '2021-12-31 00:04:39', 116);
INSERT INTO `t_comment` VALUES (39, '初次见面，我是来自CLANNAD的一ノ瀬ことみ，请多关照。', 102, '2021-12-31 00:04:40', 117);
INSERT INTO `t_comment` VALUES (40, '初次见面，我是来自CLANNAD的春原陽平，请多关照。', 102, '2021-12-31 00:04:41', 118);
INSERT INTO `t_comment` VALUES (41, '初次见面，我是来自CLANNAD的岡崎朋也，请多关照。', 103, '2021-12-31 00:04:42', 111);
INSERT INTO `t_comment` VALUES (42, '初次见面，我是来自CLANNAD的古河 渚，请多关照。', 103, '2021-12-31 00:04:43', 112);
INSERT INTO `t_comment` VALUES (43, '初次见面，我是来自CLANNAD的藤林 杏，请多关照。', 103, '2021-12-31 00:04:44', 113);
INSERT INTO `t_comment` VALUES (44, '初次见面，我是来自CLANNAD的藤林 椋，请多关照。', 103, '2021-12-31 00:04:45', 114);
INSERT INTO `t_comment` VALUES (45, '初次见面，我是来自CLANNAD的坂上智代，请多关照。', 103, '2021-12-31 00:04:46', 115);
INSERT INTO `t_comment` VALUES (46, '初次见面，我是来自CLANNAD的伊吹風子，请多关照。', 103, '2021-12-31 00:04:47', 116);
INSERT INTO `t_comment` VALUES (47, '初次见面，我是来自CLANNAD的一ノ瀬ことみ，请多关照。', 103, '2021-12-31 00:04:48', 117);
INSERT INTO `t_comment` VALUES (48, '初次见面，我是来自CLANNAD的春原陽平，请多关照。', 103, '2021-12-31 00:04:49', 118);
INSERT INTO `t_comment` VALUES (49, '初次见面，我是来自CLANNAD的岡崎朋也，请多关照。', 104, '2021-12-31 00:04:50', 111);
INSERT INTO `t_comment` VALUES (50, '初次见面，我是来自CLANNAD的古河 渚，请多关照。', 104, '2021-12-31 00:04:51', 112);
INSERT INTO `t_comment` VALUES (51, '初次见面，我是来自CLANNAD的藤林 杏，请多关照。', 104, '2021-12-31 00:04:52', 113);
INSERT INTO `t_comment` VALUES (52, '初次见面，我是来自CLANNAD的藤林 椋，请多关照。', 104, '2021-12-31 00:04:53', 114);
INSERT INTO `t_comment` VALUES (53, '初次见面，我是来自CLANNAD的坂上智代，请多关照。', 104, '2021-12-31 00:04:54', 115);
INSERT INTO `t_comment` VALUES (54, '初次见面，我是来自CLANNAD的伊吹風子，请多关照。', 104, '2021-12-31 00:04:55', 116);
INSERT INTO `t_comment` VALUES (55, '初次见面，我是来自CLANNAD的一ノ瀬ことみ，请多关照。', 104, '2021-12-31 00:04:56', 117);
INSERT INTO `t_comment` VALUES (56, '初次见面，我是来自CLANNAD的春原陽平，请多关照。', 104, '2021-12-31 00:04:57', 118);
INSERT INTO `t_comment` VALUES (57, '初次见面，我是来自CLANNAD的岡崎朋也，请多关照。', 105, '2021-12-31 00:04:58', 111);
INSERT INTO `t_comment` VALUES (58, '初次见面，我是来自CLANNAD的古河 渚，请多关照。', 105, '2021-12-31 00:04:59', 112);
INSERT INTO `t_comment` VALUES (59, '初次见面，我是来自CLANNAD的藤林 杏，请多关照。', 105, '2021-12-31 00:05:00', 113);
INSERT INTO `t_comment` VALUES (60, '初次见面，我是来自CLANNAD的藤林 椋，请多关照。', 105, '2021-12-31 00:05:01', 114);
INSERT INTO `t_comment` VALUES (61, '初次见面，我是来自CLANNAD的坂上智代，请多关照。', 105, '2021-12-31 00:05:02', 115);
INSERT INTO `t_comment` VALUES (62, '初次见面，我是来自CLANNAD的伊吹風子，请多关照。', 105, '2021-12-31 00:05:03', 116);
INSERT INTO `t_comment` VALUES (63, '初次见面，我是来自CLANNAD的一ノ瀬ことみ，请多关照。', 105, '2021-12-31 00:05:04', 117);
INSERT INTO `t_comment` VALUES (64, '初次见面，我是来自CLANNAD的春原陽平，请多关照。', 105, '2021-12-31 00:05:05', 118);
INSERT INTO `t_comment` VALUES (65, '初次见面，我是来自CLANNAD的岡崎朋也，请多关照。', 106, '2021-12-31 00:05:06', 111);
INSERT INTO `t_comment` VALUES (66, '初次见面，我是来自CLANNAD的古河 渚，请多关照。', 106, '2021-12-31 00:05:07', 112);
INSERT INTO `t_comment` VALUES (67, '初次见面，我是来自CLANNAD的藤林 杏，请多关照。', 106, '2021-12-31 00:05:08', 113);
INSERT INTO `t_comment` VALUES (68, '初次见面，我是来自CLANNAD的藤林 椋，请多关照。', 106, '2021-12-31 00:05:09', 114);
INSERT INTO `t_comment` VALUES (69, '初次见面，我是来自CLANNAD的坂上智代，请多关照。', 106, '2021-12-31 00:05:10', 115);
INSERT INTO `t_comment` VALUES (70, '初次见面，我是来自CLANNAD的伊吹風子，请多关照。', 106, '2021-12-31 00:05:11', 116);
INSERT INTO `t_comment` VALUES (71, '初次见面，我是来自CLANNAD的一ノ瀬ことみ，请多关照。', 106, '2021-12-31 00:05:12', 117);
INSERT INTO `t_comment` VALUES (72, '初次见面，我是来自CLANNAD的春原陽平，请多关照。', 106, '2021-12-31 00:05:13', 118);
INSERT INTO `t_comment` VALUES (73, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 101, '2021-12-31 00:05:14', 119);
INSERT INTO `t_comment` VALUES (74, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 101, '2021-12-31 00:05:15', 120);
INSERT INTO `t_comment` VALUES (75, '初次见面，我是来自Angel Beats!的天使，请多关照。', 101, '2021-12-31 00:05:16', 121);
INSERT INTO `t_comment` VALUES (76, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 101, '2021-12-31 00:05:17', 122);
INSERT INTO `t_comment` VALUES (77, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 101, '2021-12-31 00:05:18', 123);
INSERT INTO `t_comment` VALUES (78, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 101, '2021-12-31 00:05:19', 124);
INSERT INTO `t_comment` VALUES (79, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 102, '2021-12-31 00:05:20', 119);
INSERT INTO `t_comment` VALUES (80, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 102, '2021-12-31 00:05:21', 120);
INSERT INTO `t_comment` VALUES (81, '初次见面，我是来自Angel Beats!的天使，请多关照。', 102, '2021-12-31 00:05:22', 121);
INSERT INTO `t_comment` VALUES (82, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 102, '2021-12-31 00:05:23', 122);
INSERT INTO `t_comment` VALUES (83, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 102, '2021-12-31 00:05:24', 123);
INSERT INTO `t_comment` VALUES (84, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 102, '2021-12-31 00:05:25', 124);
INSERT INTO `t_comment` VALUES (85, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 103, '2021-12-31 00:05:26', 119);
INSERT INTO `t_comment` VALUES (86, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 103, '2021-12-31 00:05:27', 120);
INSERT INTO `t_comment` VALUES (87, '初次见面，我是来自Angel Beats!的天使，请多关照。', 103, '2021-12-31 00:05:28', 121);
INSERT INTO `t_comment` VALUES (88, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 103, '2021-12-31 00:05:29', 122);
INSERT INTO `t_comment` VALUES (89, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 103, '2021-12-31 00:05:30', 123);
INSERT INTO `t_comment` VALUES (90, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 103, '2021-12-31 00:05:31', 124);
INSERT INTO `t_comment` VALUES (91, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 104, '2021-12-31 00:05:32', 119);
INSERT INTO `t_comment` VALUES (92, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 104, '2021-12-31 00:05:33', 120);
INSERT INTO `t_comment` VALUES (93, '初次见面，我是来自Angel Beats!的天使，请多关照。', 104, '2021-12-31 00:05:34', 121);
INSERT INTO `t_comment` VALUES (94, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 104, '2021-12-31 00:05:35', 122);
INSERT INTO `t_comment` VALUES (95, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 104, '2021-12-31 00:05:36', 123);
INSERT INTO `t_comment` VALUES (96, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 104, '2021-12-31 00:05:37', 124);
INSERT INTO `t_comment` VALUES (97, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 105, '2021-12-31 00:05:38', 119);
INSERT INTO `t_comment` VALUES (98, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 105, '2021-12-31 00:05:39', 120);
INSERT INTO `t_comment` VALUES (99, '初次见面，我是来自Angel Beats!的天使，请多关照。', 105, '2021-12-31 00:05:40', 121);
INSERT INTO `t_comment` VALUES (100, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 105, '2021-12-31 00:05:41', 122);
INSERT INTO `t_comment` VALUES (101, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 105, '2021-12-31 00:05:42', 123);
INSERT INTO `t_comment` VALUES (102, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 105, '2021-12-31 00:05:43', 124);
INSERT INTO `t_comment` VALUES (103, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 106, '2021-12-31 00:05:44', 119);
INSERT INTO `t_comment` VALUES (104, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 106, '2021-12-31 00:05:45', 120);
INSERT INTO `t_comment` VALUES (105, '初次见面，我是来自Angel Beats!的天使，请多关照。', 106, '2021-12-31 00:05:46', 121);
INSERT INTO `t_comment` VALUES (106, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 106, '2021-12-31 00:05:47', 122);
INSERT INTO `t_comment` VALUES (107, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 106, '2021-12-31 00:05:48', 123);
INSERT INTO `t_comment` VALUES (108, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 106, '2021-12-31 00:05:49', 124);
INSERT INTO `t_comment` VALUES (109, '初次见面，我是来自CLANNAD的岡崎朋也，请多关照。', 107, '2021-12-31 00:05:50', 111);
INSERT INTO `t_comment` VALUES (110, '初次见面，我是来自CLANNAD的古河 渚，请多关照。', 107, '2021-12-31 00:05:51', 112);
INSERT INTO `t_comment` VALUES (111, '初次见面，我是来自CLANNAD的藤林 杏，请多关照。', 107, '2021-12-31 00:05:52', 113);
INSERT INTO `t_comment` VALUES (112, '初次见面，我是来自CLANNAD的藤林 椋，请多关照。', 107, '2021-12-31 00:05:53', 114);
INSERT INTO `t_comment` VALUES (113, '初次见面，我是来自CLANNAD的坂上智代，请多关照。', 107, '2021-12-31 00:05:54', 115);
INSERT INTO `t_comment` VALUES (114, '初次见面，我是来自CLANNAD的伊吹風子，请多关照。', 107, '2021-12-31 00:05:55', 116);
INSERT INTO `t_comment` VALUES (115, '初次见面，我是来自CLANNAD的一ノ瀬ことみ，请多关照。', 107, '2021-12-31 00:05:56', 117);
INSERT INTO `t_comment` VALUES (116, '初次见面，我是来自CLANNAD的春原陽平，请多关照。', 107, '2021-12-31 00:05:57', 118);
INSERT INTO `t_comment` VALUES (117, '初次见面，我是来自CLANNAD的岡崎朋也，请多关照。', 108, '2021-12-31 00:05:58', 111);
INSERT INTO `t_comment` VALUES (118, '初次见面，我是来自CLANNAD的古河 渚，请多关照。', 108, '2021-12-31 00:05:59', 112);
INSERT INTO `t_comment` VALUES (119, '初次见面，我是来自CLANNAD的藤林 杏，请多关照。', 108, '2021-12-31 00:06:00', 113);
INSERT INTO `t_comment` VALUES (120, '初次见面，我是来自CLANNAD的藤林 椋，请多关照。', 108, '2021-12-31 00:06:01', 114);
INSERT INTO `t_comment` VALUES (121, '初次见面，我是来自CLANNAD的坂上智代，请多关照。', 108, '2021-12-31 00:06:02', 115);
INSERT INTO `t_comment` VALUES (122, '初次见面，我是来自CLANNAD的伊吹風子，请多关照。', 108, '2021-12-31 00:06:03', 116);
INSERT INTO `t_comment` VALUES (123, '初次见面，我是来自CLANNAD的一ノ瀬ことみ，请多关照。', 108, '2021-12-31 00:06:04', 117);
INSERT INTO `t_comment` VALUES (124, '初次见面，我是来自CLANNAD的春原陽平，请多关照。', 108, '2021-12-31 00:06:05', 118);
INSERT INTO `t_comment` VALUES (125, '初次见面，我是来自CLANNAD的岡崎朋也，请多关照。', 109, '2021-12-31 00:06:06', 111);
INSERT INTO `t_comment` VALUES (126, '初次见面，我是来自CLANNAD的古河 渚，请多关照。', 109, '2021-12-31 00:06:07', 112);
INSERT INTO `t_comment` VALUES (127, '初次见面，我是来自CLANNAD的藤林 杏，请多关照。', 109, '2021-12-31 00:06:08', 113);
INSERT INTO `t_comment` VALUES (128, '初次见面，我是来自CLANNAD的藤林 椋，请多关照。', 109, '2021-12-31 00:06:09', 114);
INSERT INTO `t_comment` VALUES (129, '初次见面，我是来自CLANNAD的坂上智代，请多关照。', 109, '2021-12-31 00:06:10', 115);
INSERT INTO `t_comment` VALUES (130, '初次见面，我是来自CLANNAD的伊吹風子，请多关照。', 109, '2021-12-31 00:06:11', 116);
INSERT INTO `t_comment` VALUES (131, '初次见面，我是来自CLANNAD的一ノ瀬ことみ，请多关照。', 109, '2021-12-31 00:06:12', 117);
INSERT INTO `t_comment` VALUES (132, '初次见面，我是来自CLANNAD的春原陽平，请多关照。', 109, '2021-12-31 00:06:13', 118);
INSERT INTO `t_comment` VALUES (133, '初次见面，我是来自CLANNAD的岡崎朋也，请多关照。', 110, '2021-12-31 00:06:14', 111);
INSERT INTO `t_comment` VALUES (134, '初次见面，我是来自CLANNAD的古河 渚，请多关照。', 110, '2021-12-31 00:06:15', 112);
INSERT INTO `t_comment` VALUES (135, '初次见面，我是来自CLANNAD的藤林 杏，请多关照。', 110, '2021-12-31 00:06:16', 113);
INSERT INTO `t_comment` VALUES (136, '初次见面，我是来自CLANNAD的藤林 椋，请多关照。', 110, '2021-12-31 00:06:17', 114);
INSERT INTO `t_comment` VALUES (137, '初次见面，我是来自CLANNAD的坂上智代，请多关照。', 110, '2021-12-31 00:06:18', 115);
INSERT INTO `t_comment` VALUES (138, '初次见面，我是来自CLANNAD的伊吹風子，请多关照。', 110, '2021-12-31 00:06:19', 116);
INSERT INTO `t_comment` VALUES (139, '初次见面，我是来自CLANNAD的一ノ瀬ことみ，请多关照。', 110, '2021-12-31 00:06:20', 117);
INSERT INTO `t_comment` VALUES (140, '初次见面，我是来自CLANNAD的春原陽平，请多关照。', 110, '2021-12-31 00:06:21', 118);
INSERT INTO `t_comment` VALUES (141, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 107, '2021-12-31 00:06:22', 119);
INSERT INTO `t_comment` VALUES (142, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 107, '2021-12-31 00:06:23', 120);
INSERT INTO `t_comment` VALUES (143, '初次见面，我是来自Angel Beats!的天使，请多关照。', 107, '2021-12-31 00:06:24', 121);
INSERT INTO `t_comment` VALUES (144, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 107, '2021-12-31 00:06:25', 122);
INSERT INTO `t_comment` VALUES (145, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 107, '2021-12-31 00:06:26', 123);
INSERT INTO `t_comment` VALUES (146, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 107, '2021-12-31 00:06:27', 124);
INSERT INTO `t_comment` VALUES (147, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 108, '2021-12-31 00:06:28', 119);
INSERT INTO `t_comment` VALUES (148, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 108, '2021-12-31 00:06:29', 120);
INSERT INTO `t_comment` VALUES (149, '初次见面，我是来自Angel Beats!的天使，请多关照。', 108, '2021-12-31 00:06:30', 121);
INSERT INTO `t_comment` VALUES (150, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 108, '2021-12-31 00:06:31', 122);
INSERT INTO `t_comment` VALUES (151, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 108, '2021-12-31 00:06:32', 123);
INSERT INTO `t_comment` VALUES (152, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 108, '2021-12-31 00:06:33', 124);
INSERT INTO `t_comment` VALUES (153, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 109, '2021-12-31 00:06:34', 119);
INSERT INTO `t_comment` VALUES (154, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 109, '2021-12-31 00:06:35', 120);
INSERT INTO `t_comment` VALUES (155, '初次见面，我是来自Angel Beats!的天使，请多关照。', 109, '2021-12-31 00:06:36', 121);
INSERT INTO `t_comment` VALUES (156, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 109, '2021-12-31 00:06:37', 122);
INSERT INTO `t_comment` VALUES (157, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 109, '2021-12-31 00:06:38', 123);
INSERT INTO `t_comment` VALUES (158, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 109, '2021-12-31 00:06:39', 124);
INSERT INTO `t_comment` VALUES (159, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 110, '2021-12-31 00:06:40', 119);
INSERT INTO `t_comment` VALUES (160, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 110, '2021-12-31 00:06:41', 120);
INSERT INTO `t_comment` VALUES (161, '初次见面，我是来自Angel Beats!的天使，请多关照。', 110, '2021-12-31 00:06:42', 121);
INSERT INTO `t_comment` VALUES (162, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 110, '2021-12-31 00:06:43', 122);
INSERT INTO `t_comment` VALUES (163, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 110, '2021-12-31 00:06:44', 123);
INSERT INTO `t_comment` VALUES (164, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 110, '2021-12-31 00:06:45', 124);
INSERT INTO `t_comment` VALUES (165, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 111, '2021-12-31 00:06:46', 119);
INSERT INTO `t_comment` VALUES (166, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 111, '2021-12-31 00:06:47', 120);
INSERT INTO `t_comment` VALUES (167, '初次见面，我是来自Angel Beats!的天使，请多关照。', 111, '2021-12-31 00:06:48', 121);
INSERT INTO `t_comment` VALUES (168, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 111, '2021-12-31 00:06:49', 122);
INSERT INTO `t_comment` VALUES (169, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 111, '2021-12-31 00:06:50', 123);
INSERT INTO `t_comment` VALUES (170, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 111, '2021-12-31 00:06:51', 124);
INSERT INTO `t_comment` VALUES (171, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 112, '2021-12-31 00:06:52', 119);
INSERT INTO `t_comment` VALUES (172, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 112, '2021-12-31 00:06:53', 120);
INSERT INTO `t_comment` VALUES (173, '初次见面，我是来自Angel Beats!的天使，请多关照。', 112, '2021-12-31 00:06:54', 121);
INSERT INTO `t_comment` VALUES (174, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 112, '2021-12-31 00:06:55', 122);
INSERT INTO `t_comment` VALUES (175, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 112, '2021-12-31 00:06:56', 123);
INSERT INTO `t_comment` VALUES (176, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 112, '2021-12-31 00:06:57', 124);
INSERT INTO `t_comment` VALUES (177, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 113, '2021-12-31 00:06:58', 119);
INSERT INTO `t_comment` VALUES (178, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 113, '2021-12-31 00:06:59', 120);
INSERT INTO `t_comment` VALUES (179, '初次见面，我是来自Angel Beats!的天使，请多关照。', 113, '2021-12-31 00:07:00', 121);
INSERT INTO `t_comment` VALUES (180, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 113, '2021-12-31 00:07:01', 122);
INSERT INTO `t_comment` VALUES (181, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 113, '2021-12-31 00:07:02', 123);
INSERT INTO `t_comment` VALUES (182, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 113, '2021-12-31 00:07:03', 124);
INSERT INTO `t_comment` VALUES (183, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 114, '2021-12-31 00:07:04', 119);
INSERT INTO `t_comment` VALUES (184, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 114, '2021-12-31 00:07:05', 120);
INSERT INTO `t_comment` VALUES (185, '初次见面，我是来自Angel Beats!的天使，请多关照。', 114, '2021-12-31 00:07:06', 121);
INSERT INTO `t_comment` VALUES (186, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 114, '2021-12-31 00:07:07', 122);
INSERT INTO `t_comment` VALUES (187, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 114, '2021-12-31 00:07:08', 123);
INSERT INTO `t_comment` VALUES (188, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 114, '2021-12-31 00:07:09', 124);
INSERT INTO `t_comment` VALUES (189, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 115, '2021-12-31 00:07:10', 119);
INSERT INTO `t_comment` VALUES (190, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 115, '2021-12-31 00:07:11', 120);
INSERT INTO `t_comment` VALUES (191, '初次见面，我是来自Angel Beats!的天使，请多关照。', 115, '2021-12-31 00:07:12', 121);
INSERT INTO `t_comment` VALUES (192, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 115, '2021-12-31 00:07:13', 122);
INSERT INTO `t_comment` VALUES (193, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 115, '2021-12-31 00:07:14', 123);
INSERT INTO `t_comment` VALUES (194, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 115, '2021-12-31 00:07:15', 124);
INSERT INTO `t_comment` VALUES (195, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 116, '2021-12-31 00:07:16', 119);
INSERT INTO `t_comment` VALUES (196, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 116, '2021-12-31 00:07:17', 120);
INSERT INTO `t_comment` VALUES (197, '初次见面，我是来自Angel Beats!的天使，请多关照。', 116, '2021-12-31 00:07:18', 121);
INSERT INTO `t_comment` VALUES (198, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 116, '2021-12-31 00:07:19', 122);
INSERT INTO `t_comment` VALUES (199, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 116, '2021-12-31 00:07:20', 123);
INSERT INTO `t_comment` VALUES (200, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 116, '2021-12-31 00:07:21', 124);
INSERT INTO `t_comment` VALUES (201, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 117, '2021-12-31 00:07:22', 119);
INSERT INTO `t_comment` VALUES (202, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 117, '2021-12-31 00:07:23', 120);
INSERT INTO `t_comment` VALUES (203, '初次见面，我是来自Angel Beats!的天使，请多关照。', 117, '2021-12-31 00:07:24', 121);
INSERT INTO `t_comment` VALUES (204, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 117, '2021-12-31 00:07:25', 122);
INSERT INTO `t_comment` VALUES (205, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 117, '2021-12-31 00:07:26', 123);
INSERT INTO `t_comment` VALUES (206, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 117, '2021-12-31 00:07:27', 124);
INSERT INTO `t_comment` VALUES (207, '初次见面，我是来自Angel Beats!的音無結弦，请多关照。', 118, '2021-12-31 00:07:28', 119);
INSERT INTO `t_comment` VALUES (208, '初次见面，我是来自Angel Beats!的仲村ゆり，请多关照。', 118, '2021-12-31 00:07:29', 120);
INSERT INTO `t_comment` VALUES (209, '初次见面，我是来自Angel Beats!的天使，请多关照。', 118, '2021-12-31 00:07:30', 121);
INSERT INTO `t_comment` VALUES (210, '初次见面，我是来自Angel Beats!的日向秀樹，请多关照。', 118, '2021-12-31 00:07:31', 122);
INSERT INTO `t_comment` VALUES (211, '初次见面，我是来自Angel Beats!的直井文人，请多关照。', 118, '2021-12-31 00:07:32', 123);
INSERT INTO `t_comment` VALUES (212, '初次见面，我是来自Angel Beats!的ユイ，请多关照。', 118, '2021-12-31 00:07:33', 124);

-- ----------------------------
-- Table structure for t_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_follow`;
CREATE TABLE `t_follow`  (
  `follow_id` bigint NOT NULL AUTO_INCREMENT,
  `userid_following` bigint NOT NULL,
  `userid_followed` bigint NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`follow_id`) USING BTREE,
  INDEX `fk_followed`(`userid_followed` ASC) USING BTREE,
  INDEX `fk_following`(`userid_following` ASC) USING BTREE,
  CONSTRAINT `fk_followed` FOREIGN KEY (`userid_followed`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_following` FOREIGN KEY (`userid_following`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 177 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_follow
-- ----------------------------
INSERT INTO `t_follow` VALUES (1, 100, 101, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (2, 100, 102, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (3, 100, 103, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (4, 100, 104, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (5, 100, 105, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (6, 100, 106, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (7, 100, 107, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (8, 100, 108, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (9, 100, 109, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (10, 100, 110, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (11, 100, 111, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (12, 100, 112, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (13, 100, 113, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (14, 100, 114, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (15, 100, 115, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (16, 100, 116, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (17, 100, 117, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (18, 100, 118, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (19, 100, 119, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (20, 100, 120, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (21, 100, 121, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (22, 100, 122, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (23, 100, 123, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (24, 100, 124, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (25, 101, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (26, 101, 102, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (27, 101, 103, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (28, 101, 104, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (29, 101, 105, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (30, 101, 106, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (31, 102, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (32, 102, 101, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (33, 102, 103, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (34, 102, 104, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (35, 102, 105, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (36, 102, 106, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (37, 103, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (38, 103, 101, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (39, 103, 102, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (40, 103, 104, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (41, 103, 105, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (42, 103, 106, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (43, 104, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (44, 104, 101, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (45, 104, 102, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (46, 104, 103, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (47, 104, 105, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (48, 104, 106, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (49, 105, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (50, 105, 101, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (51, 105, 102, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (52, 105, 103, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (53, 105, 104, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (54, 105, 106, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (55, 106, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (56, 106, 101, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (57, 106, 102, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (58, 106, 103, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (59, 106, 104, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (60, 106, 105, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (61, 107, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (62, 107, 108, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (63, 107, 109, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (64, 107, 110, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (65, 108, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (66, 108, 107, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (67, 108, 109, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (68, 108, 110, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (69, 109, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (70, 109, 107, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (71, 109, 108, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (72, 109, 110, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (73, 110, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (74, 110, 107, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (75, 110, 108, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (76, 110, 109, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (77, 111, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (78, 111, 112, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (79, 111, 113, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (80, 111, 114, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (81, 111, 115, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (82, 111, 116, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (83, 111, 117, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (84, 111, 118, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (85, 112, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (86, 112, 111, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (87, 112, 113, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (88, 112, 114, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (89, 112, 115, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (90, 112, 116, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (91, 112, 117, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (92, 112, 118, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (93, 113, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (94, 113, 111, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (95, 113, 112, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (96, 113, 114, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (97, 113, 115, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (98, 113, 116, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (99, 113, 117, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (100, 113, 118, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (101, 114, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (102, 114, 111, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (103, 114, 112, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (104, 114, 113, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (105, 114, 115, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (106, 114, 116, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (107, 114, 117, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (108, 114, 118, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (109, 115, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (110, 115, 111, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (111, 115, 112, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (112, 115, 113, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (113, 115, 114, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (114, 115, 116, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (115, 115, 117, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (116, 115, 118, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (117, 116, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (118, 116, 111, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (119, 116, 112, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (120, 116, 113, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (121, 116, 114, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (122, 116, 115, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (123, 116, 117, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (124, 116, 118, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (125, 117, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (126, 117, 111, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (127, 117, 112, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (128, 117, 113, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (129, 117, 114, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (130, 117, 115, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (131, 117, 116, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (132, 117, 118, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (133, 118, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (134, 118, 111, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (135, 118, 112, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (136, 118, 113, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (137, 118, 114, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (138, 118, 115, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (139, 118, 116, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (140, 118, 117, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (141, 119, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (142, 119, 120, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (143, 119, 121, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (144, 119, 122, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (145, 119, 123, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (146, 119, 124, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (147, 120, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (148, 120, 119, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (149, 120, 121, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (150, 120, 122, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (151, 120, 123, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (152, 120, 124, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (153, 121, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (154, 121, 119, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (155, 121, 120, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (156, 121, 122, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (157, 121, 123, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (158, 121, 124, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (159, 122, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (160, 122, 119, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (161, 122, 120, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (162, 122, 121, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (163, 122, 123, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (164, 122, 124, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (165, 123, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (166, 123, 119, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (167, 123, 120, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (168, 123, 121, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (169, 123, 122, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (170, 123, 124, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (171, 124, 100, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (172, 124, 119, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (173, 124, 120, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (174, 124, 121, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (175, 124, 122, '2021-12-29 00:00:00');
INSERT INTO `t_follow` VALUES (176, 124, 123, '2021-12-29 00:00:00');

-- ----------------------------
-- Table structure for t_like
-- ----------------------------
DROP TABLE IF EXISTS `t_like`;
CREATE TABLE `t_like`  (
  `like_id` bigint NOT NULL AUTO_INCREMENT,
  `blog_id` bigint NOT NULL,
  `userid` bigint NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`like_id`) USING BTREE,
  INDEX `fk_blog2`(`blog_id` ASC) USING BTREE,
  INDEX `fk_user3`(`userid` ASC) USING BTREE,
  CONSTRAINT `fk_blog2` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`blog_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user3` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_like
-- ----------------------------
INSERT INTO `t_like` VALUES (1, 102, 101, '2021-12-31 00:00:00');
INSERT INTO `t_like` VALUES (2, 103, 101, '2021-12-31 00:00:01');
INSERT INTO `t_like` VALUES (3, 104, 101, '2021-12-31 00:00:02');
INSERT INTO `t_like` VALUES (4, 105, 101, '2021-12-31 00:00:03');
INSERT INTO `t_like` VALUES (5, 106, 101, '2021-12-31 00:00:04');
INSERT INTO `t_like` VALUES (6, 108, 101, '2021-12-31 00:00:05');
INSERT INTO `t_like` VALUES (7, 109, 101, '2021-12-31 00:00:06');
INSERT INTO `t_like` VALUES (8, 110, 101, '2021-12-31 00:00:07');
INSERT INTO `t_like` VALUES (9, 112, 101, '2021-12-31 00:00:08');
INSERT INTO `t_like` VALUES (10, 113, 101, '2021-12-31 00:00:09');
INSERT INTO `t_like` VALUES (11, 114, 101, '2021-12-31 00:00:10');
INSERT INTO `t_like` VALUES (12, 115, 101, '2021-12-31 00:00:11');
INSERT INTO `t_like` VALUES (13, 116, 101, '2021-12-31 00:00:12');
INSERT INTO `t_like` VALUES (14, 117, 101, '2021-12-31 00:00:13');
INSERT INTO `t_like` VALUES (15, 120, 101, '2021-12-31 00:00:14');
INSERT INTO `t_like` VALUES (16, 121, 101, '2021-12-31 00:00:15');
INSERT INTO `t_like` VALUES (17, 124, 101, '2021-12-31 00:00:16');
INSERT INTO `t_like` VALUES (18, 102, 107, '2021-12-31 00:00:17');
INSERT INTO `t_like` VALUES (19, 103, 107, '2021-12-31 00:00:18');
INSERT INTO `t_like` VALUES (20, 104, 107, '2021-12-31 00:00:19');
INSERT INTO `t_like` VALUES (21, 105, 107, '2021-12-31 00:00:20');
INSERT INTO `t_like` VALUES (22, 106, 107, '2021-12-31 00:00:21');
INSERT INTO `t_like` VALUES (23, 108, 107, '2021-12-31 00:00:22');
INSERT INTO `t_like` VALUES (24, 109, 107, '2021-12-31 00:00:23');
INSERT INTO `t_like` VALUES (25, 110, 107, '2021-12-31 00:00:24');
INSERT INTO `t_like` VALUES (26, 112, 107, '2021-12-31 00:00:25');
INSERT INTO `t_like` VALUES (27, 113, 107, '2021-12-31 00:00:26');
INSERT INTO `t_like` VALUES (28, 114, 107, '2021-12-31 00:00:27');
INSERT INTO `t_like` VALUES (29, 115, 107, '2021-12-31 00:00:28');
INSERT INTO `t_like` VALUES (30, 116, 107, '2021-12-31 00:00:29');
INSERT INTO `t_like` VALUES (31, 117, 107, '2021-12-31 00:00:30');
INSERT INTO `t_like` VALUES (32, 120, 107, '2021-12-31 00:00:31');
INSERT INTO `t_like` VALUES (33, 121, 107, '2021-12-31 00:00:32');
INSERT INTO `t_like` VALUES (34, 124, 107, '2021-12-31 00:00:33');
INSERT INTO `t_like` VALUES (35, 102, 111, '2021-12-31 00:00:34');
INSERT INTO `t_like` VALUES (36, 103, 111, '2021-12-31 00:00:35');
INSERT INTO `t_like` VALUES (37, 104, 111, '2021-12-31 00:00:36');
INSERT INTO `t_like` VALUES (38, 105, 111, '2021-12-31 00:00:37');
INSERT INTO `t_like` VALUES (39, 106, 111, '2021-12-31 00:00:38');
INSERT INTO `t_like` VALUES (40, 108, 111, '2021-12-31 00:00:39');
INSERT INTO `t_like` VALUES (41, 109, 111, '2021-12-31 00:00:40');
INSERT INTO `t_like` VALUES (42, 110, 111, '2021-12-31 00:00:41');
INSERT INTO `t_like` VALUES (43, 112, 111, '2021-12-31 00:00:42');
INSERT INTO `t_like` VALUES (44, 113, 111, '2021-12-31 00:00:43');
INSERT INTO `t_like` VALUES (45, 114, 111, '2021-12-31 00:00:44');
INSERT INTO `t_like` VALUES (46, 115, 111, '2021-12-31 00:00:45');
INSERT INTO `t_like` VALUES (47, 116, 111, '2021-12-31 00:00:46');
INSERT INTO `t_like` VALUES (48, 117, 111, '2021-12-31 00:00:47');
INSERT INTO `t_like` VALUES (49, 120, 111, '2021-12-31 00:00:48');
INSERT INTO `t_like` VALUES (50, 121, 111, '2021-12-31 00:00:49');
INSERT INTO `t_like` VALUES (51, 124, 111, '2021-12-31 00:00:50');
INSERT INTO `t_like` VALUES (52, 102, 118, '2021-12-31 00:00:51');
INSERT INTO `t_like` VALUES (53, 103, 118, '2021-12-31 00:00:52');
INSERT INTO `t_like` VALUES (54, 104, 118, '2021-12-31 00:00:53');
INSERT INTO `t_like` VALUES (55, 105, 118, '2021-12-31 00:00:54');
INSERT INTO `t_like` VALUES (56, 106, 118, '2021-12-31 00:00:55');
INSERT INTO `t_like` VALUES (57, 108, 118, '2021-12-31 00:00:56');
INSERT INTO `t_like` VALUES (58, 109, 118, '2021-12-31 00:00:57');
INSERT INTO `t_like` VALUES (59, 110, 118, '2021-12-31 00:00:58');
INSERT INTO `t_like` VALUES (60, 112, 118, '2021-12-31 00:00:59');
INSERT INTO `t_like` VALUES (61, 113, 118, '2021-12-31 00:01:00');
INSERT INTO `t_like` VALUES (62, 114, 118, '2021-12-31 00:01:01');
INSERT INTO `t_like` VALUES (63, 115, 118, '2021-12-31 00:01:02');
INSERT INTO `t_like` VALUES (64, 116, 118, '2021-12-31 00:01:03');
INSERT INTO `t_like` VALUES (65, 117, 118, '2021-12-31 00:01:04');
INSERT INTO `t_like` VALUES (66, 120, 118, '2021-12-31 00:01:05');
INSERT INTO `t_like` VALUES (67, 121, 118, '2021-12-31 00:01:06');
INSERT INTO `t_like` VALUES (68, 124, 118, '2021-12-31 00:01:07');
INSERT INTO `t_like` VALUES (69, 102, 119, '2021-12-31 00:01:08');
INSERT INTO `t_like` VALUES (70, 103, 119, '2021-12-31 00:01:09');
INSERT INTO `t_like` VALUES (71, 104, 119, '2021-12-31 00:01:10');
INSERT INTO `t_like` VALUES (72, 105, 119, '2021-12-31 00:01:11');
INSERT INTO `t_like` VALUES (73, 106, 119, '2021-12-31 00:01:12');
INSERT INTO `t_like` VALUES (74, 108, 119, '2021-12-31 00:01:13');
INSERT INTO `t_like` VALUES (75, 109, 119, '2021-12-31 00:01:14');
INSERT INTO `t_like` VALUES (76, 110, 119, '2021-12-31 00:01:15');
INSERT INTO `t_like` VALUES (77, 112, 119, '2021-12-31 00:01:16');
INSERT INTO `t_like` VALUES (78, 113, 119, '2021-12-31 00:01:17');
INSERT INTO `t_like` VALUES (79, 114, 119, '2021-12-31 00:01:18');
INSERT INTO `t_like` VALUES (80, 115, 119, '2021-12-31 00:01:19');
INSERT INTO `t_like` VALUES (81, 116, 119, '2021-12-31 00:01:20');
INSERT INTO `t_like` VALUES (82, 117, 119, '2021-12-31 00:01:21');
INSERT INTO `t_like` VALUES (83, 120, 119, '2021-12-31 00:01:22');
INSERT INTO `t_like` VALUES (84, 121, 119, '2021-12-31 00:01:23');
INSERT INTO `t_like` VALUES (85, 124, 119, '2021-12-31 00:01:24');
INSERT INTO `t_like` VALUES (86, 102, 122, '2021-12-31 00:01:25');
INSERT INTO `t_like` VALUES (87, 103, 122, '2021-12-31 00:01:26');
INSERT INTO `t_like` VALUES (88, 104, 122, '2021-12-31 00:01:27');
INSERT INTO `t_like` VALUES (89, 105, 122, '2021-12-31 00:01:28');
INSERT INTO `t_like` VALUES (90, 106, 122, '2021-12-31 00:01:29');
INSERT INTO `t_like` VALUES (91, 108, 122, '2021-12-31 00:01:30');
INSERT INTO `t_like` VALUES (92, 109, 122, '2021-12-31 00:01:31');
INSERT INTO `t_like` VALUES (93, 110, 122, '2021-12-31 00:01:32');
INSERT INTO `t_like` VALUES (94, 112, 122, '2021-12-31 00:01:33');
INSERT INTO `t_like` VALUES (95, 113, 122, '2021-12-31 00:01:34');
INSERT INTO `t_like` VALUES (96, 114, 122, '2021-12-31 00:01:35');
INSERT INTO `t_like` VALUES (97, 115, 122, '2021-12-31 00:01:36');
INSERT INTO `t_like` VALUES (98, 116, 122, '2021-12-31 00:01:37');
INSERT INTO `t_like` VALUES (99, 117, 122, '2021-12-31 00:01:38');
INSERT INTO `t_like` VALUES (100, 120, 122, '2021-12-31 00:01:39');
INSERT INTO `t_like` VALUES (101, 121, 122, '2021-12-31 00:01:40');
INSERT INTO `t_like` VALUES (102, 124, 122, '2021-12-31 00:01:41');
INSERT INTO `t_like` VALUES (103, 102, 123, '2021-12-31 00:01:42');
INSERT INTO `t_like` VALUES (104, 103, 123, '2021-12-31 00:01:43');
INSERT INTO `t_like` VALUES (105, 104, 123, '2021-12-31 00:01:44');
INSERT INTO `t_like` VALUES (106, 105, 123, '2021-12-31 00:01:45');
INSERT INTO `t_like` VALUES (107, 106, 123, '2021-12-31 00:01:46');
INSERT INTO `t_like` VALUES (108, 108, 123, '2021-12-31 00:01:47');
INSERT INTO `t_like` VALUES (109, 109, 123, '2021-12-31 00:01:48');
INSERT INTO `t_like` VALUES (110, 110, 123, '2021-12-31 00:01:49');
INSERT INTO `t_like` VALUES (111, 112, 123, '2021-12-31 00:01:50');
INSERT INTO `t_like` VALUES (112, 113, 123, '2021-12-31 00:01:51');
INSERT INTO `t_like` VALUES (113, 114, 123, '2021-12-31 00:01:52');
INSERT INTO `t_like` VALUES (114, 115, 123, '2021-12-31 00:01:53');
INSERT INTO `t_like` VALUES (115, 116, 123, '2021-12-31 00:01:54');
INSERT INTO `t_like` VALUES (116, 117, 123, '2021-12-31 00:01:55');
INSERT INTO `t_like` VALUES (117, 120, 123, '2021-12-31 00:01:56');
INSERT INTO `t_like` VALUES (118, 121, 123, '2021-12-31 00:01:57');
INSERT INTO `t_like` VALUES (119, 124, 123, '2021-12-31 00:01:58');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userid` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_sex` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_superuser` int NULL DEFAULT 0,
  `last_login` datetime NULL DEFAULT NULL,
  `user_register_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (100, '通晓宇宙', 'Pxy123456..', '1', '2581011320@qq.com', '18764739387', 'https://avatars.githubusercontent.com/u/22099765', 1, '2001-10-30 23:59:00', '2001-10-30 23:59:00');
INSERT INTO `t_user` VALUES (101, '相沢祐一', 'yuichi-aizawa@kanon.key', '1', 'yuichi-aizawa@kanon.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123805/%E7%9B%B8%E6%B2%A2%E7%A5%90%E4%B8%80.png', 1, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (102, '月宮あゆ', 'ayu-tsukimiya@kanon.key', '0', 'ayu-tsukimiya@kanon.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123815/%E6%9C%88%E5%AE%AE%E3%81%82%E3%82%86.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (103, '水瀬名雪', 'nayuki-minase@kanon.key', '0', 'nayuki-minase@kanon.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123821/%E6%B0%B4%E7%80%AC%E5%90%8D%E9%9B%AA.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (104, '川澄 舞', 'mai-kawasumi@kanon.key', '0', 'mai-kawasumi@kanon.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123827/%E5%B7%9D%E6%BE%84-%E8%88%9E.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (105, '美坂 栞', 'shiori-misaka@kanon.key', '0', 'shiori-misaka@kanon.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123830/%E7%BE%8E%E5%9D%82-%E6%A0%9E.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (106, '沢渡真琴', 'makoto-sawatari@kanon.key', '0', 'makoto-sawatari@kanon.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123834/%E6%B2%A2%E6%B8%A1%E7%9C%9F%E7%90%B4.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (107, '国崎往人', 'yukito-kunisaki@air.key', '1', 'yukito-kunisaki@air.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123841/%E5%9B%BD%E5%B4%8E%E5%BE%80%E4%BA%BA.png', 1, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (108, '神尾観鈴', 'misuzu-kamio@air.key', '0', 'misuzu-kamio@air.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123849/%E7%A5%9E%E5%B0%BE%E8%A6%B3%E9%88%B4.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (109, '霧島佳乃', 'kano-kirishima@air.key', '0', 'kano-kirishima@air.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123856/%E9%9C%A7%E5%B3%B6%E4%BD%B3%E4%B9%83.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (110, '遠野美凪', 'minagi-tohno@air.key', '0', 'minagi-tohno@air.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123859/%E9%81%A0%E9%87%8E%E7%BE%8E%E5%87%AA.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (111, '岡崎朋也', 'tomoya-okazaki@clannad.key', '1', 'tomoya-okazaki@clannad.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123909/%E5%B2%A1%E5%B4%8E%E6%9C%8B%E4%B9%9F.png', 1, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (112, '古河 渚', 'nagisa-furukawa@clannad.key', '0', 'nagisa-furukawa@clannad.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123916/%E5%8F%A4%E6%B2%B3-%E6%B8%9A.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (113, '藤林 杏', 'kyou-fujibayashi@clannad.key', '0', 'kyou-fujibayashi@clannad.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123919/%E8%97%A4%E6%9E%97-%E6%9D%8F.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (114, '藤林 椋', 'ryou-fujibayashi@clannad.key', '0', 'ryou-fujibayashi@clannad.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123925/%E8%97%A4%E6%9E%97-%E6%A4%8B.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (115, '坂上智代', 'tomoyo-sakagami@clannad.key', '0', 'tomoyo-sakagami@clannad.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123929/%E5%9D%82%E4%B8%8A%E6%99%BA%E4%BB%A3.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (116, '伊吹風子', 'fuko-ibuki@clannad.key', '0', 'fuko-ibuki@clannad.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123935/%E4%BC%8A%E5%90%B9%E9%A2%A8%E5%AD%90.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (117, '一ノ瀬ことみ', 'kotomi-ichinose@clannad.key', '0', 'kotomi-ichinose@clannad.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123932/%E4%B8%80%E3%83%8E%E7%80%AC%E3%81%93%E3%81%A8%E3%81%BF.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (118, '春原陽平', 'youhei-sunohara@clannad.key', '1', 'youhei-sunohara@clannad.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123942/%E6%98%A5%E5%8E%9F%E9%99%BD%E5%B9%B3.png', 1, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (119, '音無結弦', 'yuzuru-otonashi@angelbeats.key', '1', 'yuzuru-otonashi@angelbeats.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101539/83a6288beb333dd9e698774a5f3c201b.png', 1, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (120, '仲村ゆり', 'yuri-nakamura@angelbeats.key', '0', 'yuri-nakamura@angelbeats.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101712/e92168f0b30574ebd39be004ed9f6532.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (121, '天使', 'tenshi@angelbeats.key', '0', 'tenshi@angelbeats.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101833/57de04aa59fc17c0480c6a7444972245.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (122, '日向秀樹', 'hideki-hinata@angelbeats.key', '1', 'hideki-hinata@angelbeats.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101952/aff2524e0d3925c35e42027b65652587.png', 1, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (123, '直井文人', 'ayato-naoi@angelbeats.key', '1', 'ayato-naoi@angelbeats.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13102120/1eb3b16bd7c0d27d6ec8c0ffbbba1000.png', 1, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (124, 'ユイ', 'yui@angelbeats.key', '0', 'yui@angelbeats.key', '12345678901', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13102227/329df80c7aa719861f18c2c7a6ab861c.png', 0, '2021-12-29 00:00:00', '2021-12-29 00:00:00');
INSERT INTO `t_user` VALUES (125, '测试', '123', '1', '123', '12345678910', 'https://q1.qlogo.cn/g?b=qq&nk=1346802467&src_uin=www.jlwz.cn&s=0', 0, '2023-04-03 14:08:29', '2023-03-29 17:50:46');
INSERT INTO `t_user` VALUES (126, 'tieke', 'Clx12345@', NULL, '3366438434@qq.com', NULL, 'https://q1.qlogo.cn/g?b=qq&nk=3366438434&src_uin=www.jlwz.cn&s=0', 0, '2023-04-03 15:38:30', '2023-04-03 15:37:53');

-- ----------------------------
-- View structure for v_blogdetail
-- ----------------------------
DROP VIEW IF EXISTS `v_blogdetail`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_blogdetail` AS select `t_blog`.`blog_id` AS `blog_id`,`t_blog`.`blog_title` AS `blog_title`,`t_blog`.`blog_content` AS `blog_content`,`t_blog`.`userid` AS `userid`,`t_user`.`username` AS `username`,`t_user`.`avatar` AS `avatar`,`t_blog`.`blog_status` AS `blog_status`,`t_blog`.`create_time` AS `create_time`,`t_blog`.`update_time` AS `update_time`,`t_blog`.`cover_image` AS `cover_image`,coalesce(`l`.`like_count`,0) AS `like_count`,coalesce(`c`.`comment_count`,0) AS `comment_count`,coalesce(`bm`.`bookmark_count`,0) AS `bookmark_count` from ((((`t_blog` join `t_user` on((`t_blog`.`userid` = `t_user`.`userid`))) left join (select `t_like`.`blog_id` AS `blog_id`,count(0) AS `like_count` from `t_like` group by `t_like`.`blog_id`) `l` on((`t_blog`.`blog_id` = `l`.`blog_id`))) left join (select `t_comment`.`blog_id` AS `blog_id`,count(0) AS `comment_count` from `t_comment` group by `t_comment`.`blog_id`) `c` on((`t_blog`.`blog_id` = `c`.`blog_id`))) left join (select `t_bookmark`.`blog_id` AS `blog_id`,count(0) AS `bookmark_count` from `t_bookmark` group by `t_bookmark`.`blog_id`) `bm` on((`t_blog`.`blog_id` = `bm`.`blog_id`)));

-- ----------------------------
-- View structure for v_bloginfo
-- ----------------------------
DROP VIEW IF EXISTS `v_bloginfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_bloginfo` AS select `t_blog`.`blog_id` AS `blog_id`,`t_blog`.`blog_title` AS `blog_title`,left(`t_blog`.`blog_content`,100) AS `blog_summary`,`t_blog`.`userid` AS `userid`,`t_user`.`username` AS `username`,`t_user`.`avatar` AS `avatar`,`t_blog`.`blog_status` AS `blog_status`,`t_blog`.`create_time` AS `create_time`,`t_blog`.`update_time` AS `update_time`,`t_blog`.`cover_image` AS `cover_image`,coalesce(`l`.`like_count`,0) AS `like_count`,coalesce(`c`.`comment_count`,0) AS `comment_count`,coalesce(`bm`.`bookmark_count`,0) AS `bookmark_count` from ((((`t_blog` join `t_user` on((`t_blog`.`userid` = `t_user`.`userid`))) left join (select `t_like`.`blog_id` AS `blog_id`,count(0) AS `like_count` from `t_like` group by `t_like`.`blog_id`) `l` on((`t_blog`.`blog_id` = `l`.`blog_id`))) left join (select `t_comment`.`blog_id` AS `blog_id`,count(0) AS `comment_count` from `t_comment` group by `t_comment`.`blog_id`) `c` on((`t_blog`.`blog_id` = `c`.`blog_id`))) left join (select `t_bookmark`.`blog_id` AS `blog_id`,count(0) AS `bookmark_count` from `t_bookmark` group by `t_bookmark`.`blog_id`) `bm` on((`t_blog`.`blog_id` = `bm`.`blog_id`)));

-- ----------------------------
-- View structure for v_blogwithuser
-- ----------------------------
DROP VIEW IF EXISTS `v_blogwithuser`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_blogwithuser` AS select `t_blog`.`blog_id` AS `blog_id`,`t_blog`.`blog_title` AS `blog_title`,`t_blog`.`blog_content` AS `blog_content`,`t_blog`.`userid` AS `userid`,`t_blog`.`blog_status` AS `blog_status`,`t_blog`.`create_time` AS `create_time`,`t_blog`.`update_time` AS `update_time`,`t_blog`.`cover_image` AS `cover_image`,`t_user`.`username` AS `username`,`t_user`.`avatar` AS `avatar` from (`t_blog` join `t_user` on((`t_blog`.`userid` = `t_user`.`userid`)));

-- ----------------------------
-- View structure for v_chat
-- ----------------------------
DROP VIEW IF EXISTS `v_chat`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_chat` AS select `c`.`chat_id` AS `chat_id`,`c`.`userid_from` AS `userid_from`,`c`.`userid_to` AS `userid_to`,`c`.`message_content` AS `message_content`,`c`.`createtime` AS `createtime`,`u1`.`username` AS `from_username`,`u1`.`avatar` AS `from_avatar`,`u2`.`username` AS `to_username`,`u2`.`avatar` AS `to_avatar`,`c`.`read` AS `read` from ((`t_chat` `c` join `t_user` `u1` on((`c`.`userid_from` = `u1`.`userid`))) join `t_user` `u2` on((`c`.`userid_to` = `u2`.`userid`)));

-- ----------------------------
-- View structure for v_comment
-- ----------------------------
DROP VIEW IF EXISTS `v_comment`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_comment` AS select `t_comment`.`comment_id` AS `comment_id`,`t_comment`.`comment_content` AS `comment_content`,`t_comment`.`blog_id` AS `blog_id`,`t_comment`.`createtime` AS `createtime`,`t_comment`.`userid` AS `userid`,`t_user`.`username` AS `username`,`t_user`.`avatar` AS `avatar` from (`t_comment` join `t_user` on((`t_comment`.`userid` = `t_user`.`userid`)));

SET FOREIGN_KEY_CHECKS = 1;
