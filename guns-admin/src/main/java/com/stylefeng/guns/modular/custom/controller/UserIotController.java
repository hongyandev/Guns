package com.stylefeng.guns.modular.custom.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.custom.model.UserIot;
import com.stylefeng.guns.modular.custom.service.IUserIotService;

/**
 * 用户设备控制器
 *
 * @author fengshuonan
 * @Date 2018-07-25 10:19:25
 */
@Controller
@RequestMapping("/userIot")
public class UserIotController extends BaseController {

    private String PREFIX = "/custom/userIot/";

    @Autowired
    private IUserIotService userIotService;

    /**
     * 跳转到用户设备首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userIot.html";
    }

    /**
     * 跳转到添加用户设备
     */
    @RequestMapping("/userIot_add")
    public String userIotAdd() {
        return PREFIX + "userIot_add.html";
    }

    /**
     * 跳转到修改用户设备
     */
    @RequestMapping("/userIot_update/{userIotId}")
    public String userIotUpdate(@PathVariable Integer userIotId, Model model) {
        UserIot userIot = userIotService.selectById(userIotId);
        model.addAttribute("item",userIot);
        LogObjectHolder.me().set(userIot);
        return PREFIX + "userIot_edit.html";
    }

    /**
     * 获取用户设备列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return userIotService.selectList(null);
    }

    /**
     * 新增用户设备
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UserIot userIot) {
        userIotService.insert(userIot);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户设备
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer userIotId) {
        userIotService.deleteById(userIotId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户设备
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UserIot userIot) {
        userIotService.updateById(userIot);
        return SUCCESS_TIP;
    }

    /**
     * 用户设备详情
     */
    @RequestMapping(value = "/detail/{userIotId}")
    @ResponseBody
    public Object detail(@PathVariable("userIotId") Integer userIotId) {
        return userIotService.selectById(userIotId);
    }
}
