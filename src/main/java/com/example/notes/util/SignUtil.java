package com.example.notes.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 简单签名和验证签名的工具类
 *
 * @author Lzk
 */
public class SignUtil {

    /**
     * 验证不需要密钥的签名
     *
     * @param map 待验证数据
     * @return 签名是否正确
     */
    public static boolean validateSign(Map<String, String> map) {
        String mapSign = map.get("sign");
        if (StringUtils.isEmpty(mapSign)) {
            return false;
        }
        List<String> keys = new ArrayList<>(map.keySet());
        //排除sign参数
        keys.remove("sign");
        //字典排序
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            //拼接字符串
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        String signString = sb.toString();
        //去除最后一个'&'
        signString = StringUtils.substring(signString, 0, signString.length() - 1);
        //MD5加密并转成大写
        String sign = DigestUtils.md5Hex(signString).toUpperCase();
        //比较签名
        return StringUtils.equals(sign, mapSign);
    }

    /**
     * 验证需要密钥的签名
     *
     * @param map    待验证数据
     * @param secret 签名密钥
     * @return 签名是否正确
     */
    public static boolean validateSignWithSecret(Map<String, String> map, String secret) {
        String mapSign = map.get("sign");
        if (StringUtils.isEmpty(mapSign)) {
            return false;
        }
        List<String> keys = new ArrayList<>(map.keySet());
        //排除sign参数
        keys.remove("sign");
        //字典排序
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            //拼接字符串
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        String signString = sb.toString();
        //去除最后一个'&'
        signString = StringUtils.substring(signString, 0, signString.length() - 1);
        //混合密钥md5
        String sign = DigestUtils.md5Hex(signString + secret).toUpperCase();
        //比较签名
        return StringUtils.equals(sign, mapSign);
    }

    /**
     * 不需要密钥的签名方法
     *
     * @param map 待签名数据
     * @return 生成签名
     */
    public static String sign(Map<String, String> map) {
        List<String> keys = new ArrayList<>(map.keySet());
        //字典排序
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            //拼接字符串
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        String signString = sb.toString();
        //去除最后一个'&'
        signString = StringUtils.substring(signString, 0, signString.length() - 1);
        //MD5加密并转成大写
        return DigestUtils.md5Hex(signString).toUpperCase();
    }

    /**
     * 需要密钥的签名方法
     *
     * @param map    待签名数据
     * @param secret 签名密钥
     * @return 生成签名
     */
    public static String signWithSecret(Map<String, String> map, String secret) {
        List<String> keys = new ArrayList<>(map.keySet());
        //字典排序
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            //拼接字符串
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        //待签名数据
        String signString = sb.toString();
        //去除最后一个'&'
        signString = StringUtils.substring(signString, 0, signString.length() - 1);
        //MD5加密并转成大写
        return DigestUtils.md5Hex(signString + secret).toUpperCase();
    }

    /**
     * 生成随机数
     *
     * @return 随机数
     */
    public static String generateNonceStr() {
        Long l = new Random().nextLong();
        if (l < 0) {
            l = -l;
        }
        return String.valueOf(l);
    }

}
