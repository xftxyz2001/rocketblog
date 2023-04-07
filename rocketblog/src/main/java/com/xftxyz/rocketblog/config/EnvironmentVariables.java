package com.xftxyz.rocketblog.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EnvironmentVariables {

    // 上传文件的目录
    String UPLOAD_DIRECTORY = "uploads";

    // COOKIE中token的key
    String COOKIE_TOKEN = "token";
    // SESSION中用户的key
    String SESSION_USER = "user";

    // 验证码位数
    int CODE_LENGTH = 6;
    // SESSION中验证码的key
    String SESSION_CODE = "code";

    // 验证码邮件主题
    String EMAIL_CODE_SUBJECT = "火箭博客验证码";
    // 验证码邮件内容
    String EMAIL_CODE_CONTENT = "您的验证码为：";

    // 找回密码邮件主题
    String EMAIL_FORGET_SUBJECT = "火箭博客找回密码";
    // 找回密码邮件内容
    String EMAIL_FORGET_CONTENT = "您的密码是：";

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
            put("titan", List.of("666"));
            // 縁の空
            put("yosuga", List.of("In solitude, where we are least alone."));
            // 我妻由乃
            put("yuno", List.of());
            // 島風
            put("shimakaze", List.of());
            // V家
            put("vocaloid", List.of());
            // 夏娜
            put("shana", List.of());
            // EVA
            put("eva", List.of());
            // 秒速5センチメートル
            put("gosec", List.of());
            // 君の名は
            put("kiminona", List.of());
            // クズの本懐
            put("kuzunohongai", List.of());
            // 坂本ですが
            put("sakamoto", List.of());
            // 井上円了
            put("enryo", List.of());
        }
    };

}
