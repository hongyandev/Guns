package com.stylefeng.guns.core.common.enums;

public enum IotEnum {

    LIVING(1, "living"),
    HOMELINK(2, "homelink");

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private Integer code;
    private String name;

    IotEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static IotEnum fromCode(Integer code) {
        for(IotEnum iot : IotEnum.values()) {
            if (iot.getCode() == code) {
                return iot;
            }
        }
        return null;
    }

}
