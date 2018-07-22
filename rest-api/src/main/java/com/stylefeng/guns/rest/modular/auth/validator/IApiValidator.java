package com.stylefeng.guns.rest.modular.auth.validator;

import com.stylefeng.guns.rest.model.SecretKey;
import com.stylefeng.guns.rest.modular.auth.validator.dto.AppCredence;

public interface IApiValidator {

    SecretKey validate(AppCredence credence);

}
