package com.stylefeng.guns.core.enums;

public enum IotType {

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

    IotType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static IotType getIotType(Integer code) {
        for(IotType iot : IotType.values()) {
            if (iot.getCode() == code) {
                return iot;
            }
        }
        return null;
    }

}
