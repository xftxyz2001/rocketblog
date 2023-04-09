package com.xftxyz.rocketblog.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EnvironmentVariables {
    // 评论、消息最大长度
    int MAX_LENGTH = 500;

    // 默认分页大小
    String DEFAULT_PAGE_SIZE = "5";

    // 上传文件的目录
    String UPLOAD_DIRECTORY = "uploads";

    // COOKIE中token的key
    String COOKIE_TOKEN = "token";
    // COOKIE中token的过期时间（秒）
    int COOKIE_TOKEN_EXPIRE = 60 * 60 * 24 * 7; // 7天
    // SESSION中用户的key
    String SESSION_USER = "user";

    // 验证码位数
    int CODE_LENGTH = 6;
    // 验证码过期时间（秒）
    long CODE_EXPIRE_TIME = 5 * 60; // 5分钟
    // 验证码邮件主题
    String EMAIL_CODE_SUBJECT = "火箭博客验证码";
    // 验证码邮件内容
    String EMAIL_CODE_CONTENT = "您的验证码为: %s，有效期为" + (CODE_EXPIRE_TIME / 60) + "分钟，请尽快验证。如非本人操作，请忽略此邮件。";

    // 找回密码邮件主题
    String EMAIL_FORGET_SUBJECT = "火箭博客找回密码";
    // 找回密码邮件内容
    String EMAIL_FORGET_CONTENT = "您的密码是: %s";

    // 密码掩码
    String PASSWORD_MASK = "********";

    // 图片对应签名s
    Map<String, List<String>> IMAGE_SIGNATURE = new HashMap<>() {
        {
            // 雨宮優子
            put("amamiya", List.of("让我们……再一次与夕君见面…！"));
            // 立華奏
            put("angel", List.of("音无结弦之时，天使跃动之心。立于浮华之世，奏响天籁之音。",
                    "也请让我相信，你一直以来所相信的事吧：活着，是件美好的事"));
            // LoveLive
            put("lovelive", List.of("みんなで叶えた物語"));
            // 御坂美琴
            put("misaka", List.of("你指尖跃动的电光，是我此生不灭的信仰，唯我超电磁炮永世长存！",
                    "君の指先を舞ってる電光は、私の一生変わらない信仰であり、このレールガンだけが永遠に生きてる！",
                    "That spark glittering on your fingertips is my unshakeable faith for life! May my Railgun be lasting for eternity!",
                    "就算是我，也可以战斗，就算是我，也可以成为你的力量！"));
            // 小鳥遊六花
            put("rika", List.of("爆裂吧现实！粉碎吧精神！放逐这个世界",
                    "爆裂吧，现实！粉碎吧，精神！Vanishment This World!",
                    "爆ぜろリアル！弾けろシナプス！パニッシュメント・ディス・ワールド!"));
            // 進撃の巨人
            put("titan", List.of("什么都无法舍弃的人,什么也改变不了."));
            // 縁の空
            put("yosuga", List.of("In solitude, where we are least alone."));
            // 我妻由乃
            put("yuno", List.of("啊雪,我们一起去杀尸体吧"));
            // 島風
            put("shimakaze", List.of("駆逐舰岛风です。スピードなら谁にも负けません。速きこと、岛风の如し、です!",
                    "我是驱逐舰岛风。速度的话不会输给任何人的。疾如岛风，de-su!"));
            // V家
            put("vocaloid", List.of("行きましょう!"));
            // 夏娜
            put("shana", List.of("用别人的回忆充实自己是没有意义的。"));
            // EVA
            put("eva", List.of("这一次，我一定要让你幸福。",
                    "神不为者，人为之。",
                    "只要活着，哪里都是天堂。"));
            // 秒速5センチメートル
            put("gosec", List.of("时间明显的含着恶意，朝我的头上慢慢的流过，我只好咬紧牙根，拼命的忍住不让眼泪掉下来。",
                    "樱花下落的速度，依旧是秒速五厘米。而不论我们之间通过千条短信，我们之间的距离也不会拉近一厘米。"));
            // 君の名は
            put("kiminona", List.of("只要记住你的名字，不管你在世界的哪个地方。我一定会，去见你。"));
            // クズの本懐
            put("kuzunohongai", List.of("爱得如此痛心，爱得如此恐惧！",
                    "最宝贵的东西 当然最想珍惜 最简单的事情 却成了最难办到的事情"));
            // 坂本ですが
            put("sakamoto", List.of("人们在紧急情况下达成共同的目的，自然而然的就会拉近彼此的距离。",
                    "在下坂本，有何贵干"));
            // 井上円了
            put("enryo", List.of("欲知星海之秘，力量远胜猜想！",
                    "肌腱替代了活塞；血肉替代了钢铁；鲜血只是生物冷却剂。否认这点并回避它不只是机械正统派——这是愚蠢。"));
        }
    };

}
