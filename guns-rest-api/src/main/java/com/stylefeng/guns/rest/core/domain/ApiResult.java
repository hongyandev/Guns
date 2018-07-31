package com.stylefeng.guns.rest.core.domain;

import lombok.Setter;

@Setter
public class ApiResult<T> implements Result<T> {
    private Integer code;
    private String message;
    private T data;
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public T getData() {
        return data;
    }
}
