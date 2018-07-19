package com.stylefeng.guns.modular.custom.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.common.exception.FileUploadException;
import com.stylefeng.guns.core.common.file.FilePath;
import com.stylefeng.guns.core.util.OssUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.model.ProductExtend;
import com.stylefeng.guns.modular.custom.model.ProductFunattri;
import com.stylefeng.guns.modular.custom.model.ProductImage;
import com.stylefeng.guns.modular.custom.service.IProductService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * product控制器
 *
 * @author guanqing
 * @Date 2018-07-12 21:47:39
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    private String PREFIX = "/custom/product/";

    @Autowired
    private IProductService productService;
    
    @Autowired
    private OssUtil ossUtil;

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
    @ApiOperation(value = "添加产品界面",notes = "点开添加产品的页面",tags = {"产品管理"},response = String.class)
    @RequestMapping(value = "/product_add",method = RequestMethod.GET)
    public String productAdd() {
        return PREFIX + "product_add.html";
    }
    
    /**
     * 跳转到product uploadImg
     */
    @RequestMapping("/product_uploadImg/{productId}")
    public String productUploadImg(@PathVariable String productId, Model model) {
    	ProductImage image = productService.selectImageByProductKey(productId);
    	if (Objects.isNull(image))
    			image = new ProductImage();
    			image.setProductKey(productId);
    	model.addAttribute("item", image);
        return PREFIX + "product_uploadImg.html";
    }

    /**
     * 跳转功能属性页面
     */
    @RequestMapping("/product_addAttribute/{productId}")
    public String productAttri(@PathVariable String productId, Model model) {
    	ProductFunattri funattri = productService.selectFunattriByProductKey(productId);
    	if (Objects.isNull(funattri))
    		funattri = new ProductFunattri();
    		funattri.setProductKey(productId);
    	model.addAttribute("item", funattri);
    	return PREFIX + "product_addAttribute.html";
    }
    
    /**
     * product详情
     */
    @ApiOperation(value = "产品基础属性",notes = "产品基础属性",tags = {"产品管理"},response = String.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "productId", value = "产品PK", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "/detail/{productId}",method = RequestMethod.GET)
    public String detail(@PathVariable("productId") String productId,@ApiIgnore Model model) {
    	Product product = productService.selectById(productId);
        model.addAttribute("item",product);
        return PREFIX + "product_detail.html";
    }
    
    /**
     * product拓展属性
     */
    @ApiOperation(value = "产品拓展属性",notes = "产品扩展属性",tags = {"产品管理"},response = String.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "productId", value = "产品PK", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "/detailExtend/{productId}",method = RequestMethod.GET)
    public String detailExtend(@PathVariable("productId") String productId,@ApiIgnore Model model) {
    	List<ProductExtend> list = productService.selectByProductKey(productId);
    	model.addAttribute("item", list);
    	return PREFIX + "product_detailExtend.html";
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
    @ApiOperation(value = "添加产品",notes = "同步/更新产品",tags = {"产品管理"},response = SuccessTip.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "productKey", value = "产品PK", required = true, dataType = "String", paramType = "query"),
    	@ApiImplicitParam(name = "iotPackage", value = "IoT平台", required = true, dataType = "int",paramType = "query", allowableValues = "1")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(@ApiIgnore Product product) throws Exception {
		productService.pullProductInfoFromIot(product);
		return SUCCESS_TIP;
    }

    /**
     * 删除product
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String productId) {
        productService.deleteProductAndRelated(productId);
        return SUCCESS_TIP;
    }

    /**
     * 修改product的功能属性
     */
    @RequestMapping(value = "/updateFunAttri")
    @ResponseBody
    public Object update(ProductFunattri funattri) {
    	productService.updateFunattriByProductKey(funattri);
        return SUCCESS_TIP;
    }
    
    @RequestMapping(value = "/uploadProductImage")
    public Object uploadProductImage(@RequestParam("file") MultipartFile file,@RequestParam String productId) {
    	if(file.isEmpty())
    		return new ErrorTip(BizExceptionEnum.FILE_NOT_FOUND);
    	FilePath path = ossUtil.transferTo(file);
    	try {
			productService.saveProductImage(productId, path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FileUploadException(BizExceptionEnum.UPLOAD_ERROR, path);
		}
    	return SUCCESS_TIP;
    }
}
