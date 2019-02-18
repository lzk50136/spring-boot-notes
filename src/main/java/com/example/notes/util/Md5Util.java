package com.example.notes.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密和验证工具类
 *
 * @author Lzk
 */
public class Md5Util {

    /**
     * MD5方法
     *
     * @param text 明文
     * @return 密文
     */
    public static String md5(String text) {
        return DigestUtils.md5Hex(text);
    }

    /**
     * MD5方法
     *
     * @param text 明文
     * @param key  密钥
     * @return 密文
     */
    public static String md5(String text, String key) {
        return DigestUtils.md5Hex(text + key);
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key  密钥
     * @param md5  密文
     * @return 验证结果
     */
    public static boolean verify(String text, String key, String md5) {
        return md5(text, key).equalsIgnoreCase(md5);
    }

}
