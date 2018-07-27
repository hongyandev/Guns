package com.stylefeng.guns.modular.alions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AliOnsBusinessService implements IAliOnsBusinessService {
	
	private final static Logger log = LoggerFactory.getLogger(AliOnsBusinessService.class);

	@Override
	public boolean checkBusinessService(Object object) {
		log.warn("CheckBusinessService");
		return true;
	}

	@Override
	public boolean execBusinessService(Object object, Object arg) {
		log.warn("ExecBusinessService {}", arg);
		return true;
	}

}
