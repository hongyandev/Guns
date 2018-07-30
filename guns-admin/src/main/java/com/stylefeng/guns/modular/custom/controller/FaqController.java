package com.stylefeng.guns.modular.custom.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.custom.model.Faq;
import com.stylefeng.guns.modular.custom.service.IFaqService;
import com.stylefeng.guns.modular.custom.warpper.FaqWarpper;
import com.stylefeng.guns.modular.system.model.SecretKey;

/**
 * 帮助控制器
 *
 * @author fengshuonan
 * @Date 2018-07-30 12:58:20
 */
@Controller
@RequestMapping("/faq")
public class FaqController extends BaseController {

    private String PREFIX = "/custom/faq/";

    @Autowired
    private IFaqService faqService;

    /**
     * 跳转到帮助首页
     */
    @RequestMapping("")
    public String index(Model model) {
    	model.addAttribute("faqTypes", ConstantFactory.me().findInDict(62));
        return PREFIX + "faq.html";
    }

    /**
     * 跳转到添加帮助
     */
    @RequestMapping("/faq_add")
    public String faqAdd(Model model) {
    	model.addAttribute("faqTypes", ConstantFactory.me().findInDict(62));
        return PREFIX + "faq_add.html";
    }

    /**
     * 跳转到修改帮助
     */
    @RequestMapping("/faq_update/{faqId}")
    public String faqUpdate(@PathVariable Integer faqId, Model model) {
        Faq faq = faqService.selectById(faqId);
        model.addAttribute("item",faq);
        model.addAttribute("faqTypes", ConstantFactory.me().findInDict(62));
        LogObjectHolder.me().set(faq);
        return PREFIX + "faq_edit.html";
    }

    /**
     * 获取帮助列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String faqTitle, String faqType) {
    	EntityWrapper<Faq> wrapper = new EntityWrapper<Faq>();
    	if (Objects.nonNull(faqTitle)) {
			wrapper.like("faqTitle", faqTitle);
		}
    	if (Objects.nonNull(faqType)) {
    		wrapper.eq("faqType", faqType);
		}
    	List<Map<String, Object>> list = faqService.selectMaps(wrapper);
        return new FaqWarpper(list).warp();
    }

    /**
     * 新增帮助
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Faq faq) {
    	Date now = new Date();
    	faq.setCreateDate(now);
    	faq.setUpdateDate(now);
        faqService.insert(faq);
        return SUCCESS_TIP;
    }

    /**
     * 删除帮助
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer faqId) {
        faqService.deleteById(faqId);
        return SUCCESS_TIP;
    }

    /**
     * 修改帮助
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Faq faq) {
    	faq.setUpdateDate(new Date());
        faqService.updateById(faq);
        return SUCCESS_TIP;
    }

    /**
     * 帮助详情
     */
    @RequestMapping(value = "/detail/{faqId}")
    @ResponseBody
    public Object detail(@PathVariable("faqId") Integer faqId) {
        return faqService.selectById(faqId);
    }
}
