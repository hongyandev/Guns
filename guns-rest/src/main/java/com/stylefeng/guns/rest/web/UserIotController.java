package com.stylefeng.guns.rest.web;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.rest.core.domain.Result;
import com.stylefeng.guns.rest.core.utils.ResultUtil;
import com.stylefeng.guns.rest.model.UserIot;
import com.stylefeng.guns.rest.service.IUserIotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户设备控制器
 *
 * @author fengshuonan
 * @Date 2018-07-25 10:19:25
 */
@Controller
@RequestMapping("/userIot")
public class UserIotController extends BaseController {

    @Autowired
    private IUserIotService userIotService;

    /**
     * 获取用户设备列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody UserIot userIot) {
        List<Map> list = userIotService.listBinding(userIot);
        return ResultUtil.success(list);
    }

    /**
     * 新增用户设备
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Object> add(@RequestBody UserIot userIot) {
        userIotService.insert(userIot);
        return ResultUtil.success();
    }

    /**
     * 删除用户设备
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Object> delete(@RequestBody UserIot userIot) {
        return ResultUtil.success();
    }

    /**
     * 修改用户设备
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Object> update(@RequestBody UserIot userIot) {
        userIotService.updateById(userIot);
        return ResultUtil.success();
    }

    /**
     * 用户设备详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Result<Object> detail(@RequestBody UserIot userIot) {
        return ResultUtil.success();
    }
}
