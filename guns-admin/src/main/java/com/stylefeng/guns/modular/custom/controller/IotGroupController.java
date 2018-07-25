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

import com.stylefeng.guns.modular.custom.model.IotGroup;
import com.stylefeng.guns.modular.custom.service.IIotGroupService;

/**
 * 设备分组控制器
 *
 * @author fengshuonan
 * @Date 2018-07-25 10:16:10
 */
@Controller
@RequestMapping("/iotGroup")
public class IotGroupController extends BaseController {

    private String PREFIX = "/custom/iotGroup/";

    @Autowired
    private IIotGroupService iotGroupService;

    /**
     * 跳转到设备分组首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "iotGroup.html";
    }

    /**
     * 跳转到添加设备分组
     */
    @RequestMapping("/iotGroup_add")
    public String iotGroupAdd() {
        return PREFIX + "iotGroup_add.html";
    }

    /**
     * 跳转到修改设备分组
     */
    @RequestMapping("/iotGroup_update/{iotGroupId}")
    public String iotGroupUpdate(@PathVariable Integer iotGroupId, Model model) {
        IotGroup iotGroup = iotGroupService.selectById(iotGroupId);
        model.addAttribute("item",iotGroup);
        LogObjectHolder.me().set(iotGroup);
        return PREFIX + "iotGroup_edit.html";
    }

    /**
     * 获取设备分组列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return iotGroupService.selectList(null);
    }

    /**
     * 新增设备分组
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(IotGroup iotGroup) {
        iotGroupService.insert(iotGroup);
        return SUCCESS_TIP;
    }

    /**
     * 删除设备分组
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer iotGroupId) {
        iotGroupService.deleteById(iotGroupId);
        return SUCCESS_TIP;
    }

    /**
     * 修改设备分组
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(IotGroup iotGroup) {
        iotGroupService.updateById(iotGroup);
        return SUCCESS_TIP;
    }

    /**
     * 设备分组详情
     */
    @RequestMapping(value = "/detail/{iotGroupId}")
    @ResponseBody
    public Object detail(@PathVariable("iotGroupId") Integer iotGroupId) {
        return iotGroupService.selectById(iotGroupId);
    }
}
