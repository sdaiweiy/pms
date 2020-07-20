package com.sinodevice.pms.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 系统配置相关参数
 * </p>
 *
 * @author jobob
 * @since 2018-11-13
 */
@Data
@Component
@ConfigurationProperties(prefix = "crab")
public class SystemProperties {

    /**
     * 线上环境
     */
    private boolean online = false;

}
