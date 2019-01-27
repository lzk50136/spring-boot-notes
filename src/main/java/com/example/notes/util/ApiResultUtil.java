package com.example.notes.util;


public class ApiResultUtil {

    private ApiResultUtil() {

    }

    public static <T> ApiResult success(T t) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(10000);
        apiResult.setMessage("成功");
        apiResult.setData(t);
        return apiResult;
    }

    public static <T> ApiResult success() {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(10000);
        apiResult.setMessage("成功");
        return apiResult;
    }

    public static <T> ApiResult error(T t) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(10001);
        apiResult.setMessage("失败");
        apiResult.setData(t);
        return apiResult;
    }

    public static <T> ApiResult error(int code, T t) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(code);
        apiResult.setMessage("失败");
        apiResult.setData(t);
        return apiResult;
    }

    public static <T> ApiResult error(int code, String message, T t) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        apiResult.setData(t);
        return apiResult;
    }

    public static <T> ApiResult otherError(T t) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(10099);
        apiResult.setMessage("失败");
        apiResult.setData(t);
        return apiResult;
    }

    public static <T> ApiResult show(int code, String message, T t) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        apiResult.setData(t);
        return apiResult;
    }


}
