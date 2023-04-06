package com.xftxyz.rocketblog.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.pojo.User;

import jakarta.servlet.http.HttpSession;

public class Utils {

    // 随机数生成器
    private static Random random = new Random();

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

    public static String getImageUrl(Map<String, List<String>> map) {
        String baseUrl = "https://ip.ntrqq.net/images/";
        if (map == null || map.isEmpty()) {
            return null;
        }
        int entryIndex = random.nextInt(map.size());
        Map.Entry<String, List<String>> entry = map.entrySet().stream().skip(entryIndex).findFirst().get();
        String checked = entry.getKey();
        String queryUrl = baseUrl + checked + ".png?";
        String randomString = Long.toString(random.nextLong(), 36).substring(2);

        List<String> list = entry.getValue();
        int listSize = list.size();
        if (listSize < 1) {
            return queryUrl + randomString;
        }
        int listIndex = random.nextInt();
        String diyWords = list.get(listIndex);

        String encodeWords = null;
        try {
            encodeWords = Base64.getEncoder().encodeToString(URLEncoder.encode(diyWords, "UTF-8").getBytes());
        } catch (UnsupportedEncodingException e) {
            encodeWords = "JUU2JUFDJUEyJUU4JUJGJThFJUVGJUJDJTgx"; // "欢迎"
        }
        queryUrl += "wd=" + encodeWords + "&r=" + randomString;

        return queryUrl;
    }

}
