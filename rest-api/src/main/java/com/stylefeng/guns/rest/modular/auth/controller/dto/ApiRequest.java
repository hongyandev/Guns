package com.stylefeng.guns.rest.modular.auth.controller.dto;

import com.stylefeng.guns.rest.modular.auth.validator.dto.AppCredence;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Setter
public class ApiRequest implements AppCredence {
    @NotBlank
    private String id;
    @NotBlank
    private String appKey;
    private String body;
    @Range(min = 1)
    private Long time;
    @NotBlank
    private String sign;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getAppKey() {
        return this.appKey;
    }

    @Override
    public Long getTime() {
        return this.time;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public String getSign() {
        return this.sign;
    }
}
