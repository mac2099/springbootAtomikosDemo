package com.mac.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>description：</p>
 * <p>copyright： copyright(C)2016-2099</p>
 * <p>Life is short,we need passion</p>
 * <p>Summary： </p>
 * <p>instructions： </p>
 * Date 2020-01-17</p>
 * Author mac
 *
 * @version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "mysql.datasource.mac")
public class MacProperties extends BaseProperties {
}
