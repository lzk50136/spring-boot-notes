package com.example.notes.util;


/**
 * 统一返回结果工具类
 *
 * @author Lzk
 */
public class ApiResultUtil {

    private ApiResultUtil() {

    }

    public static <T> ApiResult<T> success(T t) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(10000);
        apiResult.setMessage("成功");
        apiResult.setData(t);
        return apiResult;
    }

    public static <T> ApiResult<T> error(Integer code, String message, T t) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        apiResult.setData(t);
        return apiResult;
    }

}
