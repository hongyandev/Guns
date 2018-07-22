package com.stylefeng.guns.rest.modular.auth.validator.impl;

import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.core.exception.ApiRepsEnum;
import com.stylefeng.guns.rest.model.SecretKey;
import com.stylefeng.guns.rest.modular.auth.validator.IApiValidator;
import com.stylefeng.guns.rest.modular.auth.validator.dto.AppCredence;
import com.stylefeng.guns.rest.persistence.SecretKeyMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class ApiValidator implements IApiValidator {

    @Autowired
    SecretKeyMapper secretKeyMapper;

    @Override
    public SecretKey validate(AppCredence credence) {
        SecretKey secretKey = secretKeyMapper.selectById(credence.getAppKey());
        if(Objects.isNull(secretKey)){
            throw new GunsException(ApiRepsEnum.REQUEST_AUTH_ERROR);
        }
        return secretKey;
    }
}
