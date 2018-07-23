package com.stylefeng.guns.core.enums;

import lombok.Getter;

/**
* create by guanqing
* 2018年7月23日 下午2:44:04
*/
@Getter
public enum OssType {

	// 图片
	OSS_IMAGE(1),
	// 文件
	OSS_FILE(2);
	
	private Integer ossType;
	
	private OssType(Integer ossType) {
		// TODO Auto-generated constructor stub
		this.ossType = ossType;
	}
}
