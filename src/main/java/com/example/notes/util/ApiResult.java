package com.example.notes.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 统一返回结果
 *
 * @author Lzk
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 2757120139568271793L;

    private Integer code;
    private String message;
    private T data;
}
