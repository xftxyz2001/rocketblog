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

 Date: 03/04/2023 11:33:35
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
INSERT INTO `t_blog` VALUES (101, 'キャラクター:相沢祐一', 'Keyの初代主人公にして、もっとも空気を読まない男。被害者は主に５人だったが、さらに増える模様。基本事なかれ主義ではあるが、歯に衣を着せぬ言動故にトラブルを巻き起こすことも。主人公たちのリーダー格でもある。', 101, 1, '2021-12-30 00:00:00', '2021-12-30 00:00:00', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123802/%E7%9B%B8%E6%B2%A2%E7%A5%90%E4%B8%80-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (102, 'キャラクター:月宮あゆ', '雪の降る町からやって来た、たい焼きドロボウの常習犯。全国のたい焼き屋に手配書が回っているらしい。口癖は「うぐぅ」。', 102, 1, '2021-12-30 00:00:01', '2021-12-30 00:00:01', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123812/%E6%9C%88%E5%AE%AE%E3%81%82%E3%82%86-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (103, 'キャラクター:水瀬名雪', '相沢祐一の居候先の少女。幼い頃待ちぼうけを食らわされた恨みを忘れず、７年ぶりの再会の際には、雪の中で２時間放置した。母親が作った危険なジャムの存在も、もちろん告げることは無かった。', 103, 1, '2021-12-30 00:00:02', '2021-12-30 00:00:02', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123819/%E6%B0%B4%E7%80%AC%E5%90%8D%E9%9B%AA-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (104, 'キャラクター:川澄 舞', '「魔物を討つ者」と称して夜の学校を徘徊する少女。不法侵入である。相沢祐一にかけられた呪いのため、「はちみつクマさん」と「ぽんぽこタヌキさん」しか言えない。牛丼を与えておけば大人しい。', 104, 1, '2021-12-30 00:00:03', '2021-12-30 00:00:03', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123825/%E5%B7%9D%E6%BE%84-%E8%88%9E-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (105, 'キャラクター:美坂 栞', '次の誕生日に死ぬ、とカウントダウンを始める少女。姉から避けられていたこともあったが、今は表向き和解している。好きな物はアイスクリーム。どんなに寒くても食べる事をやめない。いや…やめられないのかもしれない。', 105, 1, '2021-12-30 00:00:04', '2021-12-30 00:00:04', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123830/%E7%BE%8E%E5%9D%82-%E6%A0%9E.png');
INSERT INTO `t_blog` VALUES (106, 'キャラクター:沢渡真琴', '相沢祐一最大の被害者にしてもっとも深い愛情を向けた少女。水瀬家では「おでん種」として連れてこられた過去を持つ。どんなに機嫌が悪くても肉まんを見せれば解決する。漫画の影響で結婚願望が強い。', 106, 1, '2021-12-30 00:00:05', '2021-12-30 00:00:05', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123834/%E6%B2%A2%E6%B8%A1%E7%9C%9F%E7%90%B4.png');
INSERT INTO `t_blog` VALUES (107, 'キャラクター:国崎往人', '住所不定自称大道芸人と言う名の無職。すでに成人しているが、何かを懐かしんでか学園に現れる。常に金欠のため、行き倒れることも。特技は法術による人形操りと、カラスに変身すること。', 107, 1, '2021-12-30 00:00:06', '2021-12-30 00:00:06', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123839/%E5%9B%BD%E5%B4%8E%E5%BE%80%E4%BA%BA-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (108, 'キャラクター:神尾観鈴', '海辺の町からやって来た元祖ぼっち系少女。友だち作りに励んでいるようだが成果は出ていない。休みの日は海の家「かみお」で母親の手伝いをしている、いい子。だけど隙を見せるとゴールしたがる、こまった子。', 108, 1, '2021-12-30 00:00:07', '2021-12-30 00:00:07', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123846/%E7%A5%9E%E5%B0%BE%E8%A6%B3%E9%88%B4-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (109, 'キャラクター:霧島佳乃', '手首に巻いた大きな黄色いバンダナがトレードマークの少女。「バンダナを外さずに大人になれば魔法が使える」という姉の言葉を信じている。それを公言しているためか、周囲からは少し生温かな目で見られることも。', 109, 1, '2021-12-30 00:00:08', '2021-12-30 00:00:08', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123853/%E9%9C%A7%E5%B3%B6%E4%BD%B3%E4%B9%83-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (110, 'キャラクター:遠野美凪', 'お米券を配り歩くわがままボディ。物腰柔らかなしゃべりは、聞く者に眠りを誘…否、心を穏やかにする。成績優秀容姿端麗にもかかわらず、年下のみちるとばかり一緒にいるせいで、実は同世代の友人に乏しい。', 110, 1, '2021-12-30 00:00:09', '2021-12-30 00:00:09', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123859/%E9%81%A0%E9%87%8E%E7%BE%8E%E5%87%AA.png');
INSERT INTO `t_blog` VALUES (111, 'キャラクター:岡崎朋也', '不良と呼ばれながらも垣間見せる誠実な素顔とのギャップで多くのヒロインを虜にしてきた男。通称ハーレム王。その甲斐性は時に親友ポジションの春原陽平にまで及ぶ。本人はどの主人公より一途で、結婚まで経験している。', 111, 1, '2021-12-30 00:00:10', '2021-12-30 00:00:10', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24175506/f0c4a6644a2e84507eb1814e9cbf9248.png');
INSERT INTO `t_blog` VALUES (112, 'キャラクター:古河 渚', '演劇部に所属するソロ女優。初舞台、初主演もソロ。無茶振りが過ぎると思っているが、決して口に出さない。最近、だんご大家族の中身が、粒あんかこしあんか気になっている。学校下の坂道で、突然叫び出すことがある。', 112, 1, '2021-12-30 00:00:11', '2021-12-30 00:00:11', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123914/%E5%8F%A4%E6%B2%B3-%E6%B8%9A-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (113, 'キャラクター:藤林 杏', '無類の投擲能力を持つKey初代双子キャラの姉の方。ラッキースポットは体育倉庫。むっつりである。相手の真意を確かめるために、髪を切って双子の妹に成り代わるといった、策略家の一面を持つ。', 113, 1, '2021-12-30 00:00:12', '2021-12-30 00:00:12', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123923/%E8%97%A4%E6%9E%97-%E6%A4%8B-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (114, 'キャラクター:藤林 椋', '的中率100％のデス占いを行うKey初代双子キャラの妹の方。姉より胸があることを密かに誇っている。将来の夢は看護師。主人公に攻略されなくても自力で新しい幸せを掴める強い少女。尻軽ではない。', 114, 1, '2021-12-30 00:00:13', '2021-12-30 00:00:13', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123939/%E6%98%A5%E5%8E%9F%E9%99%BD%E5%B9%B3-%E8%A1%A8%E6%83%85.png');
INSERT INTO `t_blog` VALUES (115, 'キャラクター:坂上智代', '桜並木を守るため、様々な人の力を借りて生徒会長となった少女。その人望のおかげで学校の創立者祭では春原陽平相手に連続蹴り64hitを記録した。岡崎朋也の変態性を引き出す才能の持ち主。', 115, 1, '2021-12-30 00:00:14', '2021-12-30 00:00:14', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123929/%E5%9D%82%E4%B8%8A%E6%99%BA%E4%BB%A3.png');
INSERT INTO `t_blog` VALUES (116, 'キャラクター:伊吹風子', '無差別に木彫りのヒトデを配り回る少女。星と勘違いされないと物足りなく感じてきている。岡崎朋也の巧みな調教により、鼻からジュースを飲むことが出来るようになった。成人しても言動は変わらない。', 116, 1, '2021-12-30 00:00:15', '2021-12-30 00:00:15', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123935/%E4%BC%8A%E5%90%B9%E9%A2%A8%E5%AD%90.png');
INSERT INTO `t_blog` VALUES (117, 'キャラクター:一ノ瀬ことみ', 'ヴァイオリンによるアウトレンジ攻撃を得意とする天災……天才少女。友人たちから、個性を強めるためツッコミやギャグを伝授されるが、ほぼ不発に終わる。永い年月をかけて届けられたクマのヌイグルミの匂いに思う所はあったけど、みんなの手前、言葉を飲み込んだ。', 117, 1, '2021-12-30 00:00:16', '2021-12-30 00:00:16', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123932/%E4%B8%80%E3%83%8E%E7%80%AC%E3%81%93%E3%81%A8%E3%81%BF.png');
INSERT INTO `t_blog` VALUES (118, 'キャラクター:春原陽平', '岡崎朋也の（本人は）親友（のつもり）。元サッカー部所属の帰宅部。プライドを捨てれば藤林杏をGetできたかもしれない。普段だらしないけど、根っこには誠実さをもつ……が、やっぱりだらしない。それと便座カバー。', 118, 1, '2021-12-30 00:00:17', '2021-12-30 00:00:17', 'https://kaginado.com/wordpress/wp-content/uploads/2021/09/24123942/%E6%98%A5%E5%8E%9F%E9%99%BD%E5%B9%B3.png');
INSERT INTO `t_blog` VALUES (119, 'キャラクター:音無結弦', '出会い頭に胸を刺せと言ってくる危ない人物。その無謀さが勇気と認められたのか、いきなり死んだ世界戦線の幹部に。「いい人」の立ち位置を戦線内で築いていく。冷静で物わかりの良いキャラに見えるが、ただノリが悪いだけ。', 119, 1, '2021-12-30 00:00:18', '2021-12-30 00:00:18', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101539/83a6288beb333dd9e698774a5f3c201b.png');
INSERT INTO `t_blog` VALUES (120, 'キャラクター:仲村ゆり', '神を憎むあまり結成した「死んだ世界戦線」そのリーダー。思い込みが激しく、詰めの甘さに定評がある。何事も形から入るクセがある。主なアイテムは帽子と眼鏡。机に肘をついて口元を隠すポーズは特に気に入っている。', 120, 1, '2021-12-30 00:00:19', '2021-12-30 00:00:19', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101712/e92168f0b30574ebd39be004ed9f6532.png');
INSERT INTO `t_blog` VALUES (121, 'キャラクター:天使', '麻婆を食べている間は無害。ガードスキルは自衛の手段のはずだが、その殺傷力の高さに闇を感じる。また、各スキル名には抑えきれないパトスが滲む。羽を生やすというアドバイスは天啓だと思っている。', 121, 1, '2021-12-30 00:00:20', '2021-12-30 00:00:20', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101833/57de04aa59fc17c0480c6a7444972245.png');
INSERT INTO `t_blog` VALUES (122, 'キャラクター:日向秀樹', 'やんよ。ゆりの片腕的存在だったが、音無の登場でその地位を奪われる。だが音無への距離の詰め方の速さと面倒見の良さに、ただならぬ感情を抱いていると疑われる。本人は否定している。', 122, 1, '2021-12-30 00:00:21', '2021-12-30 00:00:21', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13101952/aff2524e0d3925c35e42027b65652587.png');
INSERT INTO `t_blog` VALUES (123, 'キャラクター:直井文人', '音無の傍から離れようとしない忠犬。目線さえ合わされないが、それも今では1つの悦びと化している。かつては死後の学園を牛耳り神へと成ろうしたが遠い記憶。隙あらば音無への物理的距離を詰める。催眠術は便利。', 123, 1, '2021-12-30 00:00:22', '2021-12-30 00:00:22', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13102120/1eb3b16bd7c0d27d6ec8c0ffbbba1000.png');
INSERT INTO `t_blog` VALUES (124, 'キャラクター:ユイ', '「やんよ」という語尾に過剰反応する。なお滲み出る日向への思いは隠せておらず、攻撃という悪手を選んでしまう。ひとりになった時に顔を赤くして後悔するタイプ。', 124, 1, '2021-12-30 00:00:23', '2021-12-30 00:00:23', 'https://kaginado.com/wordpress/wp-content/uploads/2022/03/13102227/329df80c7aa719861f18c2c7a6ab861c.png');

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
  PRIMARY KEY (`chat_id`) USING BTREE,
  INDEX `fk_from`(`userid_from` ASC) USING BTREE,
  INDEX `fk_to`(`userid_to` ASC) USING BTREE,
  CONSTRAINT `fk_from` FOREIGN KEY (`userid_from`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_to` FOREIGN KEY (`userid_to`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_chat
-- ----------------------------
INSERT INTO `t_chat` VALUES (1, 100, 101, '相沢祐一，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (2, 100, 102, '月宮あゆ，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (3, 100, 103, '水瀬名雪，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (4, 100, 104, '川澄 舞，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (5, 100, 105, '美坂 栞，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (6, 100, 106, '沢渡真琴，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (7, 100, 107, '国崎往人，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (8, 100, 108, '神尾観鈴，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (9, 100, 109, '霧島佳乃，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (10, 100, 110, '遠野美凪，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (11, 100, 111, '岡崎朋也，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (12, 100, 112, '古河 渚，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (13, 100, 113, '藤林 杏，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (14, 100, 114, '藤林 椋，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (15, 100, 115, '坂上智代，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (16, 100, 116, '伊吹風子，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (17, 100, 117, '一ノ瀬ことみ，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (18, 100, 118, '春原陽平，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (19, 100, 119, '音無結弦，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (20, 100, 120, '仲村ゆり，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (21, 100, 121, '天使，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (22, 100, 122, '日向秀樹，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (23, 100, 123, '直井文人，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');
INSERT INTO `t_chat` VALUES (24, 100, 124, 'ユイ，欢迎来到这座永不终结的梦境之中，这里有红色和白色的旋律，有寒冷和温暖的交错，有悲伤和希望的盼望。让我们一起进入这个世界，探索这个世界的秘密，发现这个世界的美好。', '2021-12-29 00:00:00');

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
INSERT INTO `t_comment` VALUES (1, 'はじめまして、私は 国崎往人 です。AIR から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:02', 107);
INSERT INTO `t_comment` VALUES (2, 'はじめまして、私は 神尾観鈴 です。AIR から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:03', 108);
INSERT INTO `t_comment` VALUES (3, 'はじめまして、私は 霧島佳乃 です。AIR から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:04', 109);
INSERT INTO `t_comment` VALUES (4, 'はじめまして、私は 遠野美凪 です。AIR から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:05', 110);
INSERT INTO `t_comment` VALUES (5, 'はじめまして、私は 国崎往人 です。AIR から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:06', 107);
INSERT INTO `t_comment` VALUES (6, 'はじめまして、私は 神尾観鈴 です。AIR から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:07', 108);
INSERT INTO `t_comment` VALUES (7, 'はじめまして、私は 霧島佳乃 です。AIR から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:08', 109);
INSERT INTO `t_comment` VALUES (8, 'はじめまして、私は 遠野美凪 です。AIR から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:09', 110);
INSERT INTO `t_comment` VALUES (9, 'はじめまして、私は 国崎往人 です。AIR から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:10', 107);
INSERT INTO `t_comment` VALUES (10, 'はじめまして、私は 神尾観鈴 です。AIR から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:11', 108);
INSERT INTO `t_comment` VALUES (11, 'はじめまして、私は 霧島佳乃 です。AIR から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:12', 109);
INSERT INTO `t_comment` VALUES (12, 'はじめまして、私は 遠野美凪 です。AIR から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:13', 110);
INSERT INTO `t_comment` VALUES (13, 'はじめまして、私は 国崎往人 です。AIR から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:14', 107);
INSERT INTO `t_comment` VALUES (14, 'はじめまして、私は 神尾観鈴 です。AIR から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:15', 108);
INSERT INTO `t_comment` VALUES (15, 'はじめまして、私は 霧島佳乃 です。AIR から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:16', 109);
INSERT INTO `t_comment` VALUES (16, 'はじめまして、私は 遠野美凪 です。AIR から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:17', 110);
INSERT INTO `t_comment` VALUES (17, 'はじめまして、私は 国崎往人 です。AIR から来ました。よろしくお願いします。', 105, '2021-12-31 00:04:18', 107);
INSERT INTO `t_comment` VALUES (18, 'はじめまして、私は 神尾観鈴 です。AIR から来ました。よろしくお願いします。', 105, '2021-12-31 00:04:19', 108);
INSERT INTO `t_comment` VALUES (19, 'はじめまして、私は 霧島佳乃 です。AIR から来ました。よろしくお願いします。', 105, '2021-12-31 00:04:20', 109);
INSERT INTO `t_comment` VALUES (20, 'はじめまして、私は 遠野美凪 です。AIR から来ました。よろしくお願いします。', 105, '2021-12-31 00:04:21', 110);
INSERT INTO `t_comment` VALUES (21, 'はじめまして、私は 国崎往人 です。AIR から来ました。よろしくお願いします。', 106, '2021-12-31 00:04:22', 107);
INSERT INTO `t_comment` VALUES (22, 'はじめまして、私は 神尾観鈴 です。AIR から来ました。よろしくお願いします。', 106, '2021-12-31 00:04:23', 108);
INSERT INTO `t_comment` VALUES (23, 'はじめまして、私は 霧島佳乃 です。AIR から来ました。よろしくお願いします。', 106, '2021-12-31 00:04:24', 109);
INSERT INTO `t_comment` VALUES (24, 'はじめまして、私は 遠野美凪 です。AIR から来ました。よろしくお願いします。', 106, '2021-12-31 00:04:25', 110);
INSERT INTO `t_comment` VALUES (25, 'はじめまして、私は 岡崎朋也 です。CLANNAD から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:26', 111);
INSERT INTO `t_comment` VALUES (26, 'はじめまして、私は 古河 渚 です。CLANNAD から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:27', 112);
INSERT INTO `t_comment` VALUES (27, 'はじめまして、私は 藤林 杏 です。CLANNAD から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:28', 113);
INSERT INTO `t_comment` VALUES (28, 'はじめまして、私は 藤林 椋 です。CLANNAD から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:29', 114);
INSERT INTO `t_comment` VALUES (29, 'はじめまして、私は 坂上智代 です。CLANNAD から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:30', 115);
INSERT INTO `t_comment` VALUES (30, 'はじめまして、私は 伊吹風子 です。CLANNAD から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:31', 116);
INSERT INTO `t_comment` VALUES (31, 'はじめまして、私は 一ノ瀬ことみ です。CLANNAD から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:32', 117);
INSERT INTO `t_comment` VALUES (32, 'はじめまして、私は 春原陽平 です。CLANNAD から来ました。よろしくお願いします。', 101, '2021-12-31 00:04:33', 118);
INSERT INTO `t_comment` VALUES (33, 'はじめまして、私は 岡崎朋也 です。CLANNAD から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:34', 111);
INSERT INTO `t_comment` VALUES (34, 'はじめまして、私は 古河 渚 です。CLANNAD から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:35', 112);
INSERT INTO `t_comment` VALUES (35, 'はじめまして、私は 藤林 杏 です。CLANNAD から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:36', 113);
INSERT INTO `t_comment` VALUES (36, 'はじめまして、私は 藤林 椋 です。CLANNAD から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:37', 114);
INSERT INTO `t_comment` VALUES (37, 'はじめまして、私は 坂上智代 です。CLANNAD から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:38', 115);
INSERT INTO `t_comment` VALUES (38, 'はじめまして、私は 伊吹風子 です。CLANNAD から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:39', 116);
INSERT INTO `t_comment` VALUES (39, 'はじめまして、私は 一ノ瀬ことみ です。CLANNAD から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:40', 117);
INSERT INTO `t_comment` VALUES (40, 'はじめまして、私は 春原陽平 です。CLANNAD から来ました。よろしくお願いします。', 102, '2021-12-31 00:04:41', 118);
INSERT INTO `t_comment` VALUES (41, 'はじめまして、私は 岡崎朋也 です。CLANNAD から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:42', 111);
INSERT INTO `t_comment` VALUES (42, 'はじめまして、私は 古河 渚 です。CLANNAD から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:43', 112);
INSERT INTO `t_comment` VALUES (43, 'はじめまして、私は 藤林 杏 です。CLANNAD から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:44', 113);
INSERT INTO `t_comment` VALUES (44, 'はじめまして、私は 藤林 椋 です。CLANNAD から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:45', 114);
INSERT INTO `t_comment` VALUES (45, 'はじめまして、私は 坂上智代 です。CLANNAD から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:46', 115);
INSERT INTO `t_comment` VALUES (46, 'はじめまして、私は 伊吹風子 です。CLANNAD から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:47', 116);
INSERT INTO `t_comment` VALUES (47, 'はじめまして、私は 一ノ瀬ことみ です。CLANNAD から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:48', 117);
INSERT INTO `t_comment` VALUES (48, 'はじめまして、私は 春原陽平 です。CLANNAD から来ました。よろしくお願いします。', 103, '2021-12-31 00:04:49', 118);
INSERT INTO `t_comment` VALUES (49, 'はじめまして、私は 岡崎朋也 です。CLANNAD から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:50', 111);
INSERT INTO `t_comment` VALUES (50, 'はじめまして、私は 古河 渚 です。CLANNAD から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:51', 112);
INSERT INTO `t_comment` VALUES (51, 'はじめまして、私は 藤林 杏 です。CLANNAD から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:52', 113);
INSERT INTO `t_comment` VALUES (52, 'はじめまして、私は 藤林 椋 です。CLANNAD から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:53', 114);
INSERT INTO `t_comment` VALUES (53, 'はじめまして、私は 坂上智代 です。CLANNAD から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:54', 115);
INSERT INTO `t_comment` VALUES (54, 'はじめまして、私は 伊吹風子 です。CLANNAD から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:55', 116);
INSERT INTO `t_comment` VALUES (55, 'はじめまして、私は 一ノ瀬ことみ です。CLANNAD から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:56', 117);
INSERT INTO `t_comment` VALUES (56, 'はじめまして、私は 春原陽平 です。CLANNAD から来ました。よろしくお願いします。', 104, '2021-12-31 00:04:57', 118);
INSERT INTO `t_comment` VALUES (57, 'はじめまして、私は 岡崎朋也 です。CLANNAD から来ました。よろしくお願いします。', 105, '2021-12-31 00:04:58', 111);
INSERT INTO `t_comment` VALUES (58, 'はじめまして、私は 古河 渚 です。CLANNAD から来ました。よろしくお願いします。', 105, '2021-12-31 00:04:59', 112);
INSERT INTO `t_comment` VALUES (59, 'はじめまして、私は 藤林 杏 です。CLANNAD から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:00', 113);
INSERT INTO `t_comment` VALUES (60, 'はじめまして、私は 藤林 椋 です。CLANNAD から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:01', 114);
INSERT INTO `t_comment` VALUES (61, 'はじめまして、私は 坂上智代 です。CLANNAD から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:02', 115);
INSERT INTO `t_comment` VALUES (62, 'はじめまして、私は 伊吹風子 です。CLANNAD から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:03', 116);
INSERT INTO `t_comment` VALUES (63, 'はじめまして、私は 一ノ瀬ことみ です。CLANNAD から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:04', 117);
INSERT INTO `t_comment` VALUES (64, 'はじめまして、私は 春原陽平 です。CLANNAD から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:05', 118);
INSERT INTO `t_comment` VALUES (65, 'はじめまして、私は 岡崎朋也 です。CLANNAD から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:06', 111);
INSERT INTO `t_comment` VALUES (66, 'はじめまして、私は 古河 渚 です。CLANNAD から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:07', 112);
INSERT INTO `t_comment` VALUES (67, 'はじめまして、私は 藤林 杏 です。CLANNAD から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:08', 113);
INSERT INTO `t_comment` VALUES (68, 'はじめまして、私は 藤林 椋 です。CLANNAD から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:09', 114);
INSERT INTO `t_comment` VALUES (69, 'はじめまして、私は 坂上智代 です。CLANNAD から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:10', 115);
INSERT INTO `t_comment` VALUES (70, 'はじめまして、私は 伊吹風子 です。CLANNAD から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:11', 116);
INSERT INTO `t_comment` VALUES (71, 'はじめまして、私は 一ノ瀬ことみ です。CLANNAD から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:12', 117);
INSERT INTO `t_comment` VALUES (72, 'はじめまして、私は 春原陽平 です。CLANNAD から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:13', 118);
INSERT INTO `t_comment` VALUES (73, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 101, '2021-12-31 00:05:14', 119);
INSERT INTO `t_comment` VALUES (74, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 101, '2021-12-31 00:05:15', 120);
INSERT INTO `t_comment` VALUES (75, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 101, '2021-12-31 00:05:16', 121);
INSERT INTO `t_comment` VALUES (76, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 101, '2021-12-31 00:05:17', 122);
INSERT INTO `t_comment` VALUES (77, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 101, '2021-12-31 00:05:18', 123);
INSERT INTO `t_comment` VALUES (78, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 101, '2021-12-31 00:05:19', 124);
INSERT INTO `t_comment` VALUES (79, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 102, '2021-12-31 00:05:20', 119);
INSERT INTO `t_comment` VALUES (80, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 102, '2021-12-31 00:05:21', 120);
INSERT INTO `t_comment` VALUES (81, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 102, '2021-12-31 00:05:22', 121);
INSERT INTO `t_comment` VALUES (82, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 102, '2021-12-31 00:05:23', 122);
INSERT INTO `t_comment` VALUES (83, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 102, '2021-12-31 00:05:24', 123);
INSERT INTO `t_comment` VALUES (84, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 102, '2021-12-31 00:05:25', 124);
INSERT INTO `t_comment` VALUES (85, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 103, '2021-12-31 00:05:26', 119);
INSERT INTO `t_comment` VALUES (86, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 103, '2021-12-31 00:05:27', 120);
INSERT INTO `t_comment` VALUES (87, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 103, '2021-12-31 00:05:28', 121);
INSERT INTO `t_comment` VALUES (88, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 103, '2021-12-31 00:05:29', 122);
INSERT INTO `t_comment` VALUES (89, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 103, '2021-12-31 00:05:30', 123);
INSERT INTO `t_comment` VALUES (90, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 103, '2021-12-31 00:05:31', 124);
INSERT INTO `t_comment` VALUES (91, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 104, '2021-12-31 00:05:32', 119);
INSERT INTO `t_comment` VALUES (92, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 104, '2021-12-31 00:05:33', 120);
INSERT INTO `t_comment` VALUES (93, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 104, '2021-12-31 00:05:34', 121);
INSERT INTO `t_comment` VALUES (94, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 104, '2021-12-31 00:05:35', 122);
INSERT INTO `t_comment` VALUES (95, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 104, '2021-12-31 00:05:36', 123);
INSERT INTO `t_comment` VALUES (96, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 104, '2021-12-31 00:05:37', 124);
INSERT INTO `t_comment` VALUES (97, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:38', 119);
INSERT INTO `t_comment` VALUES (98, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:39', 120);
INSERT INTO `t_comment` VALUES (99, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:40', 121);
INSERT INTO `t_comment` VALUES (100, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:41', 122);
INSERT INTO `t_comment` VALUES (101, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:42', 123);
INSERT INTO `t_comment` VALUES (102, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 105, '2021-12-31 00:05:43', 124);
INSERT INTO `t_comment` VALUES (103, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:44', 119);
INSERT INTO `t_comment` VALUES (104, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:45', 120);
INSERT INTO `t_comment` VALUES (105, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:46', 121);
INSERT INTO `t_comment` VALUES (106, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:47', 122);
INSERT INTO `t_comment` VALUES (107, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:48', 123);
INSERT INTO `t_comment` VALUES (108, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 106, '2021-12-31 00:05:49', 124);
INSERT INTO `t_comment` VALUES (109, 'はじめまして、私は 岡崎朋也 です。CLANNAD から来ました。よろしくお願いします。', 107, '2021-12-31 00:05:50', 111);
INSERT INTO `t_comment` VALUES (110, 'はじめまして、私は 古河 渚 です。CLANNAD から来ました。よろしくお願いします。', 107, '2021-12-31 00:05:51', 112);
INSERT INTO `t_comment` VALUES (111, 'はじめまして、私は 藤林 杏 です。CLANNAD から来ました。よろしくお願いします。', 107, '2021-12-31 00:05:52', 113);
INSERT INTO `t_comment` VALUES (112, 'はじめまして、私は 藤林 椋 です。CLANNAD から来ました。よろしくお願いします。', 107, '2021-12-31 00:05:53', 114);
INSERT INTO `t_comment` VALUES (113, 'はじめまして、私は 坂上智代 です。CLANNAD から来ました。よろしくお願いします。', 107, '2021-12-31 00:05:54', 115);
INSERT INTO `t_comment` VALUES (114, 'はじめまして、私は 伊吹風子 です。CLANNAD から来ました。よろしくお願いします。', 107, '2021-12-31 00:05:55', 116);
INSERT INTO `t_comment` VALUES (115, 'はじめまして、私は 一ノ瀬ことみ です。CLANNAD から来ました。よろしくお願いします。', 107, '2021-12-31 00:05:56', 117);
INSERT INTO `t_comment` VALUES (116, 'はじめまして、私は 春原陽平 です。CLANNAD から来ました。よろしくお願いします。', 107, '2021-12-31 00:05:57', 118);
INSERT INTO `t_comment` VALUES (117, 'はじめまして、私は 岡崎朋也 です。CLANNAD から来ました。よろしくお願いします。', 108, '2021-12-31 00:05:58', 111);
INSERT INTO `t_comment` VALUES (118, 'はじめまして、私は 古河 渚 です。CLANNAD から来ました。よろしくお願いします。', 108, '2021-12-31 00:05:59', 112);
INSERT INTO `t_comment` VALUES (119, 'はじめまして、私は 藤林 杏 です。CLANNAD から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:00', 113);
INSERT INTO `t_comment` VALUES (120, 'はじめまして、私は 藤林 椋 です。CLANNAD から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:01', 114);
INSERT INTO `t_comment` VALUES (121, 'はじめまして、私は 坂上智代 です。CLANNAD から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:02', 115);
INSERT INTO `t_comment` VALUES (122, 'はじめまして、私は 伊吹風子 です。CLANNAD から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:03', 116);
INSERT INTO `t_comment` VALUES (123, 'はじめまして、私は 一ノ瀬ことみ です。CLANNAD から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:04', 117);
INSERT INTO `t_comment` VALUES (124, 'はじめまして、私は 春原陽平 です。CLANNAD から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:05', 118);
INSERT INTO `t_comment` VALUES (125, 'はじめまして、私は 岡崎朋也 です。CLANNAD から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:06', 111);
INSERT INTO `t_comment` VALUES (126, 'はじめまして、私は 古河 渚 です。CLANNAD から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:07', 112);
INSERT INTO `t_comment` VALUES (127, 'はじめまして、私は 藤林 杏 です。CLANNAD から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:08', 113);
INSERT INTO `t_comment` VALUES (128, 'はじめまして、私は 藤林 椋 です。CLANNAD から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:09', 114);
INSERT INTO `t_comment` VALUES (129, 'はじめまして、私は 坂上智代 です。CLANNAD から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:10', 115);
INSERT INTO `t_comment` VALUES (130, 'はじめまして、私は 伊吹風子 です。CLANNAD から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:11', 116);
INSERT INTO `t_comment` VALUES (131, 'はじめまして、私は 一ノ瀬ことみ です。CLANNAD から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:12', 117);
INSERT INTO `t_comment` VALUES (132, 'はじめまして、私は 春原陽平 です。CLANNAD から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:13', 118);
INSERT INTO `t_comment` VALUES (133, 'はじめまして、私は 岡崎朋也 です。CLANNAD から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:14', 111);
INSERT INTO `t_comment` VALUES (134, 'はじめまして、私は 古河 渚 です。CLANNAD から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:15', 112);
INSERT INTO `t_comment` VALUES (135, 'はじめまして、私は 藤林 杏 です。CLANNAD から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:16', 113);
INSERT INTO `t_comment` VALUES (136, 'はじめまして、私は 藤林 椋 です。CLANNAD から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:17', 114);
INSERT INTO `t_comment` VALUES (137, 'はじめまして、私は 坂上智代 です。CLANNAD から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:18', 115);
INSERT INTO `t_comment` VALUES (138, 'はじめまして、私は 伊吹風子 です。CLANNAD から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:19', 116);
INSERT INTO `t_comment` VALUES (139, 'はじめまして、私は 一ノ瀬ことみ です。CLANNAD から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:20', 117);
INSERT INTO `t_comment` VALUES (140, 'はじめまして、私は 春原陽平 です。CLANNAD から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:21', 118);
INSERT INTO `t_comment` VALUES (141, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 107, '2021-12-31 00:06:22', 119);
INSERT INTO `t_comment` VALUES (142, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 107, '2021-12-31 00:06:23', 120);
INSERT INTO `t_comment` VALUES (143, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 107, '2021-12-31 00:06:24', 121);
INSERT INTO `t_comment` VALUES (144, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 107, '2021-12-31 00:06:25', 122);
INSERT INTO `t_comment` VALUES (145, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 107, '2021-12-31 00:06:26', 123);
INSERT INTO `t_comment` VALUES (146, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 107, '2021-12-31 00:06:27', 124);
INSERT INTO `t_comment` VALUES (147, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:28', 119);
INSERT INTO `t_comment` VALUES (148, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:29', 120);
INSERT INTO `t_comment` VALUES (149, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:30', 121);
INSERT INTO `t_comment` VALUES (150, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:31', 122);
INSERT INTO `t_comment` VALUES (151, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:32', 123);
INSERT INTO `t_comment` VALUES (152, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 108, '2021-12-31 00:06:33', 124);
INSERT INTO `t_comment` VALUES (153, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:34', 119);
INSERT INTO `t_comment` VALUES (154, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:35', 120);
INSERT INTO `t_comment` VALUES (155, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:36', 121);
INSERT INTO `t_comment` VALUES (156, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:37', 122);
INSERT INTO `t_comment` VALUES (157, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:38', 123);
INSERT INTO `t_comment` VALUES (158, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 109, '2021-12-31 00:06:39', 124);
INSERT INTO `t_comment` VALUES (159, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:40', 119);
INSERT INTO `t_comment` VALUES (160, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:41', 120);
INSERT INTO `t_comment` VALUES (161, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:42', 121);
INSERT INTO `t_comment` VALUES (162, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:43', 122);
INSERT INTO `t_comment` VALUES (163, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:44', 123);
INSERT INTO `t_comment` VALUES (164, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 110, '2021-12-31 00:06:45', 124);
INSERT INTO `t_comment` VALUES (165, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 111, '2021-12-31 00:06:46', 119);
INSERT INTO `t_comment` VALUES (166, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 111, '2021-12-31 00:06:47', 120);
INSERT INTO `t_comment` VALUES (167, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 111, '2021-12-31 00:06:48', 121);
INSERT INTO `t_comment` VALUES (168, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 111, '2021-12-31 00:06:49', 122);
INSERT INTO `t_comment` VALUES (169, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 111, '2021-12-31 00:06:50', 123);
INSERT INTO `t_comment` VALUES (170, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 111, '2021-12-31 00:06:51', 124);
INSERT INTO `t_comment` VALUES (171, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 112, '2021-12-31 00:06:52', 119);
INSERT INTO `t_comment` VALUES (172, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 112, '2021-12-31 00:06:53', 120);
INSERT INTO `t_comment` VALUES (173, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 112, '2021-12-31 00:06:54', 121);
INSERT INTO `t_comment` VALUES (174, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 112, '2021-12-31 00:06:55', 122);
INSERT INTO `t_comment` VALUES (175, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 112, '2021-12-31 00:06:56', 123);
INSERT INTO `t_comment` VALUES (176, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 112, '2021-12-31 00:06:57', 124);
INSERT INTO `t_comment` VALUES (177, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 113, '2021-12-31 00:06:58', 119);
INSERT INTO `t_comment` VALUES (178, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 113, '2021-12-31 00:06:59', 120);
INSERT INTO `t_comment` VALUES (179, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 113, '2021-12-31 00:07:00', 121);
INSERT INTO `t_comment` VALUES (180, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 113, '2021-12-31 00:07:01', 122);
INSERT INTO `t_comment` VALUES (181, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 113, '2021-12-31 00:07:02', 123);
INSERT INTO `t_comment` VALUES (182, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 113, '2021-12-31 00:07:03', 124);
INSERT INTO `t_comment` VALUES (183, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 114, '2021-12-31 00:07:04', 119);
INSERT INTO `t_comment` VALUES (184, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 114, '2021-12-31 00:07:05', 120);
INSERT INTO `t_comment` VALUES (185, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 114, '2021-12-31 00:07:06', 121);
INSERT INTO `t_comment` VALUES (186, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 114, '2021-12-31 00:07:07', 122);
INSERT INTO `t_comment` VALUES (187, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 114, '2021-12-31 00:07:08', 123);
INSERT INTO `t_comment` VALUES (188, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 114, '2021-12-31 00:07:09', 124);
INSERT INTO `t_comment` VALUES (189, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 115, '2021-12-31 00:07:10', 119);
INSERT INTO `t_comment` VALUES (190, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 115, '2021-12-31 00:07:11', 120);
INSERT INTO `t_comment` VALUES (191, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 115, '2021-12-31 00:07:12', 121);
INSERT INTO `t_comment` VALUES (192, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 115, '2021-12-31 00:07:13', 122);
INSERT INTO `t_comment` VALUES (193, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 115, '2021-12-31 00:07:14', 123);
INSERT INTO `t_comment` VALUES (194, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 115, '2021-12-31 00:07:15', 124);
INSERT INTO `t_comment` VALUES (195, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 116, '2021-12-31 00:07:16', 119);
INSERT INTO `t_comment` VALUES (196, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 116, '2021-12-31 00:07:17', 120);
INSERT INTO `t_comment` VALUES (197, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 116, '2021-12-31 00:07:18', 121);
INSERT INTO `t_comment` VALUES (198, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 116, '2021-12-31 00:07:19', 122);
INSERT INTO `t_comment` VALUES (199, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 116, '2021-12-31 00:07:20', 123);
INSERT INTO `t_comment` VALUES (200, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 116, '2021-12-31 00:07:21', 124);
INSERT INTO `t_comment` VALUES (201, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 117, '2021-12-31 00:07:22', 119);
INSERT INTO `t_comment` VALUES (202, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 117, '2021-12-31 00:07:23', 120);
INSERT INTO `t_comment` VALUES (203, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 117, '2021-12-31 00:07:24', 121);
INSERT INTO `t_comment` VALUES (204, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 117, '2021-12-31 00:07:25', 122);
INSERT INTO `t_comment` VALUES (205, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 117, '2021-12-31 00:07:26', 123);
INSERT INTO `t_comment` VALUES (206, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 117, '2021-12-31 00:07:27', 124);
INSERT INTO `t_comment` VALUES (207, 'はじめまして、私は 音無結弦 です。Angel Beats! から来ました。よろしくお願いします。', 118, '2021-12-31 00:07:28', 119);
INSERT INTO `t_comment` VALUES (208, 'はじめまして、私は 仲村ゆり です。Angel Beats! から来ました。よろしくお願いします。', 118, '2021-12-31 00:07:29', 120);
INSERT INTO `t_comment` VALUES (209, 'はじめまして、私は 天使 です。Angel Beats! から来ました。よろしくお願いします。', 118, '2021-12-31 00:07:30', 121);
INSERT INTO `t_comment` VALUES (210, 'はじめまして、私は 日向秀樹 です。Angel Beats! から来ました。よろしくお願いします。', 118, '2021-12-31 00:07:31', 122);
INSERT INTO `t_comment` VALUES (211, 'はじめまして、私は 直井文人 です。Angel Beats! から来ました。よろしくお願いします。', 118, '2021-12-31 00:07:32', 123);
INSERT INTO `t_comment` VALUES (212, 'はじめまして、私は ユイ です。Angel Beats! から来ました。よろしくお願いします。', 118, '2021-12-31 00:07:33', 124);

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
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_chat` AS select `c`.`chat_id` AS `chat_id`,`c`.`userid_from` AS `userid_from`,`c`.`userid_to` AS `userid_to`,`c`.`message_content` AS `message_content`,`c`.`createtime` AS `createtime`,`u`.`username` AS `from_username`,`u`.`avatar` AS `from_avatar` from (`t_chat` `c` join `t_user` `u` on((`c`.`userid_from` = `u`.`userid`)));

-- ----------------------------
-- View structure for v_comment
-- ----------------------------
DROP VIEW IF EXISTS `v_comment`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_comment` AS select `t_comment`.`comment_id` AS `comment_id`,`t_comment`.`comment_content` AS `comment_content`,`t_comment`.`blog_id` AS `blog_id`,`t_comment`.`createtime` AS `createtime`,`t_comment`.`userid` AS `userid`,`t_user`.`username` AS `username`,`t_user`.`avatar` AS `avatar` from (`t_comment` join `t_user` on((`t_comment`.`userid` = `t_user`.`userid`)));

SET FOREIGN_KEY_CHECKS = 1;
