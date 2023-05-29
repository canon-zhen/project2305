package com.caizhen.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result 类是为了构造 response，主要是响应码
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    //响应码
    private int code;
    private String message;
    private Object result;

    public Result(int code) {
        this.code = code;
    }
}
