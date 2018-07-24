package com.stylefeng.guns.rest.modular.auth.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.modular.auth.dao.SecretKeyMapper;
import com.stylefeng.guns.rest.modular.auth.model.SecretKey;
import com.stylefeng.guns.rest.modular.auth.service.ISecretKeyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用密钥表 服务实现类
 * </p>
 *
 * @author myc123
 * @since 2018-07-23
 */
@Service
public class SecretKeyServiceImpl extends ServiceImpl<SecretKeyMapper, SecretKey> implements ISecretKeyService {


}
