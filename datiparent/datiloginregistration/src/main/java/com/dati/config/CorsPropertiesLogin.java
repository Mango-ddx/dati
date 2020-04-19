package com.dati.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 *
 * 配置ajax跨域
 * */
@ConfigurationProperties("cors.prop")
public class CorsPropertiesLogin extends CorsProperties {
}
