package com.example.notes.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Data：相当于Getter+Setter+ToString+@RequiredArgsConstructor。
 * EqualsAndHashCode：用在类上，自动生成equals方法和hashCode方法。
 * Accessors：暂不清楚
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;

}
