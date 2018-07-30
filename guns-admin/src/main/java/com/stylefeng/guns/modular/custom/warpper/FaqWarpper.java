package com.stylefeng.guns.modular.custom.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

public class FaqWarpper extends BaseControllerWarpper {

	public FaqWarpper(List<Map<String, Object>> list) {
		super(list);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		map.put("faqTypeName", ConstantFactory.me().getDictsByName("帮助问题类别", (Integer) map.get("faqType")));
	}

}
