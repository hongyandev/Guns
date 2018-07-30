package com.stylefeng.guns.rest.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 项目相关配置
 *
 * @author fengshuonan
 * @date 2017年10月23日16:44:15
 */
@Configuration
@ConfigurationProperties(prefix = RestProperties.REST_PREFIX)
public class RestProperties {

    public static final String REST_PREFIX = "rest";

    public String getSignHeader() {
        return signHeader;
    }

    public void setSignHeader(String signHeader) {
        this.signHeader = signHeader;
    }

    private String signHeader = "AppKey";

    private boolean signOpen = true;

    public boolean isSignOpen() {
        return signOpen;
    }

    public void setSignOpen(boolean signOpen) {
        this.signOpen = signOpen;
    }
}
