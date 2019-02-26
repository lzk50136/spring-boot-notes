package com.example.notes.exception;

import com.example.notes.util.ApiResult;
import com.example.notes.util.ApiResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 全局异常拦截处理器
 * ControllerAdvice：控制器增强，将作用在所有注解了RequestMapping的控制器的方法上。
 * ExceptionHandler：应用到所有RequestMapping注解方法，用于全局处理控制器里的异常。
 * InitBinder：应用到所有RequestMapping注解方法，在其执行之前初始化数据绑定器.
 * ModelAttribute：应用到所有RequestMapping注解方法，把值绑定到Model中，使全局RequestMapping可以获取到该值。
 *
 * @author Lzk
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 统一错误
     */
    @ExceptionHandler(Exception.class)
    public ApiResult exception(Exception e) {
        logger.error(e.getMessage());
        logger.error(Arrays.toString(e.getStackTrace()));
        return ApiResultUtil.error(10099, "失败", e.getMessage());
    }

}