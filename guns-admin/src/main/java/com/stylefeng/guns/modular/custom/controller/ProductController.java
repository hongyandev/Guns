package com.stylefeng.guns.modular.custom.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.service.IProductService;

/**
 * product控制器
 *
 * @author fengshuonan
 * @Date 2018-07-12 21:47:39
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    private String PREFIX = "/custom/product/";

    @Autowired
    private IProductService productService;

    /**
     * 跳转到product首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "product.html";
    }

    /**
     * 跳转到添加product
     */
    @RequestMapping("/product_add")
    public String productAdd() {
        return PREFIX + "product_add.html";
    }

    /**
     * 跳转到修改product
     */
    @RequestMapping("/product_update/{productId}")
    public String productUpdate(@PathVariable Integer productId, Model model) {
        Product product = productService.selectById(productId);
        model.addAttribute("item",product);
        LogObjectHolder.me().set(product);
        return PREFIX + "product_edit.html";
    }

    /**
     * 获取product列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return productService.selectList(null);
    }

    /**
     * 新增product
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Product product) {
        productService.insert(product);
        return SUCCESS_TIP;
    }
    
    /**
     * 表单提交日期绑定
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
    	sdf.setLenient(false);
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    /**
     * 删除product
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer productId) {
        productService.deleteById(productId);
        return SUCCESS_TIP;
    }

    /**
     * 修改product
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Product product) {
        productService.updateById(product);
        return SUCCESS_TIP;
    }

    /**
     * product详情
     */
    @RequestMapping(value = "/detail/{productId}")
    @ResponseBody
    public Object detail(@PathVariable("productId") Integer productId) {
        return productService.selectById(productId);
    }
}
