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
 * 异常拦截处理器
 *
 * @author lzk
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
        return ApiResultUtil.error(10006, builder.toString());
    }

    private <T extends Throwable> ApiResult resultFormat(T e) {
        logger.error(e.getMessage());
        logger.error(Arrays.toString(e.getStackTrace()));
        return ApiResultUtil.otherError(e.getMessage());
    }

}