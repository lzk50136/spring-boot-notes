package com.example.notes.util;


import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * HTTP请求工具类
 *
 * @author Lzk
 */
public class OkHttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);
    private static OkHttpClient okHttpClient;

    static {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    private OkHttpUtil() {

    }

    /**
     * 拼接url和请求参数
     *
     * @param url    待拼接的url
     * @param params 待拼接的请求参数
     * @return 已拼接好的url
     */
    private static String urlJoint(String url, Map<String, String> params) {
        if (url != null) {
            StringBuilder endUrl = new StringBuilder(url);
            if (params != null && params.keySet().size() > 0) {
                boolean isFirst = true;
                Set<Map.Entry<String, String>> entrySet = params.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    if (isFirst && !url.contains("?")) {
                        isFirst = false;
                        endUrl.append("?");
                    } else {
                        endUrl.append("&");
                    }
                    endUrl.append(entry.getKey());
                    endUrl.append("=");
                    endUrl.append(entry.getValue());
                }
            }
            return endUrl.toString();
        }
        return null;
    }

    /**
     * get请求
     *
     * @param url    请求的url
     * @param params 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return 响应体
     */
    public static String getMapParams(String url, Map<String, String> params) {
        url = urlJoint(url, params);
        Request request = new Request.Builder()
                .url(url)
                .build();
        return newCall(request);
    }

    /**
     * 带认证请求头的get请求
     *
     * @param url           请求的url
     * @param params        请求的参数，在浏览器？后面的数据，没有可以传null
     * @param authorization 认证密钥
     * @return 响应体
     */
    public static String getMapParamsForAuthorization(String url, Map<String, String> params, String authorization) {
        url = urlJoint(url, params);
        Request request = new Request.Builder()
                .addHeader("Authorization", authorization)
                .url(url)
                .build();
        return newCall(request);
    }

    /**
     * 发送表单格式数据的post请求
     *
     * @param url    请求url
     * @param params 表单数据
     * @return 响应体
     */
    public static String postMapParams(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return newCall(request);
    }

    /**
     * 发送json格式数据的post请求
     *
     * @param url  请求url
     * @param json json格式数据的字符串
     * @return 响应体
     */
    public static String postJsonParams(String url, String json) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return newCall(request);
    }

    /**
     * 发送xml格式数据的post请求
     *
     * @param url 请求url
     * @param xml xml格式数据的字符串
     * @return 响应体
     */
    public static String postXmlParams(String url, String xml) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return newCall(request);
    }

    /**
     * 获取响应体的二进制流的发送json格式数据的post请求
     *
     * @param url  请求url
     * @param json 请求json数据
     * @return 响应体二进制流
     */
    public static byte[] postJsonByte(String url, String json) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        byte[] bytes = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                bytes = response.body().bytes();
            } else {
                logger.info(response.toString());
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return bytes;
    }

    /**
     * 发送http请求
     *
     * @param request request对象
     * @return 响应体
     */
    private static String newCall(Request request) {
        Response response = null;
        String string = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                string = response.body().string();
            } else {
                logger.info(response.toString());
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return string;
    }

}