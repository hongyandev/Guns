package com.stylefeng.guns.rest.modular.auth.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.core.util.RedisUtil;
import com.stylefeng.guns.rest.core.config.properties.RestProperties;
import com.stylefeng.guns.rest.modular.auth.model.SecretKey;
import com.stylefeng.guns.rest.modular.auth.security.DataSecurityAction;
import com.stylefeng.guns.rest.core.enums.ApiResultEnum;
import com.stylefeng.guns.rest.modular.auth.service.ISecretKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 带签名的http信息转化器
 *
 * @author fengshuonan
 * @date 2017-08-25 15:42
 */
public class WithSignMessageConverter extends FastJsonHttpMessageConverter {

    @Autowired
    RestProperties restProperties;

    @Autowired
    DataSecurityAction dataSecurityAction;

    @Autowired
    ISecretKeyService secretKeyService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        InputStream in = inputMessage.getBody();
        Object o = JSON.parseObject(in, super.getFastJsonConfig().getCharset(), BaseTransferEntity.class, super.getFastJsonConfig().getFeatures());

        //先转化成原始的对象
        BaseTransferEntity baseTransferEntity = (BaseTransferEntity) o;

        //校验签名
        String appKey = HttpKit.getRequest().getHeader(restProperties.getSignHeader());

        Object s = redisUtil.get("APPKEY_" + appKey);

        SecretKey secretKey = Objects.nonNull(s) ? (SecretKey) s : secretKeyService.selectOne(new EntityWrapper<SecretKey>().eq("appKey", appKey));

        if (Objects.isNull(secretKey)){
            throw new GunsException(ApiResultEnum.REQUEST_AUTH_ERROR);
        }

        if (Objects.isNull(s)) {
            redisUtil.set("APPKEY_" + appKey, secretKey);
        }

        String object = baseTransferEntity.getObject();
        String encrypt = MD5Util.encrypt(object + secretKey.getAppSecret());

        if (!encrypt.equals(baseTransferEntity.getSign())) {
            throw new GunsException(ApiResultEnum.SIGN_ERROR);
        }
        
        String json = dataSecurityAction.unlock(object);
        //校验签名后再转化成应该的对象
        return JSON.parseObject(json, type);
    }
}
