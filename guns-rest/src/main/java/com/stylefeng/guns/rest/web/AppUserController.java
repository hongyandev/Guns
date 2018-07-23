package com.stylefeng.guns.rest.web;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.core.domain.FilePath;
import com.stylefeng.guns.core.enums.OssType;
import com.stylefeng.guns.core.enums.ResultOssEnum;
import com.stylefeng.guns.core.exception.FileUploadException;
import com.stylefeng.guns.core.util.OssUtil;
import com.stylefeng.guns.rest.core.domain.Result;
import com.stylefeng.guns.rest.core.enums.ResultEnum;
import com.stylefeng.guns.rest.core.utils.ResultUtil;
import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.service.impl.AppUserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-12
 */
@Controller
@RequestMapping("/appUser")
public class AppUserController {

	@Autowired
	private OssUtil ossUtil;
	
	@Autowired
	private AppUserServiceImpl appUserServiceImpl;
	
	@RequestMapping(value = "/modifyAppUser",method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> modifyAppUser(@RequestBody AppUser appUser){
		appUserServiceImpl.updateById(appUser);
		return ResultUtil.success();
	}
	
	@RequestMapping(value = "/modifyHeadImge")
	public Result<Object> modifyHeadImge(@RequestParam("file") MultipartFile file,String userId){
		if(file.isEmpty())
			return ResultUtil.failure(ResultEnum.FILE_NOT_FOUND);
		FilePath path = ossUtil.transferTo(file,OssType.OSS_IMAGE);
		
		try {
			appUserServiceImpl.modifyHeadImge(userId, path);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUploadException(ResultOssEnum.FILE_UPLOAD_ERROR, path);
		}
		return ResultUtil.success();
	}
}

