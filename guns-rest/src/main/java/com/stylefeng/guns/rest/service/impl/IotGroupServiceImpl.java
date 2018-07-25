package com.stylefeng.guns.rest.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.model.IotGroup;
import com.stylefeng.guns.rest.persistence.IotGroupMapper;
import com.stylefeng.guns.rest.service.IIotGroupService;

/**
 * <p>
 * 设备分组表 服务实现类
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-25
 */
@Service
public class IotGroupServiceImpl extends ServiceImpl<IotGroupMapper, IotGroup> implements IIotGroupService {

}
