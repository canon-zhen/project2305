package com.caizhen.commonutils.utils;

import java.util.Random;

/**
 * 随机字符串获取
 * @author caizhen
 * @since 2023-05-17
 */
public class StringUtil {
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
