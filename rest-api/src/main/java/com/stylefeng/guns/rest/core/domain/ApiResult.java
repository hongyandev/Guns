package com.stylefeng.guns.rest.core.domain;

import lombok.Data;

@Data
public class ApiResult<T> extends Result<T> {
    private String id;
}
