package com.stylefeng.guns.rest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.rest.core.domain.Result;
import com.stylefeng.guns.rest.core.utils.ResultUtil;
import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.model.IotGroup;
import com.stylefeng.guns.rest.service.IIotGroupService;
import com.stylefeng.guns.rest.service.IUserIotService;


/**
 * 设备分组控制器
 *
 * @author fengshuonan
 * @Date 2018-07-25 10:16:10
 */
@Controller
@RequestMapping("/iotGroup")
public class IotGroupController extends BaseController {

    @Autowired
    private IIotGroupService iotGroupService;
    
    @Autowired
    private IUserIotService userIotService;

    /**
     * 获取设备分组列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody AppUser user) {
        List<IotGroup> list = iotGroupService.selectList(new EntityWrapper<IotGroup>().eq("userId", user.getUserId()));
        return ResultUtil.success(list);
    }

    /**
     * 新增设备分组
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Object> add(@RequestBody IotGroup iotGroup) {
        iotGroupService.insert(iotGroup);
        return ResultUtil.success();
    }

    /**
     * 删除设备分组
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Object> delete(@RequestBody IotGroup iotGroup) {
        iotGroupService.deleteById(iotGroup.getGroupId());
        return ResultUtil.success();
    }

    /**
     * 修改设备分组
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Object> update(@RequestBody IotGroup iotGroup) {
        iotGroupService.updateById(iotGroup);
        return ResultUtil.success();
    }
    
    /**
     * 分组信息
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Result<Object> detail(@RequestBody IotGroup iotGroup) {
    	IotGroup group = iotGroupService.selectById(iotGroup.getGroupId());
        return ResultUtil.success(group);
    }

}
