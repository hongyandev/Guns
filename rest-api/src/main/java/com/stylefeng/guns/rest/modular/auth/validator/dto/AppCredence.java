package com.stylefeng.guns.rest.modular.auth.validator.dto;

public interface AppCredence {
    String getId();
    String getAppKey();
    Long getTime();
    String getBody();
    String getSign();
}
