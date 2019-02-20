package com.example.notes.exception;

import com.example.notes.util.ApiResult;
import com.example.notes.util.ApiResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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
        return resultFormat(e);
    }

    /**
     * 处理实体字段校验不通过异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrorList = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrorList) {
            builder.append(error.getDefaultMessage());
        }
        return ApiResultUtil.error(10006, "失败", builder.toString());
    }

    /**
     * 返回统一格式
     */
    private <T extends Throwable> ApiResult resultFormat(T t) {
        logger.error(t.getMessage());
        logger.error(Arrays.toString(t.getStackTrace()));
        return ApiResultUtil.error(10099, "失败", t.getMessage());
    }

}