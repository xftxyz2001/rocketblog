package com.xftxyz.rocketblog.util;

import java.util.Random;
import java.util.UUID;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.pojo.User;

import jakarta.servlet.http.HttpSession;

public class Utils {

    // 随机数生成器
    public static Random random = new Random();

    // 生产UUID
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    // 获取当前用户
    public static User currentUser(HttpSession session) {
        return (User) session.getAttribute(EnvironmentVariables.SESSION_USER);
    }

    // 生成随机字符串
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // int number = (int) (Math.random() * 62);
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
