package com.stylefeng.guns.modular.custom.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.model.ProductExtend;
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
    public String productUpdate(@PathVariable String productId, Model model) {
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
     * 新增product
     * @throws Exception 
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Product product) throws Exception {
		productService.pullProductInfoFromIot(product);
		return SUCCESS_TIP;
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
    public String detail(@PathVariable("productId") String productId,Model model) {
    	Product product = productService.selectById(productId);
        model.addAttribute("item",product);
        return PREFIX + "product_edit.html";
    }
    
    /**
     * product拓展属性
     */
    @RequestMapping(value = "/detailExtend/{productId}")
    public String detailExtend(@PathVariable("productId") String productId,Model model) {
    	List<ProductExtend> list = productService.selectByProductKey(productId);
    	model.addAttribute("item", list);
    	return PREFIX + "product_detailExtend.html";
    }
}
